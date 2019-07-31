package com.dicegame.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "dice_results")
public class DiceResults
{
    @EmbeddedId
    private DiceResultsIdentity diceResultsIdentity;
    @NotNull
    private int rollResult;
//    @ManyToOne
//    @JoinColumn(name = "id_roll", nullable = false)
//    private DiceRolls diceRolls;

    public DiceResults(DiceResultsIdentity diceResultsIdentity, int rollResult)
    {
        this.diceResultsIdentity = diceResultsIdentity;
        this.rollResult = rollResult;
    }

//    public DiceRolls getDiceRolls()
//    {
//        return diceRolls;
//    }
//
//    public void setDiceRolls(DiceRolls diceRolls)
//    {
//        this.diceRolls = diceRolls;
//    }
    public DiceResultsIdentity getDiceResultsIdentity()
    {
        return diceResultsIdentity;
    }

    public void setDiceResultsIdentity(DiceResultsIdentity diceResultsIdentity)
    {
        this.diceResultsIdentity = diceResultsIdentity;
    }

    public int getRollResult()
    {
        return rollResult;
    }

    public void setRollResult(int rollResult)
    {
        this.rollResult = rollResult;
    }
}
