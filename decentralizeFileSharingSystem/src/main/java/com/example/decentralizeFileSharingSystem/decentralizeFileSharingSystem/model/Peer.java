package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "peers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Peer {

    @Id
    @GeneratedValue
    private UUID id;  // Unique peer ID

    @Column(nullable = false)
    private String deviceName;  // e.g., "Harsh's Phone"

    @Column(nullable = false)
    private String deviceType;  // e.g., "Android"

    @Column(nullable = false)
    private String ipAddress;   // optional for logging / connection

    private LocalDateTime lastSeen; // Last active timestamp

}