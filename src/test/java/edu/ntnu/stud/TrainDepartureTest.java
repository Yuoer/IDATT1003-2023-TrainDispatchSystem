package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {
  @Test
  void testGetDepartureTime() {
    LocalTime departureTime = LocalTime.of(8, 30);
    TrainDeparture trainDeparture = new TrainDeparture(departureTime, "L1", 123, "Destination", 2, LocalTime.of(0, 0));
    assertEquals(departureTime, trainDeparture.getDepartureTime());
  }

  @Test
  void testSetLine() {
    TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2, LocalTime.of(0, 0));
    trainDeparture.setLine("L2");
    assertEquals("L2", trainDeparture.getLine());
  }

  @Test
  void testNegativeTrack() {
    TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2, LocalTime.of(0, 0));
    trainDeparture.setTrack(1);
    assertEquals(1, trainDeparture.getTrack());
  }

  // Add more tests for other methods as needed
}