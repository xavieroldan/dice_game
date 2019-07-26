package com.dicegame.app;

import com.dicegame.app.factory.GameFactory;
import com.dicegame.app.factory.PlayerFactory;
import com.dicegame.domain.Player;
import java.util.Scanner;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        boolean isExiting = false;
        while (!isExiting)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Indica un nombre de jugador o exit para salir");
            String name = sc.nextLine();
            switch (name)
            {
                case "exit":
                    isExiting = true;
                    break;
                default:

                    PlayerFactory playerFactory = new PlayerFactory();
                    Player pepeUser = new Player(name, playerFactory);
                    GameFactory pepeFactory = new GameFactory(pepeUser, false);
                    for (int i = 0; i < 10; i++)
                    {
                        pepeFactory.playGame();
                    }
                    System.out.println("\nPorcentaje de acierto: " + pepeUser.getSuccessRate() + "\n");
                    pepeUser.getHistoricGames();
            }
        }

    }

}
