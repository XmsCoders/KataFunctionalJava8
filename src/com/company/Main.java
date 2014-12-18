package com.company;

import static java.lang.Math.floorMod;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.company.Utils.makeEvolve;
/**
 * Java 8 fun exercise.
 * Thanks to D.Renault ;)
 * See http://www.labri.fr/perso/renault/working/teaching/tap/tap.php
 */
public class Main {

    public static void main(String[] args) {
        final int width  = 30;
        final int height = 30;

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(width,true));


        Random rng     = new Random();                        // Create board
        Cell[][] board = new Cell[height][width];
        for (int i=0; i<height; i++)
            for(int j = 0; j<width; j++) {
                board[i][j] = new Cell(shell);
                board[i][j].setVisible(rng.nextInt(2)>0); }

        for (int i=0; i<height; i++)                          // Add neighbors, borderless grid (borders communicate thanks to floorMod)
            for(int j = 0; j<width; j++) {
                board[i][j].neighbors.add(board[floorMod(i + 1, height - 1)][floorMod(j,width - 1)]);
                board[i][j].neighbors.add(board[floorMod(i+1, height-1)][floorMod(j+1, width-1)]);
                board[i][j].neighbors.add(board[floorMod(i+1,height-1)][floorMod(j-1,width-1)]);
                board[i][j].neighbors.add(board[floorMod(i-1,height-1)][floorMod(j,width-1)]);
                board[i][j].neighbors.add(board[floorMod(i-1,height-1)][floorMod(j+1,width-1)]);
                board[i][j].neighbors.add(board[floorMod(i-1,height-1)][floorMod(j-1,width-1)]);
                board[i][j].neighbors.add(board[floorMod(i,height-1)][floorMod(j+1,width-1)]);
                board[i][j].neighbors.add(board[floorMod(i,height-1)][floorMod(j-1,width-1)]);
            }

// Collect all buttons into a long list
        final List<Cell> lbts = Stream.of(board)
                .flatMap(Stream::of)
                .collect(Collectors.toList());

// Create thread called in game of life
        new Thread(() -> {
            for (int k = 0; k < 300; k++) {
                try { Thread.sleep(200);
                } catch (InterruptedException exn) {}
                display.asyncExec(() -> makeEvolve(lbts));
            }}).start();

        //Display shell
        shell.open(); shell.pack();
        while (!shell.isDisposed())
            if (!display.readAndDispatch())
                display.sleep();
        display.dispose();
    }
}
