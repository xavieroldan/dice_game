package com.dicegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private String idGames;
    private boolean isWinner;
    private boolean isAnonim;
    private boolean isDeleted;

    @ManyToOne(targetEntity = Players.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_players", nullable = true)
    @JsonIgnore
    private Players players;

    @OneToMany(targetEntity = DiceResults.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Set<DiceResults> diceResults;

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

    public void setIdGames(String id)
    {
        this.idGames = id;
    }

    public boolean getIsWinner()
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

//    @Override
//    public String toString()
//    {
//        return "DiceGames{" + "idGames=" + idGames + ", isWinner=" + isWinner + ", isAnonim=" + isAnonim + ", isDeleted=" + isDeleted + ", diceResults=" + diceResults + ", players=" + players + '}';
//    }
    @Override
    public String toString()
    {
        return "DiceGames{" + "idGames=" + idGames + ", isWinner=" + isWinner + ", isAnonim=" + isAnonim + ", isDeleted=" + isDeleted + ", players=" + players + '}';
    }

}
