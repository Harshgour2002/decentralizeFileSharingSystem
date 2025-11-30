package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalingMessageDTO {

    private String type;    // OFFER, ANSWER, ICE_CANDIDATE
    private String fromPeerId;
    private String toPeerId;
    private String payload; // SDP or ICE candidate in JSON/string

}