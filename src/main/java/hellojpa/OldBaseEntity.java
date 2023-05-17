package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/*
* 상속관계, 테이블과 매핑 및 엔티티가 아니며 자식 클래스에 매핑 정보만 제공
* 직접 사용할 경우가 없으므로 추상화 클래스로 생성
* JPA에서 상속이 가능한 경우는 부모 클래스가 @Entity 또는 @MappedSuperclass 어노테이션이 있어야 가능.
* */
@MappedSuperclass
public abstract class OldBaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
