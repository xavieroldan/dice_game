package com.dicegame.app;

import com.dicegame.app.factory.GameFactory;
import com.dicegame.app.tools.RandomRollGen;
import com.dicegame.domain.User;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Main
{
    public static void main(String[] args)
    {
        User pepeUser = new User("Pepe");
        GameFactory pepeFactory = new GameFactory(pepeUser, true);
        for (int i = 0; i < 50; i++)
        {
            pepeFactory.playGame();
        }

        for (int i = 0; i < pepeUser.getHistoricGames().size(); i++)
        {
            System.out.println(pepeUser.getHistoricGames().get(i).toString());

        }

    }

}
