package lt.codeacademy.blog.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data

@Entity
@Table(name = "Blogs")
public class Blog {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "post_id")
   private Long postId;
   @Column(name = "title")
   @NotEmpty(message = "Headline must not be empty")
   private String title;
   @Column(name = "description")
   @NotEmpty(message = "Text field must not be empty")
   private String description;
   @Column(name = "author")
   @NotEmpty(message = "Author field must not be empty")
   private String author;


}
