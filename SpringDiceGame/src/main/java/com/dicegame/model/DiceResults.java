package com.dicegame.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "id_results")
    private String idResults;
    private int idDice;
    private int results;
    @ManyToOne
    @JoinColumn(name = "id_games")
//    @JoinColumn(name = "id_games", nullable = true)
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
