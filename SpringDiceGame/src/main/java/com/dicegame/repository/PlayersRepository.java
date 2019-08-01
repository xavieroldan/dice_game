package com.dicegame.repository;

import com.dicegame.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
//public interface PlayersRepository extends CrudRepository<Players, String>
public interface PlayersRepository extends JpaRepository<Players, String>
{

}
