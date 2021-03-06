package de.mischokacademy.NetPlanDesignerUI.Controller;

import de.mischokacademy.NetPlanDesignerUI.Database.KnotInputFormRepository;
import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import de.mischokacademy.NetPlanDesignerUI.Domain.KnotInputForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class NetPlanController {
    private static final List<KnotInputForm> knotInputFormList = new ArrayList<>();

    @Autowired
    KnotInputFormRepository knotInputFormRepository;

    @GetMapping("/")
    public String getStartPage() {
        return "home";
    }

    @GetMapping("about")
    public String getAboutPage() {

        return "about";
    }

    @GetMapping("input")
    public String getKnotFormInput(Model model) {
        Objects.requireNonNull(model);

        model.addAttribute("knotInputForm", new KnotInputForm(knotInputFormList.size() + 1));
        model.addAttribute("knotInputFormList", knotInputFormRepository.findAll());

        return "knotInputForm";
    }

    @PostMapping("input")
    public String saveKnotFormInput(@Valid KnotInputForm knotInputForm, BindingResult bindingResult, Model model) {
        Objects.requireNonNull(bindingResult);
        Objects.requireNonNull(knotInputForm);
        Objects.requireNonNull(model);

        knotInputFormList.add(knotInputForm);

        knotInputFormRepository.save(knotInputFormList.get(knotInputFormList.size() - 1));

        model.addAttribute("knotInputForm", new KnotInputForm(knotInputFormList.size() + 1));
        model.addAttribute("knotInputFormList", knotInputFormRepository.findAll());

        return "knotInputForm";
    }

    @GetMapping("table")
    public String getNetPlanOutputTable(Model model) {
        Objects.requireNonNull(model);

        List<Knot> knots = convertKnotInputFormListToKnotList(knotInputFormRepository.findAll());

        model.addAttribute("knotInputForm", new KnotInputForm(knotInputFormList.size() + 1));
        model.addAttribute("knotInputFormList", knotInputFormRepository.findAll());
        model.addAttribute("knots", knots);

        return "outputTable";
    }

    public List<Knot> convertKnotInputFormListToKnotList(List<KnotInputForm> knotInputFormList) {
        Objects.requireNonNull(knotInputFormList);

        List<Knot> result = new ArrayList<>();

        for (KnotInputForm inputForm : knotInputFormList) {
            Knot tempKnot = new Knot(inputForm.getOperationNumber(), inputForm.getOperationDescription(), inputForm.getDurationInMinutes());
            result.add(tempKnot);

            Integer predOneIndex = inputForm.getPredecessorOneListIndex();
            Integer predTwoIndex = inputForm.getPredecessorTwoListIndex();
            Integer predThreeIndex = inputForm.getPredecessorThreeListIndex();

            if (predOneIndex != null) {
                tempKnot.getPredecessor().add(result.get(predOneIndex));
                result.get(predOneIndex).getSuccessor().add(tempKnot);
            }

            if (predTwoIndex != null) {
                tempKnot.getPredecessor().add(result.get(predTwoIndex));
                result.get(predTwoIndex).getSuccessor().add(tempKnot);
            }

            if (predThreeIndex != null) {
                tempKnot.getPredecessor().add(result.get(predThreeIndex));
                result.get(predThreeIndex).getSuccessor().add(tempKnot);
            }
        }

        return result;
    }

    @org.jetbrains.annotations.NotNull
    public static Boolean validateNotTwoEnds(List<Knot> knots) {
        Objects.requireNonNull(knots);

        List<Knot> result = new ArrayList<>();

        for (Knot knot : knots) {
            if (knot.getSuccessor().isEmpty()) {
                result.add(knot);
            }
        }

        return result.size() <= 1;
    }

}
