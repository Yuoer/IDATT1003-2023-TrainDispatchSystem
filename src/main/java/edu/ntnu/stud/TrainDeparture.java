package edu.ntnu.stud;
import java.time.LocalTime;

public class TrainDeparture {
  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
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
  
}
