/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.tool;

import com.dice.model.Player;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class RateDTO
{

    private UUID player;
    private double rate;

    public RateDTO(UUID player, double rate)
    {
        this.setPlayer(player);
        this.setRate(rate);
    }

    public UUID getPlayer()
    {
        return player;
    }

    public void setPlayer(UUID player)
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

}
