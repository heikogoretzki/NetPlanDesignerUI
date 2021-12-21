package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CalculateBufferTest {

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
    void FreeBufferKnotsTest() {
        List<Knot> knots = knots();

        assertAll(() -> assertEquals(0, knots.get(0).getFreeBuffer()),
                () -> assertEquals(0, knots.get(1).getFreeBuffer()),
                () -> assertEquals(0, knots.get(2).getFreeBuffer()),
                () -> assertEquals(0, knots.get(3).getFreeBuffer()),
                () -> assertEquals(0, knots.get(4).getFreeBuffer()),
                () -> assertEquals(0, knots.get(5).getFreeBuffer()),
                () -> assertEquals(3, knots.get(6).getFreeBuffer()),
                () -> assertEquals(3, knots.get(7).getFreeBuffer()),
                () -> assertEquals(0, knots.get(8).getFreeBuffer()),
                () -> assertEquals(0, knots.get(9).getFreeBuffer()));
    }

    @Test
    void TotalBufferKnotsTest() {
        List<Knot> knots = knots();

        assertAll(() -> assertEquals(0, knots.get(0).getTotalBuffer()),
                () -> assertEquals(0, knots.get(1).getTotalBuffer()),
                () -> assertEquals(0, knots.get(2).getTotalBuffer()),
                () -> assertEquals(3, knots.get(3).getTotalBuffer()),
                () -> assertEquals(3, knots.get(4).getTotalBuffer()),
                () -> assertEquals(0, knots.get(5).getTotalBuffer()),
                () -> assertEquals(3, knots.get(6).getTotalBuffer()),
                () -> assertEquals(3, knots.get(7).getTotalBuffer()),
                () -> assertEquals(0, knots.get(8).getTotalBuffer()),
                () -> assertEquals(0, knots.get(9).getTotalBuffer()));
    }

    @Test
    void FreeBufferKnotListTest() {
        List<Knot> knotList = knotList();

        assertAll(() -> assertEquals(0, knotList.get(0).getFreeBuffer()),
                () -> assertEquals(0, knotList.get(1).getFreeBuffer()),
                () -> assertEquals(6, knotList.get(2).getFreeBuffer()),
                () -> assertEquals(0, knotList.get(3).getFreeBuffer()),
                () -> assertEquals(2, knotList.get(4).getFreeBuffer()),
                () -> assertEquals(0, knotList.get(5).getFreeBuffer()),
                () -> assertEquals(0, knotList.get(6).getFreeBuffer()),
                () -> assertEquals(0, knotList.get(7).getFreeBuffer()),
                () -> assertEquals(14, knotList.get(8).getFreeBuffer()),
                () -> assertEquals(0, knotList.get(9).getFreeBuffer()));
    }

    @Test
    void TotalBufferKnotListTest() {
        List<Knot> knotList = knotList();

        assertAll(() -> assertEquals(6, knotList.get(0).getTotalBuffer()),
                () -> assertEquals(6, knotList.get(1).getTotalBuffer()),
                () -> assertEquals(6, knotList.get(2).getTotalBuffer()),
                () -> assertEquals(0, knotList.get(3).getTotalBuffer()),
                () -> assertEquals(2, knotList.get(4).getTotalBuffer()),
                () -> assertEquals(0, knotList.get(5).getTotalBuffer()),
                () -> assertEquals(0, knotList.get(6).getTotalBuffer()),
                () -> assertEquals(0, knotList.get(7).getTotalBuffer()),
                () -> assertEquals(14, knotList.get(8).getTotalBuffer()),
                () -> assertEquals(0, knotList.get(9).getTotalBuffer()));
    }
}
