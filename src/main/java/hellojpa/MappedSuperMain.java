package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class MappedSuperMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            oldmember member = new oldmember();
            member.setUsername("맵슈퍼클래스");
            member.setCreatedBy("Ko");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            oldmember findMember = em.find(oldmember.class, member.getId());
            System.out.println("find Member Id : " + findMember.getId() + "createdBy : " + findMember.getCreatedBy() + "createdDate : " + findMember.getCreatedDate());

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
