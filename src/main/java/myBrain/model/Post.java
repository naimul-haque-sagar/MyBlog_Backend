package myBrain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column
    private String title;
    @Lob
    @Column
    @NotEmpty
    private String content;
    @Column
    private Instant createdOn;
    @Column
    private Instant updatedOn;
    @Column
    @NotBlank
    private String username;

}
