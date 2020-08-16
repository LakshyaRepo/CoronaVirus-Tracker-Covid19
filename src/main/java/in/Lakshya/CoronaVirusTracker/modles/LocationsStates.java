package in.Lakshya.CoronaVirusTracker.modles;

public class LocationsStates {
    private String state;
    private String country;
    private int latestCases;
    private int differece;

    public int getDifferece() {
        return differece;
    }

    public void setDifferece(int differece) {
        this.differece = differece;
    }

    public int getLatestCases() {
        return latestCases;
    }

    public void setLatestCases(int latestCases) {
        this.latestCases = latestCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "LocationsStates{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestCases=" + latestCases +
                '}';
    }
}
