package edu.junit5.quickstart.state;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Tests for core functionality of State.
 *
 * @author Robin Steil
 */
public class StateTest {

  /**
   * Test state initialisation is always the same.
   */
  @Test
  public void testStateInitialisationIsAlwaysTheSame() {
    State state1 = State.getInstance();
    State state2 = State.getInstance();

    Assertions.assertEquals(state1, state2);
  }
}
