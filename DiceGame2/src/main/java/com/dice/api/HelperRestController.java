/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.api;

import com.dice.model.Game;
import com.dice.model.Player;
import com.dice.repository.GameRepository;
import com.dice.repository.PlayerRepository;
import com.dice.tool.ErrorTransactionException;
import com.dice.tool.ErrorValueException;
import com.dice.tool.RateDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Component
public class HelperRestController
{
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    GameRepository gameRepo;

    //Check a List of Players is OK
    protected List<Player> verifyListPlayers(List<Player> listPlayer) throws ErrorTransactionException
    {
        if (listPlayer.isEmpty() || listPlayer == null)
        {
            throw new ErrorTransactionException("No hay jugadores en el sistema.");
        }

        return listPlayer;
    }

    //Transform a List of Players in an sort List by the Rate (worst the first)
    protected List<RateDTO> sortPlayersByRate() throws ErrorTransactionException
    {
        List<Player> listPlayer = verifyListPlayers((List<Player>) playerRepo.findAll());
        List<RateDTO> listRateDTO = new ArrayList<>();
        for (Player player : listPlayer)
        {
            //Count the games
            double games = player.getListGame().size();
            if (games != 0)
            {
                List<Game> listGame = player.getListGame();
                double wins = 0;
                //Count the wins
                for (Game game : listGame)
                {
                    if (game.getIsWinner())
                    {
                        wins++;
                    }
                }
                //Calculate and output the results
                double result = (wins / games) * 100;
                RateDTO resultDTO = new RateDTO(player, result);
                listRateDTO.add(resultDTO);
            }
            else
            {
                //No games rate 0%
                RateDTO resultDTO = new RateDTO(player, 0);
                listRateDTO.add(resultDTO);
            }
        }
        //Order the list
        Collections.sort(listRateDTO);
        return listRateDTO;
    }

    protected Player verifyName(Player player) throws ErrorValueException
    {
        try
        {
            if (player == null || player.getName().trim().isEmpty()
                    || player.getName() == null)
            {
                throw new ErrorValueException("Nombre de jugador incorrecto.");
            }
            //Trim the name
            String name = player.getName();
            player.setName(name.trim());
            return player;
        }
        catch (Exception e)
        {
            throw new ErrorValueException(e + " El parámetro es null.");
        }
    }

    protected List<RateDTO> getListPlayersRateDTO(List<Player> listPlayers)
    {
        List<RateDTO> output = new ArrayList<>();
        //Get player Ratio
        for (Player player : listPlayers)
        {
            UUID idPlayer = player.getIdPlayer();
            double ratio = 0;
            if (!player.getListGame().isEmpty())
            {
                ratio = (gameRepo.countPlayerWinGames(idPlayer)
                        / gameRepo.countPlayerGames(idPlayer)) * 100;
            }
            RateDTO rateDTO = new RateDTO(player, ratio);
            output.add(rateDTO);
        }
        return output;
    }

}
