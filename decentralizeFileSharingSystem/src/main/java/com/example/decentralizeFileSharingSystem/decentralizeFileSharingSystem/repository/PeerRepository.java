package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.repository;

import com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.model.Peer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeerRepository extends JpaRepository<Peer, UUID> {

}