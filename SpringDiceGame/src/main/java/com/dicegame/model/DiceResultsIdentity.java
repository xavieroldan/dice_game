package com.dicegame.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Embeddable
public class DiceResultsIdentity implements Serializable
{

    @NotNull
    @Size(max = 20)
    private String diceId;

    @NotNull
    @Size(max = 20)
    private String rollId;

//    @ManyToOne
//    @JoinColumn(name = "id_roll", nullable = false)
//    private DiceRolls diceRolls;
    public DiceResultsIdentity(String diceId, String rollId)
    {
        this.diceId = diceId;
        this.rollId = rollId;
    }

    @Override
    public int hashCode()
    {
        int result = diceId.hashCode();
        result = 31 * result + rollId.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        DiceResultsIdentity that = (DiceResultsIdentity) o;

        if (!diceId.equals(that.diceId))
        {
            return false;
        }
        return rollId.equals(that.rollId);
    }

    public String getDiceId()
    {
        return diceId;
    }

    public void setDiceId(String diceId)
    {
        this.diceId = diceId;
    }

    public String getRollId()
    {
        return rollId;
    }

    public void setRollId(String rollId)
    {
        this.rollId = rollId;
    }

}
