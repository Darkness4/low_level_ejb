package example;

import javax.ejb.*;

@Stateless
public class GestionLivreImpl implements GestionLivre {
  @Override
  public Livre nouveauLivre(String isbn, String title) {
    return new Livre(isbn, title);
  }
}
