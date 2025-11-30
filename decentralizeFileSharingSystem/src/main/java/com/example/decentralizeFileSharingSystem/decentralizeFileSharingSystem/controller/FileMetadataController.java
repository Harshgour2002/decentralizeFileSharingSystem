package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.controller;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.dto.FileMetadataDTO;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.Peer;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.service.FileMetadataService;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.service.PeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileMetadataController {

    private final FileMetadataService fileMetadataService;
    private final PeerService peerService;

    @GetMapping("/{peerId}")
    public ResponseEntity<List<FileMetadataDTO>> getFilesByPeer(@PathVariable UUID peerId) {
        Peer peer = peerService.getPeer(peerId)
                .orElseThrow(() -> new RuntimeException("Peer not found"));

        List<FileMetadataDTO> files = fileMetadataService.getFilesForPeer(peer)
                .stream()
                .map(FileMetadataDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(files);
    }

}