package com.greedy.data.menu.repository;

import com.greedy.data.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* Menu라는 엔티티를 이용한 저장소, Primary타입을 두번째 인자로 전달. */
/*Spring Data JPA를 사용하면 기존의 EntityManagerFactory, EntityManager, EntityTransaction같은 객체가 필요 없다.
*
* Repository 인터페이스 상속구조
*  : Repository <- CrudRepository <- PagingAndSortingRepository <- JpaRepository
*
* Repository : 기능이 거의 없는 단순 저장소 역할
* CrudRepository : 기본적인 CRUD 기능을 제공함
* PagingAndSortingRepository : 페이징 및 정렬 처리하는 기능을 추가 제공함
* JpaRepository : CrudRepository의 기능과 PagingAndSortingRepository 기능을 모두 제공함
*                   추가적으로 JPA에 특화된 메소드 기능을 제공하고 있다.
*
* 쿼리메소드
* : JPQL을 메소드로 대신 처리할 수 있도록 제공하는 기능
*   메소드의 이름으로 필요한 쿼리를 만들어주는 기능으로 "find + 엔티티이름 + By + 변수이름"과 같이 네이밍 룰만 알면 사용 가능하다.
* ex) findMenuByMenuCode() : Menu 엔티티에서 Code 속성에 대해 조건처리하여 조회한다.
*
* 엔티티의 이름을 생략하고 쓸 수도 있는데 이는 해당 Repository 인터페이스의 타입 파라미터로 정의한 부분을 자동으로 인식하기 때문이다.
* ex) findByMenuCode() <- 이렇게 엔티티 생략 가능함
*
* 쿼리메소드 유형
* And                           ex) findByMenuCodeAndName -> x.menuCode = ?1 and x.menuName = ?2
* Or                            ex) findMenuCodeOrName -> x.menuCode = ?1 or x.menuName = ?2
* Between                       ex) findByMenuPriceBetween -> where x.menuPrice between ?1 and ?2
* LessThan                      ex) findByMenyPriceLessThan -> where x.price < ?1
* LessThanEqual                 ex) findByMenuPriceLessThanEqual -> where x.price <= ?1
* GreaterThan                   ex) findByMenuPriceGreaterThan -> where x.price > ?1
* GreaterThanEqual              ex) findByMenuPriceGreaterThanEqual -> where x.price >= ?1
* After                         ex) findByDateAfter -> where x.date > ?1
* Before                        ex) findByDateBefore -> where x.date < ?1
* IsNull                        ex) findByMenuNameIsNull -> where x.menuName is null
* NotNull, IsNotNull            ex) findByMenuName(Is)NotNull -> where x.menuName is not null
* Like                          ex) findByMenunameLike -> where x.name like ?1
* NotLike                       ex) findByMenuNameNotLike -> where x.menuName not like ?1
* StartingWith                  ex) findByMenuNameStartingWith -> where x.menuName like ?1 || '%'
* EndingWith                    ex) findByMenuNameEndingWith -> where x.menuName list '%' || ?1
* Containing                    ex) findByMenuNameContaining -> where x.menuName list '%' || ?1 || '%'
* OrderBy                       ex) findByMenuNameOrderByMenuCodeDesc -> where x.menuPrice = ?1 order by x.menuCode desc
* Not                           ex) findByMenuNameNot -> where x.menuName<> ?1
* In                            ex) findByMenuNameIn(Collection<Name> names) -> where x.menuName in (?1)
*
* */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /* 기본적으로 제공하는 기능들 외에 다른 기능을 만들고 싶을 때 */
    /* 이런 기능을 쿼리메소드라고 한다. */
    List<Menu> findByMenuNameContaining(String menuName);

}
