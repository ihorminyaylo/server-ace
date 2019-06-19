package com.cordio.cordio.controller;

import com.cordio.cordio.dto.GameDTO;
import com.cordio.cordio.model.business.Player;
import com.cordio.cordio.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody GameDTO gameDTO) {
        try {
            return ResponseEntity.ok(gameService.create(gameDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody GameDTO gameDTO, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(gameService.update(gameDTO, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
