package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class holds a list of train departures.
 * The list of train departures is stored in an ArrayList.
 *
 * @author 562289
 * @version 0.4
 * @since 0.1
 */

public class TrainDepartureRegister {
  private final ArrayList<TrainDeparture> trainDepartures;
  private LocalTime time;

  public TrainDepartureRegister(int timeHour, int timeMinute) {
    this.trainDepartures = new ArrayList<>();
    this.time = LocalTime.of(timeHour, timeMinute);
  }

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
   * Sets the time.
   *
   * @param hour   sets the hour
   * @param minute sets the minute
   */
  public void setTime(int hour, int minute) throws IllegalArgumentException {
    verifyTime(hour, minute);
    if (this.time.isAfter(LocalTime.of(hour, minute))) {
      throw new IllegalArgumentException("Time must be after current time");
    } else {
      time = LocalTime.of(hour, minute);
      removeTrainDeparturesBeforeTime(time);
    }
  }

  public LocalTime getTime() {
    return time;
  }

  /**
   * Searches for a train departure with the given train number.
   *
   * @param trainNumber the train number to search for
   * @return the train departure with the given train number
   *     or null if no such train departure exists
   */
  public TrainDeparture searchTrainDepartureByTrainNumber(int trainNumber) {
    return trainDepartures.stream()
        .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .findFirst()
        .orElse(null);
  }

  /**
   * Adds a train departure to the list of train departures.
   *
   * @param departureHour the hour of the departure time
   * @param departureMinute the minute of the departure time
   * @param line the line
   * @param trainNumber the train number
   * @param destination the destination
   * @param track the track
   * @param delayHour the hour of the delay
   * @param delayMinute the minute of the delay
   * @throws IllegalArgumentException if the train number already exists
   */
  public void addTrainDeparture(int departureHour, int departureMinute, String line,
                                   int trainNumber, String destination, int track, int delayHour,
                                   int delayMinute) throws IllegalArgumentException {
    TrainDeparture existingTrainDeparture = searchTrainDepartureByTrainNumber(trainNumber);
    if (existingTrainDeparture != null) {
      // A train departure with the same train number already exists
      throw new IllegalArgumentException("Train number already exists");
    }
    TrainDeparture newTrainDeparture = new TrainDeparture(departureHour, departureMinute, line,
        trainNumber, destination, track, delayHour, delayMinute);
    trainDepartures.add(newTrainDeparture);
  }

  /**
   * Searches for a train departures by destination.
   *
   * @param destination the destination to search for
   * @return a list of train departures with the given destination
   */
  public List<TrainDeparture> searchTrainDeparturesByDestination(String destination) {
    return trainDepartures.stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toList());
  }

  /**
   * Removes the trains with departures after the given time.
   *
   * @param time the time to remove after
   */
  public void removeTrainDeparturesBeforeTime(LocalTime time) {
    trainDepartures.removeIf(trainDeparture ->
        trainDeparture.getActualDepartureTime().isBefore(time));
  }

  /**
   * Sorts the train departures by departure time.
   *
   * @return a list of train departures sorted by departure time
   */
  public List<TrainDeparture> getTimeSortedTrainDepartures() {
    return trainDepartures.stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .collect(Collectors.toList());
  }

  public List<TrainDeparture> getTrainDepartures() {
    return trainDepartures;
  }

  /**
   * Finds a train departure by train number and sets the delay.
   *
   * @param trainNumber the train number
   * @param delayHour the hour of the delay
   * @param delayMinute the minute of the delay
   */
  public void setTrainDelay(int trainNumber, int delayHour, int delayMinute) {
    TrainDeparture trainDeparture = searchTrainDepartureByTrainNumber(trainNumber);
    if (trainDeparture == null) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    trainDeparture.setDelay(delayHour, delayMinute);
  }

  /**
   * Finds a train departure by train number and sets the track.
   *
   * @param trainNumber the train number
   * @param track the track
   */
  public void setTrainTrack(int trainNumber, int track) {
    TrainDeparture trainDeparture = searchTrainDepartureByTrainNumber(trainNumber);
    if (trainDeparture == null) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    trainDeparture.setTrack(track);
  }

  /**
   * Prints the train departures sorted by departure time as a table.
   */
  public void printSortedTrainDepartures() {
    System.out.println("| Departure Time | Line | Train Number |   Destination   | Track | "
        + "Delay | Time: " + getTime());
    System.out.println("|----------------|------|--------------|-----------------|-------"
        + "|-------|");
    for (TrainDeparture data : getTimeSortedTrainDepartures()) {
      System.out.println(data.toString());
    }
  }

  /**
   * Prints the train departures as a table.
   */
  public void printTrainDepartures() {
    System.out.println("| Departure Time | Line | Train Number |   Destination   | Track | "
        + "Delay | Time: " + getTime());
    System.out.println("|----------------|------|--------------|-----------------|-------"
        + "|-------|");
    for (TrainDeparture data : getTrainDepartures()) {
      System.out.println(data.toString());
    }
  }
}
