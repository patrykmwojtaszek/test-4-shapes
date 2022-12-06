package pl.kurs.test4shapes.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShapeDto {

    private Long id;
    private String type;
    private double width;
    private int version;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String lastModifiedBy;
    private double area;
    private double perimeter;

}
