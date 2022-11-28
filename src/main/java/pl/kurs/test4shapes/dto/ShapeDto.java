package pl.kurs.test4shapes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.kurs.test4shapes.model.ShapeType;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShapeDto {

    private Long id;
    private ShapeType type;
    private double width;
    private LocalDateTime createdAt;
    private double area;
    private double perimeter;
}
