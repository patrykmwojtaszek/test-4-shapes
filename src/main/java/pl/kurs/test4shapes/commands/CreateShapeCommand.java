package pl.kurs.test4shapes.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.kurs.test4shapes.validators.InvalidType;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateShapeCommand {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @InvalidType(message = "INVALID_TYPE")
    private String type;
    private List<Double> parameters = new ArrayList<>();

    public CreateShapeCommand(String type, List<Double> parameters) {
        this.type = type;
        this.parameters = parameters;
    }
}