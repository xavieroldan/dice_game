/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.tools;

import com.dicegame.model.DiceGames;
import com.dicegame.model.DiceResults;
import com.dicegame.model.Players;
import com.dicegame.repository.DiceGamesRepository;
import com.dicegame.repository.DiceResultsRepository;
import com.dicegame.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Service
public class GamesService
{
    @Autowired
    private PlayersRepository playerRepo;
    @Autowired
    private DiceGamesRepository gameRepo;
    @Autowired
    private DiceResultsRepository resultRepo;

    public void createPlayers(Players player)
    {
        playerRepo.save(player);
    }

    public void createGames(DiceGames game)
    {
        gameRepo.save(game);
    }

    public void createResults(DiceResults result)
    {
        resultRepo.save(result);
    }
}
