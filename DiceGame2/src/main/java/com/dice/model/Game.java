package com.dice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "game")
public class Game
{
    @Id
    @Column(name = "idgame", length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idGame;

    @Column(
            name = "anonim", length = 1,
            columnDefinition = "boolean default false",
            nullable = false
    )
    private boolean isAnonim;

    @Column(
            name = "winner", length = 1,
            columnDefinition = "boolean default false",
            nullable = false
    )
    private boolean isWinner;

    @Column(
            name = "deleted", length = 1,
            columnDefinition = "boolean default false",
            nullable = false
    )
    private boolean isDeleted;

    @ManyToOne(
            targetEntity = Player.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "idplayer",
            nullable = true)
    @JsonIgnore
    private Player player;

    public Game()
    {
    }

    public Game(UUID idGame, boolean isAnonim, boolean isWinner, boolean isDeleted)
    {
        this.idGame = idGame;
        this.isAnonim = isAnonim;
        this.isWinner = isWinner;
        this.isDeleted = isDeleted;
    }

    public UUID getIdGame()
    {
        return idGame;
    }

    public void setIdGame(UUID idGame)
    {
        this.idGame = idGame;
    }

    public boolean getIsAnonim()
    {
        return isAnonim;
    }

    public void setIsAnonim(boolean isAnonim)
    {
        this.isAnonim = isAnonim;
    }

    public boolean getIsWinner()
    {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner)
    {
        this.isWinner = isWinner;
    }

    public boolean getIsDeleted()
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
        return "Game{" + "idGame=" + idGame + ", isAnonim=" + isAnonim + ", isWinner=" + isWinner + ", isDeleted=" + isDeleted + ", player=" + player + '}';
    }
}
