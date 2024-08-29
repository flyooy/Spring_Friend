package de.supercode.friends.services;

import de.supercode.friends.entities.Friend;
import de.supercode.friends.entities.FriendGroup;
import de.supercode.friends.repositories.FriendRepository;
import de.supercode.friends.repositories.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final FriendRepository friendRepository;

    public GroupService(GroupRepository groupRepository, FriendRepository friendRepository) {
        this.groupRepository = groupRepository;
        this.friendRepository = friendRepository;
    }

    public FriendGroup createNewGroup(FriendGroup friendGroup) {
        return groupRepository.save(friendGroup);
    }

    public FriendGroup getGroupById(long id) {
        return groupRepository.findById(id).get();
    }

    public List<FriendGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    public void addFriendToGroup(long groupId, long friendId) {
    Optional<FriendGroup> optionalGroup = groupRepository.findById(groupId);
        if(!optionalGroup.isPresent())
    {
        throw new RuntimeException("Group not found with ID: " + groupId);
    }
    FriendGroup friendGroup = optionalGroup.get();
    Optional<Friend> optionalFriend = friendRepository.findById(friendId);
        if(!optionalFriend.isPresent())
    {
        throw new RuntimeException("Friend not found with ID: " + friendId);
    }
    Friend friend = optionalFriend.get();
        friendGroup.addFriend(friend);
        groupRepository.save(friendGroup);
}
    public void deleteFriendFromGroup(long groupId, long friendId) {
        Optional<FriendGroup> optionalGroup = groupRepository.findById(groupId);
        if(!optionalGroup.isPresent())
        {
            throw new RuntimeException("Group not found with ID: " + groupId);
        }
        FriendGroup friendGroup = optionalGroup.get();
        Optional<Friend> optionalFriend = friendRepository.findById(friendId);
        if(!optionalFriend.isPresent())
        {
            throw new RuntimeException("Friend not found with ID: " + friendId);
        }
        Friend friend = optionalFriend.get();
        friendGroup.removeFriend(friend);
        groupRepository.save(friendGroup);
    }

    public void deleteGroupById(long id) {
        groupRepository.deleteById(id);
    }

    public FriendGroup updateGroup(FriendGroup group) {
        Optional<FriendGroup> existingGroup = groupRepository.findById(group.getId());
        if (existingGroup.isPresent()) {
            return groupRepository.save(group);
        } else {
            throw new EntityNotFoundException("FriendGroup with ID " + group.getId() + " not found.");
        }
    }
}
