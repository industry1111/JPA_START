package hellojpa;


import javax.persistence.*;

@Entity
//@Table ( name = "aaa" )
//name defautl 값은 클래스 명  ( name = "Member" )
//@SequenceGenerator( name ="MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ")
public class oldMember {

    /*
        @Id :PK 직접할당
        @GeneratedValue
            IDENTITY : 데이터베이스에 위임
            SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용 , ORACLE @SequenceGenerator 필요
            TABLE :
            AUTO : 방언에 따라 자동 지정, 기본

        @Column : 컬럼매핑
            name = ""(default): 매핑할 테이블의 컬럼이름(객체의 필드이름),
            insertable/updatable : 등록 변경 가능여부 (TRUE) - DB에 컬럼 반영여부
            nullabe(DDL) : null 값 허용 여부(TRUE)  false 설정 시 NOT NULL 제약조건 생성
            unique(DDL) : @Table(uniqueConstrains와 같음 )
            columnDefinition(DDL) : 데이터베이스 컬럼 정보를 직접 줄 수 있다.
            length(DDL) :  문자길이 제약 조건 String  타입에만 서용 (255)
            precision/scale : BigDecimal/BigInteger 타입에서 사용 precision 은 소수점 포함 전체자릿수, scale 은 소수점 자릿수
        @Temporal : 날짜타입 매핑
            LocalDate, LocalDateTime 사용할 때는 생략 가능
        @Enumerated : enum 타입 매핑
        @Lob : BLOB,CLOB 매핑
        @Transient : 특정 필드를 컬럼에 매핑하지 않음 (매핑 무시)
     */

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "name" )
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "oldMember{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team.getId() +
                ", teamName=" + team.getName() +
                '}';
    }
}
