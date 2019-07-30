package com.dicegame.controllers;

import com.dicegame.app.factory.PlayerFactory;
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
    private String playerId;
    private String name;
    private Date regDate;
    private double successRate;
    private List<DiceGame> historicGames;

    public PlayerController(String name, PlayerFactory playerFactory) throws Exception
    {
        this.playerId = UUID.randomUUID().toString().replace("-", "");
        this.name = name;
        this.regDate = TimeStamp.getDate();
        this.successRate = 0;
        List<DiceGame> diceGame = new ArrayList<>();
        this.historicGames = diceGame;
        //add the adding to the db in the constructor
        playerFactory.create(this);
    }

    public String getPlayerId()
    {
        return playerId;
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

    public List<DiceGame> getHistoricGames()
    {

        System.out.println("Histórico de jugadas"
                + "\n--------------------");
        for (DiceGame historicGame : historicGames)
        {
            System.out.println(historicGame.toString());
        }
        return historicGames;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSuccessRate(double successRate)
    {
        this.successRate = successRate;
    }

    public void addGame(DiceGame diceGame)
    {
        //add the Game to the user historic
        historicGames.add(diceGame);
        double games = historicGames.size();
        double winGames = 0;
        for (DiceGame historicGame : historicGames)
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

//	voidGames(), borra el histórico de jugadas del usuario	
    }

    @Override
    public String toString()
    {
        return "User{" + "playerId=" + playerId + ", name=" + name + ", regDate=" + regDate + ", successRate=" + successRate + ", historicGames=" + historicGames + '}';
    }

}
