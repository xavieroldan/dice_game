/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.app;

import com.dicegame.app.tools.TimeStamp;
import com.dicegame.models.Players;
import java.util.Date;
import spring.objects.NewPlayerRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class MainSpring
{
    public static void main(String[] args)
    {
        String name = "Torcuato";
        String idPlayer = "khkjhkjhkjh";
        Date date = TimeStamp.getDate();
        Players player = new Players(idPlayer, name, date);
        NewPlayerRepository playerRepo = new NewPlayerRepository();
        playerRepo.save(player);

    }

}
