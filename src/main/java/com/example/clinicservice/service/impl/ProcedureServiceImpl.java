package com.example.clinicservice.service.impl;

import com.example.clinicservice.model.Procedure;
import com.example.clinicservice.repository.ProcedureRepository;
import com.example.clinicservice.service.AppointmentService;
import com.example.clinicservice.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;

    private final AppointmentService appointmentService;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, AppointmentService appointmentService) {
        this.procedureRepository = procedureRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public Procedure create(Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    @Override
    public Procedure readById(Long procedureId) {
        return procedureRepository.findById(procedureId).orElse(null);
    }

    @Override
    public Procedure update(Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    @Override
    public void delete(Procedure procedure) {
        procedureRepository.delete(procedure);
    }

    @Override
    public List<Procedure> getAll() {
        return procedureRepository.findAll();
    }

    @Override
    public List<Integer> getAllOffices() {
        return procedureRepository.findAllByOrderByOfficeAsc()
                .stream()
                .map(Procedure::getOffice)
                .toList();
    }

    @Override
    public List<Procedure> sortProceduresByOffice(List<Procedure> procedures, long office) {
        List<Procedure> officeProcedures = new ArrayList<>();
        for(Procedure p : procedures){
            if(p.getOffice()==office){
                officeProcedures.add(p);
            }
        }
        return officeProcedures;
    }

    @Override
    public List<Procedure> getByProcedureName(String name) {
        return procedureRepository.findAllByProcedureNameOrderByProcedureDateAscStartTimeAsc(name);
    }

    @Override
    public List<Procedure> getFreeProcedures(List<Procedure> procedures) {
        List<Procedure> freeProcedures = new ArrayList<>();
        for(Procedure procedure : procedures){
            for (int i = 0; i < appointmentService.getAll().size(); i++) {
                if(appointmentService.getAll().get(i).getProcedure()!=procedure){
                    freeProcedures.add(procedure);
                }
            }
        }
        return freeProcedures;
    }

//    @Override
//    public List<Procedure> findAllByDoctorOrderByProcedureDateAscStartTimeAsc(String doctorName){
//        return procedureRepository.findAllByDoctorOrderByProcedureDateAscStartTimeAsc(doctorName);
//    }
//
//    @Override
//    public List<Procedure> findAllByDoctorAndProcedureNameOrderByProcedureDateAscStartTimeAsc(String doctorName, String procedureName){
//        return procedureRepository.findAllByDoctorAndProcedureNameOrderByProcedureDateAscStartTimeAsc(doctorName, procedureName );
//    }
}
