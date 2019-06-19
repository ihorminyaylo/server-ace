package com.cordio.cordio.repository;

import com.cordio.cordio.model.business.Game;
import com.cordio.cordio.model.business.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByIdGreaterThanOrderById(Integer id);
    //Game update(Game game);
}
