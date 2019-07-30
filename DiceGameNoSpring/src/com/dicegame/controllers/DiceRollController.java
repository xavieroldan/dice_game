package com.dicegame.controllers;

import com.dicegame.control.DiceRollsJpaController;
import com.dicegame.control.PlayersJpaController;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceRollController
{

    private int diceId;
    private int rollResult;

    public DiceRollController()
    {
    }

    public DiceRollController(int diceId)
    {
        this.diceId = diceId;
        this.rollResult = 0; //default at instantiate 0 points
    }

    public int getDiceId()
    {
        return diceId;
    }

    public void setDiceId(int diceId)
    {
        this.diceId = diceId;
    }

    public int getRollResult()
    {
        return rollResult;
    }

    public void setRollResult(int rollResult)
    {
        this.rollResult = rollResult;
    }

    @Override
    public String toString()
    {
        return "        \nDiceRoll{" + "diceId=" + diceId + ", rollResult=" + rollResult + '}';
    }
}
