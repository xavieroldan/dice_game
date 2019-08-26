package com.dice.tool;

import com.dice.model.DiceResult;
import com.dice.model.Game;
import com.dice.model.Player;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameMakerSixDice
{

    private static final int ROLL_MAX = 6;
    private static final int WIN_RESULTS[] =
    {
        5, 6
    };

    public Player playGameSixDice(Player player)
    {
        //create the dice game
        Game game = new Game();
        game.setIdGame(UUID.randomUUID());
        game.setPlayer(player);
        game.setListDiceResult(new ArrayList<>());
        //lets play!
        boolean isWinner = true;
        for (int i = 1; i < ROLL_MAX + 1; i++)
        {
            //launch the dice
            int result = RandomRollGen.getRandomRoll();
            DiceResult diceResult = new DiceResult(UUID.randomUUID(), result);
            diceResult.setGame(game);
            game.getListDiceResult().add(diceResult);
            //verify winner game for diferents results
            boolean winCheck = true;
            for (int j : WIN_RESULTS)
            {
                boolean antWinCheck = winCheck;
                if (result == j)
                {
                    //Is the first result true : winner
                    winCheck = true;
                    System.out.println(i + "-Jugada Winner-" + result);
                }
                else
                {
                    if (antWinCheck)
                    {
                        System.out.println("Sigue en winner");
                        /*
                        Si el anterior es falso verifica este
                        y si es true entonces es winner. No funciona porque siempre 
                        me dice que es true aunque no sean los números...
                         */
                        winCheck = true;
                    }
                    else
                    {
                        winCheck = false;
                    }
                    System.out.println(i + "-Jugada Loser-" + result);
                }
            }
        }
        //getting the result
        System.out.println("Resultado Winner: " + isWinner);

        if (isWinner)
        {
            game.setIsWinner(true);
        }
        else
        {
            game.setIsWinner(false);
        }

        if (player.getListGame() == null)
        {
            player.setListGame(new ArrayList<>());
        }
        player.getListGame().add(game);
        return player;
    }
}
