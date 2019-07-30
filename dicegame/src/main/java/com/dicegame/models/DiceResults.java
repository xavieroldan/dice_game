package com.dicegame.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "dice_results")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "DiceResults.findAll", query = "SELECT d FROM DiceResults d")
            , @NamedQuery(name = "DiceResults.findByIdDice", query = "SELECT d FROM DiceResults d WHERE d.diceResultsPK.idDice = :idDice")
            , @NamedQuery(name = "DiceResults.findByRollResult", query = "SELECT d FROM DiceResults d WHERE d.rollResult = :rollResult")
            , @NamedQuery(name = "DiceResults.findByDiceRollsIdRoll", query = "SELECT d FROM DiceResults d WHERE d.diceResultsPK.diceRollsIdRoll = :diceRollsIdRoll")
        })
public class DiceResults implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiceResultsPK diceResultsPK;
    @Basic(optional = false)
    @Column(name = "roll_result")
    private int rollResult;
    @JoinColumn(name = "dice_rolls_id_roll", referencedColumnName = "id_roll", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DiceRolls diceRolls;

    public DiceResults()
    {
    }

    public DiceResults(DiceResultsPK diceResultsPK)
    {
        this.diceResultsPK = diceResultsPK;
    }

    public DiceResults(DiceResultsPK diceResultsPK, int rollResult)
    {
        this.diceResultsPK = diceResultsPK;
        this.rollResult = rollResult;
    }

    public DiceResults(int idDice, String diceRollsIdRoll)
    {
        this.diceResultsPK = new DiceResultsPK(idDice, diceRollsIdRoll);
    }

    public DiceResultsPK getDiceResultsPK()
    {
        return diceResultsPK;
    }

    public void setDiceResultsPK(DiceResultsPK diceResultsPK)
    {
        this.diceResultsPK = diceResultsPK;
    }

    public int getRollResult()
    {
        return rollResult;
    }

    public void setRollResult(int rollResult)
    {
        this.rollResult = rollResult;
    }

    public DiceRolls getDiceRolls()
    {
        return diceRolls;
    }

    public void setDiceRolls(DiceRolls diceRolls)
    {
        this.diceRolls = diceRolls;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (diceResultsPK != null ? diceResultsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof DiceResults))
        {
            return false;
        }
        DiceResults other = (DiceResults) object;
        if ((this.diceResultsPK == null && other.diceResultsPK != null) || (this.diceResultsPK != null && !this.diceResultsPK.equals(other.diceResultsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.dicegame.models.DiceResults[ diceResultsPK=" + diceResultsPK + " ]";
    }

}
