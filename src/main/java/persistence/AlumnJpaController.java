package persistence;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import models.Alumn;

public class AlumnJpaController implements Serializable {

    EntityManagerFactory emf = null;

    public AlumnJpaController() {
        this.emf = Persistence.createEntityManagerFactory("crudPU");
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    //OPERACIONES CRUD USANDO JPA
    
    //create
    public void create(Alumn alumno) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        }
    }

    //update
    public void edit(Alumn alumno) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            alumno = em.merge(alumno);
            em.getTransaction().commit();
        }
    }

    //delete
    public void destroy(int id) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Alumn alumno;
            alumno = em.getReference(Alumn.class, id);
            alumno.getAlumnId();
            em.remove(alumno);
            em.getTransaction().commit();
        }
    }

    //read all
    public List<Alumn> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    //read
    public List<Alumn> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<Alumn> findUserEntities(boolean all, int maxResults, int firstResult) {
        try (EntityManager em = getEntityManager()) {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumn.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }
    }

    public Alumn findUser(int id) {
        try (EntityManager em = getEntityManager()) {
            return em.find(Alumn.class, id);
        }
    }

    public int getUserCount() {
        try (EntityManager em = getEntityManager()) {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumn> rt = cq.from(Alumn.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
    }
}
