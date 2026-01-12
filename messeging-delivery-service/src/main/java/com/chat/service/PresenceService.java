package com.chat.service;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class PresenceService {

	private final Map<String, Instant> lastSeenMap = new ConcurrentHashMap<>();
    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    public void markOnline(String userId) {
        onlineUsers.add(userId);
        lastSeenMap.put(userId, Instant.now());
    }

    public void markOffline(String userId) {
        onlineUsers.remove(userId);
        lastSeenMap.put(userId, Instant.now());
    }

    public void heartbeat(String userId) {
        onlineUsers.add(userId);
        lastSeenMap.put(userId, Instant.now());
    }

    public boolean isOnline(String userId) {
        return onlineUsers.contains(userId);
    }

    public Instant getLastSeen(String userId) {
        return lastSeenMap.get(userId);
    }
	
}
