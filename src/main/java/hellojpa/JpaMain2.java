package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("TEAMA");
            em.persist(team);

            oldMember oldMember1 = new oldMember();
            oldMember1.setUsername("MEMBER1");
            oldMember1.changeTeam(team);

            em.persist(oldMember1);

            em.flush();
            em.clear();

//            oldMember findMember = em.find(oldMember.class,oldMember1.getId());
            oldMember findMember = em.getReference(oldMember.class, oldMember1.getId());
            System.out.println("findMember :" + findMember.getClass()); //findMember :class hellojpa.oldMember$HibernateProxy$0wxNer8b 하이버네이트에서 임의로 만든 클래스란걸 확인 가능
            System.out.println("findMember Username : " + findMember.getUsername());


//            List<oldMember> members = findMember.getTeam().getMembers();
//            for (oldMember member : members) {
//                System.out.println("member : " +member.getUsername());
//            }
//            Team findTeam = em.find(Team.class, 1L);
//            List<oldMember> members1 = findTeam.getMembers();
//            for (oldMember member : members1) {
//                System.out.println("member1 : " + member.getUsername());
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}


