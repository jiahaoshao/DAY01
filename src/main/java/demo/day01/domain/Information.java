package demo.day01.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Information")
@Data
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String avatar;
    private String username;
    private int age;
    private String email;
    private String mobilePhoneNumber;
    private String area;
    private int createDate;
    private String nickname;
    private String sex;
    private String work;
    private String hobby;
    private String design;
}
