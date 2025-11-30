package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.controller;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.dto.PeerDTO;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.Peer;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.service.PeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/peers")
@RequiredArgsConstructor
public class PeerController {

    private final PeerService peerService;

    /**
     * Register a new peer
     */
    @PostMapping("/register")
    public ResponseEntity<PeerDTO> registerPeer(@RequestBody PeerDTO peerDTO) {
        Peer peer = peerService.registerPeer(
                peerDTO.getDeviceName(),
                peerDTO.getDeviceType(),
                peerDTO.getIpAddress()
        );
        return ResponseEntity.ok(PeerDTO.fromEntity(peer));
    }

    /**
     * Get list of online peers (excluding requester)
     */
    @GetMapping
    public ResponseEntity<List<PeerDTO>> getOnlinePeers(@RequestParam UUID excludePeerId) {
        List<PeerDTO> peers = peerService.getOnlinePeers(excludePeerId)
                .stream()
                .map(PeerDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peers);
    }

}