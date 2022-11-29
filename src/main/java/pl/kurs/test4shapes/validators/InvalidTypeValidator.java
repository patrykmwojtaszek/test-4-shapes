package pl.kurs.test4shapes.validators;

import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.model.ShapeType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InvalidTypeValidator implements ConstraintValidator<InvalidType, ShapeType> {

    @Override
    public void initialize(InvalidType constraintAnnotation) {
    }

    @Override
    public boolean isValid(ShapeType type, ConstraintValidatorContext constraintValidatorContext) {
        return type == null || Arrays.asList(ShapeType.values()).contains(type);
    }
}
