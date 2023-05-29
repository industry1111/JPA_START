package jpabook.jpashop;

import jpabook.jpashop.domain.Member;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            List<Member> resultList = em.createQuery("select m from Member m where m.name like '%kim'", Member.class).getResultList();
            List<Member> resultList2 = em.createNativeQuery("select member_id, city, street, zipcode, name from member", Member.class).getResultList();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
