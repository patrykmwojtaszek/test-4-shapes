package pl.kurs.test4shapes.factory;

import pl.kurs.test4shapes.model.Shape;

import java.util.List;

public interface IShapeCreator {
    String getType();
    Shape create(List<Double> parameters);
}
