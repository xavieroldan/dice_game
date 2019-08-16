package com.dicegame.tools;

import com.dicegame.model.DiceGames;
import com.dicegame.model.DiceResults;
import com.dicegame.model.Players;
import java.util.HashSet;
import java.util.UUID;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameMaker
{

    private static final int ROLL_MAX = 2;
    private static final int WIN_RESULTS[] =
    {
        7
    };

    public DiceGames playGame(Players player)
    {
        String RandomID = UUID.randomUUID().toString().replace("-", "");
        //create the dice game
        DiceGames diceGame = new DiceGames();
        diceGame.setIdGames(RandomID);
        diceGame.setPlayers(player);
        diceGame.setDiceResults(new HashSet<DiceResults>());
        //lets play!
        int totalResult = 0;
        for (int i = 1; i < ROLL_MAX + 1; i++)
        {
            //launch the dice
            int result = RandomRollGen.getRandomRoll();
            DiceResults diceResult = new DiceResults(UUID.randomUUID(), i, result);
            diceResult.setDiceGames(diceGame);
            diceGame.getDiceResults().add(diceResult); //save the result
            totalResult += result;
        }
        //getting the result
        for (int i : WIN_RESULTS)
        {
            if (totalResult == i)
            {
                diceGame.setIsWinner(true);
            }
            else
            {
                diceGame.setIsWinner(false);
            }
        }
        return diceGame;
    }
}
