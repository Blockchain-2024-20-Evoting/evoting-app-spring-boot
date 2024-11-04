package com.crymuzz.evotingapispring.entity.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionRegisterDTO {

    @NotBlank(message = "El campo del nombre no puede ser nulo o en blanco")
    private String name;

    @NotNull(message = "La fecha de inicio de la elección no puede ser nula")
    @Future
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalización de la elección no puede ser nula")
    @Future
    private LocalDate endDate;

    @AssertTrue(message = "La fecha de fin debe ser mayor que la fecha de inicio")
    public boolean isEndDateAfterStartDate() {
        return endDate.isAfter(startDate); // Verifica que endDate sea estrictamente posterior a startDate
    }

    @AssertTrue(message = "La fecha de inicio no puede ser anterior a la fecha actual")
    public boolean isStartDateNotBeforeToday() {
        return !startDate.isBefore(LocalDate.now(ZoneId.of("America/Lima")));
    }

    @AssertTrue(message = "La fecha de inicio y de finalización no pueden ser iguales")
    public boolean isStartDateNotEqualToEndDate() {
        return !startDate.isEqual(endDate); // Verifica que las fechas no sean iguales
    }
}
