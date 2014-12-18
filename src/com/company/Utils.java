package com.company;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alan on 15/12/14.
 */
public class Utils {
    static void makeEvolve(List<Cell> lc) {
        List<Cell> living = lc.stream()
                .filter(c -> (!(c.getVisible()) &&
                        (c.livingNeighbors() == 3)))
                .collect(Collectors.toList());


        List<Cell> dying = lc.stream()
                .filter(c -> ((c.getVisible()) &&
                        ((c.livingNeighbors() < 2) || (c.livingNeighbors() > 3))))
                .collect(Collectors.toList());
        living.forEach(c -> c.setVisible(true));
        dying.forEach (c -> c.setVisible(false));
    }
}
