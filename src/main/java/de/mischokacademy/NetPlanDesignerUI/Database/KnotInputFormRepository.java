package de.mischokacademy.NetPlanDesignerUI.Database;

import de.mischokacademy.NetPlanDesignerUI.Domain.KnotInputForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnotInputFormRepository extends JpaRepository<KnotInputForm, Long> {



}
