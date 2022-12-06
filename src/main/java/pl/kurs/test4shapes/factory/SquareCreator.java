package pl.kurs.test4shapes.factory;

import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.model.Circle;
import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.model.Square;

import java.util.List;

@Service
public class SquareCreator implements IShapeCreator {
    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public Shape create(List<Double> parameters) {
        return new Square(getType(), parameters);
    }
}