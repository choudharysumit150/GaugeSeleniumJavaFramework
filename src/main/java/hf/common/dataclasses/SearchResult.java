package hf.common.dataclasses;


public class SearchResult implements Comparable<SearchResult>{

    private String trainDepartureTime;
    public String getTrainDepartureTime() {
        return trainDepartureTime;
    }
    public void setTrainDepartureTime(String trainDepartureTime) {
        this.trainDepartureTime = trainDepartureTime;
    }
    private String trainArrivalTime;
    public String getTrainArrivalTime() {
        return trainArrivalTime;
    }
    public void setTrainArrivalTime(String trainArrivalTime) { this.trainArrivalTime = trainArrivalTime;}
    private String standardFare;
    public String getStandardFare() { return standardFare; }
    public void setStandardFare(String standardFare) { this.standardFare = standardFare; }
    private String firstClassFare;
    public String getFirstClassFare() { return firstClassFare; }
    public void setFirstClassFare(String firstClassFare) { this.firstClassFare = firstClassFare; }
    private String travelTime;
    public String getTravelTime() { return travelTime; }
    public void setTravelTime(String travelTime) { this.travelTime = travelTime; }
    private String interchangeCount;
    public String getInterchangeCount() { return interchangeCount; }
    public void setInterchangeCount(String interchangeCount) { this.interchangeCount = interchangeCount; }


    @Override
    public int compareTo(SearchResult o) {
        if(Integer.parseInt(this.getTrainDepartureTime().replace(":",""))>Integer.parseInt(o.getTrainDepartureTime().replace(":","")))
            return 1;
        if(Integer.parseInt(this.getTrainDepartureTime().replace(":",""))<Integer.parseInt(o.getTrainDepartureTime().replace(":","")))
            return -1;

        return 0;

    }
}


