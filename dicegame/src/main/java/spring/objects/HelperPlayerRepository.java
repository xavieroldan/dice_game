/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.objects;

import com.dicegame.models.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
public interface HelperPlayerRepository extends JpaRepository<Players, Long>

{

}
