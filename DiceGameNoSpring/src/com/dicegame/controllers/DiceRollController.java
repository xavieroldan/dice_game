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

    private int idDice;
    private int rollResult;

    public DiceRollController()
    {
    }

    public DiceRollController(int diceId)
    {
        this.idDice = diceId;
        this.rollResult = 0; //default at instantiate 0 points
    }

    public int getIdDice()
    {
        return idDice;
    }

    public void setIdDice(int diceId)
    {
        this.idDice = diceId;
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
        return "        \nDiceRoll{" + "diceId=" + idDice + ", rollResult=" + rollResult + '}';
    }
}
