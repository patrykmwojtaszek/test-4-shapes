package pl.kurs.test4shapes.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.test4shapes.commands.CreateShapeCommand;
import pl.kurs.test4shapes.commands.UpdateShapeCommand;
import pl.kurs.test4shapes.dto.ShapeDto;
import pl.kurs.test4shapes.model.*;
import pl.kurs.test4shapes.service.ShapeService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/shapes")
@RequiredArgsConstructor
public class ShapeController {

    private final ShapeService shapeService;
    private final ModelMapper modelMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<ShapeDto> addShape(@Valid @RequestBody CreateShapeCommand createShapeCommand) throws InterruptedException, ClassNotFoundException {
        ShapeDto shapeDto = modelMapper.map(shapeService.add(createShapeCommand), ShapeDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeDto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ShapeDto> editShape(@RequestBody UpdateShapeCommand updateShapeCommand) {
        ShapeDto shapeDto = modelMapper.map(shapeService.update(updateShapeCommand), ShapeDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeDto);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ShapeDto>> getFilteredShapes(@RequestParam(required = false) String type,
                                                         @RequestParam(required = false) Double areaFrom,
                                                         @RequestParam(required = false) Double areaTo,
                                                         @RequestParam(required = false) Double perimeterFrom,
                                                         @RequestParam(required = false) Double perimeterTo,
                                                         @RequestParam(required = false) Double widthFrom,
                                                         @RequestParam(required = false) Double widthTo,
                                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAtFrom,
                                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAtTo){
        List<Shape> shapeList = shapeService.getFilteredShapeList(type, areaFrom, areaTo, perimeterFrom, perimeterTo, widthFrom, widthTo, createdAtFrom, createdAtTo);
        List<ShapeDto> shapeDtoList = shapeList.stream()
                .map(x -> modelMapper.map(x, ShapeDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(shapeDtoList);
    }

}
