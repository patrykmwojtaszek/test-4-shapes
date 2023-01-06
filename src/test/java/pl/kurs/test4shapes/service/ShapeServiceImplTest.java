package pl.kurs.test4shapes.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test4shapes.Test4ShapesApplication;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.exceptions.NoEntityException;
import pl.kurs.test4shapes.factory.ShapeFactory;
import pl.kurs.test4shapes.model.*;
import pl.kurs.test4shapes.repository.IShapeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest(classes= Test4ShapesApplication.class)
@ExtendWith(MockitoExtension.class)
class ShapeServiceImplTest {

    @Mock
    private IShapeRepository shapeRepository;
    @Mock
    private ShapeFactory shapeFactory;
    @InjectMocks
    private ShapeServiceImpl shapeServiceImpl;

//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//        this.shapeServiceImpl = new ShapeServiceImpl(shapeRepository, shapeFactory);
//    }

    @Test
    public void add_shouldAdd() {
        //given
        Square shape = new Square("SQUARE", List.of(5.0));
        CreateShapeCommand createShapeCommand = new CreateShapeCommand("SQUARE", List.of(5.0));
        //when
        when(shapeFactory.create(any(CreateShapeCommand.class))).thenReturn(shape);
        when(shapeRepository.save(any(Shape.class))).thenReturn(shape);
        Shape shapeResult = shapeServiceImpl.add(createShapeCommand);
        //then
        assertEquals(shape, shapeResult);

        verify(shapeFactory).create(createShapeCommand);
//        verifyNoInteractions(shapeRepository);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> shapeServiceImpl.add(null));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeList() {
            //given
            Circle shape1 = new Circle("CIRCLE", List.of(4.0));
            Square shape2 = new Square("SQUARE", List.of(6.0));
            Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
            List<Shape> shapeList = new ArrayList<>();
            shapeList.add(shape1);
            shapeList.add(shape2);
            shapeList.add(shape3);
            //when
            Mockito.when(shapeRepository.findShapesByParams(null, null, null, null, null, null, null, null, null)).thenReturn(shapeList);
            List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList(null, null, null, null, null, null, null, null, null);
            //then
            assertEquals(shapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListById() {
        //given
        Circle shape1 = new Circle("CIRCLE", List.of(4.0));
        Square shape2 = new Square("SQUARE", List.of(6.0));
        Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        List<Shape> filteredShapeList = shapeList;
        filteredShapeList.remove(0);
        filteredShapeList.remove(1);
        Mockito.when(shapeRepository.findShapesByParams( Mockito.eq(null), Mockito.eq(null), Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null))).thenReturn(filteredShapeList);
        List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList(null, null, null, null, null, null, null, null, null);
        //then
        assertEquals(filteredShapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByType() {
        //given
        Circle shape1 = new Circle("CIRCLE", List.of(4.0));
        Square shape2 = new Square("SQUARE", List.of(6.0));
        Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        List<Shape> filteredShapeList = shapeList;
        filteredShapeList.remove(0);
        filteredShapeList.remove(0);
        Mockito.when(shapeRepository.findShapesByParams(any(String.class), Mockito.eq(null), Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null))).thenReturn(filteredShapeList);
        List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList("RECTANGLE", null, null, null, null, null, null, null, null);
        //then
        assertEquals(filteredShapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByArea() {
        //given
        Circle shape1 = new Circle("CIRCLE", List.of(4.0));
        Square shape2 = new Square("SQUARE", List.of(6.0));
        Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        List<Shape> filteredShapeList = shapeList;
        filteredShapeList.remove(2);
        Mockito.when(shapeRepository.findShapesByParams( Mockito.eq(null), any(Double.class), any(Double.class),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null))).thenReturn(filteredShapeList);
        List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList( null, 20.0, 40.0, null, null, null, null, null, null);
        //then
        assertEquals(filteredShapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByPerimeter() {
        //given
        Circle shape1 = new Circle("CIRCLE", List.of(4.0));
        Square shape2 = new Square("SQUARE", List.of(6.0));
        Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        List<Shape> filteredShapeList = shapeList;
        filteredShapeList.remove(2);
        Mockito.when(shapeRepository.findShapesByParams(Mockito.eq(null), Mockito.eq(null), Mockito.eq(null), any(Double.class), any(Double.class),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null))).thenReturn(filteredShapeList);
        List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList(null, null, null, 15.0, 30.0, null, null, null, null);
        //then
        assertEquals(filteredShapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByWidth() {
        //given
        Circle shape1 = new Circle("CIRCLE", List.of(4.0));
        Square shape2 = new Square("SQUARE", List.of(6.0));
        Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        List<Shape> filteredShapeList = shapeList;
        filteredShapeList.remove(0);
        Mockito.when(shapeRepository.findShapesByParams(Mockito.eq(null), Mockito.eq(null), Mockito.eq(null),Mockito.eq(null),Mockito.eq(null), any(Double.class), any(Double.class),Mockito.eq(null),Mockito.eq(null))).thenReturn(filteredShapeList);
        List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList(null, null, null, null, null, 3.0, 7.5, null, null);
        //then
        assertEquals(filteredShapeList, shapeListResult);
    }

    @Test
    public void getFilteredShapeList_shouldGetFilteredShapeListByCreatedAt() {
        //given
        Circle shape1 = new Circle("CIRCLE", List.of(4.0));
        Square shape2 = new Square("SQUARE", List.of(6.0));
        Rectangle shape3 = new Rectangle("RECTANGLE", List.of(2.0, 3.0));
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shape1);
        shapeList.add(shape2);
        shapeList.add(shape3);
        //when
        List<Shape> filteredShapeList = shapeList;
        Mockito.when(shapeRepository.findShapesByParams(Mockito.eq(null), Mockito.eq(null), Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null),Mockito.eq(null), any(LocalDate.class), any(LocalDate.class))).thenReturn(filteredShapeList);
        List<Shape> shapeListResult = shapeServiceImpl.getFilteredShapeList(null, null, null, null, null, null, null, LocalDate.of(2020, 01, 01), LocalDate.of(2060, 01, 01));
        //then
        assertEquals(filteredShapeList, shapeListResult);
    }
}