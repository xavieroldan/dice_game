package com.dicegame.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Entity
@Table(name = "players")
public class Player implements Serializable
{
    @Id
    private String idPlayer;
    private String name;
    private Date regDate;
    
    @OneToMany(targetEntity = DiceGame.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Set<DiceGame> diceGame;
    
    public Player()
    {
    }
    
    public Player(String idPlayer, String name, Date regDate, Set<DiceGame> diceGame)
    {
        this.setIdPlayer(idPlayer);
        this.setName(name);
        this.setRegDate(regDate);
        this.setDiceGame(diceGame);
    }
    
    public String getIdPlayer()
    {
        return idPlayer;
    }
    
    public void setIdPlayer(String id)
    {
        this.idPlayer = id;
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
    
    public void setRegDate(Date regDate)
    {
        this.regDate = regDate;
    }
    
    public Set<DiceGame> getDiceGame()
    {
        return diceGame;
    }
    
    public void setDiceGame(Set<DiceGame> diceGame)
    {
        this.diceGame = diceGame;
    }
    
    @Override
    public String toString()
    {
        return "Player{" + "idPlayer=" + idPlayer + ", name=" + name + ", regDate=" + regDate + ", diceGame=" + diceGame + '}';
    }
    
}
