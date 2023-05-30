package jpajpql;

import javax.persistence.*;
import java.util.List;

public class jpqlMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            JMember member = new JMember();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //반환 타입이 확실할 떄
            TypedQuery<JMember> query = em.createQuery("select m from JMember m where m.username like '%kim%' ", JMember.class);
            //불확실
            Query query1 = em.createQuery("select m from JMember m where m.username like '%kim%'");

            //다건 조회 Exception 발생 X
            List<JMember> resultList = query.getResultList(); // 단건 query.getSingleResult() Exception 발생 O - 조회 결과가 없을 시, 둘 이상일 시

            //바인드 변수 query.setParameter("변수명", 값);
            TypedQuery<JMember> query2 = em.createQuery("select m from JMember m where m.username = :username", JMember.class);
            query2.setParameter("username", "member1");
            List<JMember> resultList1 = query2.getResultList();
            System.out.println("resultList1 = " + resultList1.get(0).getUsername());


            tx.commit();
        } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            } finally {
                em.close();
        }
        emf.close();
    }
}
