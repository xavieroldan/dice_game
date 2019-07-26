package com.dicegame.app.factory;

import com.dicegame.app.tools.RandomRollGen;
import com.dicegame.domain.Dice;
import com.dicegame.domain.DiceGame;
import com.dicegame.domain.DiceRoll;
import com.dicegame.domain.User;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameFactory
{

    private User user;
    private boolean isAnonim;

    public GameFactory(User user, boolean isAnonim)
    {
        this.user = user;
        this.isAnonim = isAnonim;
    }

    public void playGame()
    {
        int playerId = user.getPlayerId();
        String name = user.getName();

        DiceGame diceGame = new DiceGame(playerId);
        if (!isAnonim) // is not anonim set the Dice Game parameters
        {
            //set no anonimous parámeters
            diceGame.setIsAnonim(isAnonim);
            diceGame.setGameNick(name);
        }
        //is anonim, the constructor makes the work
        //Lets play ;D

        for (int i = 1; i < diceGame.getROLL_MAX() + 1; i++)
        {
            //Here the loop 
            DiceRoll diceRoll = new DiceRoll(); //Create the dice roll
            Dice dice = new Dice(i); //Create the dice
            diceRoll.setDiceId(dice.getDiceId()); //Launch the dice
            diceRoll.setRollResult(RandomRollGen.getRandomRoll()); // get the result of the play dice 1       
            diceGame.setDiceRoll(diceRoll);//set dice roll in the game 
        }

        System.out.println("Jugada acabada:" + diceGame.getGameResult());
        if (diceGame.getIsWinner())
        {
            System.out.println(">>>>>>>>>>>>Has ganado!");
        }
        else
        {
            System.out.println("Perdiste");
        }
        //add to the player historic
        user.addGame(diceGame);

    }

}
