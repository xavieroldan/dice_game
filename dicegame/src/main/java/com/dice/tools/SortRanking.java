/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dice.tools;

import com.dice.models.RateDTO;
import java.util.Comparator;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class SortRanking implements Comparator<RateDTO>
{
//https://stackoverflow.com/questions/27530332/compareto-method-with-doublesdouble-cannot-be-dereferenced

    public int compare(RateDTO p1, RateDTO p2)

    {
        // Assume no nulls, and simple ordinal comparisons

        // First by rate - stop if this gives a result.
        int rateResult = Double.valueOf(p1.getRate()).compareTo(p2.getRate());
        if (rateResult != 0)
        {
            return rateResult;
        }

        // Next by games played
        int p1NumGames = p1.getPlayer().getListGame().size();
        int p2NumGames = p2.getPlayer().getListGame().size();
        int facultyResult = Integer.valueOf(p1NumGames).compareTo(p2NumGames);
        if (facultyResult != 0)
        {
            return facultyResult;
        }

        // Finally by register date
        return p1.getPlayer().getRegDate().compareTo(p2.getPlayer().getRegDate());

        //https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
        //Mirar como ordenar d euna vez con diferentes parámetros
//        movies.sort(new Comparator<Movie>() {
//            @Override
//            public int compare(Movie m1, Movie m2) {
//                if(m1.getStarred() == m2.getStarred()){
//                    return 0;
//                }
//                return m1.getStarred() ? -1 : 1;
//             }
//        });
//        movies.forEach(System.out::println);
//**********************************************
//@Override public int compare(final Report record1, final Report record2) {
//    int c;
//    c = record1.getReportKey().compareTo(record2.getReportKey());
//    if (c == 0)
//       c = record1.getStudentNumber().compareTo(record2.getStudentNumber());
//    if (c == 0)
//       c = record1.getSchool().compareTo(record2.getSchool());
//    return c;
//}
    }
}
