package com.company;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Thanks to D.Renault ;)
 * See http://www.labri.fr/perso/renault/working/teaching/tap/tap.php
 */
public class Cell {
    private Button button;
    List<Cell> neighbors;
    public Cell(Composite shell) {
        button = new Button(shell, SWT.CHECK);
        neighbors = new ArrayList<Cell>();
        button.setText("    "); }
    public void setVisible(boolean b) { button.setSelection(b); }
    public boolean getVisible() { return button.getSelection(); }
    public void addListener(Listener f) { button.addListener(SWT.PUSH, f); }
    public int livingNeighbors() {
        return (int) neighbors.stream()
                .filter(Cell::getVisible).count(); }
}
