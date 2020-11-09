package example;

import example.exceptions.LivreDejaEmprunte;
import example.exceptions.NbMaxEmpruntsAtteint;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface GestionEmprunt {
    int MAX_EMPRUNT = 3;
    Emprunteur find(int numemp);
    List<Emprunteur> desemprunter();
    List<Emprunteur> findAll();
    LivreEmp updateLivreEmp(LivreEmp livreEmp);
    Emprunteur update(Emprunteur emprunteur);
    List<LivreEmp> take(Emprunteur emprunteur, LivreEmp... livreEmps) throws NbMaxEmpruntsAtteint, LivreDejaEmprunte;
    List<LivreEmp> giveBack(Emprunteur emprunteur, LivreEmp... livreEmps);
}
