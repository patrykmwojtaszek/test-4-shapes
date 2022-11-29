package pl.kurs.test4shapes.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.test4shapes.Test4ShapesApplication;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.model.Circle;
import pl.kurs.test4shapes.model.Shape;
import pl.kurs.test4shapes.model.ShapeType;
import pl.kurs.test4shapes.model.Square;
import pl.kurs.test4shapes.service.IShapeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= Test4ShapesApplication.class)
@AutoConfigureMockMvc
class ShapeControllerTest {

    @Autowired
    private MockMvc postman;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private CreateShapeCommand createShapeCommand;
    @Mock
    private IShapeService shapeService;
    @Mock
    private ModelMapper modelMapper;
    private ShapeController shapeController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.shapeController = new ShapeController(shapeService, modelMapper);
    }

    @Test
    public void addShape_shouldAddShape() throws Exception {
        //given
        Square shape = new Square(ShapeType.SQUARE, List.of(5.0));
        String json = objectMapper.writeValueAsString(shape);

        //when
                postman.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        //then
        String response2 = postman.perform(MockMvcRequestBuilders.get("/api/v1/shapes/?id=1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Square> responseObjList = objectMapper.readValue(response2, new TypeReference<List<Square>>(){});
        Square responseObj = responseObjList.get(0);

        Assertions.assertEquals(shape, responseObj);
    }

    @Test
    public void getFilteredShapes_shouldGetFilteredShapes() throws Exception {
        //given
        Square shape1 = new Square(ShapeType.SQUARE, List.of(5.0));
        Circle shape2 = new Circle(ShapeType.CIRCLE, List.of(2.0));
        String json1 = objectMapper.writeValueAsString(shape1);
        String json2 = objectMapper.writeValueAsString(shape2);

        //when
        postman.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json1))
                .andExpect(status().isCreated());

        postman.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andExpect(status().isCreated());

        //then
        String response = postman.perform(MockMvcRequestBuilders.get("/api/v1/shapes/?id=3"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Circle> responseObjList = objectMapper.readValue(response, new TypeReference<List<Circle>>(){});
        Circle responseObj = responseObjList.get(0);

        Assertions.assertEquals(shape2, responseObj);
    }
}