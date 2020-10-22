package example;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class BookServiceBean implements BookServiceRemote {
    @PersistenceContext(unitName = "Biblio_PU")
    private EntityManager em;

    @Override
    public Livre create(Livre livre) {
        em.persist(livre);
        em.flush();
        return livre;
    }

    @Override
    public Livre find(String isbn) {
        return em.find(Livre.class, isbn);
    }

    @Override
    public List<Livre> findAll() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Livre> cq = cb.createQuery(Livre.class);
        final Root<Livre> rootEntry = cq.from(Livre.class);
        final CriteriaQuery<Livre> all = cq.select(rootEntry);
        final TypedQuery<Livre> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void delete(Livre livre) {
        livre = em.merge(livre);
        em.remove(livre);
        em.flush();
    }

    @Override
    public void delete(String isbn) {
        final Livre livre = em.find(Livre.class, isbn);
        em.remove(livre);
        em.flush();
    }

    @Override
    public Livre update(Livre livre) {
        livre = em.merge(livre);
        em.flush();
        return livre;
    }
}
