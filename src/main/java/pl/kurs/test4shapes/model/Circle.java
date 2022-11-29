package pl.kurs.test4shapes.model;

import lombok.Data;
import pl.kurs.test4shapes.commands.CreateShapeCommand;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("CIRCLE")
public class Circle extends Shape implements IShape{

    private double radius;

    public Circle() {
    }

    public Circle(ShapeType type, List<Double> parameters) {
        super(type, parameters);
        this.radius = parameters.get(0);
        super.setArea(calculateArea());
        super.setPerimeter(calculatePerimeter());
        super.setWidth(2 * radius);
    }


    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}
