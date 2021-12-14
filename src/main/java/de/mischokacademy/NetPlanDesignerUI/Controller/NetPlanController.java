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

    private List<Knot> convertKnotInputFormListToKnotList(List<KnotInputForm> knotInputFormList) {

        return null;
    }

    private void calculateNetPlanResults(List<Knot> knots) {

    }

    private String validateNotTwoEnds(BindingResult bindingResult, List<Knot> knots) {

        return null;
    }

    private void validate(BindingResult bindingResult, KnotInputForm knotInputForm) {

    }
}
