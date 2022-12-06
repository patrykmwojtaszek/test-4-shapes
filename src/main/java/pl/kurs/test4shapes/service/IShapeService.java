package pl.kurs.test4shapes.service;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.model.Shape;
import java.time.LocalDate;
import java.util.List;

public interface IShapeService {

    Shape add(CreateShapeCommand createShapeCommand);

    List<Shape> getFilteredShapeList(Long id,
                                     String type,
                                     Double areaFrom,
                                     Double areaTo,
                                     Double perimeterFrom,
                                     Double perimeterTo,
                                     Double widthFrom,
                                     Double widthTo,
                                     LocalDate createdAtFrom,
                                     LocalDate createdAtTo);

}
