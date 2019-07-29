package com.dicegame.app;

import com.dicegame.app.tools.TimeStamp;
import com.dicegame.control.PlayersJpaController;
import com.dicegame.control.exceptions.FieldVoidException;
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
public class MainDestroyDuplicate
{
    public static void main(String[] args) throws Exception
    {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("DiceGameNoSpringPU");
        PlayersJpaController playerControl = new PlayersJpaController(emf);

        Date date = TimeStamp.getDate();
        Players player = new Players("kkkkkaaaaaa", "Pepilloaaaa", date);
        playerControl.create(player);
    }

}
