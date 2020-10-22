package example;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BookServiceRemote {
    Livre create(Livre livre);
    Livre find(String isbn);
    List<Livre> findAll();
    void delete(Livre livre);
    void delete(String isbn);
    Livre update(Livre livre);
}
