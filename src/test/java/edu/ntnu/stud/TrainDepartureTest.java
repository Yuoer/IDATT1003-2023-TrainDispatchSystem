package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

/**
 * Test class for TrainDeparture
 *
 */

class TrainDepartureTest {

  TrainDeparture trainDeparture;
  @BeforeEach
  void setUp() {
    trainDeparture =
        new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 0, 0);
  }
  @Nested
  @DisplayName("Positive tests for get methods")
  class positiveGetTests {

    @Test
    @DisplayName("Test for getDepartureTime")
    void testGetDepartureTime() {
      assertEquals(LocalTime.of(8, 30), trainDeparture.getDepartureTime());
    }

    @Test
    @DisplayName("Test for getLine")
    void getLine() {
      assertEquals("L1", trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test for getTrainNumber")
    void getTrainNumber() {
      assertEquals(123, trainDeparture.getTrainNumber());
    }

    @Test
    @DisplayName("Test for getDestination")
    void getDestination() {
      assertEquals("Destination", trainDeparture.getDestination());
    }

    @Test
    @DisplayName("Test for getTrack")
    void getTrack() {
      assertEquals(2, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test for getDelay")
    void getDelay() {
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }
  }

  @Nested
  @DisplayName("Negative tests for get methods")
  class negativeGetTests {
    @Test
    @DisplayName("Test for getDepartureTime")
    void negativeGetDepartureTime() {
      assertNotEquals(LocalTime.of(8, 31), trainDeparture.getDepartureTime());
    }

    @Test
    @DisplayName("Test for getLine")
    void negativeGetLine() {
      assertNotEquals("L2", trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test for getTrainNumber")
    void negativeGetTrainNumber() {
      assertNotEquals(124, trainDeparture.getTrainNumber());
    }

    @Test
    @DisplayName("Test for getDestination")
    void negativeGetDestination() {
      assertNotEquals("Dest", trainDeparture.getDestination());
    }

    @Test
    @DisplayName("Test for getTrack")
    void negativeGetTrack() {
      assertNotEquals(3, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test for getDelay")
    void negativGetDelay() {
      assertNotEquals(LocalTime.of(0, 1), trainDeparture.getDelay());
    }
  }

  @Nested
  @DisplayName("Positive tests for set methods")
  class positiveSetTests {
    @Test
    @DisplayName("Test for setLine")
    void testSetLine() {
      trainDeparture.setLine("B7");
      assertEquals("B7", trainDeparture.getLine());
    }
    @Test
    void testNegativeTrack() {
      trainDeparture.setTrack(1);
      assertEquals(1, trainDeparture.getTrack());
    }
    @Test
    void testSetDelay() {
      trainDeparture.setDelay(1, 0);
      assertEquals(LocalTime.of(1, 0), trainDeparture.getDelay());
    }
  }

  @Nested
  @DisplayName("Negative tests for set methods")
  class negativeSetTests {
    @Test
    @DisplayName("Test for setLine")
    void negativeTestSetLine() {
      trainDeparture.setLine("B7");
      assertNotEquals("B8", trainDeparture.getLine());
    }
    @Test
    @DisplayName("Test for setTrack")
    void negativeTestSetTrack() {
      trainDeparture.setTrack(1);
      assertNotEquals(2, trainDeparture.getTrack());
    }
    @Test
    @DisplayName("Test for setDelay")
    void negativeTestSetDelay() {
      trainDeparture.setDelay(1, 0);
      assertNotEquals(LocalTime.of(1, 1), trainDeparture.getDelay());
    }
  }

}
