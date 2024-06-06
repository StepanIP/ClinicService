package com.example.clinicservice.controller;

import com.example.clinicservice.model.Procedure;
import com.example.clinicservice.service.AccountService;
import com.example.clinicservice.service.ProcedureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/Clinic-name/procedure")
public class ProcedureController {

    final AccountService accountService;
    final ProcedureService procedureService;

    public ProcedureController(AccountService accountService, ProcedureService procedureService) {
        this.accountService = accountService;
        this.procedureService = procedureService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Procedure>> allProcedures() {
        List<Procedure> list = procedureService.getAll();

        Map<String, List<Procedure>> groupedProcedures = list.stream()
                .collect(Collectors.groupingBy(
                        procedure -> procedure.getProcedureName() + procedure.getProcedureDate(),
                        Collectors.toList()
                ));

        List<Procedure> uniqueProcedures = new ArrayList<>();

        groupedProcedures.forEach((key, procedures) -> {
            LocalTime minStartTime = procedures.stream().map(Procedure::getStartTime).min(Comparator.naturalOrder()).orElse(null);
            LocalTime maxEndTime = procedures.stream().map(Procedure::getEndTime).max(Comparator.naturalOrder()).orElse(null);

            uniqueProcedures.add(new Procedure(
                    procedures.get(0).getId(),
                    procedures.get(0).getProcedureName(),
                    procedures.get(0).getOffice(),
                    procedures.get(0).getDoctor(),
                    procedures.get(0).getProcedureDate(),
                    minStartTime,
                    maxEndTime
            ));
        });

        return new ResponseEntity<>(uniqueProcedures, HttpStatus.OK);
    }

    @PostMapping("/free")
    public ResponseEntity<List<Procedure>> getAvailableProcedure(@RequestParam("procedureName") String procedureName) {
        return new ResponseEntity<>(procedureService.getFreeProcedures(
                procedureService.getByProcedureName(procedureName)), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Procedure>> getProcedureByName(@RequestParam("procedureName") String procedureName) {
        return new ResponseEntity<>(procedureService.getByProcedureName(
                (procedureName)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Procedure>> searchProcedures(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "office", required = false) String office) {

        List<Procedure> procedures = procedureService.searchProcedures(name, office);
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }

}

