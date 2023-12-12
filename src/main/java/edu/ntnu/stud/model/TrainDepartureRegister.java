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
 * @author 10054
 * @version 1.0
 * @since 0.1
 */

public class TrainDepartureRegister {
  private final ArrayList<TrainDeparture> trainDepartures;
  private LocalTime time;

  /**
   * Constructs a train departure register with the specified time.
   *
   * @param timeHour the hour of the time
   * @param timeMinute the minute of the time
   */
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
   * @throws IllegalArgumentException if the train number already exists in the register
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
   * Sets the time to the given hour and minute if the time is after the current time and
   * removes the train departures before the new time.
   *
   * @param hour sets the hour
   * @param minute sets the minute
   * @throws IllegalArgumentException if the time is before the current time
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
   * Searches for a train departure with the given train number.
   * Streams through the list of train departures and finds the first train departure
   * with the given train number. If no such train departure exists, null is returned.
   *
   * @param trainNumber the train number to search for
   * @return the train departure with the given train number
   *     or null if no such train departure exists
   * @throws IllegalArgumentException if the train number is invalid
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
   * Streams through the list of train departures and finds all train departures
   * with the given destination. If the filtered list is empty, null is returned.
   *
   * @param destination the destination to search for
   * @return a list of train departures with the given destination
   *     or null if no such train departures exist
   */
  public List<TrainDeparture> searchTrainDeparturesByDestination(String destination) {
    List<TrainDeparture> filteredList = getTrainDepartures().stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .toList();
    return filteredList.isEmpty() ? null : filteredList;
  }

  /**
   * Sorts the train departures by departure time. Streams through the list of train departures
   * and sorts them by departure time using a comparator. If the list is empty, null is returned.
   *
   * @return a list of train departures sorted by departure time
   */
  public List<TrainDeparture> getTimeSortedTrainDepartures() {
    if (getTrainDepartures().isEmpty()) {
      return null;
    }
    return getTrainDepartures().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .toList();
  }

  /**
   * Removes train departures from the list of train departures if their actual departure time is
   * before the given time.
   *
   * @param time the time to remove after
   */
  public void removeTrainDeparturesBeforeTime(LocalTime time) {
    trainDepartures.removeIf(trainDeparture ->
        trainDeparture.getActualDepartureTime().isBefore(time));
  }

  /**
   * Returns a String object of the list of train departures.
   * Streams through the list of already sorted train departures and creates a String object
   * of the train departures.
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
   * Streams through the list of train departures sorted by destination and creates a String object
   * of the train departures.
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
   * Finds the train departure by train number and creates a String object of the train departure.
   *
   * @param trainNumber the train number to search for
   * @return a String object of the list of train departures by train number
   */
  public String printTrainDepartureByTrainNumber(int trainNumber) {
    return searchTrainDepartureByTrainNumber(trainNumber).toString();
  }
}
