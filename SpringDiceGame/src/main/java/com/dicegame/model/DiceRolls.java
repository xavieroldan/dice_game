/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Players playersIdplayers;
    //TODO: define the relation of the table
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diceRolls")
//    private List<DiceResults> diceResultsList;

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

    public Players getPlayersIdplayers()
    {
        return playersIdplayers;
    }

    public void setPlayersIdplayers(Players playersIdplayers)
    {
        this.playersIdplayers = playersIdplayers;
    }

}
