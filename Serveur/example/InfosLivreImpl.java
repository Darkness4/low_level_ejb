package example;

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
    public List<LivreEmp> desemprunter() {
        final List<LivreEmp> modified = new ArrayList<>();
        var list = findAll();
        for (LivreEmp livreEmp : list) {
            livreEmp.setEmpruntepar(null);
            update(livreEmp);
            modified.add(livreEmp);
        }
        return modified;
    }

    @Override
    public LivreEmp update(LivreEmp livreEmp) {
        livreEmp = em.merge(livreEmp);
        em.flush();
        return livreEmp;
    }
}
