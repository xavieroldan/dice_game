package com.dicegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "dice_results")
public class DiceResult
{
    @Id
    private String idResult;
    private int idDice;
    private int results;
    @ManyToOne(targetEntity = DiceGame.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_games", nullable = true)
    @JsonIgnore
    private DiceGame diceGame;

    public DiceResult()
    {
    }

    public DiceResult(String idResult, int idDice, int result)
    {
        this.setIdResult(idResult);
        this.setIdDice(idDice);
        this.setResult(result);
    }

    public String getIdResult()
    {
        return idResult;
    }

    public void setIdResult(String idResult)
    {
        this.idResult = idResult;
    }

    public int getIdDice()
    {
        return idDice;
    }

    public void setIdDice(int idDice)
    {
        this.idDice = idDice;
    }

    public int getResult()
    {
        return results;
    }

    public void setResult(int result)
    {
        this.results = result;
    }

    public DiceGame getDiceGames()
    {
        return diceGame;
    }

    public void setDiceGame(DiceGame diceGame)
    {
        this.diceGame = diceGame;
    }

    @Override
    public String toString()
    {
        return "DiceResult{" + "idResult=" + idResult + ", idDice=" + idDice + ", result=" + results + ", diceGame=" + diceGame + '}';
    }

}
