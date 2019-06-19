package com.cordio.cordio.repository;

import com.cordio.cordio.model.business.Player;
import com.cordio.cordio.model.security.Role;
import com.cordio.cordio.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
