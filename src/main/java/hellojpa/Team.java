package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "team") // 어느 변수와 맵핑되어있는지
    private List<oldMember> members = new ArrayList<>();

    public List<oldMember> getMembers() {
        return members;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
