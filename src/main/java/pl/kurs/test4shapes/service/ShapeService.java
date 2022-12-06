package pl.kurs.test4shapes.service;

import com.sun.istack.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.exceptions.NoEntityException;
import pl.kurs.test4shapes.factory.ShapeFactory;
import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.repository.IShapeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@Service
@RequiredArgsConstructor
public class ShapeService implements IShapeService {

    private final IShapeRepository shapeRepository;
    private final ShapeFactory shapeFactory;

    @Override
    public Shape add(CreateShapeCommand createShapeCommand) {
        if (Objects.isNull(createShapeCommand)) throw new NoEntityException("No entity to add!");
        return shapeRepository.saveAndFlush(shapeFactory.create(createShapeCommand));
    }

    @Override
    public List<Shape> getFilteredShapeList(@Nullable Long id,
                                            @Nullable String type,
                                            @Nullable Double areaFrom,
                                            @Nullable Double areaTo,
                                            @Nullable Double perimeterFrom,
                                            @Nullable Double perimeterTo,
                                            @Nullable Double widthFrom,
                                            @Nullable Double widthTo,
                                            @Nullable LocalDate createdAtFrom,
                                            @Nullable LocalDate createdAtTo) {

        List<Shape> shapes = shapeRepository.findShapesByIdAndTypeAndAreaBetweenAndPerimeterBetweenAndWidthBetweenAndCreatedAtBetween(id, type, areaFrom, areaTo, perimeterFrom, perimeterTo, widthFrom, widthTo, createdAtFrom, createdAtTo);

        return shapes;
    }
}
