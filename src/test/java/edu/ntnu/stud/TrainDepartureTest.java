package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {
  @Test
  @DisplayName("Hva testen er")
  void testGetDepartureTime() {
    LocalTime departureTime = LocalTime.of(8, 30);
    TrainDeparture trainDeparture =
        new TrainDeparture(departureTime, "L1", 123, "Destination", 2, LocalTime.of(0, 0));
    assertEquals(departureTime, trainDeparture.getDepartureTime());
  }

  @Test
  void testSetLine() {
    TrainDeparture trainDeparture =
        new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2, LocalTime.of(0, 0));
    trainDeparture.setLine("L2");
    assertEquals("L2", trainDeparture.getLine());
  }

  @Test
  void testNegativeTrack() {
    TrainDeparture trainDeparture =
        new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2, LocalTime.of(0, 0));
    trainDeparture.setTrack(1);
    assertEquals(1, trainDeparture.getTrack());
  }

  @Nested
  @DisplayName("Test for TrainDeparture")
  class TrainDepartureTest {
    @Test
    @DisplayName("Test for getDepartureTime")
    void testGetDepartureTime() {
      LocalTime departureTime = LocalTime.of(8, 30);
      TrainDeparture trainDeparture =
          new TrainDeparture(departureTime, "L1", 123, "Destination", 2, LocalTime.of(0, 0));
      assertEquals(departureTime, trainDeparture.getDepartureTime());
    }

    @Test
    @DisplayName("Test for setLine")
    void testSetLine() {
      TrainDeparture trainDeparture =
          new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2,
              LocalTime.of(0, 0));
      trainDeparture.setLine("L2");
      assertEquals("L2", trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test for setTrack")
    void testSetTrack() {
      TrainDeparture trainDeparture =
          new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2,
              LocalTime.of(0, 0));
      trainDeparture.setTrack(1);
      assertEquals(1, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test for setDelay")
    void testSetDelay() {
      TrainDeparture trainDeparture =
          new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2,
              LocalTime.of(0, 0));
      trainDeparture.setDelay(LocalTime.of(0, 10));
      assertEquals(LocalTime.of(0, 10), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test for getLine")
    void testGetLine() {
      TrainDeparture trainDeparture =
          new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2,
              LocalTime.of(0, 0));
      assertEquals("L1", trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test for getTrainNumber")
    void testGetTrainNumber() {
      TrainDeparture trainDeparture =
          new TrainDeparture(LocalTime.of(8, 30), "L1", 123, "Destination", 2,
              LocalTime.of(0, 0));
      assertEquals(123, trainDeparture.getTrainNumber());
    }
  }
}
