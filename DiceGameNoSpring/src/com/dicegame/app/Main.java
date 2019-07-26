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
        for (int i = 0; i < 10; i++)
        {
            pepeFactory.playGame();
        }
        System.out.println("\nPorcentaje de acierto: " + pepeUser.getSuccessRate() + "\n");
        pepeUser.getHistoricGames();

    }

}
