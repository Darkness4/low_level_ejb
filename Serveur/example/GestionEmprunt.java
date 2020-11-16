package example;

import example.entities.Emprunteur;
import example.entities.LivreEmp;
import example.exceptions.LivreDejaEmprunte;
import example.exceptions.NbMaxEmpruntsAtteint;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface GestionEmprunt {
    Emprunteur gerer(int numemp);
    List<Emprunteur> reinitialiser();
    List<Emprunteur> findAll();
    List<LivreEmp> take(LivreEmp... livreEmps) throws NbMaxEmpruntsAtteint, LivreDejaEmprunte;
    List<LivreEmp> giveBack(LivreEmp... livreEmps);
}
