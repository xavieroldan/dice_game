package com.dicegame.domain;

import com.dicegame.app.tools.TimeStamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class User
{

    private int playerId;
    private String name;
    private Date regDate;
    private boolean isAnonymous;
    private double successRate;
    private List<DiceGame> historicGames;

    public User(String name, boolean isAnonymous)
    {
        this.playerId = 1; // TODO: generate the UUID 
        this.name = name;
        this.regDate = TimeStamp.getDate();
        this.isAnonymous = isAnonymous;
        this.successRate = 0;
        List<DiceGame> diceGame = new ArrayList<>();
        this.historicGames = diceGame;
    }

    public int getPlayerId()
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

    public boolean isIsAnonymous()
    {
        return isAnonymous;
    }

    public double getSuccessRate()
    {
        return successRate;
    }

    public List<DiceGame> getHistoricGames()
    {
        return historicGames;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setIsAnonymous(boolean isAnonymous)
    {
        this.isAnonymous = isAnonymous;
    }

    public void setSuccessRate(double successRate)
    {
        this.successRate = successRate;
    }

    public void addGame(DiceGame diceGame)
    {
        //	addGame (DiceGame), añade un juego a su histórico, actualizando el ratio del jugador. 
    }

    public void voidGames()
    {

//	voidGames(), borra el histórico de jugadas del usuario	
    }
}
