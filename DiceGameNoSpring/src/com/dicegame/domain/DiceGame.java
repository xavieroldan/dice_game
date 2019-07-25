package com.dicegame.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceGame
{

    private static int gameCount;
    private final int ROLL_MAX = 2;
    private final int WIN_POINTS = 7;

    private List<DiceRoll> dicesRolls;
    private int playerId; // TODO: change type of playerId to UUID?
    private String gameNick;
    private boolean isAnonim;
    private int gameId;
    private boolean isWinner;
    private int rollCount;
    private int gameResult;

    public DiceGame(int playerId)
    {
        gameCount++;

        List<DiceRoll> dicesRolls = new ArrayList<>();
        this.dicesRolls = dicesRolls;
        this.gameId = gameCount;
        this.gameNick = "ANÒNIM";
        this.isAnonim = true;
        this.playerId = playerId;
        this.rollCount = 1;
        this.isWinner = false;
        this.gameResult = 0;
    }

    public int getROLL_MAX()
    {
        return ROLL_MAX;
    }

    public int getWIN_POINTS()
    {
        return WIN_POINTS;
    }

    public List<DiceRoll> getDicesRolls()
    {
        return dicesRolls;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public int getGameId()
    {
        return gameId;
    }

    public boolean getIsWinner()
    {
        return isWinner;
    }

    public int getRollCount()
    {
        return rollCount;
    }

    public int getGameResult()
    {
        return gameResult;
    }

    private void setDicesRolls(List<DiceRoll> dicesRolls)
    {
        this.dicesRolls = dicesRolls;
    }

    private void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    private void setIsWinner(boolean isWinner)
    {
        this.isWinner = isWinner;
    }

    public void setGameNick(String gameNick)
    {
        this.gameNick = gameNick;
    }

    public void setIsAnonim(boolean isAnonim)
    {
        this.isAnonim = isAnonim;
    }

    public boolean setDiceRoll(DiceRoll diceRoll)
    {
        dicesRolls.add(diceRoll);
        if (rollCount != ROLL_MAX) // Isn´t the last roll
        {
            rollCount++;
            gameResult += diceRoll.getRollResult();
            return false;
        }
        else // Is the last roll
        {
            gameResult += diceRoll.getRollResult();
            rollCount = 0;
            if (gameResult == WIN_POINTS) // Winner game
            {
                isWinner = true;
            }
            else //Lose game
            {
                isWinner = false;
            }
//TODO: update the DB with the roll and the result 
            return isWinner;
        }
    }

    @Override
    public String toString()
    {
        return "DiceGame{" + "dicesRolls=" //+ dicesRolls.
                + ", playerId=" + playerId + ", gameNick="
                + gameNick + ", isAnonim=" + isAnonim
                + ", gameId=" + gameId + ", isWinner=" + isWinner
                + ", rollCount=" + rollCount + ", gameResult=" + gameResult + '}';
    }

}
