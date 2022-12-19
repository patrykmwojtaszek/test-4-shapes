package pl.kurs.test4shapes.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.kurs.test4shapes.validators.InvalidType;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateShapeCommand {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @InvalidType(message = "INVALID_TYPE")
    private String type;
    private List<Double> parameters = new ArrayList<>();

    public UpdateShapeCommand(Long id, @InvalidType(message = "INVALID_TYPE") String type, List<Double> parameters) {
        this.id = id;
        this.type = type;
        this.parameters = parameters;
    }
}
