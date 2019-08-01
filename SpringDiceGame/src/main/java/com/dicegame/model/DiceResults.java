package com.dicegame.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idResults;
    private int idDice;
    private int result;
    @ManyToOne
    @JoinColumn(name = "id_games", nullable = true)
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
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
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
        return "DiceResults{" + "idResults=" + idResults + ", idDice=" + idDice + ", result=" + result + ", diceGames=" + diceGames + '}';
    }

}
