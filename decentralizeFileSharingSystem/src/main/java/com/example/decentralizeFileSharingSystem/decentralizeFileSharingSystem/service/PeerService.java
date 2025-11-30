package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.service;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.Peer;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.repository.PeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PeerService {

    private final PeerRepository peerRepository;

    // In-memory map for fast online peer tracking
    private final Map<UUID, Peer> onlinePeers = new ConcurrentHashMap<>();

    /**
     * Register a new peer
     */
    public Peer registerPeer(String deviceName, String deviceType, String ipAddress) {
        Peer peer = Peer.builder()
                .deviceName(deviceName)
                .deviceType(deviceType)
                .ipAddress(ipAddress)
                .lastSeen(LocalDateTime.now())
                .build();

        peer = peerRepository.save(peer);
        onlinePeers.put(peer.getId(), peer);
        return peer;
    }

    /**
     * Mark peer as online
     */
    public void markOnline(UUID peerId) {
        peerRepository.findById(peerId).ifPresent(peer -> {
            peer.setLastSeen(LocalDateTime.now());
            onlinePeers.put(peerId, peer);
        });
    }

    /**
     * Mark peer as offline
     */
    public void markOffline(UUID peerId) {
        onlinePeers.remove(peerId);
    }

    /**
     * Get all online peers except requesting peer
     */
    public List<Peer> getOnlinePeers(UUID excludePeerId) {
        List<Peer> peers = new ArrayList<>(onlinePeers.values());
        peers.removeIf(peer -> peer.getId().equals(excludePeerId));
        return peers;
    }

    /**
     * Get peer by ID
     */
    public Optional<Peer> getPeer(UUID peerId) {
        return Optional.ofNullable(onlinePeers.get(peerId));
    }

}