## JPQL (Java Persistence Query Language)

JPQL은 JPA(Java Persistence API)의 쿼리 언어로, 객체 지향 데이터베이스 작업을 위해 사용하며 엔티티와 관련된 객체를 대상으로 쿼리를 작성하고 실행 가능. 
SQL과 유사한 문법을 사용하지만, 테이블이 아닌 엔티티와 속성을 기반으로 작성

JPQL기본적인 메서드는 다음과 같습니다:

1. `createQuery`: `EntityManager`의 `createQuery` 메서드를 사용하여 JPQL 쿼리를 생성합니다. JPQL 쿼리는 문자열 형태로 작성되며, 반환할 엔티티 클래스와 함께 `TypedQuery` 객체를 생성, 반환타입을 특정하지 못할 땐 `Query` 객체를 생성

2. `createNativeQuery`: `EntityManager`의 `createNativeQuery` 메서드를 사용하여 네이티브 SQL 쿼리를 실행할 수 있습니다. JPQL과 달리 SQL 문법을 사용하여 직접 쿼리를 작성합니다. 반환할 엔티티 클래스 대신 `ResultSet`를 사용하여 결과를 가져올 수도 있습니다.

3. `getResultList()` : 다건의 데이터를 조회 해오며 `Exception` 발생하지 않으며, 조회 결과가 없으면 빈 리스트 반환.

4. `getSingleResult()` : 단건의 데이터를 조회 해오며, 조회 결과가 없으면 `NoResultException` 발생, 조회 결과가 둘 이상이면 `NonUniqueResultException` 발생하므로 예외처리 필수

5.  바인드 변수의 경우 `setParameter()` 메서드를 사용하여 값을 설정할 수 있습니다. 바인드 변수는 `?` 또는 `:name` 형태로 작성할 수 있습니다.
```java
// JPQL 쿼리
String jpql = "SELECT m FROM Member m WHERE m.name LIKE '%kim'";
TypedQuery<Member> query = em.createQuery(jpql, Member.class);
List<Member> resultList = query.getResultList();

// 네이티브 SQL 쿼리
String sql = "SELECT * FROM members WHERE name LIKE '%kim'";
Query nativeQuery = em.createNativeQuery(sql, Member.class);
List<Member> resultList = nativeQuery.getResultList();

// 바인드 변수 사용
String jpql = "SELECT m FROM Member m WHERE m.name LIKE :name";
TypedQuery<Member> query = em.createQuery(jpql, Member.class);
query.setParameter("name", "%kim");
List<Member> resultList = query.getResultList();
```

JPQL과 네이티브 쿼리는 데이터베이스 작업을 수행하는 데 유용한 방법입니다. JPQL은 객체 지향적인 쿼리 작성을 제공하고, 네이티브 쿼리는 직접 SQL을 사용하여 세부적인 작업을 수행할 수 있습니다.

### 프로젝션
JPQL에서는 엔티티 뿐만 아니라 엔티티의 속성을 조회할 수 있습니다. 이를 프로젝션(projection)이라고 합니다. 프로젝션은 다음과 같이 작성할 수 있습니다:

```java
// 엔티티 프로젝션
String jpql1 = "SELECT m FROM Member m";
String jpql2 = "SELECT t FROM Member m join m.team t";
// 임베디드 프로젝션
String jpql3 = "SELECT o.address FROM Order o";
// 스칼라 프로젝션
String jpql4 = "SELECT distinct m.name, m.age FROM Member m";
```

#### 프로젝션 - 여러값 조회
JPQL에서는 여러 값을 조회할 때 `Object[]` 또는 `List<Object[]>` 타입으로 조회할 수 있습니다. `Object[]`는 조회 결과를 `Object` 배열로 반환하며, `List<Object[]>`는 조회 결과를 `Object[]`를 원소로 갖는 리스트로 반환합니다.

