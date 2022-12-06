package pl.kurs.test4shapes.factory;

import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.model.Circle;
import pl.kurs.test4shapes.model.Rectangle;
import pl.kurs.test4shapes.model.Shape;

import java.util.List;

@Service
public class RectangleCreator implements IShapeCreator{
    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public Shape create(List<Double> parameters) {
        return new Rectangle(getType(), parameters);
    }
}
