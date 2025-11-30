package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "file_metadata")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileMetadata {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peer_id", nullable = false)
    private Peer peer;  // Owner peer

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private long fileSize;

    private int totalChunks;

    private int uploadedChunks;

    @Enumerated(EnumType.STRING)
    private FileStatus status;  // IN_PROGRESS, COMPLETED

}