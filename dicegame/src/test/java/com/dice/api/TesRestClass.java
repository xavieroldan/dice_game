/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.api;

import com.dice.apis.PlayerRestController;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesRestClass
{

    @Autowired
    private PlayerRestController controller;

    @Test
    public void contexLoads() throws Exception
    {
        assertThat(controller).isNotNull();
    }

}