```java
// Object[] 타입 조회
String jpql1 = "SELECT m.name, m.age FROM Member m";
List<Object[]> resultList1 = em.createQuery(jpql1, Object[].class)
    .getResultList();
for (Object[] row : resultList1) {
    String name = (String) row[0];
    int age = (int) row[1];
}

//new 명령어로 조회
String jpql2 = "SELECT new jpabook.jpql.MemberDTO(m.name, m.age) FROM Member m";
List<UserDTO> resultList2 = em.createQuery(jpql2, UserDTO.class)
    .getResultList();
for (MemberDTO memberDTO : resultList2) {
    String name = memberDTO.getName();
    int age = memberDTO.getAge();
}
```

### 페이징
JPQL에서는 `setFirstResult()`과 `setMaxResults()` 메서드를 사용하여 페이징을 할 수 있습니다. `setFirstResult()`은 조회 시작 위치를 설정하며, `setMaxResults()`는 조회할 데이터 수를 설정합니다.

```java
String jpql = "SELECT m FROM Member m ORDER BY m.name DESC";
List<Member> resultList = em.createQuery(jpql, Member.class)
    .setFirstResult(10)
    .setMaxResults(20)
    .getResultList();
```

### 서브쿼리
JPQL에서는 서브쿼리를 사용할 수 있습니다. 서브쿼리는 `WHERE`, `HAVING`, `SELECT` 절에서 사용할 수 있습니다. 서브쿼리는 다음과 같이 작성할 수 있습니다:

```java
// WHERE 절 서브쿼리
String jpql1 = "SELECT m FROM Member m WHERE m.age > (SELECT AVG(m2.age) FROM Member m2)";
// FROM 절 서브쿼리
String jpql2 = "SELECT m, (SELECT COUNT(o) FROM Order o WHERE o.member = m) FROM Member m";
// SELECT 절 서브쿼리
String jpql3 = "SELECT (SELECT AVG(m2.age) FROM Member m2) FROM Member m";
```

#### 서브쿼리 지원 함수
JPQL에서는 다음과 같은 서브쿼리 지원 함수를 제공합니다:## JPQL (Java Persistence Query Language)

JPQL은 JPA(Java Persistence API)의 쿼리 언어로, 객체 지향 데이터베이스 작업을 위해 사용하며 엔티티와 관련된 객체를 대상으로 쿼리를 작성하고 실행 가능.
SQL과 유사한 문법을 사용하지만, 테이블이 아닌 엔티티와 속성을 기반으로 작성

JPQL기본적인 메서드는 다음과 같습니다:

1. `createQuery`: `EntityManager`의 `createQuery` 메서드를 사용하여 JPQL 쿼리를 생성합니다. JPQL 쿼리는 문자열 형태로 작성되며, 반환할 엔티티 클래스와 함께 `TypedQuery` 객체를 생성, 반환타입을 특정하지 못할 땐 `Query` 객체를 생성

2. `createNativeQuery`: `EntityManager`의 `createNativeQuery` 메서드를 사용하여 네이티브 SQL 쿼리를 실행할 수 있습니다. JPQL과 달리 SQL 문법을 사용하여 직접 쿼리를 작성합니다. 반환할 엔티티 클래스 대신 `ResultSet`를 사용하여 결과를 가져올 수도 있습니다.

3. `getResultList()` : 다건의 데이터를 조회 해오며 `Exception` 발생하지 않으며, 조회 결과가 없으면 빈 리스트 반환.

4. `getSingleResult()` : 단건의 데이터를 조회 해오며, 조회 결과가 없으면 `NoResultException` 발생, 조회 결과가 둘 이상이면 `NonUniqueResultException` 발생하므로 예외처리 필수

5.  바인드 변수의 경우 `setParameter()` 메서드를 사용하여 값을 설정할 수 있습니다. 바인드 변수는 `?` 또는 `:name` 형태로 작성할 수 있습니다.
```java
// JPQL 쿼리
String jpql = "SELECT m FROM Member m WHERE m.name LIKE '%kim'";
TypedQuery<Member> query = em.createQuery(jpql, Member.class);
List<Member> resultList = query.getResultList();

// 네이티브 SQL 쿼리
String sql = "SELECT * FROM members WHERE name LIKE '%kim'";
Query nativeQuery = em.createNativeQuery(sql, Member.class);
List<Member> resultList = nativeQuery.getResultList();

// 바인드 변수 사용
String jpql = "SELECT m FROM Member m WHERE m.name LIKE :name";
TypedQuery<Member> query = em.createQuery(jpql, Member.class);
query.setParameter("name", "%kim");
List<Member> resultList = query.getResultList();
```

