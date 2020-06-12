package lt.codeacademy.blog.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
   @NotEmpty
   @Length(max = 100)
   private String title;
   @Column(name = "description")//padaryt kad rodytu characteriu skaiciu rasant
   @NotEmpty
   @Length(max = 1000)
   private String description;
   @Column(name = "author")
   @NotEmpty
   @Length(max = 200)
   private String author;


}
