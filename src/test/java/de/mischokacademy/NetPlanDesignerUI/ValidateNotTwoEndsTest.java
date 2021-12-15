package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ValidateNotTwoEndsTest {

    @Test
    void Test1() {
        List<Knot> knotList = new ArrayList<>();

        knotList.add(new Knot(1, "One", 1, null, null));                // 0
        knotList.add(new Knot(2, "Two", 2, List.of(knotList.get(0)), null));       // 1
        knotList.add(new Knot(3, "Three", 2, List.of(knotList.get(1)), null));     // 2
        knotList.add(new Knot(4, "Four", 1, List.of(knotList.get(2)), null));      // 3
        knotList.add(new Knot(5, "Five", 3, List.of(knotList.get(3)), null));      // 4
        knotList.add(new Knot(6, "Six", 1, List.of(knotList.get(3)), null));       // 5

        knotList.get(0).setSuccessor(List.of(knotList.get(1)));
        knotList.get(1).setSuccessor(List.of(knotList.get(2)));
        knotList.get(2).setSuccessor(List.of(knotList.get(3)));
        knotList.get(3).setSuccessor(List.of(knotList.get(4), knotList.get(5)));
        knotList.get(4).setSuccessor(List.of(knotList.get(0)));
        knotList.get(5).setSuccessor(List.of(knotList.get(0)));



    }

    @Test
    void Test2() {



    }
}
