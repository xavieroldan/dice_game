package com.dicegame.app.factory;

import com.dicegame.control.PlayersJpaController;
import com.dicegame.control.exceptions.FieldVoidException;
import com.dicegame.control.exceptions.IllegalOrphanException;
import com.dicegame.control.exceptions.NonexistentEntityException;
import com.dicegame.control.exceptions.PreexistingEntityException;
import com.dicegame.domain.Player;
import com.dicegame.models.Players;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class PlayerFactory
{

    private EntityManagerFactory emf = javax.persistence.Persistence.
            createEntityManagerFactory("DiceGameNoSpringPU");
    private PlayersJpaController playerControl = new PlayersJpaController(emf);

    public boolean create(Player input) throws Exception
    {
        boolean isAdded = false;
        if (input.getName().isEmpty())
        {
            throw new FieldVoidException("El nombre no puede estar vacío");
        }
        //first verify if exists 
        List<Players> listPlayers = playerControl.findPlayersEntities();
        for (Players listPlayer : listPlayers)
        {
            if (listPlayer.getName().equalsIgnoreCase(input.getName()))//exit at database ignoreCase
            {
                throw new PreexistingEntityException(""
                        + "Nombre de jugador ya existente: no se permiten duplicados");
            }
        }
        //not exist at the DB: lest create it
        String playerId = input.getPlayerId();
        String name = input.getName();
        Date regDate = input.getRegDate();
        Players player = new Players(playerId, name, regDate);
        playerControl.create(player);
        isAdded = true;
        return isAdded;
    }

    public boolean edit(Player input, String newName) throws Exception
    {
        boolean isEdited = false;
        if (input.getName().isEmpty())
        {
            throw new FieldVoidException("El nombre no puede estar vacío");
        }

        //edit on database
        Players player = playerControl.findPlayers(input.getPlayerId());
        player.setName(newName);
        playerControl.edit(player);
        input.setName(newName); //change in object
        isEdited = true;
        return isEdited;
    }

    public boolean delete(Player input) throws IllegalOrphanException, NonexistentEntityException
    {
        boolean isDeleted = false;
        playerControl.destroy(input.getPlayerId());
        isDeleted = true;
        return isDeleted;
    }

}
