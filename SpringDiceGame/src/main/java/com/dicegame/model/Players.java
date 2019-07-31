/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
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
    private String idplayers;
    private String name;
    private Date regDate;

    @OneToMany(mappedBy = "players")
    private Set<DiceRolls> diceRolls;

    public Set<DiceRolls> getDiceRolls()
    {
        return diceRolls;
    }

    public void setDiceRolls(Set<DiceRolls> diceRolls)
    {
        this.diceRolls = diceRolls;
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

}
