package edu.ntnu.stud.model;

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
 * @author 10054
 * @version 1.0
 * @since 0.1
 */

public class TrainDeparture {
  private final LocalTime departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;



  /**
   * Constructs a train departure with the specified departure time,
   * line, train number, destination, track and delay. Checks that the
   * departure time, line, train number, destination, track and delay are valid.
   *
   * @param departureHour the departure hour of the train
   *
   * @param departureMinute the departure minute of the train
   *
   * @param line the line of the train
   *
   * @param trainNumber the train number of the train
   *
   * @param destination the destination of the train
   *
   * @param track the track of the train
   *
   * @param delayHour the hour delay of the train
   *
   * @param delayMinute the minute delay of the train
   */
  public TrainDeparture(int departureHour, int departureMinute, String line, int trainNumber,
                        String destination, int track, int delayHour, int delayMinute)
      throws IllegalArgumentException {
    Verifier.verifyTime(departureHour, departureMinute);
    Verifier.verifyLine(line);
    Verifier.verifyTrainNumber(trainNumber);
    Verifier.verifyDestination(destination);
    Verifier.verifyTrack(track);
    Verifier.verifyTime(delayHour, delayMinute);

    this.departureTime = LocalTime.of(departureHour, departureMinute);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = LocalTime.of(delayHour, delayMinute);
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
   * Returns a LocalTime object of the actual departure time of the train departure.
   *
   * @return actual departure time of the train
   */
  public LocalTime getActualDepartureTime() {
    return this.departureTime.plusHours(this.delay.getHour()).plusMinutes(this.delay.getMinute());
  }

  /**
   * Verifies and sets the track of the train departure.
   *
   * @param track the track of the train
   */
  public void setTrack(int track) throws IllegalArgumentException {
    Verifier.verifyTrack(track);
    this.track = track;
  }

  /**
   * Verifies and sets the delay of the train departure.
   *
   * @param delayHour the delay of the train
   */
  public void setDelay(int delayHour, int delayMinute) throws IllegalArgumentException {
    Verifier.verifyTime(delayHour, delayMinute);
    this.delay = LocalTime.of(delayHour, delayMinute);
  }

  /**
   * Returns a String object of the train departure. If the track is -1,
   * the track is not set and the String object does not contain the track.
   *
   * @return String object of the train departure
   */
  @Override
  public String toString() {
    if (getTrack() == -1) {
      return String.format("| %-14s | %-4s | %-12d | %-15s | %-5s | %-5s |",
          getDepartureTime(), getLine(), getTrainNumber(), getDestination(), getDelay(), "");
    } else {
      return String.format("| %-14s | %-4s | %-12d | %-15s | %-5s | %-5d |",
          getDepartureTime(), getLine(), getTrainNumber(), getDestination(), getDelay(),
          getTrack());
    }
  }
}
