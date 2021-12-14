package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KnotTest {
    private List<Knot> knots = new ArrayList<>();
    private List<Knot> result = new ArrayList<>();

    // knotList.get(0).setSuccessor(List.of(knotList.get(1),knotList.get(2)));

    @Test
    void testCalculateEarliestTime() {
        knots.add(new Knot(1, "Meetup", 3, null, List.of(knots.get(0))));
//        knots.add(new Knot(2, "Check Personal", 1, 1, 3));
//        knots.add(new Knot(3, "Make a Plan", 4, 2, 4-6));
//        knots.add(new Knot(4, "Doing 1", 2, 3, 7));
//        knots.add(new Knot(5, "Doing 2", 2, 3, 8));
//        knots.add(new Knot(6, "Doing 3", 2, 3, 9));
//        knots.add(new Knot(7, "Test 1", 3, 4, 10));
//        knots.add(new Knot(8, "Test 2", 3, 5, 10));
//        knots.add(new Knot(9, "Test 3", 3, 6, 10));
//        knots.add(new Knot(10, "End", 1, 7-9, null));

        knots.get(0).setSuccessor(List.of(knots.get(1), knots.get(2)));

    }
}
