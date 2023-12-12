package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.model.TrainDepartureRegister;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

/**
 * Test class for TrainDepartureRegister. This class tests the print methods, get methods, set
 * methods and addTrainDeparture method.
 *
 */
public class TrainDepartureRegisterTest {
  TrainDepartureRegister departures;
  @BeforeEach
  void setUp() {
    departures = new TrainDepartureRegister(8, 30);

    departures.addTrainDeparture(10, 40, "G7", 456, "Bergen", 3, 0, 0);
    departures.addTrainDeparture(9, 30, "L1", 105, "Oslo", 2, 0, 5);
    departures.addTrainDeparture(9, 40, "F12", 123, "Trondheim", 5, 0, 10);
  }

  @Nested
  @DisplayName("Positive tests for get methods")
  class positiveGetTests {

    @Test
    @DisplayName("Test for getTime")
    void testGetTimeOriginal() {
      assertEquals(LocalTime.of(8, 30), departures.getTime());
    }

    @Test
    @DisplayName("Test for getTime when time is changed")
    void testGetTimeNewTime() {
      departures.setTime(9, 35);
      assertEquals(LocalTime.of(9, 35), departures.getTime());
    }

    @Test
    @DisplayName("Test for getTrainDepartures")
    void testGetTrainDepartures() {
      assertEquals(
          "[| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |, "
              + "| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |]",
          departures.getTrainDepartures().toString());

    }

    @Test
    @DisplayName("Test for searchTrainDepartureByTrainNumber")
    void testSearchTrainDepartureByTrainNumber() {
      assertEquals( "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |",
              departures.searchTrainDepartureByTrainNumber(123).toString());
    }

    @Test
    @DisplayName("Test for searchTrainDeparturesByDestination")
    void testSearchTrainDeparturesByDestination() {
      departures.addTrainDeparture(9, 40, "L1", 223, "Trondheim", 2, 0, 5);
      assertEquals("[| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 09:40          | L1   | 223          | Trondheim       | 00:05 | 2     |]",
          departures.searchTrainDeparturesByDestination("Trondheim").toString());
    }

    @Test
    @DisplayName("Test for getTimeSortedTrainDepartures")
    void testGetTimeSortedTrainDepartures() {
      assertEquals(
          "[| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |]",
          departures.getTimeSortedTrainDepartures().toString());
    }
  }

  @Nested
  @DisplayName("Negative tests for get methods")
  class negativeGetTests {

    @Test
    @DisplayName("Test for getTime")
    void testGetTime() {
      assertNotEquals(LocalTime.of(8, 31), departures.getTime());
    }

    @Test
    @DisplayName("Test for getTrainDepartures return null when no train departures exists")
    void testGetTrainDeparturesReturnNull() {
      TrainDepartureRegister emptyRegister = new TrainDepartureRegister(8, 30);
      assertEquals("[]", emptyRegister.getTrainDepartures().toString());
    }

    @Test
    @DisplayName("Test for searchTrainDepartureByTrainNumber returning null when no such train departure exists")
    void testSearchTrainDepartureByTrainNumberReturnNull() {
      assertNull(departures.searchTrainDepartureByTrainNumber(144));
    }

    @Test
    @DisplayName("Test for searchTrainDeparturesByDestination returning null when no such train departure exists")
    void testSearchTrainDeparturesByDestinationReturnNull() {
      assertNull(departures.searchTrainDeparturesByDestination("Stavanger"));
    }

    @Test
    @DisplayName("Test for getTimeSortedTrainDepartures return null when no train departure exists")
    void testGetTimeSortedTrainDeparturesReturnNull() {
      TrainDepartureRegister emptyRegister = new TrainDepartureRegister(8, 30);
      assertNull(emptyRegister.getTimeSortedTrainDepartures());
    }
  }

  @Nested
  @DisplayName("Positive tests for addTrainDeparture")
  class positiveAddTrainDepartureTest {
    @Test
    @DisplayName("Test for addTrainDeparture")
    void testAddTrainDeparture() {
      departures.addTrainDeparture(9, 40, "L1", 223, "Trondheim", 2, 0, 5);
      assertEquals("[| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |, "
              + "| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 09:40          | L1   | 223          | Trondheim       | 00:05 | 2     |]",
          departures.getTrainDepartures().toString());
    }

