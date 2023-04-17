package br.com.tex.hoteldevaneio.utils;

import br.com.tex.hoteldevaneio.annotation.CheckData;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckDataValidator implements ConstraintValidator<CheckData, ReservaInputDTO> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public boolean isValid(ReservaInputDTO reserva, ConstraintValidatorContext context) {
        if (reserva.getCheckIn() == null || reserva.getCheckOut() == null) {
            return true;
        }
        return LocalDate.parse(reserva.getCheckIn(), formatter).isBefore(LocalDate.parse(reserva.getCheckOut(), formatter));
    }
}