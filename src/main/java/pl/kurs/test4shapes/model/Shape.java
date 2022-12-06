package pl.kurs.test4shapes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kurs.test4shapes.commands.CreateShapeCommand;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "shapes")
public abstract class Shape implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shape")
    private Long id;

//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String type;

    @ElementCollection
//    @Column(name = "parameters", nullable = false)
    private List<Double> parameters = new ArrayList<>();

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
//    @Version
    private int version = 0;

    @Column(nullable = false)
    @CreatedBy
    private String createdBy;

//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    private LocalDateTime createdAt;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Column(nullable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(nullable = false)
    private double area;

    @Column(nullable = false)
    private double perimeter;

    public Shape() {
    }

    public Shape(String type, List<Double> parameters) {
        this.type = type;
        this.parameters = parameters;
        this.version = ++version;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
    }

}
