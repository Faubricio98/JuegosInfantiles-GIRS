/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.swing.JPanel;

/**
 *
 * @author congr
 */
public abstract class PanelGame extends JPanel{
    public PanelGame() {
    }
    public abstract void init();
    public abstract boolean getBackMenu();
}
