/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.objects;

import com.dicegame.models.Players;
import java.security.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Repository
public class NewPlayerRepository
{

//    @Autowired
    private HelperPlayerRepository repository;

    public void save(Players player) throws InvalidParameterException
    {
        if (player == null)
        {
            throw new InvalidParameterException();
        }
        try
        {
            repository.save(player);
        }
        catch (Exception e)
        {
            System.out.println("Error al guardar: " + e);
            throw new InvalidParameterException();
        }
    }

}
