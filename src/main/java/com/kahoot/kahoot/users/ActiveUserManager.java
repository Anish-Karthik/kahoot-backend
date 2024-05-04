package com.kahoot.kahoot.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import lombok.ToString;

@ToString
@Component
public class ActiveUserManager {

    private final Map<String, Map<String, LiveUser>> activeUsers = new ConcurrentHashMap<>();

    public void addUser(String room, LiveUser user) {
        activeUsers.computeIfAbsent(room, k -> new ConcurrentHashMap<>()).put(user.getUsername(), user);
    }

    public boolean userExists(String room, LiveUser user) {
        Map<String, LiveUser> users = activeUsers.get(room);
        return users != null && users.containsKey(user.getUsername());
    }

    public void updateUser(String room, LiveUser user) {
        Map<String, LiveUser> users = activeUsers.get(room);
        users.put(user.getUsername(), user);
    }

    public void removeUser(String room, LiveUser user) {
        Map<String, LiveUser> users = activeUsers.get(room);
        System.out.println(users);
        System.out.println(user);
        
        try {
            users.remove(user.getUsername());
        } catch (Exception e) {
            System.out.println("Error removing user");
            e.printStackTrace();
        }
        System.out.println("After remove");
        System.out.println(users);
        System.out.println(activeUsers);
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

