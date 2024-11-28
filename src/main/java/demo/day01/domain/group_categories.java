package demo.day01.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "group_categories")
@Entity
public class group_categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int groupId;

    private int categoryId;
}
