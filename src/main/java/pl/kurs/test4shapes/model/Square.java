package pl.kurs.test4shapes.model;

import lombok.Data;
import pl.kurs.test4shapes.commands.CreateShapeCommand;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("SQUARE")
public class Square extends Shape implements IShape{

    private double width;

    public Square() {
    }

    public Square(CreateShapeCommand createShapeCommand) {
        super(createShapeCommand);
//        super.setWidth(createShapeCommand.getParameters().get(0));
        this.width = createShapeCommand.getParameters().get(0);
        super.setArea(calculateArea());
        super.setPerimeter(calculatePerimeter());
    }


    @Override
    public double calculateArea() {
//        return super.getWidth() * super.getWidth();
        return width * width;
    }

    @Override
    public double calculatePerimeter() {
//        return 4 * super.getWidth();
        return 4 * width;
    }
}
