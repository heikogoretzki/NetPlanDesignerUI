package de.mischokacademy.NetPlanDesignerUI.Controller;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import de.mischokacademy.NetPlanDesignerUI.Domain.KnotInputForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NetPlanController {
    private static List<KnotInputForm> knotInputFormList = new ArrayList<>();
    private static List<Knot> knots = new ArrayList<>();

    @GetMapping("/")
    public String getStartPage() {

        return "home";
    }

    @GetMapping("input")
    public String getKnotFormInput(Model model) {

        return "knotInputForm";
    }

    @PostMapping("input")
    public String saveKnotFormInput(@Valid KnotInputForm knotInputForm, BindingResult bindingResult, Model model) {

        return "knotInputForm";
    }

    @GetMapping("table")
    public String getNetPlanOutputTable(Model model) {

        return "outputTable";
    }

    public List<Knot> convertKnotInputFormListToKnotList(List<KnotInputForm> knotInputFormList) {
        List<Knot> result = new ArrayList<>();
        for (KnotInputForm knotInputForm : knotInputFormList) {
            result.add(new Knot(knotInputForm.getOperationNumber(), knotInputForm.getOperationDescription(), knotInputForm.getDurationInMinutes()));
            if (knotInputForm.getPredecessorOneListIndex() != null) {
                result.get(result.size()-1).getPredecessor().add(result.get(knotInputForm.getPredecessorOneListIndex()));
                result.get(knotInputForm.getPredecessorOneListIndex()).getSuccessor().add(result.get(knotInputForm.getOperationNumber()-1));
            }
            if (knotInputForm.getPredecessorTwoListIndex() != null) {
                result.get(result.size()-1).getPredecessor().add(result.get(knotInputForm.getPredecessorTwoListIndex()));
                result.get(knotInputForm.getPredecessorTwoListIndex()).getSuccessor().add(result.get(knotInputForm.getOperationNumber()-1));
            }
            if (knotInputForm.getPredecessorThreeListIndex() != null) {
                result.get(result.size()-1).getPredecessor().add(result.get(knotInputForm.getPredecessorThreeListIndex()));
                result.get(knotInputForm.getPredecessorThreeListIndex()).getSuccessor().add(result.get(knotInputForm.getOperationNumber()-1));
            }
        }
        return result;
    }

    private void calculateNetPlanResults(List<Knot> knots) {

    }

    private String validateNotTwoEnds(BindingResult bindingResult, List<Knot> knots) {

        return null;
    }

}
