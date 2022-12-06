package pl.kurs.test4shapes.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class InvalidTypeValidator implements ConstraintValidator<InvalidType, String> {

    @Override
    public void initialize(InvalidType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        List<String> shapeTypes = List.of("CIRCLE", "RECTANGLE", "SQUARE");
        return shapeTypes.contains(type);
    }
}
