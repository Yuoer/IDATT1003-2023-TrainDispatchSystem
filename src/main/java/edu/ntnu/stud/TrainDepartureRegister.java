package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;

public class TrainDepartureRegister {
  ArrayList<TrainDeparture> trainDepartures;

  public TrainDepartureRegister() {
    trainDepartures = new ArrayList<>();
  }
  public void addTrainDeparture(LocalTime departureTime, String line, int trainNumber, String destination, int track, LocalTime delay) {
    TrainDeparture trainDeparture = new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);
    trainDepartures.add(trainDeparture);

  }
  public TrainDeparture searchTrainDepartureByTrainNumber(int trainNumber) {
    return trainDepartures.stream()
        .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
        .findFirst()
        .orElse(null);
  }
}
