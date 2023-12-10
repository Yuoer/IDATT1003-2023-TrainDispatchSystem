package edu.ntnu.stud.model;

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
 * @version 0.5
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
   * @throws IllegalArgumentException if the actual departure time is before the current time
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
    if (newTrainDeparture.getActualDepartureTime().isBefore(getTime())) {
      // The actual departure time is before the current time
      throw new IllegalArgumentException("Actual departure time must be after current time");
    }
    trainDepartures.add(newTrainDeparture);
  }

  /**
   * Gets the list of train departures.
   *
   * @return the list of train departures
   */
  public List<TrainDeparture> getTrainDepartures() {
    return trainDepartures;
  }

  /**
   * Gets the time.
   *
   * @return the time
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Sets the time to the given hour and minute if the time is after the current time.
   * Otherwise, an IllegalArgumentException is thrown.
   * Removes the train departures before the new time.
   *
   * @param hour   sets the hour
   * @param minute sets the minute
   */
  public void setTime(int hour, int minute) throws IllegalArgumentException {
    Verifier.verifyTime(hour, minute);
    if (getTime().isAfter(LocalTime.of(hour, minute))) {
      throw new IllegalArgumentException("Time must be after current time");
    } else {
      time = LocalTime.of(hour, minute);
      removeTrainDeparturesBeforeTime(getTime());
    }
  }

  /**
   * Searches for a train departure with the given train number.
   * Throws IllegalArgumentException if the train number is invalid.
   *
   * @param trainNumber the train number to search for
   * @return the train departure with the given train number
   *     or null if no such train departure exists
   */
  public TrainDeparture searchTrainDepartureByTrainNumber(int trainNumber)
      throws IllegalArgumentException {
    Verifier.verifyTrainNumber(trainNumber);
    return getTrainDepartures().stream()
        .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .findFirst()
        .orElse(null);
  }

  /**
   * Searches for a train departures by destination.
   *
   * @param destination the destination to search for
   * @return a list of train departures with the given destination
   *     or null if no such train departures exist
   */
  public List<TrainDeparture> searchTrainDeparturesByDestination(String destination) {
    List<TrainDeparture> filteredList = getTrainDepartures().stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toList());
    return filteredList.isEmpty() ? null : filteredList;
  }

  /**
   * Sorts the train departures by departure time.
   *
   * @return a list of train departures sorted by departure time
   */
  public List<TrainDeparture> getTimeSortedTrainDepartures() {
    if (getTrainDepartures().isEmpty()) {
      return null;
    }
    return getTrainDepartures().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
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
   * Finds a train departure by train number and sets the delay.
   *
   * @param trainNumber the train number
   * @param delayHour the hour of the delay
   * @param delayMinute the minute of the delay
   * @throws IllegalArgumentException if the train number does not exist
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
   * @throws IllegalArgumentException if the train number does not exist
   */
  public void setTrainTrack(int trainNumber, int track) {
    TrainDeparture trainDeparture = searchTrainDepartureByTrainNumber(trainNumber);
    if (trainDeparture == null) {
      throw new IllegalArgumentException("Train number does not exist");
    }
    trainDeparture.setTrack(track);
  }

  /**
   * Returns a String object of the list of train departures.
   *
   * @return a String object of the list of train departures
   */
  public String printTimeSortedTrainDepartures() {
    return getTimeSortedTrainDepartures().stream()
        .map(TrainDeparture::toString)
        .collect(Collectors.joining("\n"));
  }

  /**
   * Returns a String object of the list of train departures by destination.
   *
   * @param destination the destination to search for
   * @return a String object of the list of train departures by destination
   */
  public String printTrainDeparturesByDestination(String destination) {
    return searchTrainDeparturesByDestination(destination).stream()
        .map(TrainDeparture::toString)
        .collect(Collectors.joining("\n"));
  }

  /**
   * Returns a String object of the list of train departures by train number.
   *
   * @param trainNumber the train number to search for
   * @return a String object of the list of train departures by train number
   */
  public String printTrainDepartureByTrainNumber(int trainNumber) {
    return searchTrainDepartureByTrainNumber(trainNumber).toString();
  }
}