    @Test
    @DisplayName("Test for addTrainDeparture with line 1L")
    void testAddTrainDepartureWithLine1L() {
      departures.addTrainDeparture(9, 40, "1L", 223, "Trondheim", 2, 0, 5);
      assertEquals("[| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |, "
              + "| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 09:40          | 1L   | 223          | Trondheim       | 00:05 | 2     |]",
          departures.getTrainDepartures().toString());
    }

    @Test
    @DisplayName("Test for addTrainDeparture with 3 characters in line")
    void testAddTrainDepartureWith3CharactersInLine() {
      departures.addTrainDeparture(9, 40, "L12", 223, "Trondheim", 2, 0, 5);
      assertEquals("[| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |, "
              + "| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 09:40          | L12  | 223          | Trondheim       | 00:05 | 2     |]",
          departures.getTrainDepartures().toString());
    }

    @Test
    @DisplayName("Test for addTrainDeparture with track 99")
    void testAddTrainDepartureWithTrack99() {
      departures.addTrainDeparture(9, 40, "L1", 223, "Trondheim", 99, 0, 5);
      assertEquals("[| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |, "
              + "| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 09:40          | L1   | 223          | Trondheim       | 00:05 | 99    |]",
          departures.getTrainDepartures().toString());
    }

    @Test
    @DisplayName("Test for addTrainDeparture with track -1")
    void testAddTrainDepartureWithTrackMinus1() {
      departures.addTrainDeparture(9, 40, "L1", 223, "Trondheim", -1, 0, 5);
      assertEquals("[| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |, "
              + "| 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |, "
              + "| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |, "
              + "| 09:40          | L1   | 223          | Trondheim       | 00:05 |       |]",
          departures.getTrainDepartures().toString());
    }
  }

  @Nested
  @DisplayName("Negative tests for addTrainDeparture")
  class negativeAddTrainDepartureTest {

