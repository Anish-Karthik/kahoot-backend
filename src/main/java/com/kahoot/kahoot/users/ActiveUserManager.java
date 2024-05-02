package com.kahoot.kahoot.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.ToString;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ToString
@Component
public class ActiveUserManager {

    private final Map<String, Map<String, LiveUser>> activeUsers = new ConcurrentHashMap<>();

    public void addUser(String room, LiveUser user) {
        activeUsers.computeIfAbsent(room, k -> new ConcurrentHashMap<>()).put(user.getUsername(), user);
    }

    public void removeUser(String room, LiveUser user) {
        Map<String, LiveUser> users = activeUsers.get(room);
        if (users != null) {
            users.remove(user.getUsername());
            if (users.isEmpty()) {
                activeUsers.remove(room);
            }
        }
    }

    // public Map<String, LiveUser> getUsers(String room) {
    //     return activeUsers.getOrDefault(room, new ConcurrentHashMap<>());
    // }

    public List<LiveUser> getUsers(String room) {
        return new ArrayList<>(activeUsers.getOrDefault(room, new ConcurrentHashMap<>()).values());
    }

    // private final Map<String, Set<LiveUser>> activeSessions = new ConcurrentHashMap<>();

    // public void addUser(String room, LiveUser user) {
    //     activeSessions.computeIfAbsent(room, k -> new CopyOnWriteArraySet<>()).add(user);
    //     // System.out.println(this);
    // }

    // public void removeUser(String room, LiveUser user) {
    //     Set<LiveUser> users = activeSessions.get(room);
    //     if (users != null) {
    //         users.remove(user);
    //         if (users.isEmpty()) {
    //             activeSessions.remove(room);
    //         }
    //     }
    // }

    // public Set<LiveUser> getUsers(String room) {
    //     return activeSessions.getOrDefault(room, new CopyOnWriteArraySet<>());
    // }



    // public void addUser(String room, String username) {
    //     activeSessions.computeIfAbsent(room, k -> new CopyOnWriteArraySet<>()).add(username);
    // }

    // public void removeUser(String room, String username) {
    //     Set<String> users = activeSessions.get(room);
    //     if (users != null) {
    //         users.remove(username);
    //         if (users.isEmpty()) {
    //             activeSessions.remove(room);
    //         }
    //     }
    // }

    // public Set<String> getUsers(String room) {
    //     return activeSessions.getOrDefault(room, new CopyOnWriteArraySet<>());
    // }
}

