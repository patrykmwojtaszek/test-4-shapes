package pl.kurs.test4shapes.service;

import com.sun.istack.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.exceptions.NoEntityException;
import pl.kurs.test4shapes.model.Circle;
import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.model.ShapeType;
import pl.kurs.test4shapes.model.Square;
import pl.kurs.test4shapes.repository.IShapeRepository;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Service
public class ShapeService implements IShapeService{

    private IShapeRepository shapeRepository;

    public ShapeService(IShapeRepository shapeRepository) {
        this.shapeRepository = shapeRepository;
    }

    @Override
    public Shape add(Shape shape) {
        if (Objects.isNull(shape)) throw new NoEntityException("No entity to add!");
        return shapeRepository.save(shape);
    }

    @Override
    public List<Shape> getFilteredShapeList(Long id,
                                            ShapeType type,
                                            Double areaFrom,
                                            Double areaTo,
                                            Double perimeterFrom,
                                            Double perimeterTo,
                                            Double widthFrom,
                                            Double widthTo,
                                            Double radiusFrom,
                                            Double radiusTo,
                                            LocalDate localDateFrom,
                                            LocalDate localDateTo) {
        List<Shape> shapes = shapeRepository.findAll();

        if (id != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getId().equals(id))
                    .collect(Collectors.toList());
        }

        if (type != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getType() == type)
                    .collect(Collectors.toList());
        }

        if (areaFrom != null) {
            shapes = shapes.stream()
//                    .filter(x -> x.getType() == ShapeType.SQUARE || x.getType() == ShapeType.RECTANGLE)
                    .filter(x -> x.getArea() >= areaFrom)
                    .collect(Collectors.toList());
        }

        if (areaTo != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getArea() <= areaTo)
                    .collect(Collectors.toList());
        }

        if (perimeterFrom != null) {
            shapes = shapes.stream()
//                    .filter(x -> x.getType() == ShapeType.SQUARE || x.getType() == ShapeType.RECTANGLE)
                    .filter(x -> x.getPerimeter() >= perimeterFrom)
                    .collect(Collectors.toList());
        }

        if (perimeterTo != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getPerimeter() <= perimeterTo)
                    .collect(Collectors.toList());
        }

        if (widthFrom != null) {
            shapes = shapes.stream()
//                    .filter(x -> x.getType() == ShapeType.SQUARE || x.getType() == ShapeType.RECTANGLE)
                    .filter(x -> x.getWidth() >= widthFrom)
                    .collect(Collectors.toList());
        }

        if (widthTo != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getWidth() <= widthTo)
                    .collect(Collectors.toList());
        }

        if (radiusFrom != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getType() == ShapeType.CIRCLE)
                    .map(x -> (Circle) x)
                    .filter(x -> x.getRadius() >= radiusFrom)
                    .collect(Collectors.toList());
        }

        if (radiusTo != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getType() == ShapeType.CIRCLE)
                    .map(x -> (Circle) x)
                    .filter(x -> x.getRadius() <= radiusTo)
                    .collect(Collectors.toList());
        }

        if (localDateFrom != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getCreatedAt().isAfter(localDateFrom.atStartOfDay()))
                    .collect(Collectors.toList());
        }

        if (localDateTo != null) {
            shapes = shapes.stream()
                    .filter(x -> x.getCreatedAt().isBefore(localDateTo.atStartOfDay()))
                    .collect(Collectors.toList());
        }


        return shapes;
    }
}
