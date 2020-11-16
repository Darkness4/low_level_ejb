package example;

import example.entities.LivreEmp;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface InfosLivre {
    LivreEmp findById(String isbn);
    List<LivreEmp> findAll();
    List<LivreEmp> reinitialiser();
}
