package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.model.TrainDeparture;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

/**
 * Test class for TrainDeparture. Tests the constructor, get methods and toString method.
 *
 */

class TrainDepartureTest {

  TrainDeparture trainDeparture;

  @Nested
  @DisplayName("Positive tests for constructor")
  class positiveConstructorTests {
      @Test
      @DisplayName("Test for constructor")
      void testConstructor() {
      TrainDeparture trainDeparture = new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 0, 0);
      assertEquals(LocalTime.of(8, 30), trainDeparture.getDepartureTime());
      assertEquals("L1", trainDeparture.getLine());
      assertEquals(123, trainDeparture.getTrainNumber());
      assertEquals("Destination", trainDeparture.getDestination());
      assertEquals(2, trainDeparture.getTrack());
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test for constructor with -1 as track")
    void testConstructorWithMinus1AsTrack() {
      TrainDeparture trainDeparture = new TrainDeparture(8, 30, "L1", 123, "Destination", -1, 0, 0);
      assertEquals(LocalTime.of(8, 30), trainDeparture.getDepartureTime());
      assertEquals("L1", trainDeparture.getLine());
      assertEquals(123, trainDeparture.getTrainNumber());
      assertEquals("Destination", trainDeparture.getDestination());
      assertEquals(-1, trainDeparture.getTrack());
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test for constructor with delay")
    void testConstructorWithDelay() {
      TrainDeparture trainDeparture = new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 1, 0);
      assertEquals(LocalTime.of(8, 30), trainDeparture.getDepartureTime());
      assertEquals("L1", trainDeparture.getLine());
      assertEquals(123, trainDeparture.getTrainNumber());
      assertEquals("Destination", trainDeparture.getDestination());
      assertEquals(2, trainDeparture.getTrack());
      assertEquals(LocalTime.of(1, 0), trainDeparture.getDelay());
    }

  }

  @Nested
  @DisplayName("Negative tests for constructor")
  class negativeConstructorTests {
    @Test
    @DisplayName("Test constructor for negative hour")
    void estConstructorForNegativeHour() {
      try{
        new TrainDeparture(-1, 30, "L1", 123, "Destination", 2, 0, 0);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());

      }
    }

    @Test
    @DisplayName("Test constructor for negative minute")
    void testConstructorForNegativeMinute() {
      try{
      new TrainDeparture(8, -1, "L1", 123, "Destination", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for special characters in line")
    void testConstructorForSpecialCharactersInLine() {
      try{
      new TrainDeparture(8, 30, "@#$", 123, "Destination", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Line must contain only capital letters and numbers", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for lower case letters in line")
    void testConstructorForLowerCaseLettersInLine() {
      try{
      new TrainDeparture(8, 30, "l1", 123, "Destination", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Line must contain only capital letters and numbers", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for train number under 1")
    void testConstructorForTrainNumberUnder1() {
      try{
      new TrainDeparture(8, 30, "L1", 0, "Destination", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Train number has to be between 1 and 999", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for train number over 999")
    void testConstructorForTrainNumberOver999() {
      try{
      new TrainDeparture(8, 30, "L1", 1000, "Destination", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Train number has to be between 1 and 999", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for destination under 2 characters")
    void testConstructorForDestinationUnder2Characters() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "D", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Destination must be between 2 and 30 characters", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for destination over 30 characters")
    void testConstructorForDestinationOver30Characters() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "DestinationDestinationDestination", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Destination must be between 2 and 30 characters", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for destination with special characters")
    void testConstructorForDestinationWithSpecialCharacters() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Dest@#$", 2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Destination must contain only letters", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for track under -1")
    void testConstructorForTrackUnderNegative1() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Destination", -2, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for track over 99")
    void testConstructorForTrackOver99() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Destination", 100, 0, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for negative hour in delay")
    void testConstructorForNegativeHourInDelay() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Destination", 2, -1, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for hour over 23 in delay")
    void testConstructorForHourOver23InDelay() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 24, 0);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for negative minute in delay")
    void testConstructorForNegativeMinuteInDelay() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 0, -1);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test constructor for minute over 59 in delay")
    void testConstructorForMinuteOver59InDelay() {
      try{
      new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 0, 60);
      fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

  }

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

    @Test
    @DisplayName("Test for getActualDepartureTime")
    void getActualDepartureTime() {
      TrainDeparture trainDepartureDelay =
          new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 0, 10);
      assertEquals(LocalTime.of(8, 40), trainDepartureDelay.getActualDepartureTime());
    }

    @Test
    @DisplayName("Test for getActualDepartureTime when delay makes hour go up")
    void getActualDepartureTimeWhenDelayMakesHourGoUp() {
      TrainDeparture trainDepartureDelay =
          new TrainDeparture(8, 50, "L1", 123, "Destination", 2, 0, 20);
      assertEquals(LocalTime.of(9, 10), trainDepartureDelay.getActualDepartureTime());
    }

    @Test
    @DisplayName("Test for getActualDepartureTime when delay makes hour go up with over 1 hour delay")
    void getActualDepartureTimeWhenDelayMakesHourGoUpWithOver1HourDelay() {
      TrainDeparture trainDepartureDelay =
          new TrainDeparture(8, 50, "L1", 123, "Destination", 2, 1, 20);
      assertEquals(LocalTime.of(10, 10), trainDepartureDelay.getActualDepartureTime());
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
    void negativeGetDelay() {
      assertNotEquals(LocalTime.of(0, 1), trainDeparture.getDelay());
    }
  }

  @Nested
  @DisplayName("Positive tests for set methods")
  class positiveSetTests {

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

    @Test
    @DisplayName("Test setTrack for number over 99")
    void setTrackTooHighThrowsException() {
      try {
        trainDeparture.setTrack(100);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test setTrack for number 0")
    void setTrackZeroThrowsException() {
      try {
        trainDeparture.setTrack(0);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test setTrack for number under -1")
    void setTrackNegativeThrowsException() {
      try {
        trainDeparture.setTrack(-2);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test setDelay for negative minutes")
    void setDelayNegativeMinutesThrowsException() {
      try {
        trainDeparture.setDelay(0, -1);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test setDelay for minutes over 59")
    void setDelayTooManyMinutesThrowsException() {
      try {
        trainDeparture.setDelay(0, 60);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test setDelay for negative hours")
    void setDelayNegativeHoursThrowsException() {
      try {
        trainDeparture.setDelay(-1, 0);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test setDelay for hours over 23")
    void setDelayTooManyHoursThrowsException() {
      try {
        trainDeparture.setDelay(24, 0);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

  }

  @Nested
  @DisplayName("Positive tests for toString")
  class positiveToStringTests {
    @Test
    @DisplayName("Test for toString")
    void testToString() {
    assertEquals("| 08:30          | L1   | 123          | Destination     | 00:00 | 2     |", trainDeparture.toString());
    }

    @Test
    @DisplayName("Test for toString with delay")
    void testToStringWithDelay() {
    TrainDeparture trainDepartureDelay =
        new TrainDeparture(8, 30, "L1", 123, "Destination", 2, 1, 0);
    assertEquals("| 08:30          | L1   | 123          | Destination     | 01:00 | 2     |", trainDepartureDelay.toString());
    }
  }

}
