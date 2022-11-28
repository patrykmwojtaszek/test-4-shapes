package pl.kurs.test4shapes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.test4shapes.model.Shape;
import java.util.List;

public interface IShapeRepository extends JpaRepository<Shape, Long> {
}
