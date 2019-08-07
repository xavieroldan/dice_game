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
public class DiceResults
{
    @Id
    private String idResults;
    private int idDice;
    private int results;
    @ManyToOne(targetEntity = DiceGames.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_games", nullable = true)
    @JsonIgnore
    private DiceGames diceGames;

    public DiceResults()
    {
    }

    public DiceResults(String idResults, int idDice, int result)
    {
        this.setIdResults(idResults);
        this.setIdDice(idDice);
        this.setResult(result);
    }

    public String getIdResults()
    {
        return idResults;
    }

    public void setIdResults(String idResults)
    {
        this.idResults = idResults;
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

    public DiceGames getDiceGames()
    {
        return diceGames;
    }

    public void setDiceGames(DiceGames diceGames)
    {
        this.diceGames = diceGames;
    }

    @Override
    public String toString()
    {
        return "DiceResults{" + "idResults=" + idResults + ", idDice=" + idDice + ", result=" + results + ", diceGames=" + diceGames + '}';
    }
}
