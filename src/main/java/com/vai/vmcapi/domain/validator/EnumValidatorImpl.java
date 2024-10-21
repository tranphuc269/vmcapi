package com.vai.vmcapi.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private EnumValidator annotation;

    @Override
    public void initialize(EnumValidator annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        for (Object enumValue : enumValues) {
            if (value.equals(enumValue.toString())) {
                return true;
            }
        }
        return false;
    }
}