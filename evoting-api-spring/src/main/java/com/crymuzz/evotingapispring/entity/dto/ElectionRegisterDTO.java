package com.crymuzz.evotingapispring.entity.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionRegisterDTO {

    @NotBlank(message = "El campo del nombre no puede ser nulo o en blanco")
    private String name;

    @NotNull(message = "La fecha de inicio de la elección no puede ser nula")
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalización de la elección no puede ser nula")
    @FutureOrPresent
    private LocalDate endDate;

    @AssertTrue(message = "La fecha de fin debe ser mayor que la fecha de inicio")
    public boolean isEndDateAfterStartDate() {
        if (startDate == null || endDate == null)
            return true; // No valida si alguna de las fechas es nula (lo maneja @NotNull)
        return endDate.isAfter(startDate); // Verifica que endDate sea estrictamente posterior a startDate
    }
}
