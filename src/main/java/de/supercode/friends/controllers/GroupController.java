package de.supercode.friends.controllers;

import de.supercode.friends.entities.Friend;
import de.supercode.friends.entities.FriendGroup;
import de.supercode.friends.services.GroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;
    public GroupController(final GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping
    public ResponseEntity<List<FriendGroup>> getAllGroups() {
        try {
            List<FriendGroup> groups = groupService.getAllGroups();
            return ResponseEntity.ok(groups);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //  500 Internal Server Error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendGroup> getGroupById(@PathVariable long id){

        FriendGroup friendGroup = groupService.getGroupById(id);
        if (friendGroup != null) {
            return ResponseEntity.ok(friendGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FriendGroup> addGroup(@RequestBody FriendGroup friendGroup) {
        try {
            FriendGroup createdFriendGroup = groupService.createNewGroup(friendGroup);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFriendGroup); //  201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //  400 Bad Request
        }
    }

    @PostMapping("/addfriend/{groupId}/{friendId}")
        public ResponseEntity<String> addFriendToGroup(@PathVariable long groupId, @PathVariable long friendId) {
            try {
                groupService.addFriendToGroup(groupId, friendId);
                return ResponseEntity.ok("Friend added to group successfully.");
            }  catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
            }
    }

    @DeleteMapping("/deletefriend/{groupId}/{friendId}")
    public ResponseEntity<FriendGroup> deleteFriendFromGroup(@PathVariable long groupId, @PathVariable long friendId) {
        try {
            groupService.deleteFriendFromGroup(groupId,friendId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGroupById(long id)
    {
        try {
            groupService.deleteGroupById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping
    public ResponseEntity<FriendGroup> updateGroup(@RequestBody FriendGroup group) {
        try {
            FriendGroup updatedFriendGroup = groupService.updateGroup(group);
            return ResponseEntity.ok(updatedFriendGroup);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); //404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
    }
}
