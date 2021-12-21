package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateCriticalPathTest {

    private List<Knot> knots() {
        List<Knot> knots = new ArrayList<>();

        knots.add(new Knot(1, "Start", 3, null));                                       // index 0
        knots.add(new Knot(2, "Second", 1, List.of(knots.get(0))));                                // index 1
        knots.add(new Knot(3, "Third", 4, List.of(knots.get(1))));                                 // index 2
        knots.add(new Knot(4, "After Third One", 2, List.of(knots.get(2))));                    // index 3
        knots.add(new Knot(5, "After Third Two", 1, List.of(knots.get(2))));                     // index 4
        knots.add(new Knot(6, "After Third Three", 3, List.of(knots.get(2))));                   // index 5
        knots.add(new Knot(7, "After Third One - Test", 3, List.of(knots.get(3))));            // index 6
        knots.add(new Knot(8, "After Third Two - Test", 4, List.of(knots.get(4))));             // index 7
        knots.add(new Knot(9, "After Third Three - Test", 5, List.of(knots.get(5))));          // index 8
        knots.add(new Knot(10, "End", 1, List.of(knots.get(6), knots.get(7), knots.get(8))));  // index 9

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

    private List<Knot> knotList() {
        List<Knot> knotList = new ArrayList<>();

        knotList.add(new Knot(1, "1", 5, null));
        knotList.add(new Knot(2, "2", 9, List.of(knotList.get(0))));
        knotList.add(new Knot(3, "3", 1, List.of(knotList.get(1))));
        knotList.add(new Knot(4, "4", 1, null));
        knotList.add(new Knot(5, "5", 2, List.of(knotList.get(3))));
        knotList.add(new Knot(6, "6", 4, List.of(knotList.get(3))));
        knotList.add(new Knot(7, "7", 1, List.of(knotList.get(4), (knotList.get(5)))));
        knotList.add(new Knot(8, "8", 15, List.of(knotList.get(6))));
        knotList.add(new Knot(9, "9", 1, List.of(knotList.get(6))));
        knotList.add(new Knot(10, "10", 2, List.of(knotList.get(2), knotList.get(7), knotList.get(8))));

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
    void CriticalPathKnotTest() {
        // 1, 2, 3, 6, 9, 10

        assertEquals(6, Knot.getCriticalPath(knots()).size());

        assertAll(() -> assertEquals(1, Knot.getCriticalPath(knots()).get(0).getOperationNumber()),
                () -> assertEquals(2, Knot.getCriticalPath(knots()).get(1).getOperationNumber()),
                () -> assertEquals(3, Knot.getCriticalPath(knots()).get(2).getOperationNumber()),
                () -> assertEquals(6, Knot.getCriticalPath(knots()).get(3).getOperationNumber()),
                () -> assertEquals(9, Knot.getCriticalPath(knots()).get(4).getOperationNumber()),
                () -> assertEquals(10, Knot.getCriticalPath(knots()).get(5).getOperationNumber()));
    }

    @Test
    void CriticalPathKnotListTest() {
        // 4, 6, 7, 8, 10 -> Critical Path

        assertEquals(5, Knot.getCriticalPath(knotList()).size());

        assertAll(() -> assertEquals(4, Knot.getCriticalPath(knotList()).get(0).getOperationNumber()),
                () -> assertEquals(6, Knot.getCriticalPath(knotList()).get(1).getOperationNumber()),
                () -> assertEquals(7, Knot.getCriticalPath(knotList()).get(2).getOperationNumber()),
                () -> assertEquals(8, Knot.getCriticalPath(knotList()).get(3).getOperationNumber()),
                () -> assertEquals(10, Knot.getCriticalPath(knotList()).get(4).getOperationNumber()));
    }
}
