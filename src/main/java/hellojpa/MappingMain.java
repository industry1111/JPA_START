package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MappingMain {
    public static void main ( String[] args ) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml의 persistence-unit name
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            List<oldmember> oldMemberList = em.createQuery(
                    "select m from oldmember m where m.username like '%kim'"
                    , oldmember.class
            ).getResultList();

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

        } finally {
            em.close();
        }

        emf.close();
    }
}
