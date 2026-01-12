package com.chat.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.service.PresenceService;

@RestController
@RequestMapping("/api/presence")
public class PresenceController {

	private final PresenceService presenceService;

    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    // frontend calls this every ~30 seconds while user is active
    @PostMapping("/heartbeat/{userId}")
    public ResponseEntity<Void> heartbeat(@PathVariable String userId) {
        presenceService.heartbeat(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getStatus(@PathVariable String userId) {
        boolean online = presenceService.isOnline(userId);
        Instant lastSeen = presenceService.getLastSeen(userId);

        Map<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("online", online);
        body.put("lastSeen", lastSeen);

        return ResponseEntity.ok(body);
    }
	
}
