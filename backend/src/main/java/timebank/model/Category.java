package timebank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "idCategory")
  private long idCategory;

  @Column(name = "name")
  private String name;

  public long getIdCategory() {
    return idCategory;
  }

  public String getName() {
    return name;
  }

}
