package com.dicegame.app;

import com.dicegame.app.factory.GameFactory;
import com.dicegame.app.factory.PlayerFactory;
import com.dicegame.domain.Player;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        PlayerFactory playerFactory = new PlayerFactory();
        Player pepeUser = new Player("Pepe", playerFactory);
        GameFactory pepeFactory = new GameFactory(pepeUser, true);
        for (int i = 0; i < 10; i++)
        {
            pepeFactory.playGame();
        }
        System.out.println("\nPorcentaje de acierto: " + pepeUser.getSuccessRate() + "\n");
        pepeUser.getHistoricGames();

    }

}
