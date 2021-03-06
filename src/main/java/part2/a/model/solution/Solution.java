package part2.a.model.solution;
import part2.a.model.TimeUtils;

import java.util.List;
import java.util.Optional;

public class Solution {

    private final String  id;
    private final String origin;
    private final String destination;
    private final String direction;
    private final Long departureTime;
    private final Long  arrivalTime;
    private final Double minprice;
    private final String duration;
    private final Integer changesno;
    private final Boolean saleable;
    private final SolutionsWrapper wrapper;
    private final List<String> trainid;

    private Optional<String> trainacronym;
    private Optional<List<String>> stopStations;

    public Solution(final List<String> trainid, final String id, final String origin, final String destination, final String direction, final Long departureTime, final Long arrivalTime,
                    final Double minprice, final String duration, final Integer changesno, final Boolean saleable, final SolutionsWrapper wrapper){
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.minprice = minprice;
        this.duration = duration;
        this.changesno = changesno;
        this.saleable = saleable;
        this.trainid = trainid;
        this.trainacronym = Optional.empty();
        this.stopStations = Optional.empty();
        this.wrapper = wrapper;
    }

    public void addDetails(final String trainacronym, final List<String> stopStations){
        this.trainacronym = Optional.of(trainacronym);
        this.stopStations = Optional.of(stopStations);
        this.wrapper.updateReady();
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Optional<String> getTrainacronym() {
        return trainacronym;
    }

    public Optional<List<String>> getStopStations() {
        return stopStations;
    }

    public String getDirection() {
        return direction;
    }

    public Long  getDepartureTime() {
        return departureTime;
    }

    public Long  getArrivalTime() {
        return arrivalTime;
    }

    public Double getMinprice() {
        return minprice;
    }

    public String getDuration() {
        return duration;
    }

    public Integer getChangesno() {
        return changesno;
    }

    public Boolean getSaleable() {
        return saleable;
    }

    public Boolean detailsReady(){
        return stopStations.isPresent() && trainacronym.isPresent();
    }

    @Override
    public String toString() {
        if (this.detailsReady()){
            String str = "";
            for (String id: trainid) {
                str+= id + (trainid.indexOf(id) == trainid.size()-1 ? "" : "  +  ");
            }
            str += "\n   " + origin + " -> " + destination + "   (direction " + direction + ")"
                + "\n   departure: " + TimeUtils.getStringTime(departureTime) + "   arrival: " +TimeUtils.getStringTime(arrivalTime) + "   (duration " + duration + ")"
                + "\n   minprice: " + minprice + "      changes: " + changesno + "      can be acquired: " + (saleable ? "yes" : "no")
                + "\n   stops: ";
            for (int i = 0; i < stopStations.get().size(); i++){
                String stop = stopStations.get().get(i);
                str += stop + ((i+1) % 5 == 0 && i != stopStations.get().size()-1 ? "\n              " : "      ");
            }
            return str + "\n";
        }
        else {
            return "";
        }
    }
}
