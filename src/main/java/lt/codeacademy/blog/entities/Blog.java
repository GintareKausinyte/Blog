package lt.codeacademy.blog.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
   @Length(min = 3, max = 255)
   private String title;
   @Column(name = "description")
   @Length(min = 1, max = 1000)
   private String description;
   @Column(name = "author")
   @Length(min = 3, max = 255)
   private String author;


}
