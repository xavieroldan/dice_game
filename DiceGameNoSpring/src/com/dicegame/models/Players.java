package com.dicegame.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "players")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Players.findAll", query = "SELECT p FROM Players p")
            , @NamedQuery(name = "Players.findByIdplayers", query = "SELECT p FROM Players p WHERE p.idplayers = :idplayers")
            , @NamedQuery(name = "Players.findByName", query = "SELECT p FROM Players p WHERE p.name = :name")
            , @NamedQuery(name = "Players.findByRegDate", query = "SELECT p FROM Players p WHERE p.regDate = :regDate")
        })
public class Players implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplayers")
    private String idplayers;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "reg_date")
    @Temporal(TemporalType.DATE)
    private Date regDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playersIdplayers")
    private List<DiceRolls> diceRollsList;

    public Players()
    {
    }

    public Players(String idplayers)
    {
        this.idplayers = idplayers;
    }

    public Players(String idplayers, String name, Date regDate)
    {
        this.idplayers = idplayers;
        this.name = name;
        this.regDate = regDate;
    }

    public String getIdplayers()
    {
        return idplayers;
    }

    public void setIdplayers(String idplayers)
    {
        this.idplayers = idplayers;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getRegDate()
    {
        return regDate;
    }

    public void setRegDate(Date regDate)
    {
        this.regDate = regDate;
    }

    @XmlTransient
    public List<DiceRolls> getDiceRollsList()
    {
        return diceRollsList;
    }

    public void setDiceRollsList(List<DiceRolls> diceRollsList)
    {
        this.diceRollsList = diceRollsList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idplayers != null ? idplayers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Players))
        {
            return false;
        }
        Players other = (Players) object;
        if ((this.idplayers == null && other.idplayers != null) || (this.idplayers != null && !this.idplayers.equals(other.idplayers)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.dicegame.models.Players[ idplayers=" + idplayers + " ]";
    }

}
