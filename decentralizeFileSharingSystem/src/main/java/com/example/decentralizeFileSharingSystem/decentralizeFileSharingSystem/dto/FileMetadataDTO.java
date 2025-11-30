package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.dto;


import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.FileMetadata;
import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.FileStatus;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileMetadataDTO {

    private UUID id;
    private String fileName;
    private long fileSize;
    private int totalChunks;
    private int uploadedChunks;
    private FileStatus status;

    public static FileMetadataDTO fromEntity(FileMetadata file) {
        return FileMetadataDTO.builder()
                .id(file.getId())
                .fileName(file.getFileName())
                .fileSize(file.getFileSize())
                .totalChunks(file.getTotalChunks())
                .uploadedChunks(file.getUploadedChunks())
                .status(file.getStatus())
                .build();
    }

}