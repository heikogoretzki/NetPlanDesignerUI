package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Controller.NetPlanController;
import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ValidateNotTwoEndsTest {

    private List<Knot> knots() {
        List<Knot> knots = new ArrayList<>();

        knots.add(new Knot(1, "One", 1, null, null));             // 0
        knots.add(new Knot(2, "Two", 1, List.of(knots.get(0)), null));       // 1
        knots.add(new Knot(3, "Three", 2, List.of(knots.get(1)), null));     // 2
        knots.add(new Knot(4, "Four", 1, List.of(knots.get(2)), null));      // 3
        knots.add(new Knot(5, "Five", 1, List.of(knots.get(3)), null));      // 4
        knots.add(new Knot(6, "Six", 1, List.of(knots.get(3)), null));       // 5

        knots.get(0).setSuccessor(List.of(knots.get(1)));
        knots.get(0).setSuccessor(List.of(knots.get(2)));
        knots.get(1).setSuccessor(List.of(knots.get(3)));
        knots.get(1).setSuccessor(List.of(knots.get(3)));
        knots.get(3).setSuccessor(List.of(knots.get(4)));
        knots.get(3).setSuccessor(List.of(knots.get(5)));

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
    void Test1() {
        assertAll(() -> assertEquals(false, NetPlanController.validateNotTwoEnds(knots())),
                () -> assertEquals(true, NetPlanController.validateNotTwoEnds(knotList())));
    }

}
