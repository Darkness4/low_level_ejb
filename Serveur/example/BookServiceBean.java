package example;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
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
    public Livre findById(String isbn) {
        return em.find(Livre.class, isbn);
    }

    @Override
    public List<Livre> findAll() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Livre> query = cb.createQuery(Livre.class);
        final CriteriaQuery<Livre> all = query.select(query.from(Livre.class));
        return em.createQuery(all).getResultList();
    }

    @Override
    public void delete(Livre livre) {
        livre = em.merge(livre);
        em.remove(livre);
        em.flush();
    }

    @Override
    public void deleteById(String isbn) {
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

    @Override
    public List<Livre> take(Livre... livres) {
        final List<Livre> modified = new ArrayList<>();
        for (Livre livre: livres) {
            if (livre.getDispo() != 0) {
                livre.decrementDispo();
                update(livre);
                modified.add(livre);
            }
        }
        return modified;
    }

    @Override
    public List<Livre> giveBack(Livre... livres) {
        final List<Livre> modified = new ArrayList<>();
        for (Livre livre: livres) {
            livre.incrementDispo();
            update(livre);
            modified.add(livre);
        }
        return modified;
    }
}
