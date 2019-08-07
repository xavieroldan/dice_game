/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.tools;

import com.dicegame.model.Player;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameSimulator
{
    public static void main(String[] args)
    {
        System.out.println("Tirada de dados");
        System.out.println("---------------");
        Player player = new Player();
        player.setIdPlayer("ljkljkljlkj");
        player.setName("Jugón");
        player.setRegDate(TimeStamp.getDate());
        for (int i = 0; i < 20; i++)
        {
//            System.out.println(GameFactory.playGame(player));

        }

    }

}
