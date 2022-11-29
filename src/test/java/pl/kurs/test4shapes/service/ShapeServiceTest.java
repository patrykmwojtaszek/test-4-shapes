package pl.kurs.test4shapes.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test4shapes.Test4ShapesApplication;
import pl.kurs.test4shapes.exceptions.NoEntityException;
import pl.kurs.test4shapes.model.*;
import pl.kurs.test4shapes.repository.IShapeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= Test4ShapesApplication.class)
class ShapeServiceTest {

    @Mock
    private IShapeRepository shapeRepository;
    private ShapeService shapeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.shapeService = new ShapeService(shapeRepository);
    }

    @Test
    public void add_shouldAdd() {
        //given
        Square shape = new Square(ShapeType.SQUARE, List.of(5.0));
        //when
//        Mockito.when(jobService.add(Mockito.any(Job.class))).thenReturn(job);
        Mockito.when(shapeRepository.save(Mockito.any(Shape.class))).thenReturn(shape);
        Shape shapeResult = shapeService.add(shape);
        //then
        Assertions.assertEquals(shape, shapeResult);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> shapeService.add(null));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeList() {
            //given
            Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
            Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
            Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
            List<Shape> shapeList = new ArrayList<>();
            shapeList.add(shape1);
            shapeList.add(shape2);
            shapeList.add(shape3);
            //when
            Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
            List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, null, null, null, null, null, null, null, null, null, null, null);
            //then
            Assertions.assertEquals(shapeList, shapeListResult);
    }

//    @Test
//    public void getFilteredShapeList_shouldGetFilteredShapeListById() {
//        //given
//        Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
//        Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
//        Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
//        List<Shape> shapeList = new ArrayList<>();
//        shapeList.add(shape1);
//        shapeList.add(shape2);
//        shapeList.add(shape3);
//        //when
//        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
//        List<Shape> shapeListResult = shapeService.getFilteredShapeList(2L, null, null, null, null, null, null, null, null, null);
//        shapeList.remove(0);
//        shapeList.remove(1);
//        //then
//        Assertions.assertEquals(shapeList, shapeListResult);
//    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByType() {
        //given
        Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
        Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
        Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
        List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, ShapeType.SQUARE, null, null, null, null, null, null, null, null, null, null);
        shapeList.remove(0);
        shapeList.remove(1);
        //then
        Assertions.assertEquals(shapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByArea() {
        //given
        Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
        Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
        Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
        List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, null, 2.0, 10.0, null, null, null, null, null, null, null, null);
        shapeList.remove(0);
        shapeList.remove(0);
        //then
        Assertions.assertEquals(shapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByPerimeter() {
        //given
        Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
        Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
        Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
        List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, null, null, null, 10.0, 24.0, null, null, null, null, null, null);
        shapeList.remove(0);
        //then
        Assertions.assertEquals(shapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByWidth() {
        //given
        Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
        Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
        Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
        List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, null, null, null, null, null, 6.0, 8.0, null, null, null, null);
        shapeList.remove(2);
        //then
        Assertions.assertEquals(shapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByRadius() {
        //given
        Circle shape1 = new Circle(ShapeType.CIRCLE, List.of(4.0));
        Square shape2 = new Square(ShapeType.SQUARE, List.of(6.0));
        Rectangle shape3 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
        List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, null, null, null, null, null, null, null, 3.0, 5.0, null, null);
        shapeList.remove(1);
        shapeList.remove(1);
        //then
        Assertions.assertEquals(shapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByDate() {
        //given
        Rectangle shape1 = new Rectangle(ShapeType.RECTANGLE, List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        //when
        Mockito.when(shapeRepository.findAll()).thenReturn(shapeList);
        List<Shape> shapeListResult = shapeService.getFilteredShapeList(null, null, null, null, null, null, null, null, null, null, LocalDate.of(2022, 06, 01), LocalDate.of(2026, 01, 01));
        //then
        Assertions.assertEquals(shapeList, shapeListResult);
    }
}