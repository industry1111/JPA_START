## JPQL (Java Persistence Query Language)

JPQL은 JPA(Java Persistence API)의 쿼리 언어로, 객체 지향 데이터베이스 작업을 위해 사용됩니다. JPQL은 엔티티와 관련된 객체를 대상으로 쿼리를 작성하고 실행할 수 있습니다. JPQL은 SQL과 유사한 문법을 사용하지만, 테이블이 아닌 엔티티와 속성을 기반으로 작성됩니다.

JPQL은 다음과 같은 방법으로 사용할 수 있습니다:

1. `createQuery`: `EntityManager`의 `createQuery` 메서드를 사용하여 JPQL 쿼리를 생성합니다. JPQL 쿼리는 문자열 형태로 작성되며, 반환할 엔티티 클래스와 함께 `TypedQuery` 객체를 생성합니다.

2. `createNativeQuery`: `EntityManager`의 `createNativeQuery` 메서드를 사용하여 네이티브 SQL 쿼리를 실행할 수 있습니다. JPQL과 달리 SQL 문법을 사용하여 직접 쿼리를 작성합니다. 반환할 엔티티 클래스 대신 `ResultSet`를 사용하여 결과를 가져올 수도 있습니다.

아래는 JPQL과 네이티브 쿼리를 사용하는 예시입니다:

```java
// JPQL 쿼리
String jpql = "SELECT m FROM Member m WHERE m.name LIKE '%kim'";
TypedQuery<Member> query = em.createQuery(jpql, Member.class);
List<Member> resultList = query.getResultList();

// 네이티브 SQL 쿼리
String sql = "SELECT * FROM members WHERE name LIKE '%kim'";
Query nativeQuery = em.createNativeQuery(sql, Member.class);
List<Member> resultList = nativeQuery.getResultList();
```

JPQL과 네이티브 쿼리는 데이터베이스 작업을 수행하는 데 유용한 방법입니다. JPQL은 객체 지향적인 쿼리 작성을 제공하고, 네이티브 쿼리는 직접 SQL을 사용하여 세부적인 작업을 수행할 수 있습니다.

## QueryDSL

QueryDSL은 자바 기반의 오픈 소스 라이브러리로, SQL, JPA, MongoDB 등 다양한 데이터베이스와 ORM 프레임워크를 지원합니다. QueryDSL은 JPQL 및 네이티브 쿼리 작성을 보다 편리하고 안전하게 해줍니다.

QueryDSL을 사용하면 다음과 같은 장점을 얻을 수 있습니다:

- 타입 안정성: QueryDSL은 자바 코드로 쿼리를 작성하기 때문에 컴파일 시점에서 타입 검사를 할 수 있어 오류를 사전에 방지할 수 있습니다.
- 가독성: 메서드 체인을 사용하여 쿼리를 작성하므로 가독성이 좋아

집니다. 코드의 의도를 명확하게 표현할 수 있습니다.
- 유연성: 다양한 데이터베이스와 ORM 프레임워크를 지원하므로 일관된 방식으로 쿼리를 작성할 수 있습니다.
- IDE 지원: 대부분의 주요 IDE에서 QueryDSL을 지원하여 자동 완성, 문법 강조, 오류 검출 등의 기능을 제공합니다.

QueryDSL의 사용 예시는 다음과 같습니다:

```java
import static com.example.entity.QMember.member;

String nameKeyword = "kim";
List<Member> members = new JPAQueryFactory(em)
    .select(member)
    .from(member)
    .where(member.name.like("%" + nameKeyword + "%"))
    .fetch();
```

위 예시에서는 QueryDSL의 `QMember` 클래스를 사용하여 Member 엔티티와 관련된 쿼리를 작성합니다. `JPAQueryFactory`를 사용하여 QueryDSL 쿼리를 생성하고, `select`, `from`, `where` 등의 메서드를 사용하여 쿼리를 작성합니다. 최종적으로 `fetch` 메서드를 호출하여 결과를 가져옵니다.

QueryDSL을 사용하면 JPQL 및 네이티브 쿼리를 보다 쉽고 안전하게 작성할 수 있으며, 코드의 가독성과 유지보수성을 향상시킬 수 있습니다. QueryDSL의 자세한 사용 방법과 예제는 공식 문서와 다양한 자료를 참고하시기 바랍니다.