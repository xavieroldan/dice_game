/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "dice_rolls")
public class DiceRolls
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idRoll;
    private boolean isWinner;
    private boolean isAnonim;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "idplayers", nullable = false)
    private Players players;
//    @OneToMany(mappedBy = "dice_rolls") //Aquí el fallo
//    private Set<DiceResults> diceResults;

    public DiceRolls()
    {
    }

//    public Set<DiceResults> getDiceResults()
//    {
//        return diceResults;
//    }
//
//    public void setDiceResults(Set<DiceResults> diceResults)
//    {
//        this.diceResults = diceResults;
//    }
    public Players getPlayers()
    {
        return players;
    }

    public void setPlayers(Players players)
    {
        this.players = players;
    }

    public String getIdRoll()
    {
        return idRoll;
    }

    public void setIdRoll(String idRoll)
    {
        this.idRoll = idRoll;
    }

    public boolean isIsWinner()
    {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner)
    {
        this.isWinner = isWinner;
    }

    public boolean isIsAnonim()
    {
        return isAnonim;
    }

    public void setIsAnonim(boolean isAnonim)
    {
        this.isAnonim = isAnonim;
    }

    public boolean isIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

}
