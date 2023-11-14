package edu.ntnu.stud;

import java.time.LocalTime;

public class UserInterface {
  public static void start() {
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.of(12, 30), "f4", 62, "Bergen", 26, LocalTime.of(0, 5));
    TrainDeparture trainDeparture2 = new TrainDeparture(LocalTime.of(12, 35), "f4", 6, "Spikkestad", 26, LocalTime.of(0, 0));
    TrainDeparture trainDeparture3 = new TrainDeparture(LocalTime.of(12, 40), "f4", 62, "Lillestrøm", 26, LocalTime.of(0, 10));
    TrainDeparture trainDeparture4 = new TrainDeparture(LocalTime.of(12, 45), "f4", 62, "Dal", 26, LocalTime.of(0, 0));
    TrainDeparture trainDeparture5 = new TrainDeparture(LocalTime.of(12, 55), "f4", 62, "Trondheim", 26, LocalTime.of(0, 20));

    System.out.println(trainDeparture1);
    System.out.println(trainDeparture2);
    System.out.println(trainDeparture3);
    System.out.println(trainDeparture4);
    System.out.println(trainDeparture5);

  }
  public void init() {

  }
}
