package hellojpa;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //transient(비영속) 상태 : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
//            oldMember oldMember = new oldMember();
//            oldMember.setUsername("HelloJPA");

            //managed(영속) 상태 : 영속성 컨텍스트에 관리되는 상태
//            System.out.println("===Before===");
//            em.persist(oldMember);//update시에는 호출 x 영속성컨텍스트에서 변경감지를 하기 떄문에
//
//            System.out.println("===After===");

            //detached(준영속) 상태 : 영속성 컨텍스트에 저장되었다가 분리된 상태
//            em.detach(member);
//            em.clear() : 영속성 컨텍스트를 모두 초기화
            //remove(삭제) 상태 : 영속성 컨텍스트에서 삭제된 상태
//            em.remove(member);

//            oldMember findOldMember = em.find(oldMember.class,101L);  // 생성시 영속성컨텍스트의 1차캐시에 저장되어 있어 DB에서 조회하는 쿼리 x
//            oldMember findOldMember2 = em.find(oldMember.class,100L); // DB에서 조회 후 1차캐시에 저장
//            oldMember findOldMember3 = em.find(oldMember.class,100L); // 1차캐시에 해당 객체가 있기 떄문에 1차캐시에서 가져옴
//            System.out.println(findOldMember2 == findOldMember3); //true 영속 엔티티 동일성 보장
//            System.out.println(findOldMember.toString());

//            Query query = em.createQuery("select m from oldMember m", oldMember.class); //flush자동호출
            //커밋이 되기전까지 영속성컨텍스트 내부의 SQL저장소에 객체의 변경상태를 감지한 후  sql생성후 커밋 시 flush


            //저장
            Team team = new Team();
            team.setName("TEAMA");
            em.persist(team);

            oldMember oldMember1 = new oldMember();
            oldMember1.setUsername("MEMBER1");
            oldMember1.setTeam(team);
            em.persist(oldMember1);

            em.flush();
            em.clear();

            oldMember findMember = em.find(oldMember.class,oldMember1.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("find Team : " + findTeam.getName());

            tx.commit();

            //flush : 영속성컨텍스트의 변경내용을 데이터베이스에 반영
            /* flush 발생 : 변경감지, 수정된 엔티티쓰기 지연 sql에 저장소에 등록 , SQL저장소의 쿼리를 데이터베이스에 전송
            flush하는 방법
            * 1.em.flush()
            * 2.트랜잭션 커밋
            * 3.JPQL쿼리 실행
            */
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
