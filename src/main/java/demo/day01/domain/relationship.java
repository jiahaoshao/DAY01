package demo.day01.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "relationship")
@Data
public class relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String requester;
    private String receiver;
    private int status;

}
