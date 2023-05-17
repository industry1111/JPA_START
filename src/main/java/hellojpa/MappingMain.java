package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MappingMain {
    public static void main ( String[] args ) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml의 persistence-unit name
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            OldMovie movie = new OldMovie();
            movie.setDirector("봉준호");
            movie.setActor("송강호");
            movie.setName("기생충");
            movie.setPrice(10000);

            OldAlbum album = new OldAlbum();
            album.setArtist("아이유");
            album.setName("boo");
            album.setPrice(1000);

            OldBook book = new OldBook();
            book.setAuthor("룰루 밀러");
            book.setIsbn("1234123");
            book.setName("물고기는 존재하지 않는다");
            book.setPrice(100);

            em.persist(movie);
            em.persist(album);
            em.persist(book);

            em.flush();
            em.clear();

            OldItem findItem = em.find(OldItem.class, movie.getId());
            System.out.println(findItem.toString());

            tx.commit();
        } catch (Exception e) {

            tx.rollback();

        } finally {
            em.close();
        }

        emf.close();
    }
}
