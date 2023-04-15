package br.com.tex.hoteldevaneio.utils;

import br.com.tex.hoteldevaneio.annotation.CheckData;
import br.com.tex.hoteldevaneio.model.dto.ReservaInputDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckDataValidator implements ConstraintValidator<CheckData, ReservaInputDTO> {

    @Override
    public boolean isValid(ReservaInputDTO reserva, ConstraintValidatorContext context) {
        if (reserva.getCheckIn() == null || reserva.getCheckOut() == null) {
            return true;
        }
        return reserva.getCheckIn().isBefore(reserva.getCheckOut());
    }
}