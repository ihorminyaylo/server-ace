package com.cordio.cordio.service;

import com.cordio.cordio.dto.PlayerDTO;
import com.cordio.cordio.model.business.Player;
import com.cordio.cordio.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Integer create(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setFirstName(playerDTO.getFirstName());
        player.setLastName(playerDTO.getLastName());
        player.setPatronymic(playerDTO.getPatronymic());
        player.setRating(1200);
        player.setBirthDate(null);
        return playerRepository.save(player).getId();
    }

    public List<Player> readAll() {
        return playerRepository.findAll();
    }
}
