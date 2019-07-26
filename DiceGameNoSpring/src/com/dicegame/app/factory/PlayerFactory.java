package com.dicegame.app.factory;

import com.dicegame.control.PlayersJpaController;
import com.dicegame.control.exceptions.PreexistingEntityException;
import com.dicegame.domain.Player;
import com.dicegame.models.Players;
import java.util.ArrayList;
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
        //first verify if exists
        List<Players> listPlayers = playerControl.findPlayersEntities();
        List<String> namePlayers = new ArrayList<>();
        for (Players listPlayer : listPlayers)
        {
            namePlayers.add(listPlayer.getName());
        }

        if (!namePlayers.contains(input.getName()))
        {
            //not exist at the DB
            String playerId = input.getPlayerId();
            String name = input.getName();
            Date regDate = input.getRegDate();
            Players player = new Players(playerId, name, regDate);
            playerControl.create(player);
            isAdded = true;
            return isAdded;
        }
        throw new PreexistingEntityException("Nombre de jugador ya existente: no se permiten duplicados");
    }
}
