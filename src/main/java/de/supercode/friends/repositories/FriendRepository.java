package de.supercode.friends.repositories;

import de.supercode.friends.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByVerdienstGreaterThan(double verdienst);
    List<Friend> findAllByIstSelbständig(boolean istSelbständig);

    @Query("SELECT f FROM Friend f WHERE YEAR(CURRENT_DATE) - YEAR(f.geburtstag) > :age")
    List<Friend> findAllByAgeGreaterThan(@Param("age") int age);
    List<Friend> findAllByVerdienstGreaterThanAndWarSchonmalKunde(double verdienst, boolean warSchonmalKunde);

    List<Friend> findAllByOrderByVerdienstDesc();
}


