package com.dicegame.app.repository;

import com.dicegame.app.tools.RandomRollGen;
import com.dicegame.jpa.control.DiceResultsJpaController;
import com.dicegame.jpa.control.DiceRollsJpaController;
import com.dicegame.jpa.control.PlayersJpaController;
import com.dicegame.exceptions.NonexistentEntityException;
import com.dicegame.models.Dice;
import com.dicegame.controllers.DiceGameController;
import com.dicegame.controllers.DiceRollController;
import com.dicegame.controllers.PlayerController;
import com.dicegame.models.DiceResults;
import com.dicegame.models.DiceResultsPK;
import com.dicegame.models.DiceRolls;
import com.dicegame.models.Players;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class GameRepository
{

    private PlayerController playerController;
    private boolean isAnonim;
    private EntityManagerFactory emf = javax.persistence.Persistence.
            createEntityManagerFactory("DiceGameNoSpringPU");
    private DiceRollsJpaController diceRollControl = new DiceRollsJpaController(emf);
    private PlayersJpaController playerControl = new PlayersJpaController(emf);
    private DiceResultsJpaController diceResultController = new DiceResultsJpaController(emf);

    public GameRepository(PlayerController playerController, boolean isAnonim)
    {
        this.playerController = playerController;
        this.isAnonim = isAnonim;
    }

    public void createGame() throws Exception
    {
        String playerId = playerController.getIdPlayer();
        String name = playerController.getName();
        DiceGameController diceGame = new DiceGameController(playerId);

        if (!isAnonim) // is not anonim set the Dice Game parameters
        {
            //set no anonimous parámeters
            diceGame.setIsAnonim(false);
            diceGame.setGameNick(name);
        }
        //if is anonim, the constructor makes the work
        //Lets play ;D
        //Create the roll in the DB and save the object
        DiceRolls diceRolls = createDiceRoll(diceGame);
        for (int i = 1; i < diceGame.getROLL_MAX() + 1; i++)
        {
            //Here the loop for the number of rolls by game
            DiceRollController diceRoll = new DiceRollController(); //Create the dice roll
            Dice dice = new Dice(i); //Create the dice
            diceRoll.setIdDice(dice.getDiceId()); //Launch the dice
            int result = RandomRollGen.getRandomRoll();
            diceRoll.setRollResult(result); // get the result of the play dice       
            diceGame.setDiceRoll(diceRoll);//set dice roll in the game  
            //Saving on the DB now:   
            //First set the diceRollsPK
            DiceResultsPK diceRPK = new DiceResultsPK();
            diceRPK.setIdDice(i);
            diceRPK.setDiceRollsIdRoll(playerId);
            //Now save in a DiceResults
            DiceResults diceResult = new DiceResults(diceRPK, result);
            diceResult.setDiceRolls(diceRolls);//save the DiceRolls entity 
            //Add the dice roll to the DB
            diceResultController.create(diceResult);
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
        //Add the Game to the DB
        addFinishedDiceRoll(diceGame);
        //add to the player historic 
        playerController.addListGame(diceGame);
    }

    public DiceRolls createDiceRoll(DiceGameController input) throws Exception
    {
        String idRoll = input.getGameId();
        boolean isAnonim = this.isAnonim;

        DiceRolls diceRolls = new DiceRolls(idRoll);//Create the entity and set id
        diceRolls.setIsAnonim(isAnonim);//set is anonim

        Players player = playerControl.findPlayers(input.getPlayerId());
        diceRolls.setPlayersIdplayers(player);
        diceRollControl.create(diceRolls);//create in the db        

        return diceRolls;
    }

    public boolean addFinishedDiceRoll(DiceGameController input) throws NonexistentEntityException, Exception
    {
        boolean isAdded = false;
        //Search in the db and update the isWinner
        //edit on database
        DiceRolls diceRoll = diceRollControl.findDiceRolls(input.getGameId());
        diceRoll.setIsWinner(input.getIsWinner());
        diceRollControl.edit(diceRoll);
        isAdded = true;
        return isAdded;
    }

    public List<DiceRolls> getListGame()
    {
        String idPlayer = playerController.getIdPlayer();
        List<DiceRolls> listDiceRolls = new ArrayList<>();

        for (DiceRolls diceRoll : diceRollControl.findDiceRollsEntities())
        {
            if (diceRoll.getPlayersIdplayers().getIdplayers().equals(idPlayer))
            {
                listDiceRolls.add(diceRoll);
            }
        }
        return listDiceRolls;
    }

    public void deleteListGames()
    {
        //delete the dice rolls

        //delete the dice games
    }

}
