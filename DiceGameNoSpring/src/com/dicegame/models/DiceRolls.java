
package com.dicegame.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "dice_rolls")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DiceRolls.findAll", query = "SELECT d FROM DiceRolls d")
    , @NamedQuery(name = "DiceRolls.findByIdRoll", query = "SELECT d FROM DiceRolls d WHERE d.idRoll = :idRoll")
    , @NamedQuery(name = "DiceRolls.findByIsWinner", query = "SELECT d FROM DiceRolls d WHERE d.isWinner = :isWinner")
    , @NamedQuery(name = "DiceRolls.findByIsAnonim", query = "SELECT d FROM DiceRolls d WHERE d.isAnonim = :isAnonim")
})
public class DiceRolls implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_roll")
    private Integer idRoll;
    @Basic(optional = false)
    @Column(name = "is_winner")
    private boolean isWinner;
    @Basic(optional = false)
    @Column(name = "is_anonim")
    private boolean isAnonim;
    @JoinColumn(name = "players_idplayers", referencedColumnName = "idplayers")
    @ManyToOne(optional = false)
    private Players playersIdplayers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diceRolls")
    private List<DiceResults> diceResultsList;

    public DiceRolls()
    {
    }

    public DiceRolls(Integer idRoll)
    {
        this.idRoll = idRoll;
    }

    public DiceRolls(Integer idRoll, boolean isWinner, boolean isAnonim)
    {
        this.idRoll = idRoll;
        this.isWinner = isWinner;
        this.isAnonim = isAnonim;
    }

    public Integer getIdRoll()
    {
        return idRoll;
    }

    public void setIdRoll(Integer idRoll)
    {
        this.idRoll = idRoll;
    }

    public boolean getIsWinner()
    {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner)
    {
        this.isWinner = isWinner;
    }

    public boolean getIsAnonim()
    {
        return isAnonim;
    }

    public void setIsAnonim(boolean isAnonim)
    {
        this.isAnonim = isAnonim;
    }

    public Players getPlayersIdplayers()
    {
        return playersIdplayers;
    }

    public void setPlayersIdplayers(Players playersIdplayers)
    {
        this.playersIdplayers = playersIdplayers;
    }

    @XmlTransient
    public List<DiceResults> getDiceResultsList()
    {
        return diceResultsList;
    }

    public void setDiceResultsList(List<DiceResults> diceResultsList)
    {
        this.diceResultsList = diceResultsList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idRoll != null ? idRoll.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiceRolls))
        {
            return false;
        }
        DiceRolls other = (DiceRolls) object;
        if ((this.idRoll == null && other.idRoll != null) || (this.idRoll != null && !this.idRoll.equals(other.idRoll)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.dicegame.models.DiceRolls[ idRoll=" + idRoll + " ]";
    }

}
