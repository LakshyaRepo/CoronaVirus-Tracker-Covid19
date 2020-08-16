package in.Lakshya.CoronaVirusTracker.controllers;

import in.Lakshya.CoronaVirusTracker.CoronaVirusDataServices;
import in.Lakshya.CoronaVirusTracker.modles.LocationsStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;
    @GetMapping("/")
    public String home(Model model){
        List<LocationsStates> allStates=coronaVirusDataServices.getAllStats();
        int totalReportedCases=allStates.stream().mapToInt(stat ->stat.getLatestCases()).sum();
        int totalNewCases=allStates.stream().mapToInt(stat ->stat.getDifferece()).sum();
        model.addAttribute("locationStates" , coronaVirusDataServices.getAllStats());
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalnewcases" ,totalNewCases);
        return "home";

    }
}
