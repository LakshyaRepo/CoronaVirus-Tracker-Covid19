package in.Lakshya.CoronaVirusTracker;


import in.Lakshya.CoronaVirusTracker.modles.LocationsStates;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataServices {

    private List<LocationsStates> allStats=new ArrayList<>();

    public List<LocationsStates> getAllStats() {
        return allStats;
    }

    public void setAllStats(List<LocationsStates> allStats) {
        this.allStats = allStats;
    }

    private static String Virus_url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
    List<LocationsStates> newStats=new ArrayList<>();
    HttpClient client= HttpClient.newHttpClient();
    HttpRequest request=HttpRequest.newBuilder()
                    .uri(URI.create(Virus_url)).build();

    HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
    StringReader csvBodyReader=new StringReader(httpResponse.body());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    for (CSVRecord record : records) {
        LocationsStates locationsStates=new LocationsStates();
        locationsStates.setState(record.get("Province/State"));
        locationsStates.setCountry(record.get("Country/Region"));
        int latestCases=Integer.parseInt(record.get(record.size()-1));
        int preCases=Integer.parseInt(record.get(record.size()-2));
        locationsStates.setLatestCases(latestCases);
        locationsStates.setDifferece(latestCases - preCases);
        //System.out.println(locationsStates);
        newStats.add(locationsStates);

    }
    this.allStats=newStats;


    }
}
