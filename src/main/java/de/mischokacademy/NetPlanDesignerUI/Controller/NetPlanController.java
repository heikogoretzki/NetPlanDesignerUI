package de.mischokacademy.NetPlanDesignerUI.Controller;

import de.mischokacademy.NetPlanDesignerUI.Domain.Knot;
import de.mischokacademy.NetPlanDesignerUI.Domain.KnotInputForm;
import de.mischokacademy.NetPlanDesignerUI.Domain.KnotInputFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class NetPlanController {
    private static final List<KnotInputForm> knotInputFormList = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    KnotInputFormRepository knotInputFormRepository;

    @GetMapping("/")
    public String getStartPage() {

//        String filterName = "Julius";
//        String queryTemplate = "SELECT * FROM test WHERE name = '%s';";
//        String query = String.format(queryTemplate, filterName);
//        System.out.println(query);
//        jdbcTemplate.execute(query);
//
//        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM test");
//        while (sqlRowSet.next()) {
//            System.out.println(sqlRowSet.getInt("id") + " -> " + sqlRowSet.getString("name"));
//        }

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
        model.addAttribute("knotInputFormList", knotInputFormList);

        return "knotInputForm";
    }

    @PostMapping("input")
    public String saveKnotFormInput(@Valid KnotInputForm knotInputForm, BindingResult bindingResult, Model model) {
        Objects.requireNonNull(bindingResult);
        Objects.requireNonNull(knotInputForm);
        Objects.requireNonNull(model);

        knotInputFormList.add(knotInputForm);


        knotInputFormRepository.save(knotInputFormList.get(knotInputFormList.size()));


        model.addAttribute("knotInputForm", new KnotInputForm(knotInputFormList.size() + 1));
        model.addAttribute("knotInputFormList", knotInputFormList);

        return "knotInputForm";
    }

    @GetMapping("table")
    public String getNetPlanOutputTable(Model model) {
        Objects.requireNonNull(model);

        List<Knot> knots = convertKnotInputFormListToKnotList(knotInputFormList);
        model.addAttribute("knotInputForm", new KnotInputForm());
        model.addAttribute("knotInputFormList", knotInputFormList);
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
