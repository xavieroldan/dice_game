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
public class DiceGame
{
    @Id
    private String idGame;
    private boolean isWinner;
    private boolean isAnonim;
    private boolean isDeleted;

    @ManyToOne(targetEntity = Player.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_players", nullable = true)
    @JsonIgnore
    private Player player;

    @OneToMany(targetEntity = DiceResult.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Set<DiceResult> diceResult;

    public DiceGame()
    {
    }

    public Set<DiceResult> getDiceResult()
    {
        return diceResult;
    }

    public void setDiceResult(Set<DiceResult> diceResult)
    {
        this.diceResult = diceResult;
    }

    public String getIdGame()
    {
        return idGame;
    }

    public void setIdGame(String id)
    {
        this.idGame = id;
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

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public String toString()
    {
        return "DiceGame{" + "idGame=" + idGame + ", isWinner=" + isWinner + ", isAnonim=" + isAnonim + ", isDeleted=" + isDeleted + ", player=" + player + '}';
    }

}
