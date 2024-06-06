package com.example.clinicservice.repository;

import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<Procedure> findAllByOrderByOfficeAsc();

    List<Procedure> findAllByProcedureNameOrderByProcedureDateAscStartTimeAsc(String name);

    List<Procedure> findAllByOfficeOrderByProcedureDateAscStartTimeAsc(int office);

    List<Procedure> findAllByProcedureNameAndOfficeOrderByProcedureDateAscStartTimeAsc(String procedureName, int office);
}