/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicegame.run;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "spring.objects")
@ComponentScan(basePackages = "com.dicegame.api")
public class WebConfig
{

}
