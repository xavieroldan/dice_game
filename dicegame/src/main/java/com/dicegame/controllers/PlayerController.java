package com.dicegame.controllers;

import com.dicegame.app.repository.PlayerRepository;
import com.dicegame.app.tools.TimeStamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class PlayerController
{
    private String IdPlayer;
    private String name;
    private Date regDate;
    private double successRate;
    private List<DiceGameController> listGames;
    private PlayerRepository playerRepository;

    public PlayerController(String name, PlayerRepository playerRepo) throws Exception
    {
        this.IdPlayer = UUID.randomUUID().toString().replace("-", "");
        this.name = name;
        this.regDate = TimeStamp.getDate();
        this.successRate = 0;
        List<DiceGameController> diceGame = new ArrayList<>();
        this.setListGames(diceGame);
        //add the adding to the db in the constructor
        this.setPlayerRepository(playerRepo);
        playerRepository.create(this);
    }

    public String getIdPlayer()
    {
        return IdPlayer;
    }

    public String getName()
    {
        return name;
    }

    public Date getRegDate()
    {
        return regDate;
    }

    public double getSuccessRate()
    {
        return successRate;
    }

    public void setName(String name) throws Exception
    {
        this.name = name;
        playerRepository.edit(this, name);
    }

    public void setSuccessRate(double successRate)
    {
        this.successRate = successRate;
    }

    public void setListGames(List<DiceGameController> listGames)
    {
        this.listGames = listGames;
    }

    public PlayerRepository getPlayerRepository()
    {
        return playerRepository;
    }

    public void setPlayerRepository(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    public void addListGame(DiceGameController diceGame)
    {
        //add the Game to the user historic
        listGames.add(diceGame);
        double games = listGames.size();
        double winGames = 0;
        for (DiceGameController historicGame : listGames)
        {
            if (historicGame.getIsWinner())
            {
                winGames++;
            }
        }
        double rate = Math.round((winGames / games) * 100); //round the result to show %
        successRate = rate; // update the success rate value in the user
    }

    public void voidGames()
    {
        listGames.clear();
    }

    @Override
    public String toString()
    {
        return "User{" + "playerId=" + IdPlayer + ", name=" + name + ", regDate=" + regDate + ", successRate=" + successRate + ", historicGames=" + listGames + '}';
    }

}
