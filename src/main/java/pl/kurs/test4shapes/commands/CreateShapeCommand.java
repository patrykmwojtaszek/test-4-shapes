package pl.kurs.test4shapes.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.kurs.test4shapes.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateShapeCommand {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ShapeType type;
    private List<Double> parameters = new ArrayList<>();

    public CreateShapeCommand(ShapeType type, List<Double> parameters) {
        this.type = type;
        this.parameters = parameters;
    }
}