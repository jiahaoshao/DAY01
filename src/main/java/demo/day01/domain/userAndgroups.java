package demo.day01.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userAndgroups")
public class userAndgroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private int groupId;
}
