package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculateEarliestTimeTest {
    private List<Knot> knots = new ArrayList<>();
    private List<Knot> result = new ArrayList<>();

    @Test
    void Test1() {
        knots.add(new Knot(1, "Meetup", 3, null, null));                                     // index 0
        knots.add(new Knot(2, "Check Personal", 1, List.of(knots.get(0)), null));                       // index 1
        knots.add(new Knot(3, "Make a Plan", 4, List.of(knots.get(1)), null));                          // index 2
        knots.add(new Knot(4, "Doing 1", 2, List.of(knots.get(2)), null));                              // index 3
        knots.add(new Knot(5, "Doing 2", 2, List.of(knots.get(2)), null));                              // index 4
        knots.add(new Knot(6, "Doing 3", 2, List.of(knots.get(2)), null));                              // index 5
        knots.add(new Knot(7, "Test 1", 3, List.of(knots.get(3)), null));                               // index 6
        knots.add(new Knot(8, "Test 2", 3, List.of(knots.get(4)), null));                               // index 7
        knots.add(new Knot(9, "Test 3", 3, List.of(knots.get(5)), null));                               // index 8
        knots.add(new Knot(10, "End", 1, List.of(knots.get(6), knots.get(7), knots.get(8)), null));     // index 9

        // Index 0                               operationNumber 1
        knots.get(0).setPredecessor(List.of(knots.get(1)));
        knots.get(1).setPredecessor(List.of(knots.get(2)));
        knots.get(2).setPredecessor(List.of(knots.get(3)));
        knots.get(3).setPredecessor(List.of(knots.get(3)));
        knots.get(4).setPredecessor(List.of(knots.get(3)));
        knots.get(5).setPredecessor(List.of(knots.get(4)));
        knots.get(6).setPredecessor(List.of(knots.get(5)));
        knots.get(7).setPredecessor(List.of(knots.get(6)));
        knots.get(8).setPredecessor(List.of(knots.get(7), knots.get(8), knots.get(9)));

    }
}