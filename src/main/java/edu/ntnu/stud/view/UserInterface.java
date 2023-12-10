package edu.ntnu.stud.view;

import edu.ntnu.stud.model.TrainDepartureRegister;
import java.util.Scanner;

/**
 * This is the user interface class for the train dispatch application.
 *
 * @author 562289
 * @version 0.2
 * @since 0.1
 */
public class UserInterface {
  TrainDepartureRegister trainDepartureRegister;
  Scanner scanner = new Scanner(System.in);
  private static final int ADD_TRAIN_DEPARTURE = 1;
  private static final int SEARCH_TRAIN_NUMBER = 2;
  private static final int SEARCH_DESTINATION = 3;
  private static final int LIST_ALL_DEPARTURES_TIME_SORTED = 4;
  private static final int SET_TRAIN_DELAY = 5;
  private static final int SET_TRAIN_TRACK = 6;
  private static final int SET_TIME = 7;
  private static final int EXIT = 8;

  /**
   * Initializes the train departure register.
   */
  public void init() {
    trainDepartureRegister = new TrainDepartureRegister(0, 0);
  }

  /**
   * Starts the user interface by adding some train departures to the register and launching the
   * menu.
   */
  public void start() {
    trainDepartureRegister.addTrainDeparture(13, 16, "L1", 106, "Spikkestad", 4, 0, 5);
    trainDepartureRegister.addTrainDeparture(13, 18, "L1", 16, "Lillestrøm", 6, 0, 10);
    trainDepartureRegister.addTrainDeparture(13, 19, "L13", 135, "Dal", 3, 0, 0);
    trainDepartureRegister.addTrainDeparture(13, 29, "L12", 124, "Eidsvoll", 1, 0, 3);

    menuLauncher();
  }

  /**
   * Prints the table header for the train departure register.
   */
  public final void printTableHeader() {
    System.out.println("-----------------------------------------------------------------"
        + "---------");
    System.out.println("| Departure Time | Line | Train Number |   Destination   | Delay | "
        + "Track | Time: " + trainDepartureRegister.getTime());
    System.out.println("|----------------|------|--------------|-----------------|-------"
        + "|-------|");
  }

  /**
   * Shows the menu with the different user choices, asks for the users input
   * and returns the choice. Ensures that the user enters a number.
   * Inspired by the menu given in the previously given practice exam.
   *
   * @return the user's choice
   */
  private int showMenu() {
    int menuChoice = 0;
    System.out.println("-----------------------------------------------------------------"
        + "---------");
    System.out.println();
    System.out.println("***** Train Departure Register Application *****");
    System.out.println("1. Add a new train departure");
    System.out.println("2. Find a specific train departure by train number");
    System.out.println("3. Find all train departures by searching for the destination");
    System.out.println("4. List all train departures sorted by time");
    System.out.println("5. Change the delay for a specific train departure");
    System.out.println("6. Assign a train track for a train departure");
    System.out.println("7. Set time on the clock");
    System.out.println("8. Quit");

    Scanner sc = new Scanner(System.in);
    if (sc.hasNextInt()) {
      menuChoice = sc.nextInt();
    } else {
      System.out.println("You must enter a number");
    }
    return menuChoice;
  }

  /**
   * Shows the menu and calls the appropriate methods based on the user's choice with a switch-case.
   * Launches the menu choice of the user or exits the application when the user chooses to quit.
   * Also inspired by the menu given in the previously given practice exam.
   */
  private void menuLauncher() {
    boolean finished = false;
    while (!finished) {
      int menuChoice = this.showMenu();
      switch (menuChoice) {
        case ADD_TRAIN_DEPARTURE:
          inputAddDeparture();
          break;
        case SEARCH_TRAIN_NUMBER:
          inputSearchTrainNumber();
          break;
        case SEARCH_DESTINATION:
          inputSearchDestination();
          break;
        case LIST_ALL_DEPARTURES_TIME_SORTED:
          printSortedTrainDepartures();
          break;
        case SET_TRAIN_DELAY:
          inputSetTrainDelay();
          break;
        case SET_TRAIN_TRACK:
          inputSetTrainTrack();
          break;
        case SET_TIME:
          inputSetTime();
          break;
        case EXIT:
          System.out.println("Thank you for using the Train Dispatch app!\n");
          finished = true;
          break;
        default:
          System.out.println("Unrecognized menu selected..");
          break;
      }
    }
  }

