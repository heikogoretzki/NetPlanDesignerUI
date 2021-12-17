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

    @Test
    void Test1() {
        List<Knot> knots = knots();

        assertEquals(0, knots.get(0).getEarliestStart());
        assertEquals(3, knots.get(0).getEarliestEnd());

        assertEquals(3, knots.get(1).getEarliestStart());
        assertEquals(4, knots.get(1).getEarliestEnd());

        assertEquals(4, knots.get(2).getEarliestStart());
        assertEquals(8, knots.get(2).getEarliestEnd());

        assertEquals(8, knots.get(3).getEarliestStart());
        assertEquals(10, knots.get(3).getEarliestEnd());

        assertEquals(8, knots.get(4).getEarliestStart());
        assertEquals(9, knots.get(4).getEarliestEnd());

        assertEquals(8, knots.get(5).getEarliestStart());
        assertEquals(11, knots.get(5).getEarliestEnd());

        assertEquals(10, knots.get(6).getEarliestStart());
        assertEquals(13, knots.get(6).getEarliestEnd());

        assertEquals(9, knots.get(7).getEarliestStart());
        assertEquals(13, knots.get(7).getEarliestEnd());

        assertEquals(11, knots.get(8).getEarliestStart());
        assertEquals(16, knots.get(8).getEarliestEnd());

        assertEquals(16, knots.get(9).getEarliestStart());
        assertEquals(17, knots.get(9).getEarliestEnd());
    }

    private List<Knot> knotList() {
        List<Knot> knotList = new ArrayList<>();

        knotList.add(new Knot(1, "1", 5, null, null));
        knotList.add(new Knot(2, "2", 9, List.of(knotList.get(0)), null));
        knotList.add(new Knot(3, "3", 1, List.of(knotList.get(1)), null));
        knotList.add(new Knot(4, "4", 1, null, null));
        knotList.add(new Knot(5, "5", 2, List.of(knotList.get(3)), null));
        knotList.add(new Knot(6, "6", 4, List.of(knotList.get(3)), null));
        knotList.add(new Knot(7, "7", 1, List.of(knotList.get(4), (knotList.get(5))), null));
        knotList.add(new Knot(8, "8", 15, List.of(knotList.get(6)), null));
        knotList.add(new Knot(9, "9", 1, List.of(knotList.get(6)), null));
        knotList.add(new Knot(10, "10", 2, List.of(knotList.get(2), knotList.get(7), knotList.get(8)), null));

        knotList.get(0).setSuccessor(List.of(knotList.get(1)));
        knotList.get(1).setSuccessor(List.of(knotList.get(2)));
        knotList.get(2).setSuccessor(List.of(knotList.get(9)));
        knotList.get(3).setSuccessor(List.of(knotList.get(4), knotList.get(5)));
        knotList.get(4).setSuccessor(List.of(knotList.get(6)));
        knotList.get(5).setSuccessor(List.of(knotList.get(6)));
        knotList.get(6).setSuccessor(List.of(knotList.get(7), knotList.get(8)));
        knotList.get(7).setSuccessor(List.of(knotList.get(9)));
        knotList.get(8).setSuccessor(List.of(knotList.get(9)));

        return knotList;
    }

    @Test
    void Test2() {
        List<Knot> knotList = knotList();

        assertEquals(0, knotList.get(0).getEarliestStart());
        assertEquals(5, knotList.get(0).getEarliestEnd());

        assertEquals(5, knotList.get(1).getEarliestStart());
        assertEquals(14, knotList.get(1).getEarliestEnd());

        assertEquals(14, knotList.get(2).getEarliestStart());
        assertEquals(15, knotList.get(2).getEarliestEnd());

        assertEquals(0, knotList.get(3).getEarliestStart());
        assertEquals(1, knotList.get(3).getEarliestEnd());

        assertEquals(1, knotList.get(4).getEarliestStart());
        assertEquals(3, knotList.get(4).getEarliestEnd());

        assertEquals(1, knotList.get(5).getEarliestStart());
        assertEquals(5, knotList.get(5).getEarliestEnd());

        assertEquals(5, knotList.get(6).getEarliestStart());
        assertEquals(6, knotList.get(6).getEarliestEnd());

        assertEquals(6, knotList.get(7).getEarliestStart());
        assertEquals(21, knotList.get(7).getEarliestEnd());

        assertEquals(6, knotList.get(8).getEarliestStart());
        assertEquals(7, knotList.get(8).getEarliestEnd());

        assertEquals(21, knotList.get(9).getEarliestStart());
        assertEquals(23, knotList.get(9).getEarliestEnd());

    }
}