package demo.day01.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Data
@Table(name = "message")
@Entity
public class message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String receiver;
    private String sender;
//    private long timestamp;
    private LocalTime timestamp;
    private boolean isSelf;

    public void setIsSelfFromString(String isSelfString) {
        this.isSelf = Boolean.parseBoolean(isSelfString);
    }
}

