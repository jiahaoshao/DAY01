package demo.day01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "groupsmain")
@Entity
public class groupMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String name;
    private String description;
    private String image;
    private String createName;
    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private List<Category> categoryList;

    // Getter 和 Setter 方法...

    @Transient
    private String categories;

}
