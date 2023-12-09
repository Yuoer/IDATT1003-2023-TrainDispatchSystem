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
 * @author 562289
 * @version 0.7
 * @since 0.1
 */

public class TrainDeparture {
  private final LocalTime departureTime;
  private String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * Verifies that the hour and minute are valid.
   *
   * @param hour the hour to verify
   *
   * @param minute the minute to verify
   *
   * @throws IllegalArgumentException if the hour is less than 0 or greater than 23
   *     or if the minute is less than 0 or greater than 59
   */
  public void verifyTime(int hour, int minute) throws IllegalArgumentException {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("Hour must be between 0 and 23");
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("Minute must be between 0 and 59");
    }
  }

  /**
   * Verifies that the line is valid.
   *
   * @param line the line to verify
   *
   * @throws IllegalArgumentException if the line is null
   *     or if the line is less than 2 or greater than 3 characters
   *     or if the line contains characters other than capital letters and numbers
   */
  public void verifyLine(String line) throws IllegalArgumentException {
    if (line.length() < 2 || line.length() > 3) {
      throw new IllegalArgumentException("Line must be between 2 and 3 characters");
    }
    if (!line.matches("[A-Z0-9]+")) {
      throw new IllegalArgumentException("Line must contain only capital letters and numbers");
    }
  }

  /**
   * Verifies that the train number is valid.
   *
   * @param trainNumber the train number to verify
   *
   * @throws IllegalArgumentException if the train number is less than 0
   */
  public void verifyTrainNumber(int trainNumber) throws IllegalArgumentException {
    if (trainNumber < 1 || trainNumber > 999) {
      throw new IllegalArgumentException("Train number has to be between 1 and 999");
    }
  }

  /**
   * Verifies that the destination is valid.
   *
   * @param destination the destination to verify
   *
   * @throws IllegalArgumentException if the destination is null
   *     or if the destination is less than 2 or greater than 30 characters
   *     or if the destination contains characters other than letters
   */
  public void verifyDestination(String destination) throws IllegalArgumentException {
    if (destination.length() < 2 || destination.length() > 30) {
      throw new IllegalArgumentException("Destination must be between 2 and 30 characters");
    }
    if (!destination.matches("[a-zA-ZæøåÆØÅ ]+")) {
      throw new IllegalArgumentException("Destination must contain only letters");
    }
  }

  /**
   * Verifies that the track is valid.
   *
   * @param track the track to verify
   *
   * @throws IllegalArgumentException if the track is less than 1 or greater than 99
   */
  public void verifyTrack(int track) throws IllegalArgumentException {
    if (track != -1 && track < 1 || track > 99) {
      throw new IllegalArgumentException("Track must be between 1 and 99 or -1");
    }
  }

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
    verifyTime(departureHour, departureMinute);
    verifyLine(line);
    verifyTrainNumber(trainNumber);
    verifyDestination(destination);
    verifyTrack(track);
    verifyTime(delayHour, delayMinute);

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
   * Verifies and sets the line of the train departure.
   *
   * @param line the line of the train
   */
  public void setLine(String line) throws IllegalArgumentException {
    verifyLine(line);
    this.line = line;
  }

  /**
   * Verifies and sets the track of the train departure.
   *
   * @param track the track of the train
   */
  public void setTrack(int track) throws IllegalArgumentException {
    verifyTrack(track);
    this.track = track;
  }

  /**
   * Verifies and sets the delay of the train departure.
   *
   * @param delayHour the delay of the train
   */
  public void setDelay(int delayHour, int delayMinute) throws IllegalArgumentException {
    verifyTime(delayHour, delayMinute);
    this.delay = LocalTime.of(delayHour, delayMinute);
  }

  /**
   * Returns a String object of the train departure.
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
