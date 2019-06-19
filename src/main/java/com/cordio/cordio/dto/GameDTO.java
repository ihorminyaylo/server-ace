package com.cordio.cordio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GameDTO {

    private Integer firstPlayerId;

    private Integer secondPlayerId;

    private List<Integer> firstPlayerSets;

    private List<Integer> secondPlayerSets;

}
