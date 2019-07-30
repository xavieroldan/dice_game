package com.dicegame.controllers;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Dice
{
    private int diceId;

    public Dice(int i)
    {
        this.diceId = i;
    }

    public int getDiceId()
    {
        return diceId;
    }

    public void setDiceId(int diceId)
    {
        this.diceId = diceId;
    }
}
