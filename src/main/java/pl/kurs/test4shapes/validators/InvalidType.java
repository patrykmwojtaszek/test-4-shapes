package pl.kurs.test4shapes.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kurs.test4shapes.model.ShapeType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {InvalidTypeValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidType {

    String message() default "{INVALID_TYPE}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
