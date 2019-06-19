package com.cordio.cordio.controller;

import com.cordio.cordio.dto.PlayerDTO;
import com.cordio.cordio.model.business.Player;
import com.cordio.cordio.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("/create")
    public Integer create(@RequestBody PlayerDTO playerDTO) {
        return playerService.create(playerDTO);
    }

    @GetMapping("/all")
    public List<Player> readAll() {
        return playerService.readAll();
    }



    //todo: remove
    @GetMapping("/api/create")
    public String userAccess() {
        return "success access";
    }
}
