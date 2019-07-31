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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idResults;
    private int idDice;
    private int result;
    @ManyToOne
    @JoinColumn(name = "id_games", nullable = false)
    private DiceGames diceGames;

    public String getId()
    {
        return idResults;
    }

    public void setId(String idResults)
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

}
