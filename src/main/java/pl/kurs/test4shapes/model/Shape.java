package pl.kurs.test4shapes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.kurs.test4shapes.commands.CreateShapeCommand;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "shapes")
public abstract class Shape implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shape")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShapeType type;

    @ElementCollection
//    @Column(name = "parameters", nullable = false)
    private List<Double> parameters = new ArrayList<>();

//    @Column(nullable = false)
//    private double height;
//
    @Column(nullable = false)
    private double width;

//    @Column(nullable = false)
//    private double radius;

//    @Column(nullable = false)
//    private LocalDate createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private double area;

    @Column(nullable = false)
    private double perimeter;

    public Shape() {
    }

    public Shape(ShapeType type, List<Double> parameters) {
        this.type = type;
        this.parameters = parameters;
        this.createdAt = LocalDateTime.now();
    }

    public Shape(CreateShapeCommand createShapeCommand) {
        this.type = createShapeCommand.getType();
        this.parameters = createShapeCommand.getParameters();
        this.createdAt = LocalDateTime.now();
//        this.width = setWidthByType(createShapeCommand);
//        this.createdAt = LocalDate.now();
//        this.area = setAreaByType(createShapeCommand);
//        this.perimeter = setPerimeterByType(createShapeCommand);
    }

//    private double setWidthByType(CreateShapeCommand createShapeCommand) {
//        double width = switch(createShapeCommand.getType().name()) {
//            case "CIRCLE" -> createShapeCommand.getParameters().get(0) * 2;
//            case "RECTANGLE" -> createShapeCommand.getParameters().get(1);
//            case "SQUARE" -> createShapeCommand.getParameters().get(0);
//            default -> 0;
//        };
////        this.width = width;
//        return width;
//    }

//    private double setAreaByType(CreateShapeCommand createShapeCommand) {
//        double area = switch(createShapeCommand.getType().name()) {
//            case "CIRCLE" -> createShapeCommand.getParameters().get(0) * createShapeCommand.getParameters().get(0) * Math.PI;
//            case "RECTANGLE" -> createShapeCommand.getParameters().get(0) * createShapeCommand.getParameters().get(1);
//            case "SQUARE" -> createShapeCommand.getParameters().get(0) * createShapeCommand.getParameters().get(0);
//            default -> 0;
//        };
////        this.area = area;
//        return area;
//    }

//    private double setPerimeterByType(CreateShapeCommand createShapeCommand) {
//        double perimeter = switch(createShapeCommand.getType().name()) {
//            case "CIRCLE" -> 2 * createShapeCommand.getParameters().get(0) * Math.PI;
//            case "RECTANGLE" -> 2 * createShapeCommand.getParameters().get(0) + 2 * createShapeCommand.getParameters().get(1);
//            case "SQUARE" -> 4 * createShapeCommand.getParameters().get(0);
//            default -> 0;
//        };
////        this.area = area;
//        return perimeter;
//    }



}
