package pl.kurs.test4shapes.model;

import lombok.Data;
import pl.kurs.test4shapes.commands.CreateShapeCommand;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("RECTANGLE")
public class Rectangle extends Shape implements IShape{

    private double height;
    private double width;

    public Rectangle() {
    }

    public Rectangle(String type, List<Double> parameters) {
        super(type, parameters);
        this.height = parameters.get(0);
        this.width = parameters.get(1);
        super.setArea(calculateArea());
        super.setPerimeter(calculatePerimeter());
    }

    @Override
    public double calculateArea() {
        return height * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (height + width);
    }
}
