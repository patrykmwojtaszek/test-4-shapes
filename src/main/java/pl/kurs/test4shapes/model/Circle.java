package pl.kurs.test4shapes.model;

import lombok.Data;
import pl.kurs.test4shapes.commands.CreateShapeCommand;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("CIRCLE")
public class Circle extends Shape implements IShape{

    private double radius;

    public Circle() {
    }

    public Circle(CreateShapeCommand createShapeCommand) {
        super(createShapeCommand);
//        super.setRadius(createShapeCommand.getParameters().get(0));
        this.radius = createShapeCommand.getParameters().get(0);
        super.setArea(calculateArea());
        super.setPerimeter(calculatePerimeter());
        super.setWidth(2 * radius);
    }

    @Override
    public double calculateArea() {
//        return super.getRadius() * super.getRadius() * Math.PI;
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
//        return 2 * super.getRadius() * Math.PI;
        return 2 * Math.PI * radius;
    }
}
