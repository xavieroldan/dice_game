package com.dicegame.domain;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Dice
{
    private static int diceCount;
    private int diceId;

    public Dice()
    {
        diceCount++;
        this.diceId = diceCount;
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
