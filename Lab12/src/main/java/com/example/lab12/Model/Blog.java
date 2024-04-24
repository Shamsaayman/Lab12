package com.example.lab12.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    //    @NotEmpty(message = "title cannot be empty")
    //    @Size(min = 4 , max = 10)
    //    @Column(columnDefinition = "varchar(10) not null unique")
    private String title;
    //    @NotEmpty(message = "body cannot be empty")
    //    @Size(min = 4)
    //    @Column(columnDefinition = "varchar(100) not null ")
    private String body;
    @ManyToOne
    @JoinColumn(name= "user_id" , referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
