package com.dicegame.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "players")
public class Players implements Serializable
{
    @Id
    private String idPlayers;
    private String name;
    private Date regDate;

    @OneToMany(targetEntity = DiceGames.class, fetch = FetchType.EAGER)
    @Column(nullable = true)
    private Set<DiceGames> diceGames;

    public Players()
    {
    }

    public Players(String idPlayers, String name, Date regDate, Set<DiceGames> diceGames)
    {
        this.idPlayers = idPlayers;
        this.name = name;
        this.regDate = regDate;
        this.diceGames = diceGames;
    }

    public String getIdPlayers()
    {
        return idPlayers;
    }

    public void setIdPlayers(String id)
    {
        this.idPlayers = id;
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

    public Set<DiceGames> getDiceGames()
    {
        return diceGames;
    }

    public void setDiceGames(Set<DiceGames> diceGames)
    {
        this.diceGames = diceGames;
    }

    @Override
    public String toString()
    {
        return "Players{" + "idPlayers=" + idPlayers + ", name=" + name + ", regDate=" + regDate + ", diceGames=" + diceGames + '}';
    }

}
