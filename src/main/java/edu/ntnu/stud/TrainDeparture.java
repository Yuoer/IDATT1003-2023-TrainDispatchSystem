package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This class represents a train departure.
 * A train departure has a departure time, a line, a train number,
 * a destination, a track and a delay.
 * The departure time is the time the train is scheduled to depart.
 * The line is the line the train is running on.
 * The train number is the number of the train.
 * The destination is the destination of the train.
 * The track is the track the train is scheduled to depart from.
 * The delay is the delay of the train.
 * The delay is the difference between the scheduled departure time
 * and the actual departure time.
 *
 *
 * @author 562289
 * @version 0.1
 * @since 0.2
 */

public class TrainDeparture {
  private final LocalTime departureTime;
  private String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * Constructs a train departure with the specified departure time,
   * line, train number, destination, track and delay.
   *
   * @param departureTime the departure time of the train
   *
   * @param line the line of the train
   *
   * @param trainNumber the train number of the train
   *
   * @param destination the destination of the train
   *
   * @param track the track of the train
   *
   * @param delay the delay of the train
   */
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber,
                        String destination, int track, LocalTime delay) {
    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  /**
   * Returns a LocalTime object of the departure time of the train departure.
   *
   * @return departure time of the train
   */
  public LocalTime getDepartureTime() {
    return this.departureTime;
  }

  /**
   * Returns a String object of the line of the train departure.
   *
   * @return line of the train
   */
  public String getLine() {
    return this.line;
  }

  /**
   * Returns an int object of the train number of the train departure.
   *
   * @return train number of the train
   */
  public int getTrainNumber() {
    return this.trainNumber;
  }

  /**
   * Returns a String object of the destination of the train departure.
   *
   * @return destination of the train
   */
  public String getDestination() {
    return this.destination;
  }

  /**
  * Returns an int object of the track of the train departure.
   *
  * @return track of the train
  */
  public int getTrack() {
    return this.track;
  }

  /**
   * Returns a LocalTime object of the delay of the train departure.
   *
   * @return delay of the train
   */
  public LocalTime getDelay() {
    return this.delay;
  }

  /**
   * Sets the line of the train departure.
   *
   * @param line the line of the train
   */
  public void setLine(String line) {
    this.line = line;
  }

  /**
   * Sets the track of the train departure.
   *
   * @param track the track of the train
   */
  public void setTrack(int track) {
    this.track = track;
  }

  /**
   * Sets the delay of the train departure.
   *
   * @param delay the delay of the train
   */
  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  /**
   * Returns a String object of the train departure.
   *
   * @return String object of the train departure
   */
  public String toString() {
    return "TrainDeparture{" + "departureTime=" + departureTime + ", line='" + line + '\'' + ","
        + " trainNumber=" + trainNumber + ", destination='" + destination + '\'' + ", track="
        + track + ", delay=" + delay + '}';
  }
}
