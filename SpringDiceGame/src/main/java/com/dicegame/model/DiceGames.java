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
@Table(name = "dice_games")
public class DiceGames
{
    @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idGames;
    private boolean isWinner;
    private boolean isAnonim;
    private boolean isDeleted;
    @OneToMany //Aquí el fallo
    private Set<DiceResults> diceResults;

    @ManyToOne
    @JoinColumn(name = "id_players", nullable = true)
    private Players players;

    public DiceGames()
    {
    }

    public Set<DiceResults> getDiceResults()
    {
        return diceResults;
    }

    public void setDiceResults(Set<DiceResults> diceResults)
    {
        this.diceResults = diceResults;
    }

    public String getIdGames()
    {
        return idGames;
    }

    public void setIdGames(String idGames)
    {
        this.idGames = idGames;
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

    public Players getPlayers()
    {
        return players;
    }

    public void setPlayers(Players players)
    {
        this.players = players;
    }

}
