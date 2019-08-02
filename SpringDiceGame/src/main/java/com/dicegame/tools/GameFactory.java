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
public class GameFactory
{

    private static final int ROLL_MAX = 2;
    private static final int WIN_RESULTS[] =
    {
        7
    };
//    private DiceGames dicegame;
//    private DiceResults diceResult;
    @Autowired
    private DiceResultsRepository repository;

//    public DiceGames playGame(Players player)
//    {
//        String RandomID = UUID.randomUUID().toString().replace("-", "");
//        //create the dice game
//        DiceGames diceGame = new DiceGames();
//        diceGame.setPlayers(player);
//        diceGame.setIdGames(RandomID);
//        diceGame.setDiceResults(new HashSet<DiceResults>());
//        //lets play!
//        int totalResult = 0;
//        for (int i = 1; i < ROLL_MAX + 1; i++)
//        {
//            int result = RandomRollGen.getRandomRoll();
//            DiceResults diceResult
//                    = new DiceResults(RandomID, i, result);
//            diceResult.setDiceGames(diceGame);
//            DiceResultsController controler = new DiceResultsController();
//            controler.saveDiceResult(diceResult);
//            diceGame.getDiceResults().add(diceResult);
//            totalResult += result;
//        }
//        //getting the result
//        for (int i : WIN_RESULTS)
//        {
//            if (totalResult == i)
//            {
//                diceGame.setIsWinner(true);
//            }
//            else
//            {
//                diceGame.setIsWinner(false);
//            }
//            System.out.println("Resultado: " + totalResult + " Winner: " + diceGame.getIsWinner());
//        }
//        return diceGame;
//    }
//    public DiceGames getDicegame()
//    {
//        return dicegame;
//    }
//
//    public void setDicegame(DiceGames dicegame)
//    {
//        this.dicegame = dicegame;
//    }
//
//    public DiceResults getDiceResult()
//    {
//        return diceResult;
//    }
//
//    public void setDiceResult(DiceResults diceResult)
//    {
//        this.diceResult = diceResult;
//    }
}
