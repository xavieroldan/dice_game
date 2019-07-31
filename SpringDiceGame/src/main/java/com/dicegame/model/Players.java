/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idPlayers;
    private String name;
    private Date regDate;

    @OneToMany(mappedBy = "players") //cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    private Set<DiceGames> diceGames;

    public Set<DiceGames> getDiceRolls()
    {
        return diceGames;
    }

    public void setDiceRolls(Set<DiceGames> diceGames)
    {
        this.diceGames = diceGames;
    }

    public String getIdPlayers()
    {
        return idPlayers;
    }

    public void setIdPlayers(String id)
    {
        this.idPlayers = idPlayers;
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

}
