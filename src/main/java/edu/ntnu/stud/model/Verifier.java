package edu.ntnu.stud.model;

/**
 * This class contains methods for verifying that a train departure is only created with valid
 * values. It throws an IllegalArgumentException if the values are invalid.
 *
 * @author 10054
 * @version 1.0
 * @since 0.1
 */
public class Verifier {

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
  public static void verifyTime(int hour, int minute) throws IllegalArgumentException {
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
  public static void verifyLine(String line) throws IllegalArgumentException {
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
   * @throws IllegalArgumentException if the train number is less than 1 and greater than 999
   */
  public static void verifyTrainNumber(int trainNumber) throws IllegalArgumentException {
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
  public static void verifyDestination(String destination) throws IllegalArgumentException {
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
  public static void verifyTrack(int track) throws IllegalArgumentException {
    if (track != -1 && track < 1 || track > 99) {
      throw new IllegalArgumentException("Track must be between 1 and 99 or -1");
    }
  }

}
