package example;

import javax.ejb.*;

@Remote
public interface GestionLivre {
  public Livre nouveauLivre(String isbn, String title);
}
