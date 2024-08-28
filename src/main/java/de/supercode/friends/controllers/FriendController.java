package de.supercode.friends.controllers;

import de.supercode.friends.entities.Friend;
import de.supercode.friends.services.FriendService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {
    FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping
    public ResponseEntity<List<Friend>> getFriends() {
        try {
            List<Friend> friends = friendService.getAllFriends();
            return ResponseEntity.ok(friends);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //  500 Internal Server Error
        }

    }

    @PostMapping
    public ResponseEntity<Friend> addFriend(@RequestBody Friend friend) {
        try {
            Friend createdFriend = friendService.saveFriend(friend);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFriend); //  201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //  400 Bad Request
        }
    }

    @PutMapping
    public ResponseEntity<Friend> updateFriend(@RequestBody Friend friend) {
        try {
            Friend updatedFriend = friendService.updateFriend(friend);
            return ResponseEntity.ok(updatedFriend);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); //404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        try {
            friendService.deleteFriend(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getFriend(@PathVariable Long id) {
        Friend friend = friendService.getFriendById(id);
        if (friend != null) {
            return ResponseEntity.ok(friend);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/verdienst/{verdienst}")
    public ResponseEntity<List<Friend>> getFriendsByVerdienstGreaterThan(@PathVariable double verdienst) {
        return ResponseEntity.ok(friendService.findAllByVerdienstGreaterThan(verdienst));
    }

    @GetMapping("/selbst/{istSelbständig}")
    public ResponseEntity<List<Friend>> findAllByIstSelbständig(@PathVariable boolean istSelbständig) {
        return ResponseEntity.ok(friendService.findAllByIstSelbständig(istSelbständig));
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Friend>> findAllByAgeGreaterThan(@PathVariable int age) {
        return ResponseEntity.ok(friendService.findAllByAgeGreaterThan(age));
    }

    @GetMapping("/verdienst/{verdienst}/{warSchonmalKunde}")
    public ResponseEntity<List<Friend>> findAllByVerdienstGreaterThanAndWarSchonmalKunde(@PathVariable double verdienst, @PathVariable boolean warSchonmalKunde) {
        return ResponseEntity.ok(friendService.findAllByVerdienstGreaterThanAndWarSchonmalKunde(verdienst, warSchonmalKunde));
    }

    @GetMapping("/sort/verdienst")
    public ResponseEntity<List<Friend>> findAllByOrderByVerdienstDesc(){
        return ResponseEntity.ok(friendService.findAllByOrderByVerdienstDesc());
    }

}
