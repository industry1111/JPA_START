package jpajpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JTeam {

    @Id @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<JMember> members = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JMember> getMembers() {
        return members;
    }

    public void setMembers(List<JMember> members) {
        this.members = members;
    }
}
