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
        //Lets play

        for (int i = 0; i < diceGame.getROLL_MAX(); i++)
        {
            //Here the loop 
            //Intentaré no crear los dados a mano 
            //Si sale bien sacar las constante de ROLL_MAX y WIN_POINTS fuera de la clase

        }
        //Create the dices
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        //Create the dice roll
        DiceRoll diceRoll = new DiceRoll();
        //Launch the dice 1        
        diceRoll.setDiceId(dice1.getDiceId());
        diceRoll.setRollResult(RandomRollGen.getRandomRoll()); // get the result of the play dice 1       
        diceGame.setDiceRoll(diceRoll); //set dice roll in the game
        //Launch the dice 2          
        diceRoll.setDiceId(dice2.getDiceId());
        diceRoll.setRollResult(RandomRollGen.getRandomRoll()); // get the result of the play dice 1        
        diceGame.setDiceRoll(diceRoll); //set dice roll in the game

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
