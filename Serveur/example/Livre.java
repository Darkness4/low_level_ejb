package example;

import java.io.Serializable;
import javax.ejb.*;

@Entity
@Table(name = "livre")
public class Livre implements Serializable {
  @Id
  @NotNull
  private String isbn;

  private String title;

  @NotNull
  private int dispo;

  public Livre() {
  }

  public Livre(String isbn, String title, int dispo) {
    this.isbn = isbn;
    this.title = title;
    this.dispo = dispo;
  }

  public Livre(String isbn, String title) {
    this(isbn, title, 1);
  }
}