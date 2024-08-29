package de.supercode.friends.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "`group`")
public class FriendGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    @JsonIgnoreProperties("group")
    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Friend> friends = new ArrayList<>();

    public FriendGroup(String name) {
        this.name = name;
    }
    public FriendGroup(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public void addFriend(Friend friend) {
        friends.add(friend);
        friend.setGroup(this);
    }
    public void removeFriend(Friend friend) {
        friends.remove(friend);
        friend.setGroup(null);
    }
}
