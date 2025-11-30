package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.service;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.FileMetadata;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.FileStatus;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.Peer;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;

    /**
     * Create a new file metadata record
     */
    public FileMetadata createFileMetadata(Peer peer, String fileName, long fileSize, int totalChunks) {
        FileMetadata fileMetadata = FileMetadata.builder()
                .peer(peer)
                .fileName(fileName)
                .fileSize(fileSize)
                .totalChunks(totalChunks)
                .uploadedChunks(0)
                .status(FileStatus.IN_PROGRESS)
                .build();

        return fileMetadataRepository.save(fileMetadata);
    }

    /**
     * Update uploaded chunks count
     */
    public void updateUploadedChunks(UUID fileId, int chunksUploaded) {
        fileMetadataRepository.findById(fileId).ifPresent(file -> {
            file.setUploadedChunks(chunksUploaded);
            if (chunksUploaded >= file.getTotalChunks()) {
                file.setStatus(FileStatus.COMPLETED);
            }
            fileMetadataRepository.save(file);
        });
    }

    /**
     * Get all files for a peer
     */
    public List<FileMetadata> getFilesForPeer(Peer peer) {
        return fileMetadataRepository.findByPeer(peer);
    }

}
