package pl.kurs.test4shapes.repository;

import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.test4shapes.model.Shape;
import java.time.LocalDate;
import java.util.List;

public interface IShapeRepository extends JpaRepository<Shape, Long> {

    @Query("SELECT c FROM Shape c WHERE (:id is null or c.id = :id)" +
            " and (:type is null or c.type = :type)" +
            " and (:areaFrom is null or c.area >= :areaFrom)" +
            " and (:areaTo is null or c.area <= :areaTo)" +
            " and (:perimeterFrom is null or c.perimeter >= :perimeterFrom)" +
            " and (:perimeterTo is null or c.perimeter <= :perimeterTo)" +
            " and (:widthFrom is null or c.width >= :widthFrom)" +
            " and (:widthTo is null or c.width <= :widthTo)" +
            " and (:createdAtFrom is null or c.createdAt >= :createdAtFrom)" +
            " and (:createdAtTo is null or c.createdAt <= :createdAtTo)")
    List<Shape> findShapesByIdAndTypeAndAreaBetweenAndPerimeterBetweenAndWidthBetweenAndCreatedAtBetween(@Nullable Long id,
                                                                                                         @Nullable String type,
                                                                                                         @Nullable Double areaFrom,
                                                                                                         @Nullable Double areaTo,
                                                                                                         @Nullable Double perimeterFrom,
                                                                                                         @Nullable Double perimeterTo,
                                                                                                         @Nullable Double widthFrom,
                                                                                                         @Nullable Double widthTo,
                                                                                                         @Nullable LocalDate createdAtFrom,
                                                                                                         @Nullable LocalDate createdAtTo);

}
