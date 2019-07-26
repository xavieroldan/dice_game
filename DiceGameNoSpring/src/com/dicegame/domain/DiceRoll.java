package com.dicegame.domain;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class DiceRoll
{

    private int diceId;
    private int rollResult;

    public DiceRoll()
    {
    }

    public DiceRoll(int diceId)
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
