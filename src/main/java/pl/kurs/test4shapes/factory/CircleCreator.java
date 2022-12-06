package pl.kurs.test4shapes.factory;

import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.model.Circle;
import pl.kurs.test4shapes.model.Shape;

import java.util.List;

@Service
public class CircleCreator implements IShapeCreator{
    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public Shape create(List<Double> parameters) {
        return new Circle(getType(), parameters);
    }
}
