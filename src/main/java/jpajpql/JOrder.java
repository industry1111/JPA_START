package jpajpql;

import javax.persistence.*;

@Entity
public class JOrder {

    @Id @GeneratedValue
    private long id;

    private int orderAmount;

    @Embedded
    private JAddress address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private JMember member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private JProduct product;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public JAddress getAddress() {
        return address;
    }

    public void setAddress(JAddress address) {
        this.address = address;
    }
}
