/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.models;

import com.dice.models.Player;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class RateDTO implements Comparable<RateDTO>
{

    private Player player;
    private double rate;

    public RateDTO(Player player, double rate)
    {
        this.setPlayer(player);
        this.setRate(rate);
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    @Override
    public int compareTo(RateDTO o)
    {
        return this.rate > o.rate ? 1 : this.rate < o.rate ? -1 : 0;
    }

    @Override
    public String toString()
    {
        return "RateDTO{" + "player=" + player + ", rate=" + rate + '}';
    }
}
