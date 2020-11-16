package example;

import example.entities.LivreEmp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class InfosLivreImpl implements InfosLivre {
    @PersistenceContext(unitName = "Biblio_PU")
    private EntityManager em;


    @Override
    public LivreEmp findById(String isbn) {
        return em.find(LivreEmp.class, isbn);
    }

    @Override
    public List<LivreEmp> findAll() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<LivreEmp> query = cb.createQuery(LivreEmp.class);
        final CriteriaQuery<LivreEmp> all = query.select(query.from(LivreEmp.class));
        return em.createQuery(all).getResultList();
    }

    @Override
    public List<LivreEmp> reinitialiser() {
        final List<LivreEmp> modified = new ArrayList<>();
        final List<LivreEmp> list = findAll();
        for (LivreEmp livreEmp : list) {
            livreEmp.setEmpruntepar(0);
            livreEmp = em.merge(livreEmp);
            modified.add(livreEmp);
        }
        em.flush();
        return modified;
    }
}