JPQL과 네이티브 쿼리는 데이터베이스 작업을 수행하는 데 유용한 방법입니다. JPQL은 객체 지향적인 쿼리 작성을 제공하고, 네이티브 쿼리는 직접 SQL을 사용하여 세부적인 작업을 수행할 수 있습니다.

### 프로젝션
JPQL에서는 엔티티 뿐만 아니라 엔티티의 속성을 조회할 수 있습니다. 이를 프로젝션(projection)이라고 합니다. 프로젝션은 다음과 같이 작성할 수 있습니다:

```java
// 엔티티 프로젝션
String jpql1 = "SELECT m FROM Member m";
String jpql2 = "SELECT t FROM Member m join fetch m.team t"; //inner join
// 임베디드 프로젝션
String jpql3 = "SELECT o.address FROM Order o";
// 스칼라 프로젝션
String jpql4 = "SELECT distinct m.name, m.age FROM Member m";
```

#### 프로젝션 - 여러값 조회
JPQL에서는 여러 값을 조회할 때 `Object[]` 또는 `List<Object[]>` 타입으로 조회할 수 있습니다. `Object[]`는 조회 결과를 `Object` 배열로 반환하며, `List<Object[]>`는 조회 결과를 `Object[]`를 원소로 갖는 리스트로 반환합니다.

```java
// Object[] 타입 조회
String jpql1 = "SELECT m.name, m.age FROM Member m";
List<Object[]> resultList1 = em.createQuery(jpql1, Object[].class)
    .getResultList();
for (Object[] row : resultList1) {
    String name = (String) row[0];
    int age = (int) row[1];
}

//new 명령어로 조회
String jpql2 = "SELECT new jpabook.jpql.MemberDTO(m.name, m.age) FROM Member m";
List<UserDTO> resultList2 = em.createQuery(jpql2, UserDTO.class)
    .getResultList();
for (MemberDTO memberDTO : resultList2) {
    String name = memberDTO.getName();
    int age = memberDTO.getAge();
}
```

### 페이징
JPQL에서는 `setFirstResult()`과 `setMaxResults()` 메서드를 사용하여 페이징을 할 수 있습니다. `setFirstResult()`은 조회 시작 위치를 설정하며, `setMaxResults()`는 조회할 데이터 수를 설정합니다.

```java
String jpql = "SELECT m FROM Member m ORDER BY m.name DESC";
List<Member> resultList = em.createQuery(jpql, Member.class)
    .setFirstResult(10)
    .setMaxResults(20)
    .getResultList();
```

### 타입표현
- 문자 : 'HELLO', 'She''s'
- 숫자 : 10L(Long), 10D(Double), 10F(Float)
- Boolean : TRUE, FALSE
- ENUM : jpabook.MemberType.Admin (패키지명 포함)
- 엔티티 타입 : TYPE(m) = Member (상속 관계에서 사용)

### 기본함수
- CONCAT, SUBSTRING, TRIM, LOWER, UPPER, LENGTH, LOCATE, ABS, SQRT, MOD, SIZE, INDEX(JPA 용도), CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP
- SIZE : 컬렉션의 크기를 조회할 때 사용합니다. `SIZE(t.members) > 0`
- INDEX : 컬렉션의 인덱스를 조회할 때 사용합니다. `INDEX(t.members) = 2`
- 사용자 정의 함수 호출 : FUNCTION('group_concat', i.name)
- 사용자 정의 함수 등록 : `@NamedQueries` 어노테이션을 사용하여 등록합니다. //dialect에 등록, @NamedQueries 어노테이션을 사용하여 등록

### fetch join
연관된 엔티티를 한 번에 조회할 때 사용합니다. fetch join을 사용하면 지연 로딩이 아닌 즉시 로딩으로 연관된 엔티티를 함께 조회합니다. fetch join은 다음과 같이 작성할 수 있습니다:

```java
// 페치 조인
String jpql = "SELECT m FROM Member m join fetch m.team";
List<Member> resultList = em.createQuery(jpql, Member.class)
    .getResultList();
```

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