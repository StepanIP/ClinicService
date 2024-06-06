package com.example.clinicservice.service;

import com.example.clinicservice.model.Procedure;

import java.util.List;

public interface ProcedureService {
    Procedure create(Procedure procedure);

    Procedure readById(Long procedureId);

    Procedure update(Procedure procedure);

    void delete(Procedure procedure);

    List<Procedure> getAll();

    List<Integer> getAllOffices();

    List<Procedure> sortProceduresByOffice(List<Procedure> procedures, long office);

    List<Procedure> getByProcedureName(String procedureName);

    List<Procedure> getFreeProcedures(List<Procedure> procedures);

    List<Procedure> searchProcedures(String name, String office);
}
