package com.dicegame.tools;

import com.dicegame.model.DiceGame;
import com.dicegame.model.DiceResult;
import com.dicegame.model.Player;
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
    }; // use an array for future development posibilities of various winning results

    public DiceGame playGame(Player player)
    {
        String RandomID = UUID.randomUUID().toString().replace("-", "");
        //create the dice game
        DiceGame diceGame = new DiceGame();
        diceGame.setIdGame(RandomID);
        diceGame.setPlayer(player);
        diceGame.setDiceResult(new HashSet<DiceResult>());
        //lets play!
        int totalResult = 0;
        for (int i = 1; i < ROLL_MAX + 1; i++)
        {
            //Put the result Id
            String resultRandomID = UUID.randomUUID().toString().replace("-", "");
            //launch the dice
            int result = RandomRollGen.getRandomRoll();
            //set the result
            DiceResult diceResult = new DiceResult(resultRandomID, i, result);
            //add to the game
            diceResult.setDiceGame(diceGame);
            diceGame.getDiceResult().add(diceResult);
            //add to the total result
            totalResult += result;
        }
        //getting the final result
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
