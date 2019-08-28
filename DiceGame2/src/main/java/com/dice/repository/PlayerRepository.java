package com.dice.repository;

import com.dice.model.Player;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public interface PlayerRepository extends CrudRepository<Player, UUID>
{
    Player findByName(String name);
}
