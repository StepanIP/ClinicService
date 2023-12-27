package com.example.clinicservice.model.comporators;


import com.example.clinicservice.model.Procedure;

import java.time.LocalTime;
import java.util.Comparator;

public class ProcedureComparator implements Comparator<Procedure> {
        @Override
        public int compare(Procedure procedure1, Procedure procedure2) {
            // Отримуємо інтервали часу для порівняння
            LocalTime range1 = procedure1.getStartTime();
            LocalTime range2 = procedure2.getStartTime();

            // Порівнюємо інтервали
            return range1.compareTo(range2);
        }
}