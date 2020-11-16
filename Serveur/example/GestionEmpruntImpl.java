package example;

import example.entities.Emprunteur;
import example.entities.LivreEmp;
import example.exceptions.LivreDejaEmprunte;
import example.exceptions.NbMaxEmpruntsAtteint;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class GestionEmpruntImpl implements GestionEmprunt {
    Emprunteur empSession = null;

    @PersistenceContext(unitName = "Biblio_PU")
    private EntityManager em;

    @Override
    public Emprunteur gerer(int numemp) {
        empSession = em.find(Emprunteur.class, numemp);
        return empSession;
    }

    @Override
    public List<Emprunteur> reinitialiser() {
        final List<Emprunteur> modified = new ArrayList<>();
        var list = findAll();
        for (Emprunteur emprunteur : list) {
            emprunteur.setNblivresemp(0);
            emprunteur = em.merge(emprunteur);
            modified.add(emprunteur);
        }
        return modified;
    }

    @Override
    public List<LivreEmp> take(LivreEmp... livreEmps) throws NbMaxEmpruntsAtteint, LivreDejaEmprunte {
        final List<LivreEmp> modified = new ArrayList<>();
        for (LivreEmp livreEmp : livreEmps) {
            if (livreEmp.getEmpruntepar() == 0) {
                if (empSession.getNblivresemp() < GestionEmprunt.MAX_EMPRUNT) {
                    empSession.incrementNblivresemp();
                    empSession = em.merge(empSession);

                    livreEmp.setEmpruntepar(empSession.getNumemp());
                    livreEmp = em.merge(livreEmp);
                    modified.add(livreEmp);
                    em.flush();
                } else {
                    throw new NbMaxEmpruntsAtteint();
                }
            } else {
                throw new LivreDejaEmprunte();
            }
        }
        return modified;
    }

    @Override
    public List<LivreEmp> giveBack(LivreEmp... livreEmps) {
        final List<LivreEmp> modified = new ArrayList<>();
        for (LivreEmp livreEmp : livreEmps) {
            if (livreEmp.getEmpruntepar() == empSession.getNumemp()) {
                empSession.decrementNblivresemp();
                empSession = em.merge(empSession);

                livreEmp = em.find(LivreEmp.class, livreEmp.getIsbn());
                livreEmp.setEmpruntepar(0);
                livreEmp = em.merge(livreEmp);
                modified.add(livreEmp);
            }
        }
        return modified;
    }

    @Override
    public List<Emprunteur> findAll() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Emprunteur> query = cb.createQuery(Emprunteur.class);
        final CriteriaQuery<Emprunteur> all = query.select(query.from(Emprunteur.class));
        return em.createQuery(all).getResultList();
    }
}
