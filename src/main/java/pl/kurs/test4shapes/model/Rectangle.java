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

    public Rectangle(ShapeType type, List<Double> parameters) {
        super(type, parameters);
        this.height = parameters.get(0);
        this.width = parameters.get(1);
        super.setArea(calculateArea());
        super.setPerimeter(calculatePerimeter());
    }

//    public Rectangle(CreateShapeCommand createShapeCommand) {
//        super(createShapeCommand);
////        super.setHeight(createShapeCommand.getParameters().get(0));
//        this.height = createShapeCommand.getParameters().get(0);
////        super.setWidth(createShapeCommand.getParameters().get(1));
//        this.width = createShapeCommand.getParameters().get(1);
//        super.setArea(calculateArea());
//        super.setPerimeter(calculatePerimeter());
//    }

    @Override
    public double calculateArea() {
//        return super.getHeight() * super.getWidth();
        return height * width;
    }

    @Override
    public double calculatePerimeter() {
//        return 2 * super.getHeight() + 2 * super.getWidth();
        return 2 * (height + width);
    }
}
