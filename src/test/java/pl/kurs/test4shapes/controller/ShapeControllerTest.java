package pl.kurs.test4shapes.controller;

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
import pl.kurs.test4shapes.Test4ShapesApplication;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
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
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(ShapeType.SQUARE, List.of(5.0));
        Shape shape = new Square(createShapeCommand);
//        shape.setCreatedAt(null);
        String json = objectMapper.writeValueAsString(shape);

        //when
        String response = postman.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

//        Shape responseObj = objectMapper.readValue(response, Square.class);

//        Assertions.assertEquals(json, response);

    }


}