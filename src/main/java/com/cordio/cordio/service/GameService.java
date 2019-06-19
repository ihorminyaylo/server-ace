package com.cordio.cordio.service;

import com.cordio.cordio.dto.GameDTO;
import com.cordio.cordio.model.business.Game;
import com.cordio.cordio.model.business.Player;
import com.cordio.cordio.repository.GameRepository;
import com.cordio.cordio.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public List<Player> create(GameDTO gameDTO) throws Exception {
        Game game;
        if (gameDTO.getFirstPlayerSets().size() != gameDTO.getSecondPlayerSets().size()) {
            throw new Exception("Counts of sets should be the same");
        }
        boolean firstWon = firstWasWon(gameDTO);
        if (firstWon) {
            game = convertFrom(gameDTO.getFirstPlayerId(), gameDTO.getFirstPlayerSets(), gameDTO.getSecondPlayerId(), gameDTO.getSecondPlayerSets());
        } else {
            game = convertFrom(gameDTO.getSecondPlayerId(), gameDTO.getSecondPlayerSets(), gameDTO.getFirstPlayerId(), gameDTO.getFirstPlayerSets());
        }
        gameRepository.save(game).getId();
        return recalculateRating(game);
    }

    public List<Player> update(GameDTO gameDTO, Integer id) throws Exception {
        Game game;
        if (gameDTO.getFirstPlayerSets().size() != gameDTO.getSecondPlayerSets().size()) {
            throw new Exception("Counts of sets should be the same");
        }
        boolean firstWon = firstWasWon(gameDTO);
        if (firstWon) {
            game = convertFrom(gameDTO.getFirstPlayerId(), gameDTO.getFirstPlayerSets(), gameDTO.getSecondPlayerId(), gameDTO.getSecondPlayerSets());
        } else {
            game = convertFrom(gameDTO.getSecondPlayerId(), gameDTO.getSecondPlayerSets(), gameDTO.getFirstPlayerId(), gameDTO.getFirstPlayerSets());
        }
        Integer gameId = gameRepository.save(game).getId();
        List<Game> games = gameRepository.findByIdGreaterThanOrderById(gameId);
        List<Player> players = new ArrayList<>();
        for (Game updateGame : games) {
            players.addAll(recalculateRating(updateGame));
        }
        return players;
    }

    private Game convertFrom(Integer wonPlayerId, List<Integer> wonPlayerSets, Integer lousePlayerId, List<Integer> lousePlayerSets) throws Exception {
        Game game = new Game();
        game.setWonPlayerId(wonPlayerId);
        game.setLousePlayerId(lousePlayerId);
        game.setWonPlayerSets(wonPlayerSets);
        game.setLousePlayerSets(lousePlayerSets);
        return game;
    }

    private boolean firstWasWon(GameDTO gameDTO) {
        Integer firstPlayerSetsWon = 0;
        Integer secondPlayerSetsWon = 0;
        if (gameDTO.getFirstPlayerSets().size() > 0) {
            for (int i = 0; i < gameDTO.getFirstPlayerSets().size(); i++) {
                if (gameDTO.getFirstPlayerSets().get(i) > gameDTO.getSecondPlayerSets().get(i)) {
                    firstPlayerSetsWon = firstPlayerSetsWon + 1;
                } else {
                    secondPlayerSetsWon = secondPlayerSetsWon + 1;
                }
            }
        }
        return firstPlayerSetsWon > secondPlayerSetsWon;
    }

    private List<Player> recalculateRating(Game game) {
        List<Player> players = new ArrayList<>();
        Player wonPlayer = playerRepository.findById(game.getWonPlayerId()).get();
        Player lousePlayer = playerRepository.findById(game.getLousePlayerId()).get();
        double coefficient = 1 / (1 + Math.pow(10, (Double.valueOf(wonPlayer.getRating()) - Double.valueOf(lousePlayer.getRating())) / 400));
        int delta = (int) (30 * (1 - coefficient));
        wonPlayer.setRating(wonPlayer.getRating() + delta);
        lousePlayer.setRating(lousePlayer.getRating() - delta);
        playerRepository.save(wonPlayer);
        playerRepository.save(lousePlayer);
        players.add(wonPlayer);
        players.add(lousePlayer);
        return players;
    }
}
