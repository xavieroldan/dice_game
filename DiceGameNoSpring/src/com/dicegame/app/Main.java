package com.dicegame.app;

import com.dicegame.app.repository.GameRepository;
import com.dicegame.app.repository.PlayerRepository;
import com.dicegame.controllers.PlayerController;
import com.dicegame.models.DiceRolls;
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

                    PlayerRepository playerRepository = new PlayerRepository();
                    PlayerController playerController = new PlayerController(name, playerRepository);
                    GameRepository gameRepository = new GameRepository(playerController, true); //Is anonim
                    for (int i = 0; i < 5; i++)
                    {
                        gameRepository.createGame();
                    }
                    System.out.println("\nPorcentaje de acierto: " + playerController.getSuccessRate() + "\n");

//                    System.out.println(gameRepository.getListGame());
                    for (DiceRolls game : gameRepository.getListGame())
                    {
                        System.out.println("Lista de juegos");
                        System.out.println("--------------------");
                        System.out.println(game.toString());
                    }
//                    playerController.getListGames();
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
