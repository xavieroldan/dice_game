package com.dicegame.models;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Dice
{
    private int idDice;

    public Dice(int i)
    {
        this.idDice = i;
    }

    public int getDiceId()
    {
        return idDice;
    }

    public void setIdDice(int diceId)
    {
        this.idDice = diceId;
    }
}
