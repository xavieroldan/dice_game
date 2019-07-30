package com.dicegame.app;

import com.dicegame.app.factory.GameFactory;
import com.dicegame.app.factory.PlayerFactory;
import com.dicegame.controllers.PlayerController;
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
            String name = sc.next();
            switch (name)
            {
                case "exit":
                    isExiting = true;
                    break;
                default:

                    PlayerFactory playerFactory = new PlayerFactory();
                    PlayerController player = new PlayerController(name, playerFactory);
                    GameFactory gameFactory = new GameFactory(player, true); //Is anonim
                    for (int i = 0; i < 1; i++)
                    {
                        gameFactory.playGame();
                    }
                    System.out.println("\nPorcentaje de acierto: " + player.getSuccessRate() + "\n");
                    player.getHistoricGames();

//                    System.out.println("Cambia el nombre a " + player.getName());
//                    name = sc.next();
//                    System.out.println("Cambiamos " + player.getName() + " x " + name);
//                    playerFactory.edit(player, name);
//                    System.out.println("Ecribe algo y enter para destruirlo");
//                    name = sc.next();
//                    playerFactory.delete(player);
//                    System.out.println("Destruido!");
                //TODO: destroy the player's game  ???                  
//                    System.out.println("Cambia el nombre a " + player.getName());
//                    name = sc.next();
//                    System.out.println("Cambiamos " + player.getName() + " x " + name);
//                    playerFactory.edit(player, name);
//                    System.out.println("Ecribe algo y enter para destruirlo");
//                    name = sc.next();
//                    playerFactory.delete(player);
//                    System.out.println("Destruido!");
                //TODO: destroy the player's game  ???                  
            }
        }

    }

}
