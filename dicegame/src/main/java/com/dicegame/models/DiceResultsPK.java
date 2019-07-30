package com.dicegame.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Embeddable
public class DiceResultsPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "id_dice")
    private int idDice;
    @Basic(optional = false)
    @Column(name = "dice_rolls_id_roll")
    private String diceRollsIdRoll;

    public DiceResultsPK()
    {
    }

    public DiceResultsPK(int idDice, String diceRollsIdRoll)
    {
        this.idDice = idDice;
        this.diceRollsIdRoll = diceRollsIdRoll;
    }

    public int getIdDice()
    {
        return idDice;
    }

    public void setIdDice(int idDice)
    {
        this.idDice = idDice;
    }

    public String getDiceRollsIdRoll()
    {
        return diceRollsIdRoll;
    }

    public void setDiceRollsIdRoll(String diceRollsIdRoll)
    {
        this.diceRollsIdRoll = diceRollsIdRoll;
    }

//    @Override
//    public int hashCode()
//    {
//        String hash = "";
//        hash += idDice;
//        hash += diceRollsIdRoll;
//        return hash;
//    }
    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof DiceResultsPK))
        {
            return false;
        }
        DiceResultsPK other = (DiceResultsPK) object;
        if (this.idDice != other.idDice)
        {
            return false;
        }
        if (this.diceRollsIdRoll != other.diceRollsIdRoll)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.dicegame.models.DiceResultsPK[ idDice=" + idDice + ", diceRollsIdRoll=" + diceRollsIdRoll + " ]";
    }

}
