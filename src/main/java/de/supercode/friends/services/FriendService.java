package de.supercode.friends.services;

import de.supercode.friends.entities.Friend;
import de.supercode.friends.repositories.FriendRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    FriendRepository friendRepository;

    public FriendService( FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getAllFriends() {
        return (List<Friend>)friendRepository.findAll();
    }
    public Friend getFriendById(long id) {
        return friendRepository.findById(id).orElse(null);
    }
    public Friend saveFriend(Friend friend) {
        return friendRepository.save(friend);
    }
    public Friend updateFriend(Friend friend) {
        Optional<Friend> existingFriend = friendRepository.findById(friend.getId());
        if (existingFriend.isPresent()) {
            return friendRepository.save(friend);
        } else {
            throw new EntityNotFoundException("Friend with ID " + friend.getId() + " not found.");
        }
    }
    public void deleteFriend(long id) {
        friendRepository.deleteById(id);
    }

    public List<Friend>  findAllByVerdienstGreaterThan(double verdienst){
        return friendRepository.findAllByVerdienstGreaterThan(verdienst);
    }

   public  List<Friend> findAllByIstSelbständig(boolean istSelbständig){
        return friendRepository.findAllByIstSelbständig(istSelbständig);
   }

   public   List<Friend> findAllByAgeGreaterThan(int age){
        return friendRepository.findAllByAgeGreaterThan(age);
   }
   public  List<Friend> findAllByVerdienstGreaterThanAndWarSchonmalKunde(double verdienst, boolean warSchonmalKunde){
        return friendRepository.findAllByVerdienstGreaterThanAndWarSchonmalKunde(verdienst, warSchonmalKunde);
   }

   public List<Friend> findAllByOrderByVerdienstDesc(){
        return friendRepository.findAllByOrderByVerdienstDesc();
   }
}
