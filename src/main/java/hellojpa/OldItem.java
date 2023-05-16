package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//JOINED(조인전략),SINGLE_TABLE(단일전략)   // TABLE_PER_CLASS는사용 추천하지않음
@DiscriminatorColumn //DTYPE 컬럼 생
public abstract class OldItem {

    @Id @GeneratedValue
    @Column(name = "OLDITEM_ID")
    private Long id;

    private String name;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OldItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
