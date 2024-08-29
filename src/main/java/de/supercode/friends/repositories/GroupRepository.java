package de.supercode.friends.repositories;

import de.supercode.friends.entities.FriendGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<FriendGroup, Long> {
}
