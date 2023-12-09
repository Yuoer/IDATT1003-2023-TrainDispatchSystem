package edu.ntnu.stud.launcher;

import edu.ntnu.stud.view.UserInterface;

/**
 * This is the main class for the train dispatch application.
 *
 * @author 562289
 * @version 0.2
 * @since 0.1
 */
public class TrainDispatchApp {

  /**
   * The main method of the train dispatch application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();

    userInterface.init();
    userInterface.start();
  }
}
