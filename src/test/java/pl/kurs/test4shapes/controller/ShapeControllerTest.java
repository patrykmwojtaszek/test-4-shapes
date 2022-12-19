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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.test4shapes.Test4ShapesApplication;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.commands.UpdateShapeCommand;
import pl.kurs.test4shapes.model.Square;
import pl.kurs.test4shapes.service.IShapeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Test4ShapesApplication.class)
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
    @WithMockUser(username = "creator", roles = {"CREATOR"})
    public void addShape_shouldAddShape() throws Exception {
        //given
        Square shape = new Square("SQUARE", List.of(5.0));
        CreateShapeCommand createShapeCommand = new CreateShapeCommand("SQUARE", List.of(5.0));
        String json = objectMapper.writeValueAsString(createShapeCommand);

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

        List<Square> responseObjList = objectMapper.readValue(response2, new TypeReference<>() {
        });
        Square responseObj = responseObjList.get(0);

        Assertions.assertEquals(shape, responseObj);
    }

    @Test
    @WithMockUser(username = "creator", roles = {"CREATOR"})
    public void editShape_shouldEditShape() throws Exception {
        //given
        Square shape = new Square("SQUARE", List.of(5.0));
        CreateShapeCommand createShapeCommand = new CreateShapeCommand("SQUARE", List.of(5.0));
        String json = objectMapper.writeValueAsString(createShapeCommand);

        //when
        postman.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        String response = postman.perform(MockMvcRequestBuilders.get("/api/v1/shapes/?id=1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Square> responseObjList = objectMapper.readValue(response, new TypeReference<>() {
        });
        Square responseObj1 = responseObjList.get(0);

        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1L, "SQUARE", List.of(5.0));
        String json2 = objectMapper.writeValueAsString(updateShapeCommand);

        postman.perform(put("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andExpect(status().isCreated());

        //then
        String response2 = postman.perform(MockMvcRequestBuilders.get("/api/v1/shapes/?id=1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Square> responseObjList2 = objectMapper.readValue(response2, new TypeReference<>() {
        });
        Square responseObj2 = responseObjList2.get(0);

        Assertions.assertEquals(responseObj1, responseObj2);
        Assertions.assertEquals(responseObj1.getVersion() + 1, responseObj2.getVersion());
    }

    @Test
    @WithMockUser(username = "creator", roles = {"CREATOR"})
    public void getFilteredShapes_shouldGetFilteredShapes() throws Exception {
        //given
        Square shape = new Square("SQUARE", List.of(5.0));
        CreateShapeCommand createShapeCommand1 = new CreateShapeCommand("CIRCLE", List.of(2.0));
        CreateShapeCommand createShapeCommand2 = new CreateShapeCommand("SQUARE", List.of(5.0));
        String json1 = objectMapper.writeValueAsString(createShapeCommand1);
        String json2 = objectMapper.writeValueAsString(createShapeCommand2);

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
        String response = postman.perform(MockMvcRequestBuilders.get("/api/v1/shapes/?id=4"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Square> responseObjList = objectMapper.readValue(response, new TypeReference<>() {
        });
        Square responseObj = responseObjList.get(0);


        Assertions.assertEquals(shape, responseObj);
    }
}