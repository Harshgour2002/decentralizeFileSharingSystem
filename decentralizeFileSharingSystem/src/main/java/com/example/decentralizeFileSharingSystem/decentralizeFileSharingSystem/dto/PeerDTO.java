package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.dto;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.Peer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeerDTO {

    private UUID id;
    private String deviceName;
    private String deviceType;
    private String ipAddress;
    private LocalDateTime lastSeen;

    public static PeerDTO fromEntity(Peer peer) {
        return PeerDTO.builder()
                .id(peer.getId())
                .deviceName(peer.getDeviceName())
                .deviceType(peer.getDeviceType())
                .ipAddress(peer.getIpAddress())
                .lastSeen(peer.getLastSeen())
                .build();
    }

}