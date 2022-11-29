package pl.kurs.test4shapes.controller;

import com.sun.istack.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.dto.ShapeDto;
import pl.kurs.test4shapes.model.*;
import pl.kurs.test4shapes.service.IShapeService;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shapes")
public class ShapeController {

    private IShapeService shapeService;
    private ModelMapper modelMapper;

    public ShapeController(IShapeService shapeService, ModelMapper modelMapper) {
        this.shapeService = shapeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ShapeDto> addShape(@Valid @RequestBody CreateShapeCommand createShapeCommand) throws InterruptedException, ClassNotFoundException {

        Shape shapeForSave = new Circle();
        if(createShapeCommand.getType() == ShapeType.CIRCLE) shapeForSave = new Circle(createShapeCommand.getType(), createShapeCommand.getParameters());
        if(createShapeCommand.getType() == ShapeType.RECTANGLE) shapeForSave = new Rectangle(createShapeCommand.getType(), createShapeCommand.getParameters());
        if(createShapeCommand.getType() == ShapeType.SQUARE) shapeForSave = new Square(createShapeCommand.getType(), createShapeCommand.getParameters());

        ShapeDto shapeDto = modelMapper.map(shapeService.add(shapeForSave), ShapeDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeDto);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Shape>> getFilteredShapes(@RequestParam(required = false) Long id,
                                                         @RequestParam(required = false) ShapeType type,
                                                         @RequestParam(required = false) Double areaFrom,
                                                         @RequestParam(required = false) Double areaTo,
                                                         @RequestParam(required = false) Double perimeterFrom,
                                                         @RequestParam(required = false) Double perimeterTo,
                                                         @RequestParam(required = false) Double widthFrom,
                                                         @RequestParam(required = false) Double widthTo,
                                                         @RequestParam(required = false) Double radiusFrom,
                                                         @RequestParam(required = false) Double radiusTo,
                                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDateFrom,
                                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDateTo){
        List<Shape> shapeList = shapeService.getFilteredShapeList(id, type, areaFrom, areaTo, perimeterFrom, perimeterTo, widthFrom, widthTo, radiusFrom, radiusTo, localDateFrom, localDateTo);

        return ResponseEntity.status(HttpStatus.CREATED).body(shapeList);
    }

}
