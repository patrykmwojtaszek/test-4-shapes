package pl.kurs.test4shapes.service;

import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.model.ShapeType;
import java.util.List;

public interface IShapeService {

    Shape add(Shape shape);

//    List<Shape> getFilteredShapes(ShapeFilterModel filterModel);
    List<Shape> getFilteredShapeList(Integer id,
                                     ShapeType type,
                                     Double areaFrom,
                                     Double areaTo,
                                     Double perimeterFrom,
                                     Double perimeterTo,
                                     Double widthFrom,
                                     Double widthTo,
                                     Double radiusFrom,
                                     Double radiusTo);

}
