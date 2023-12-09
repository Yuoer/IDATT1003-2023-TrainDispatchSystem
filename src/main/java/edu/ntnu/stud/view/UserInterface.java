package edu.ntnu.stud.view;

import edu.ntnu.stud.model.TrainDepartureRegister;
import java.util.Scanner;

/**
 * This is the user interface class for the train dispatch application.
 *
 * @author 562289
 * @version 0.1
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

  public void init() {
    trainDepartureRegister = new TrainDepartureRegister(0, 0);
  }

  public void start() {
    trainDepartureRegister.addTrainDeparture(13, 16, "L1", 106, "Spikkestad", 4, 0, 5);
    trainDepartureRegister.addTrainDeparture(13, 18, "L1", 16, "Lillestrøm", 6, 0, 10);
    trainDepartureRegister.addTrainDeparture(13, 19, "L13", 135, "Dal", 3, 0, 0);
    trainDepartureRegister.addTrainDeparture(13, 29, "L12", 124, "Eidsvoll", 1, 0, 3);

    menuLauncher();
  }

  public final void printTableHeader() {
    System.out.println("| Departure Time | Line | Train Number |   Destination   | Delay | "
        + "Track | Time: " + trainDepartureRegister.getTime());
    System.out.println("|----------------|------|--------------|-----------------|-------"
        + "|-------|");
  }

  private int showMenu() {
    int menuChoice = 0;
    System.out.println("***** Train Departure Register Application *****");
    System.out.println("1. Add train departure");
    System.out.println("2. Search for train by train number");
    System.out.println("3. List all trains with a specific destination");
    System.out.println("4. List all train departures sorted by time");
    System.out.println("5. Set train delay for a specific train departure");
    System.out.println("6. Set train track for a specific train departure");
    System.out.println("7. Set time on the clock");
    System.out.println("8. Quit");

    Scanner sc = new Scanner(System.in);
    if (sc.hasNextInt()) {
      menuChoice = sc.nextInt();
    } else {
      System.out.println("You must enter a number, not text");
    }
    return menuChoice;
  }

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
   * departures.
   */
  public void inputAddDeparture() {
    System.out.println("Enter the departure hour:");
    final int departureHour = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter the departure minute:");
    final int departureMinute = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter the line:");
    final String line = scanner.nextLine();

    System.out.println("Enter the train number:");
    final int trainNumber = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter the destination:");
    final String destination = scanner.nextLine();

    System.out.println("Enter the track number:");
    final int track = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter the delay in hours:");
    final int delayHour = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter the delay in minutes:");
    final int delayMinute = scanner.nextInt();
    scanner.nextLine();

    trainDepartureRegister.addTrainDeparture(departureHour, departureMinute, line, trainNumber,
        destination, track, delayHour, delayMinute);
    printTableHeader();
    System.out.println(trainDepartureRegister.printTimeSortedTrainDepartures());
  }

  /**
   * Searches for a train departure by train number by asking for user input and prints the result.
   */
  public void inputSearchTrainNumber() {
    System.out.println("Enter the train number:");
    int trainNumber = scanner.nextInt();
    printTableHeader();
    System.out.println(trainDepartureRegister.printTrainDepartureByTrainNumber(trainNumber));
  }

  /**
   * Searches for train departures by destination by asking for user input and prints the list.
   */
  public void inputSearchDestination() {
    System.out.println("Enter the destination:");
    String destination = scanner.nextLine();
    printTableHeader();
    System.out.println(trainDepartureRegister.printTrainDeparturesByDestination(destination));
  }

  /**
   * Prints the list of train departures sorted by time.
   */
  public void printSortedTrainDepartures() {
    printTableHeader();
    System.out.println(trainDepartureRegister.printTimeSortedTrainDepartures());
  }

  /**
   * Sets the delay of a train departure by asking for user input and prints the list of train
   * departures.
   */
  public void inputSetTrainDelay() {
    System.out.println("Enter the train number:");
    final int trainNumber = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter the delay in hours:");
    final int delayHour = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter the delay in minutes:");
    final int delayMinute = scanner.nextInt();
    scanner.nextLine();
    trainDepartureRegister.setTrainDelay(trainNumber, delayHour, delayMinute);
    printTableHeader();
    System.out.println(trainDepartureRegister.printTimeSortedTrainDepartures());
  }

  /**
   * Sets the track of a train departure by asking for user input and prints the list of train
   * departures.
   */
  public void inputSetTrainTrack() {
    System.out.println("Enter the train number:");
    final int trainNumber = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter the track number:");
    final int track = scanner.nextInt();
    scanner.nextLine();
    trainDepartureRegister.setTrainTrack(trainNumber, track);
    printTableHeader();
    System.out.println(trainDepartureRegister.printTimeSortedTrainDepartures());
  }

  /**
   * Sets the time of the clock by asking for user input and prints the list of train departures.
   */
  public void inputSetTime() {
    System.out.println("Enter the hour:");
    final int hour = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter the minute:");
    final int minute = scanner.nextInt();
    scanner.nextLine();
    trainDepartureRegister.setTime(hour, minute);
    printTableHeader();
    System.out.println(trainDepartureRegister.printTimeSortedTrainDepartures());
  }

}
