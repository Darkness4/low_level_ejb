package example;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BookServiceRemote {
    Livre create(Livre livre);
    Livre findById(String isbn);
    List<Livre> findAll();
    void delete(Livre livre);
    void deleteById(String isbn);
    Livre update(Livre livre);
    List<Livre> take(Livre ... livres);
    List<Livre> giveBack(Livre ... livres);
}
