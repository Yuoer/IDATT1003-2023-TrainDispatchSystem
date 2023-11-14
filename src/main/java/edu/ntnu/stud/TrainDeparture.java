package edu.ntnu.stud;
import java.time.LocalTime;

public class TrainDeparture {
  private final LocalTime departureTime;
  private String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  public TrainDeparture(LocalTime departureTime, String line, int trainNumber, String destination, int track, LocalTime delay) {
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }
  public LocalTime getDepartureTime() {
    return this.departureTime;
  }
  public String getLine() {
    return this.line;
  }
  public int getTrainNumber() {
    return this.trainNumber;
  }
  public String getDestination() {
    return this.destination;
  }
  public int getTrack() {
    return this.track;
  }
  public LocalTime getDelay() {
    return this.delay;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public void setTrack(int track) {
    this.track = track;
  }

  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  public String toString() {
    return "TrainDeparture{" + "departureTime=" + departureTime + ", line='" + line + '\'' + ", trainNumber=" + trainNumber + ", destination='" + destination + '\'' + ", track=" + track + ", delay=" + delay + '}';
  }
}
