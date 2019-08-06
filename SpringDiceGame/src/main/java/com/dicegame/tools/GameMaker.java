package com.dicegame.tools;

import com.dicegame.controller.DiceResultsController;
import com.dicegame.model.DiceGames;
import com.dicegame.model.DiceResults;
import com.dicegame.model.Players;
import com.dicegame.repository.DiceResultsRepository;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

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
            String resultRandomID = UUID.randomUUID().toString().replace("-", "");
            int result = RandomRollGen.getRandomRoll();
            DiceResults diceResult = new DiceResults(resultRandomID, i, result);
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
