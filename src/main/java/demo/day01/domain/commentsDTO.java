package demo.day01.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name="posts")
public class commentsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String username;
    private int groupId;
    private int fenxiang;
    private int dianzan;
    private int pinglun;
    private LocalTime time;
    private Date date;
}
