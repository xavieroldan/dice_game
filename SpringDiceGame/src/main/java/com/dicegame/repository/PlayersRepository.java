package com.dicegame.repository;

import com.dicegame.model.Players;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
public interface PlayersRepository extends CrudRepository<Players, String>
//public interface PlayersRepository extends JpaRepository<Players, String>
{

}
