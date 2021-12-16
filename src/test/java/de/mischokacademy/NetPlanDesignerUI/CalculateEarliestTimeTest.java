package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CalculateEarliestTimeTest {

    private List<Knot> knots() {
        List<Knot> knots = new ArrayList<>();

        knots.add(new Knot(1, "Start", 3, null, null));                                   // index 0
        knots.add(new Knot(2, "Second", 1, List.of(knots.get(0)), null));                            // index 1
        knots.add(new Knot(3, "Third", 4, List.of(knots.get(1)), null));                             // index 2
        knots.add(new Knot(4, "After Third One", 2, List.of(knots.get(2)), null));                   // index 3
        knots.add(new Knot(5, "After Third Two", 1, List.of(knots.get(2)), null));                   // index 4
        knots.add(new Knot(6, "After Third Three", 3, List.of(knots.get(2)), null));                 // index 5
        knots.add(new Knot(7, "After Third One - Test", 3, List.of(knots.get(3)), null));            // index 6
        knots.add(new Knot(8, "After Third Two - Test", 4, List.of(knots.get(4)), null));            // index 7
        knots.add(new Knot(9, "After Third Three - Test", 5, List.of(knots.get(5)), null));          // index 8
        knots.add(new Knot(10, "End", 1, List.of(knots.get(6), knots.get(7), knots.get(8)), null));  // index 9

        knots.get(0).setSuccessor(List.of(knots.get(1)));
        knots.get(1).setSuccessor(List.of(knots.get(2)));
        knots.get(2).setSuccessor(List.of(knots.get(3), knots.get(4), knots.get(5)));
        knots.get(3).setSuccessor(List.of(knots.get(6)));
        knots.get(4).setSuccessor(List.of(knots.get(7)));
        knots.get(5).setSuccessor(List.of(knots.get(8)));
        knots.get(6).setSuccessor(List.of(knots.get(9)));
        knots.get(7).setSuccessor(List.of(knots.get(9)));
        knots.get(8).setSuccessor(List.of(knots.get(9)));

        return knots;
    }

    private List<Knot> knotsResult() {
        List<Knot> result = new ArrayList<>();

        result.add(new Knot(1, "Start", 3, null, null, 0, 3));                                      // index 0
        result.add(new Knot(2, "Second", 1, List.of(result.get(0)), null, 3, 4));                              // index 1
        result.add(new Knot(3, "Third", 4, List.of(result.get(1)), null, 4, 8));                               // index 2
        result.add(new Knot(4, "After Third One", 2, List.of(result.get(2)), null, 8, 10));                    // index 3
        result.add(new Knot(5, "After Third Two", 1, List.of(result.get(2)), null, 8, 9));                     // index 4
        result.add(new Knot(6, "After Third Three", 3, List.of(result.get(2)), null, 8, 11));                  // index 5
        result.add(new Knot(7, "After Third One - Test", 3, List.of(result.get(3)), null, 10,13));             // index 6
        result.add(new Knot(8, "After Third Two - Test", 4, List.of(result.get(4)), null,9,13));               // index 7
        result.add(new Knot(9, "After Third Three - Test", 5, List.of(result.get(5)), null,11,16));            // index 8
        result.add(new Knot(10, "End", 1, List.of(result.get(6), result.get(7), result.get(8)), null,16,17));  // index 9

        result.get(0).setSuccessor(List.of(result.get(1)));
        result.get(1).setSuccessor(List.of(result.get(2)));
        result.get(2).setSuccessor(List.of(result.get(3), result.get(4), result.get(5)));
        result.get(3).setSuccessor(List.of(result.get(6)));
        result.get(4).setSuccessor(List.of(result.get(7)));
        result.get(5).setSuccessor(List.of(result.get(8)));
        result.get(6).setSuccessor(List.of(result.get(9)));
        result.get(7).setSuccessor(List.of(result.get(9)));
        result.get(8).setSuccessor(List.of(result.get(9)));

        return result;
    }

    @Test
    void Test1() {
        List<Knot> knots = knots();

//        knots.get(0).calculateEarliestTime();
//        knots.get(1).calculateEarliestTime();
//        knots.get(2).calculateEarliestTime();

        assertEquals(0, knots.get(0).getEarliestStart());
        assertEquals(3, knots.get(0).getEarliestEnd());
        assertEquals(3, knots.get(1).getEarliestStart());
        assertEquals(4, knots.get(1).getEarliestEnd());
        assertEquals(4, knots.get(2).getEarliestStart());
        assertEquals(8, knots.get(2).getEarliestEnd());
        assertEquals(8, knots.get(3).getEarliestStart());
        assertEquals(10, knots.get(3).getEarliestEnd());

    }
}