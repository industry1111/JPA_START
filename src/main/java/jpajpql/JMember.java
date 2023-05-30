package jpajpql;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JMember {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private JTeam team;

    @OneToMany(mappedBy = "member")
    private List<JOrder> orderList = new ArrayList<>();
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public JTeam getTeam() {
        return team;
    }

    public void setTeam(JTeam team) {
        this.team = team;
    }
}
