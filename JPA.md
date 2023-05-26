### JPA 데이터 타입

JPA 데이터 타입은 크게 엔티티 타입과 값 타입으로 분류됩니다.

#### 엔티티 타입

- `@Entity`로 정의하는 객체입니다.
- 데이터가 변해도 식별자로 지속해서 추적할 수 있습니다.
- 다른 엔티티와의 관계를 형성하여 객체 간의 연관성을 표현할 수 있습니다.
- 독립적인 생명주기를 가지고 영속성 컨텍스트에 의해 관리됩니다.

#### 값 타입

- 단순한 값으로 사용하는 자바 기본 타입이나 객체입니다.
- 식별자가 없고 값만을 가지므로 변경 시 추적이 불가능합니다.
- 주로 단순한 값의 표현에 사용됩니다.

    - **기본값 타입**
        - 단순한 기본 타입(예: String, int)을 말합니다.
        - 생명주기를 엔티티에 의존하며, 값 타입은 공유되지 않습니다.

    - **임베디드 타입**
        - 복합값 타입으로 새로운 값을 직접 정의할 수 있습니다.
        - 주로 기본 값 타입을 모아서 복합값을 만들어 사용합니다.
        - 객체와 테이블을 아주 세밀하게 매핑하는 것이 가능
        - 예를 들어, 주소(Address)라는 복합값 타입을 정의하여 사용할 수 있습니다.

    - **컬렉션 값 타입**
        - 자바 컬렉션(List, Set, Map)을 값 타입으로 사용하는 것을 말합니다.
        - 한 엔티티가 여러 개의 값 타입을 가질 수 있는 경우 사용됩니다.

엔티티 타입은 객체의 독립성과 관계 형성을 위해 값 타입은 단순한 값의 표현과 불변성을 위해,  임베디드 타입은 복합값의 관리와 코드의 가독성을 위해, 컬렉션 값 타입은 다중값의 관리와 유연성을 위해 사용됩니다.

```java
@Entity
public class User {
    @Id
    private Long id;
    
    private String name;
    
    @Embedded
    private Address homeAddress;
    
    @Embedded
    @AttributeOverride(name = "street", column = @Column(name = "work_street"))
    @AttributeOverride(name = "city", column = @Column(name = "work_city"))
    private Address workAddress;
}
```
`Address`는 별도로 정의된 임베디드 타입으로, `street`, `city`, `state`, `zipCode`라는 속성을 가지고 있습니다.

- `homeAddress`, `workAddress`: 임베디드 타입인 `Address`를 사용하는 필드입니다. `@Embedded` 어노테이션이 적용되어 해당 필드들이 임베디드 타입으로 매핑됨을 나타냅니다.

`workAddress` 필드에는 `@AttributeOverride` 어노테이션이 적용되어 `street` 속성이 `work_street` 컬럼으로 매핑되고, `city` 속성이 `work_city` 컬럼으로 매핑되도록 재정의되었습니다.