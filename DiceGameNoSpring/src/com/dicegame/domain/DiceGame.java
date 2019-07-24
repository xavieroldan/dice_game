package com.dicegame.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceGame
{

    private static final int ROLL_MAX = 2;
    private static final int WIN_POINTS = 7;

    private List<DiceRoll> dicesRolls;
    private int playerId; // TODO: change type of playerId to UUID?
    private int gameId; // TODO: change type of gameId to UUID?
    private boolean isWinner;
    private int rollCount;
    private int gameResult;

    public DiceGame(int playerId)
    {
        List<DiceRoll> dicesRolls = new ArrayList<>();
        this.dicesRolls = dicesRolls;
        this.gameId = 1; //TODO: generate news gameId;
        this.playerId = playerId;
        this.rollCount = 0;
        this.isWinner = false;
        this.gameResult = 0;
    }

    public static int getROLL_MAX()
    {
        return ROLL_MAX;
    }

    public static int getWIN_POINTS()
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

    public boolean isIsWinner()
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

    /*
setDiceRoll boolean isLast (DiceRoll), le añadimos una jugada al juego. 
    Se comprueba que no se la última y se arrastra el resultado y se devuelve un false.
    Si es el final, seteamos isWinner dependiendo del resultado, hacemos reset de contador de jugadas
	y subimos a BD el resultado del juego. Devolvemos un true.
     */
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
}
