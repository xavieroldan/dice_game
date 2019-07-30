package com.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceGameController
{
    private final int ROLL_MAX = 2;
    private final int WIN_RESULTS[] =
    {
        7
    };
    
    private List<DiceRollController> dicesRolls;
    private String idPlayer;
    private String gameNick;
    private boolean isAnonim;
    private String idGame;
    private boolean isWinner;
    private int rollCount;
    private int gameResult;
    
    public DiceGameController(String playerId)
    {
        List<DiceRollController> dicesRolls = new ArrayList<>();
        this.setDicesRolls(dicesRolls);
        this.idGame = UUID.randomUUID().toString().replace("-", "");
        this.gameNick = "ANÒNIM";
        this.isAnonim = true;
        this.setPlayerId(playerId);
        this.rollCount = 1;
        this.isWinner = false;
        this.gameResult = 0;
    }
    
    public int getROLL_MAX()
    {
        return ROLL_MAX;
    }
    
    public List<DiceRollController> getDicesRolls()
    {
        //TODO: Change to get from the DB?
        for (DiceRollController dicesRoll : dicesRolls)
        {
            System.out.println("    \nidRoll: " + idGame + dicesRoll.toString());
        }
        return dicesRolls;
    }
    
    public String getPlayerId()
    {
        return idPlayer;
    }
    
    public String getGameId()
    {
        return idGame;
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
    
    private void setDicesRolls(List<DiceRollController> dicesRolls)
    {
        this.dicesRolls = dicesRolls;
    }
    
    private void setPlayerId(String playerId)
    {
        this.idPlayer = playerId;
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
    
    public void setDiceRoll(DiceRollController diceRoll)
    {
        
        dicesRolls.add(diceRoll);
        if (rollCount != ROLL_MAX) // Isn´t the last roll
        {
            rollCount++;
            gameResult += diceRoll.getRollResult();
        }
        else // Is the last roll
        {
            gameResult += diceRoll.getRollResult();
            rollCount = 0;
            for (int i : WIN_RESULTS)
            {
                if (gameResult == i) // Winner game
                {
                    isWinner = true;
                }
                else //Lose game
                {
                    isWinner = false;
                }
                
            }
        }
    }
    
    @Override
    public String toString()
    
    {
        String result = ""; // get the result of the game 
        if (isWinner)
        {
            result = "Win";
        }
        else
        {
            result = "Lost";
        }
        
        String diceRollString = "";//get the rolls results       
        for (DiceRollController dicesRoll : dicesRolls)
        {
            diceRollString += "\nDado: " + dicesRoll.getIdDice()
                    + " Puntos: " + dicesRoll.getRollResult();
        }
        return "gameId: " + idGame + " result: " + result + diceRollString + "\n";
    }
    
}
