package de.mischokacademy.NetPlanDesignerUI;

import de.mischokacademy.NetPlanDesignerUI.Controller.NetPlanController;
import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import de.mischokacademy.NetPlanDesignerUI.Domain.KnotInputForm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ConvertKnotInputFormListToKnotListTest {

    @Test
    void Test1() {
        List<KnotInputForm> knotInputFormList = new ArrayList<>();

        knotInputFormList.add(new KnotInputForm(1, "Start", 3, null, null, null));                    // index 0
        knotInputFormList.add(new KnotInputForm(2, "Second", 1, 0, null, null));                      // index 1
        knotInputFormList.add(new KnotInputForm(3, "Third", 4, 0, null, null));                       // index 2
        knotInputFormList.add(new KnotInputForm(4, "After Third One", 2, 1, 2, null));                // index 3
        knotInputFormList.add(new KnotInputForm(5, "After Third Two", 2, 3, null, null));             // index 4
        knotInputFormList.add(new KnotInputForm(6, "After Third Three", 3, 3, null, null));           // index 5
        knotInputFormList.add(new KnotInputForm(7, "After Third One - Test", 3, 4, null, null));      // index 6
        knotInputFormList.add(new KnotInputForm(8, "After Third Two - Test", 4, 5, null, null));      // index 7
        knotInputFormList.add(new KnotInputForm(9, "After Third Three - Test", 5, 6, 7, null));       // index 8
        knotInputFormList.add(new KnotInputForm(10, "End", 1, 8, null, null));                        // index 9

        NetPlanController newController = new NetPlanController();
        List<Knot> convertedList = newController.convertKnotInputFormListToKnotList(knotInputFormList);

        assertEquals(1, convertedList.get(0).getOperationNumber());
        assertEquals("Start", convertedList.get(0).getOperationDescription());
        assertEquals(3, convertedList.get(0).getDurationInMinutes());
        assertEquals(0, convertedList.get(0).getPredecessor().size());
        assertEquals(2, convertedList.get(0).getSuccessor().size());
        assertEquals(convertedList.get(1), convertedList.get(0).getSuccessor().get(0)); // erweitern

        assertEquals(1, convertedList.get(1).getPredecessor().size());
        assertEquals(1, convertedList.get(2).getPredecessor().size());
        assertEquals(2, convertedList.get(3).getPredecessor().size());
        assertEquals(1, convertedList.get(4).getPredecessor().size());
        assertEquals(1, convertedList.get(5).getPredecessor().size());
        assertEquals(1, convertedList.get(6).getPredecessor().size());
        assertEquals(1, convertedList.get(7).getPredecessor().size());
        assertEquals(2, convertedList.get(8).getPredecessor().size());
        assertEquals(1, convertedList.get(9).getPredecessor().size());

        assertEquals(1, convertedList.get(1).getSuccessor().size());
        assertEquals(1, convertedList.get(2).getSuccessor().size());
        assertEquals(2, convertedList.get(3).getSuccessor().size());
        assertEquals(1, convertedList.get(4).getSuccessor().size());
        assertEquals(1, convertedList.get(5).getSuccessor().size());
        assertEquals(1, convertedList.get(6).getSuccessor().size());
        assertEquals(1, convertedList.get(7).getSuccessor().size());
        assertEquals(1, convertedList.get(8).getSuccessor().size());
        assertEquals(0, convertedList.get(9).getSuccessor().size());
    }

    @Test
    void Test2() {
        List<KnotInputForm> knotInputFormList = new ArrayList<>();

        knotInputFormList.add(new KnotInputForm(1, "Start", 3, null, null, null));                    // index 0
        knotInputFormList.add(new KnotInputForm(2, "Second", 1, 0, null, null));                      // index 1
        knotInputFormList.add(new KnotInputForm(3, "Third", 4, 1, null, null));                       // index 2
        knotInputFormList.add(new KnotInputForm(4, "After Third One", 2, 2, null, null));             // index 3
        knotInputFormList.add(new KnotInputForm(5, "After Third Two", 2, 2, null, null));             // index 4
        knotInputFormList.add(new KnotInputForm(6, "After Third Three", 3, 2, null, null));           // index 5
        knotInputFormList.add(new KnotInputForm(7, "After Third One - Test", 3, 3, 4, 5));            // index 6
        knotInputFormList.add(new KnotInputForm(8, "After Third Two - Test", 4, 6, null, null));      // index 7
        knotInputFormList.add(new KnotInputForm(9, "After Third Three - Test", 5, 7, null, null));    // index 8
        knotInputFormList.add(new KnotInputForm(10, "End", 1, 8, null, null));                        // index 9

        NetPlanController newController = new NetPlanController();
        List<Knot> convertedList = newController.convertKnotInputFormListToKnotList(knotInputFormList);

        assertEquals(1, convertedList.get(0).getOperationNumber());
        assertEquals("Start", convertedList.get(0).getOperationDescription());
        assertEquals(3, convertedList.get(0).getDurationInMinutes());
        assertEquals(0, convertedList.get(0).getPredecessor().size());
        assertEquals(1, convertedList.get(0).getSuccessor().size());
        assertEquals(3, convertedList.get(2).getSuccessor().size());
        assertEquals(convertedList.get(1), convertedList.get(0).getSuccessor().get(0));
        assertEquals(convertedList.get(2), convertedList.get(1).getSuccessor().get(0));
        assertEquals(convertedList.get(3), convertedList.get(2).getSuccessor().get(0));

        assertEquals(1, convertedList.get(1).getPredecessor().size());
        assertEquals(1, convertedList.get(2).getPredecessor().size());
        assertEquals(1, convertedList.get(3).getPredecessor().size());
        assertEquals(1, convertedList.get(4).getPredecessor().size());
        assertEquals(1, convertedList.get(5).getPredecessor().size());
        assertEquals(3, convertedList.get(6).getPredecessor().size());
        assertEquals(1, convertedList.get(7).getPredecessor().size());
        assertEquals(1, convertedList.get(8).getPredecessor().size());
        assertEquals(1, convertedList.get(9).getPredecessor().size());

        assertEquals(1, convertedList.get(1).getSuccessor().size());
        assertEquals(3, convertedList.get(2).getSuccessor().size());
        assertEquals(1, convertedList.get(3).getSuccessor().size());
        assertEquals(1, convertedList.get(4).getSuccessor().size());
        assertEquals(1, convertedList.get(5).getSuccessor().size());
        assertEquals(1, convertedList.get(6).getSuccessor().size());
        assertEquals(1, convertedList.get(7).getSuccessor().size());
        assertEquals(1, convertedList.get(8).getSuccessor().size());
        assertEquals(0, convertedList.get(9).getSuccessor().size());
    }
}
