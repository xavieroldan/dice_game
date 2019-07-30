package com.dicegame.app.factory;

import com.dicegame.app.tools.RandomRollGen;
import com.dicegame.control.DiceRollsJpaController;
import com.dicegame.control.PlayersJpaController;
import com.dicegame.exceptions.NonexistentEntityException;
import com.dicegame.controllers.Dice;
import com.dicegame.controllers.DiceGame;
import com.dicegame.controllers.DiceRoll;
import com.dicegame.controllers.PlayerController;
import com.dicegame.models.DiceRolls;
import com.dicegame.models.Players;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameFactory
{

    private PlayerController user;
    private boolean isAnonim;
    private EntityManagerFactory emf = javax.persistence.Persistence.
            createEntityManagerFactory("DiceGameNoSpringPU");
    private DiceRollsJpaController diceRollControl = new DiceRollsJpaController(emf);
    private PlayersJpaController playerControl = new PlayersJpaController(emf);

    public GameFactory(PlayerController user, boolean isAnonim)
    {
        this.user = user;
        this.isAnonim = isAnonim;
    }

    public void playGame() throws Exception
    {
        String playerId = user.getPlayerId();
        String name = user.getName();
        DiceGame diceGame = new DiceGame(playerId);

        if (!isAnonim) // is not anonim set the Dice Game parameters
        {
            //set no anonimous parámeters
            diceGame.setIsAnonim(false);
            diceGame.setGameNick(name);

        }
        //if is anonim, the constructor makes the work
        //Lets play ;D
        //Create the roll in the DB
        createDiceRoll(diceGame);
        for (int i = 1; i < diceGame.getROLL_MAX() + 1; i++)
        {
            //Here the loop 
            DiceRoll diceRoll = new DiceRoll(); //Create the dice roll
            Dice dice = new Dice(i); //Create the dice
            diceRoll.setDiceId(dice.getDiceId()); //Launch the dice
            diceRoll.setRollResult(RandomRollGen.getRandomRoll()); // get the result of the play dice 1       
            diceGame.setDiceRoll(diceRoll);//set dice roll in the game  
        }

        //TODO:To delete vvvvvvvvvvv
        System.out.println("Jugada acabada:" + diceGame.getGameResult());
        if (diceGame.getIsWinner())
        {
            System.out.println(">>>>>>>>>>>>Has ganado!");
        }
        else
        {
            System.out.println("Perdiste");
        }
        //TODO:To delete ^^^^^^^^^^^^

        //TODO: add the Game to the DB
        addDiceRoll(diceGame);
        //add to the player historic 
        user.addGame(diceGame);
    }

    public boolean createDiceRoll(DiceGame input) throws Exception
    {
        boolean isAdded = false;

        String idRoll = input.getGameId();
        boolean isAnonim = this.isAnonim;
        String idPlayer = this.user.getPlayerId();

        DiceRolls diceRolls = new DiceRolls(idRoll);//Create the entity and set id

        diceRolls.setIsAnonim(isAnonim);//set is anonim

        Players player = playerControl.findPlayers(input.getPlayerId());
        diceRolls.setPlayersIdplayers(player);
        diceRollControl.create(diceRolls);//create in the db        
        isAdded = true;
        return isAdded;
    }

    public boolean addDiceRoll(DiceGame input) throws NonexistentEntityException, Exception
    {
        //TODO: add the result to the DB
        boolean isAdded = false;
        //Search in the db and update the isWinner
        //edit on database
        DiceRolls diceRoll = diceRollControl.findDiceRolls(input.getGameId());
        diceRoll.setIsWinner(input.getIsWinner());
        diceRollControl.edit(diceRoll);

        isAdded = true;
        return isAdded;
    }

    public boolean createDiceGame(PlayerController input) throws Exception
    {
        return false;
    }

    public boolean updateDiceGame(PlayerController input) throws Exception
    {
        return false;
    }

    public boolean deleteGames(PlayerController input) throws Exception
    {
        return false;
    }

    //TODO: Get historic games here?
}