  /**
   * Adds a train departure to the register by asking for user input and prints the list of train
   * departures. Also catches exceptions and prints the appropriate error message if an input is
   * invalid and cannot be added to the register. Also catches exceptions and prints the appropriate
   * error message if the train number already exists in the register.
   */
  public void inputAddDeparture() {
    System.out.println("Adding a new train departure");

    System.out.println("Enter the departure hour:");
    final int departureHour = Scan.scanHourInt();

    System.out.println("Enter the departure minute:");
    final int departureMinute = Scan.scanMinuteInt();

    System.out.println("Enter the line:");
    final String line = Scan.scanLine();

    System.out.println("Enter the train number:");
    final int trainNumber = Scan.scanTrainNumber();

    System.out.println("Enter the destination:");
    final String destination = Scan.scanDestination();

    System.out.println("Enter the track number, set it to -1 if not decided:");
    final int track = Scan.scanTrack();

    System.out.println("Enter the delay in hours:");
    final int delayHour = Scan.scanHourInt();

    System.out.println("Enter the delay in minutes:");
    final int delayMinute = Scan.scanMinuteInt();

    try {
      trainDepartureRegister.addTrainDeparture(departureHour, departureMinute, line, trainNumber,
          destination, track, delayHour, delayMinute);
      printSortedTrainDepartures();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Searches for a train departure by train number by asking for user input and prints the result.
   * Checks if the train number exists in the register and prints the appropriate error message if
   * it does not.
   */
  public void inputSearchTrainNumber() {
    System.out.println("Searching for a train departure by train number");

    System.out.println("Enter the train number:");
    int trainNumber = Scan.scanTrainNumber();
    try {
      String trainDeparture = trainDepartureRegister.printTrainDepartureByTrainNumber(trainNumber);
      printTableHeader();
      System.out.println(trainDeparture);
    } catch (Exception e) {
      System.out.println("Error: You have to enter a train number that exists in the register");
    }
  }

  /**
   * Searches for train departures by destination by asking for user input and prints the list of
   * train departures. Checks if the destination exists in the register and prints the appropriate
   * error message if it does not.
   */
  public void inputSearchDestination() {
    System.out.println("Searching for train departures by destination");

    System.out.println("Enter the destination:");
    String destination = Scan.scanDestination();
    try {
      String trainDepartures =
          trainDepartureRegister.printTrainDeparturesByDestination(destination);
      printTableHeader();
      System.out.println(trainDepartures);
    } catch (Exception e) {
      System.out.println("Error: You have to enter a destination that exists in the register");
    }

  }

  /**
   * Prints the list of train departures sorted by time. Catches an exception and prints the
   * appropriate error message if the register is empty.
   */
  public void printSortedTrainDepartures() {
    try {
      System.out.println("Printing the list of train departures sorted by time");
      String sortedTrainDepartures = trainDepartureRegister.printTimeSortedTrainDepartures();
      printTableHeader();
      System.out.println(sortedTrainDepartures);
    } catch (Exception e) {
      System.out.println("Error: The register is empty");
    }
  }

  /**
   * Sets the delay of a train departure by asking for user input and prints the list of train
   * departures. Checks if the train number exists in the register and prints the appropriate error
   * message if it does not.
   */
  public void inputSetTrainDelay() {
    System.out.println("Setting the delay of a train departure");

    System.out.println("Enter the train number:");
    final int trainNumber = Scan.scanTrainNumber();

    System.out.println("Enter the delay in hours:");
    final int delayHour = Scan.scanHourInt();

    System.out.println("Enter the delay in minutes:");
    final int delayMinute = Scan.scanMinuteInt();

    try {
      trainDepartureRegister.setTrainDelay(trainNumber, delayHour, delayMinute);
      printSortedTrainDepartures();
    } catch (Exception e) {
      System.out.println("Error: You have to enter a train that exists in the register");
    }
  }

  /**
   * Sets the track of a train departure by asking for user input and prints the list of train
   * departures. Checks if the train number exists in the register and prints the appropriate error
   * message if it does not.
   */
  public void inputSetTrainTrack() {
    System.out.println("Assigning a track to a train departure");

    System.out.println("Enter the train number:");
    final int trainNumber = Scan.scanTrainNumber();

    System.out.println("Enter the track number:");
    final int track = Scan.scanTrack();

    try {
      trainDepartureRegister.setTrainTrack(trainNumber, track);
      printSortedTrainDepartures();
    } catch (Exception e) {
      System.out.println("Error: You have to enter a train that exists in the register");
    }
  }

  /**
   * Sets the time of the clock by asking for user input and prints the list of train departures.
   * Catches an exception and prints the appropriate error message if the time is before the current
   * time.
   */
  public void inputSetTime() {
    System.out.println("Setting the time of the clock");

    System.out.println("Enter the hour:");
    final int hour = Scan.scanHourInt();

    System.out.println("Enter the minute:");
    final int minute = Scan.scanMinuteInt();

    try {
      trainDepartureRegister.setTime(hour, minute);
      printSortedTrainDepartures();
    } catch (Exception e) {
      System.out.println("Error: You have to enter a time that is after the current time");
    }
  }
}
