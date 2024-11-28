package demo.day01.domain;

import lombok.Data;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String username;
    private int postId;
    private int dianzan;
    private int fenxiang;
    private int pinglun;
    private LocalTime time;
    private Date date;
}
