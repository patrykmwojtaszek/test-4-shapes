package pl.kurs.test4shapes.service;

import com.sun.istack.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.commands.UpdateShapeCommand;
import pl.kurs.test4shapes.exceptions.NoEntityException;
import pl.kurs.test4shapes.factory.ShapeFactory;
import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.repository.IShapeRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;

@Component
@Service
@RequiredArgsConstructor
public class ShapeService implements IShapeService {

    private final IShapeRepository shapeRepository;
    private final ShapeFactory shapeFactory;

    @Override
    @Transactional
    public Shape add(CreateShapeCommand createShapeCommand) {
        if (Objects.isNull(createShapeCommand)) throw new NoEntityException("No entity to add!");
        return shapeRepository.save(shapeFactory.create(createShapeCommand));
    }

    @Override
    @Transactional
    public Shape update(UpdateShapeCommand updateShapeCommand) {
        if (Objects.isNull(updateShapeCommand)) throw new NoEntityException("No entity to add!");
        Shape updatedShape = shapeRepository.findById(updateShapeCommand.getId()).orElseThrow();
        updatedShape.setParameters(updateShapeCommand.getParameters());
//        Shape updatedShape = shapeFactory.update(updateShapeCommand);
//        updatedShape.setId(updateShapeCommand.getId());
        return shapeRepository.save(updatedShape);
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
