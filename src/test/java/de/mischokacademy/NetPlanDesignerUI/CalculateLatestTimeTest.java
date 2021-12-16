package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CalculateLatestTimeTest {

    private List<Knot> knots() {
        List<Knot> knots = new ArrayList<>();

        knots.add(new Knot(1, "Start", 3, null, null,0,3));                                   // index 0
        knots.add(new Knot(2, "Second", 1, List.of(knots.get(0)), null,3,4));                            // index 1
        knots.add(new Knot(3, "Third", 4, List.of(knots.get(1)), null,4,8));                             // index 2
        knots.add(new Knot(4, "After Third One", 2, List.of(knots.get(2)), null,8,10));                   // index 3
        knots.add(new Knot(5, "After Third Two", 1, List.of(knots.get(2)), null,8,9));                   // index 4
        knots.add(new Knot(6, "After Third Three", 3, List.of(knots.get(2)), null,8,11));                 // index 5
        knots.add(new Knot(7, "After Third One - Test", 3, List.of(knots.get(3)), null,10,13));            // index 6
        knots.add(new Knot(8, "After Third Two - Test", 4, List.of(knots.get(4)), null,9,13));            // index 7
        knots.add(new Knot(9, "After Third Three - Test", 5, List.of(knots.get(5)), null,11,16));          // index 8
        knots.add(new Knot(10, "End", 1, List.of(knots.get(6), knots.get(7), knots.get(8)), null,16,17));  // index 9

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

    @Test
    void Test1() {
        List<Knot> knots = knots();

        assertEquals(0, knots.get(0).getLatestStart());
        assertEquals(3, knots.get(0).getLatestEnd());
        assertEquals(3, knots.get(1).getLatestStart());
        assertEquals(4, knots.get(1).getLatestEnd());
        assertEquals(4, knots.get(2).getLatestStart());
        assertEquals(8, knots.get(2).getLatestEnd());
        assertEquals(8, knots.get(3).getLatestStart());
        assertEquals(10, knots.get(3).getLatestEnd());
        assertEquals(8, knots.get(4).getLatestStart());
        assertEquals(9, knots.get(4).getLatestEnd());
        assertEquals(8, knots.get(5).getLatestStart());
        assertEquals(11, knots.get(5).getLatestEnd());
        assertEquals(10, knots.get(6).getLatestStart());
        assertEquals(13, knots.get(6).getLatestEnd());
        assertEquals(9, knots.get(7).getLatestStart());
        assertEquals(13, knots.get(7).getLatestEnd());
        assertEquals(11, knots.get(8).getLatestStart());
        assertEquals(16, knots.get(8).getLatestEnd());
        assertEquals(16, knots.get(9).getLatestStart());
        assertEquals(17, knots.get(9).getLatestEnd());
    }
}
