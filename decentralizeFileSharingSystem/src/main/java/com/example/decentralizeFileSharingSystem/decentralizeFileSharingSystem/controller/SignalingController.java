package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.controller;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.dto.SignalingMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SignalingController {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Receives signaling messages from peers
     * Sends it to the target peer
     */
    @MessageMapping("/signal")
    public void signaling(SignalingMessageDTO message) {
        String targetDestination = "/queue/signaling-" + message.getToPeerId();
        messagingTemplate.convertAndSend(targetDestination, message);
    }
}