package de.mischokacademy.NetPlanDesignerUI.Domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "KnotInputForm")
@Table(name = "knotinputform")
public class KnotInputForm {

    @Id
    @SequenceGenerator(
            name = "knotinputform_sequence",
            sequenceName = "knotinputform_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "knotinputform_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "operation_number"
    )
    private int operationNumber;

    @NotBlank()
    @Size(min = 1, max = 36)
    @Column(
            name = "operation_description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String operationDescription;

    @Min(value = 1)
    @Max(value = 20160) // 2 Wochen
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;

    @Column(name = "predecessor_one_list_index")
    private Integer predecessorOneListIndex;

    @Column(name = "predecessor_two_list_index")
    private Integer predecessorTwoListIndex;

    @Column(name = "predecessor_three_list_index")
    private Integer predecessorThreeListIndex;

    public KnotInputForm() {
    }

    public KnotInputForm(int operationNumber, String operationDescription, int durationInMinutes, Integer predecessorOneListIndex, Integer predecessorTwoListIndex, Integer predecessorThreeListIndex) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessorOneListIndex = predecessorOneListIndex;
        this.predecessorTwoListIndex = predecessorTwoListIndex;
        this.predecessorThreeListIndex = predecessorThreeListIndex;
    }

    public KnotInputForm(int operationNumber) {
        this.operationNumber = operationNumber;
    }

    public int getOperationNumber() {
        return operationNumber;
    }

    @SuppressWarnings("unused")
    public void setOperationNumber(int operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    @SuppressWarnings("unused")
    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    @SuppressWarnings("unused")
    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getPredecessorOneListIndex() {
        return predecessorOneListIndex;
    }

    @SuppressWarnings("unused")
    public void setPredecessorOneListIndex(Integer predecessorOneListIndex) {
        this.predecessorOneListIndex = predecessorOneListIndex;
    }

    public Integer getPredecessorTwoListIndex() {
        return predecessorTwoListIndex;
    }

    @SuppressWarnings("unused")
    public void setPredecessorTwoListIndex(Integer predecessorTwoListIndex) {
        this.predecessorTwoListIndex = predecessorTwoListIndex;
    }

    public Integer getPredecessorThreeListIndex() {
        return predecessorThreeListIndex;
    }

    @SuppressWarnings("unused")
    public void setPredecessorThreeListIndex(Integer predecessorThreeListIndex) {
        this.predecessorThreeListIndex = predecessorThreeListIndex;
    }

    @Override
    public String toString() {
        return "KnotInputForm{" +
                "operationNumber=" + operationNumber +
                ", operationDescription='" + operationDescription + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", predecessorOneListIndex=" + predecessorOneListIndex +
                ", predecessorTwoListIndex=" + predecessorTwoListIndex +
                ", predecessorThreeListIndex=" + predecessorThreeListIndex +
                '}';
    }

    public Long getId() {
        return id;
    }
}
