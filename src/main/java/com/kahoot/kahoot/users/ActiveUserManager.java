package com.kahoot.kahoot.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kahoot.kahoot.chat.Leaderboard;

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

    public List<LiveUser> getUsers(String room) {
        return new ArrayList<>(activeUsers.getOrDefault(room, new ConcurrentHashMap<>()).values());
    }

    public List<Leaderboard> getLeaderboard(String room) {
        List<Leaderboard> leaderboards = new ArrayList<>();
        Map<String, LiveUser> users = activeUsers.get(room);
        if (users == null) {
            return leaderboards;
        }
        for (LiveUser user : users.values()) {
            leaderboards.add(Leaderboard.builder()
                    .username(user.getUsername())
                    .score(user.getAnswers().stream().mapToInt(Answer::getScore).sum())
                    .build());
        }
        return leaderboards;
    }
}
