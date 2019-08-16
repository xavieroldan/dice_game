package com.dice.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "player")
public class Player
{
    @Id
    @Column(name = "idplayer", length = 16)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private UUID idPlayer;

    @Column(name = "name", length = 50)
    @NotNull
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regdate")
    private Date regDate;

    @OneToMany(
            targetEntity = Game.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<Game> listGame;

    public Player()
    {
    }

    public Player(UUID idPlayer, String name, Date regDate, List<Game> listGame)
    {
        this.setIdPlayer(idPlayer);
        this.setName(name);
        this.setRegDate(regDate);
        this.setListGame(listGame);
    }

    public UUID getIdPlayer()
    {
        return idPlayer;
    }

    public void setIdPlayer(UUID idPlayer)
    {
        this.idPlayer = idPlayer;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getRegDate()
    {
        return regDate;
    }

    public void setRegDate(Date date)
    {
        this.regDate = date;
    }

    public List<Game> getListGame()
    {
        return listGame;
    }

    public void setListGame(List<Game> listGame)
    {
        this.listGame = listGame;
    }

    @Override
    public String toString()
    {
        return "Player{" + "idPlayer=" + idPlayer + ", name=" + name + ", regDate=" + regDate + ", listGame=" + listGame + '}';
    }
}
