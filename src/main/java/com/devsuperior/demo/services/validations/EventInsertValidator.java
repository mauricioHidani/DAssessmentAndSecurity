package com.devsuperior.demo.services.validations;

import com.devsuperior.demo.dto.EntityFieldErrorDTO;
import com.devsuperior.demo.dto.EventInsertDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventInsertValidator implements ConstraintValidator<EventInsertValid, EventInsertDTO> {

    @Override
    public boolean isValid(EventInsertDTO event,
                           ConstraintValidatorContext context) {
        List<EntityFieldErrorDTO> errors = new ArrayList<>();

        if (event.getName().isBlank()) {
            errors.add(
                    new EntityFieldErrorDTO(
                            "name",
                            "Campo requerido"
                    )
            );
        }

        if (isDateBeforeNow(event.getDate())) {
            errors.add(
                    new EntityFieldErrorDTO(
                            "date",
                            "A data do evento não pode ser passada"
                    )
            );
        }

        if (event.getCityId() == null) {
            errors.add(
                    new EntityFieldErrorDTO(
                            "cityId",
                            "Campo requerido"
                    )
            );
        }

        for (EntityFieldErrorDTO e : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }

    @Override
    public void initialize(EventInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    private boolean isDateBeforeNow(LocalDate date) {
        return date.isBefore(
                LocalDate.now(
                        Clock.systemUTC()
                )
        );
    }

}
