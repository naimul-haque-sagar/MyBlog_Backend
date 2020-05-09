package myBrain.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
}
