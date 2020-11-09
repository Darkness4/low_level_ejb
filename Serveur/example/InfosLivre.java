package example;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface InfosLivre {
    LivreEmp findById(String isbn);
    List<LivreEmp> findAll();
    List<LivreEmp> desemprunter();
    LivreEmp update(LivreEmp livreEmp);
}