    @Test
    @DisplayName("Test for addTrainDeparture when train number already exists")
    void testAddTrainDepartureWhenTrainNumberAlreadyExists() {
      try {
        departures.addTrainDeparture(9, 40, "L1", 105, "Oslo", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Train number already exists", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture when actual departure time is before time on clock")
    void testAddTrainDepartureWhenActualDepartureTimeIsBeforeTimeOnClock() {
      try {
        departures.addTrainDeparture(8, 0, "L1", 223, "Trondheim", 2, 0, 0);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Actual departure time must be after current time", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with hour over 23")
    void testAddTrainDepartureWithHourOver23() {
      try {
        departures.addTrainDeparture(24, 40, "L1", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with hour under 0")
    void testAddTrainDepartureWithHourUnder0() {
      try {
        departures.addTrainDeparture(-1, 40, "L1", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with minute over 59")
    void testAddTrainDepartureWithMinuteOver59() {
      try {
        departures.addTrainDeparture(9, 60, "L1", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with minute under 0")
    void testAddTrainDepartureWithMinuteUnder0() {
      try {
        departures.addTrainDeparture(9, -1, "L1", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with line over 3 characters")
    void testAddTrainDepartureWithLineOver3Characters() {
      try {
        departures.addTrainDeparture(9, 40, "L123", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Line must be between 2 and 3 characters", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with line under 2 characters")
    void testAddTrainDepartureWithLineUnder2Characters() {
      try {
        departures.addTrainDeparture(9, 40, "L", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Line must be between 2 and 3 characters", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with line containing lowercase letters")
    void testAddTrainDepartureWithLineContainingLowercaseLetters() {
      try {
        departures.addTrainDeparture(9, 40, "l1", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Line must contain only capital letters and numbers", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for addTrainDeparture with line containing special characters")
    void testAddTrainDepartureWithLineContainingSpecialCharacters() {
      try {
        departures.addTrainDeparture(9, 40, "L1!", 223, "Trondheim", 2, 0, 5);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Line must contain only capital letters and numbers", e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Positive tests for set methods")
  class positiveSetTests {
    @Test
    @DisplayName("Test for setTime general")
    void testSetTime() {
    departures.setTime(9, 35);
    assertEquals(LocalTime.of(9, 35), departures.getTime());
    }

    @Test
    @DisplayName("Test for setTime with hour 23")
    void testSetTimeWithHour23() {
        departures.setTime(23, 35);
        assertEquals(LocalTime.of(23, 35), departures.getTime());
    }

    @Test
    @DisplayName("Test for setTime with minute 0")
    void testSetTimeWithMinute0() {
      departures.setTime(9, 0);
      assertEquals(LocalTime.of(9, 0), departures.getTime());
    }

    @Test
    @DisplayName("Test for setTime with minute 59")
    void testSetTimeWithMinute59() {
      departures.setTime(9, 59);
      assertEquals(LocalTime.of(9, 59), departures.getTime());
    }

    @Test
    @DisplayName("Test for setTrainDelay with delay 0 minutes and 0 hours")
    void testSetTrainDelayWithDelay0() {
      departures.setTrainDelay(456, 0, 0);
      assertEquals("| 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |",
          departures.searchTrainDepartureByTrainNumber(456).toString());
    }

    @Test
    @DisplayName("Test for setTrainDelay with delay 59 minutes and 0 hours")
    void testSetTrainDelayWithDelay59() {
      departures.setTrainDelay(456, 0, 59);
      assertEquals("| 10:40          | G7   | 456          | Bergen          | 00:59 | 3     |",
          departures.searchTrainDepartureByTrainNumber(456).toString());
    }

    @Test
    @DisplayName("Test for setTrainTrack with track -1")
    void testSetTrainTrackWithTrackMinus1() {
      departures.setTrainTrack(456, -1);
      assertEquals("| 10:40          | G7   | 456          | Bergen          | 00:00 |       |",
          departures.searchTrainDepartureByTrainNumber(456).toString());
    }

    @Test
    @DisplayName("Test for setTrainTrack with track 99")
    void testSetTrainTrackWithTrack99() {
      departures.setTrainTrack(456, 99);
      assertEquals("| 10:40          | G7   | 456          | Bergen          | 00:00 | 99    |",
          departures.searchTrainDepartureByTrainNumber(456).toString());
    }

  }

  @Nested
  @DisplayName("Negative tests for set methods")
  class negativeSetTimeTest {

    @Test
    @DisplayName("Test for setTime with time before current time")
    void testSetTimeWithTimeBeforeCurrentTime() {
      try {
        departures.setTime(8, 0);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Time must be after current time", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTime with hour over 23")
    void testSetTimeWithHourOver23() {
      try {
        departures.setTime(24, 35);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTime with hour under 0")
    void testSetTimeWithHourUnder0() {
      try {
        departures.setTime(-1, 35);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Hour must be between 0 and 23", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTime with minute over 59")
    void testSetTimeWithMinuteOver59() {
      try {
        departures.setTime(9, 60);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTime with minute under 0")
    void testSetTimeWithMinuteUnder0() {
      try {
        departures.setTime(9, -1);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTrainDelay with delay over 59 minutes")
    void testSetTrainDelayWithDelayOver59() {
      try {
        departures.setTrainDelay(456, 0, 60);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTrainDelay with delay under 0 minutes")
    void testSetTrainDelayWithDelayUnder0() {
      try {
        departures.setTrainDelay(456, 0, -1);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Minute must be between 0 and 59", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTrainTrack with track over 99")
    void testSetTrainTrackWithTrackOver99() {
      try {
        departures.setTrainTrack(456, 100);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test for setTrainTrack with track under -1")
    void testSetTrainTrackWithTrackUnderMinus1() {
      try {
        departures.setTrainTrack(456, -2);
        fail("The test failed since it did not throw an IllegalArgumentException");
      } catch (IllegalArgumentException e) {
        assertEquals("Track must be between 1 and 99 or -1", e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Positive tests for print methods")
  class positivePrintTests {

    @Test
    @DisplayName("Test for printTimeSortedTrainDepartures")
    void testPrintTimeSortedTrainDepartures() {
      assertEquals("""
              | 09:30          | L1   | 105          | Oslo            | 00:05 | 2     |
              | 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |
              | 10:40          | G7   | 456          | Bergen          | 00:00 | 3     |""",
          departures.printTimeSortedTrainDepartures());
    }

    @Test
    @DisplayName("Test for printTrainDeparturesByDestination")
    void testPrintTrainDeparturesByDestination() {
      departures.addTrainDeparture(9, 40, "L1", 223, "Trondheim", 2, 0, 5);
      assertEquals("| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |\n"
              + "| 09:40          | L1   | 223          | Trondheim       | 00:05 | 2     |",
          departures.printTrainDeparturesByDestination("Trondheim"));
    }

    @Test
    @DisplayName("Test for printTrainDeparturesByTrainNumber")
    void testPrintTrainDeparturesByTrainNumber() {
      assertEquals("| 09:40          | F12  | 123          | Trondheim       | 00:10 | 5     |",
          departures.printTrainDepartureByTrainNumber(123));
    }

  }

}
