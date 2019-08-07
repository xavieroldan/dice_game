package com.dicegame.repository;

import com.dicegame.model.DiceResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public interface DiceResultRepository extends JpaRepository<DiceResult, String>
{

}
