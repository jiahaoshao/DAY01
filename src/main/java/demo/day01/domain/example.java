package demo.day01.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "example")
@Data
public class example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long createtime;
    private String email;
    private String username;
    private String rolename;
}
