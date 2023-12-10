package edu.ntnu.stud.view;

import edu.ntnu.stud.model.Verifier;
import java.util.Scanner;

/**
 * A class for scanning input from the user. It also uses try and catch to verify the input and
 * prints out an error message to the user if the input is invalid.
 */
public class Scan {

  /**
   * Scans the input from the user and verifies that the hour is valid. The hour has to be between
   * 0 and 23. If it is not valid, the user is asked to enter a new hour until the input is valid.
   *
   * @return the hour
   */
  public static int scanHourInt() {
    Scanner scanner = new Scanner(System.in);
    boolean input = false;
    int hour = 0;
    while (!input) {
      try {
        hour = Integer.parseInt(scanner.nextLine());
        Verifier.verifyTime(hour, 0);
        input = true;
      } catch (Exception e) {
        System.out.println("You have to enter an integer between 0 and 23");
      }
    }
    return hour;
  }

  /**
   * Scans the input from the user and verifies that the minute is valid. The minute has to be
   * between 0 and 59. If it is not valid, the user is asked to enter a new minute until the input
   * is valid.
   *
   * @return the minute
   */
  public static int scanMinuteInt() {
    Scanner scanner = new Scanner(System.in);
    boolean input = false;
    int minute = 0;
    while (!input) {
      try {
        minute = Integer.parseInt(scanner.nextLine());
        Verifier.verifyTime(0, minute);
        input = true;
      } catch (Exception e) {
        System.out.println("You have to enter an integer between 0 and 59");
      }
    }
    return minute;
  }

  /**
   * Scans the input from the user and verifies that the line is valid. The line has to be between
   * 2 and 3 characters and can only contain capital letters and numbers. If it is not valid, the
   * user is asked to enter a new line until the input is valid.
   *
   * @return the line
   */
  public static String scanLine() {
    Scanner scanner = new Scanner(System.in);
    boolean input = false;
    String line = "";
    while (!input) {
      try {
        line = scanner.nextLine();
        Verifier.verifyLine(line);
        input = true;
      } catch (Exception e) {
        System.out.println("You have to enter a line with 2 or 3 capital letters and/or numbers");
      }
    }
    return line;
  }

  /**
   * Scans the input from the user and verifies that the train number is valid. The train number has
   * to be between 1 and 999. If it is not valid, the user is asked to enter a new train number
   * until the input is valid.
   *
   * @return the train number
   */
  public static int scanTrainNumber() {
    Scanner scanner = new Scanner(System.in);
    boolean input = false;
    int trainNumber = 0;
    while (!input) {
      try {
        trainNumber = Integer.parseInt(scanner.nextLine());
        Verifier.verifyTrainNumber(trainNumber);
        input = true;
      } catch (Exception e) {
        System.out.println("You have to enter an integer between 1 and 999");
      }
    }
    return trainNumber;
  }

  /**
   * Scans the input from the user and verifies that the destination is valid. The destination has
   * to be between 2 and 30 characters and can only contain letters. If it is not valid, the user
   * is asked to enter a new destination until the input is valid.
   *
   * @return the destination
   */
  public static String scanDestination() {
    Scanner scanner = new Scanner(System.in);
    boolean input = false;
    String destination = "";
    while (!input) {
      try {
        destination = scanner.nextLine();
        Verifier.verifyDestination(destination);
        input = true;
      } catch (Exception e) {
        System.out.println("You have to enter a destination with 2 to 30 letters");
      }
    }
    return destination;
  }

  /**
   * Scans the input from the user and verifies that the track is valid. The track has to be
   * between 1 and 99. If it is not valid, the user is asked to enter a new track until the input
   * is valid.
   *
   * @return the track
   */
  public static int scanTrack() {
    Scanner scanner = new Scanner(System.in);
    boolean input = false;
    int track = 0;
    while (!input) {
      try {
        track = Integer.parseInt(scanner.nextLine());
        Verifier.verifyTrack(track);
        input = true;
      } catch (Exception e) {
        System.out.println("You have to enter an integer between 1 and 99");
      }
    }
    return track;
  }
}
