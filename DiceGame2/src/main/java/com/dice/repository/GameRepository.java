package com.dice.repository;

import com.dice.model.Game;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public interface GameRepository extends CrudRepository<Game, UUID>
{

}
