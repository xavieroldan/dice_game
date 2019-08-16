/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication51;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class JavaApplication51
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int x = 0x80000000;
        System.out.println(x + "and");
        x = x >>> 31;
        System.out.println(x);
    }

}
