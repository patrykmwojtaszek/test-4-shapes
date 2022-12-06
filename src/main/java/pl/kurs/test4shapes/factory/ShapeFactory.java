package pl.kurs.test4shapes.factory;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.model.Shape;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Getter
public class ShapeFactory {

    private final Map<String, IShapeCreator> creators;

    public ShapeFactory(Set<IShapeCreator> creators) {
        this.creators = creators.stream().collect(Collectors.toMap(IShapeCreator::getType, Function.identity()));
    }

    public Shape create(CreateShapeCommand createShapeCommand) {
        return creators.get(createShapeCommand.getType()).create(createShapeCommand.getParameters());
    }

}
