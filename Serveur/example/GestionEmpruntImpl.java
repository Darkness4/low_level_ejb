package example;

import example.exceptions.LivreDejaEmprunte;
import example.exceptions.NbMaxEmpruntsAtteint;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class GestionEmpruntImpl implements GestionEmprunt {
    @PersistenceContext(unitName = "Biblio_PU")
    private EntityManager em;

    @Override
    public Emprunteur find(int numemp) {
        return em.find(Emprunteur.class, numemp);
    }

    @Override
    public List<Emprunteur> desemprunter() {
        final List<Emprunteur> modified = new ArrayList<>();
        var list = findAll();
        for (Emprunteur emprunteur : list) {
            emprunteur.setNblivresemp(0);
            update(emprunteur);
            modified.add(emprunteur);
        }
        return modified;
    }

    @Override
    public Emprunteur update(Emprunteur emprunteur) {
        em.persist(emprunteur);
        em.flush();
        return emprunteur;
    }

    @Override
    public LivreEmp updateLivreEmp(LivreEmp livreEmp) {
        livreEmp = em.merge(livreEmp);
        em.flush();
        return livreEmp;
    }

    @Override
    public List<LivreEmp> take(Emprunteur emprunteur, LivreEmp... livreEmps) throws NbMaxEmpruntsAtteint, LivreDejaEmprunte {
        final List<LivreEmp> modified = new ArrayList<>();
        for (LivreEmp livreEmp : livreEmps) {
            if (livreEmp.getEmpruntepar() == null) {
                if (emprunteur.getNblivresemp() < GestionEmprunt.MAX_EMPRUNT) {
                    emprunteur = find(emprunteur.getNumemp());
                    emprunteur.incrementNblivresemp();
                    update(emprunteur);

                    livreEmp.setEmpruntepar(emprunteur);
                    updateLivreEmp(livreEmp);
                    modified.add(livreEmp);
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
    public List<LivreEmp> giveBack(Emprunteur emprunteur, LivreEmp... livreEmps) {
        final List<LivreEmp> modified = new ArrayList<>();
        for (LivreEmp livreEmp : livreEmps) {
            if (livreEmp.getEmpruntepar().getNumemp() == emprunteur.getNumemp()) {
                emprunteur = find(emprunteur.getNumemp());
                emprunteur.decrementNblivresemp();
                update(emprunteur);

                livreEmp = em.find(LivreEmp.class, livreEmp.getIsbn());
                livreEmp.setEmpruntepar(null);
                updateLivreEmp(livreEmp);
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
