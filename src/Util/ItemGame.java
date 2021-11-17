/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author congr
 */
public abstract class ItemGame{

    protected int positionX;
    protected int positionY;
    protected int width;
    protected int height;
    protected ImageIcon image;
    protected JPanel itemPanel;
    
    public ItemGame(int x, int y, int w, int h) {
        this.positionX = x;
        this.positionY = y;
        this.width = w;
        this.height = h;
        this.itemPanel = new JPanel();
        this.itemPanel.setSize(this.width, this.height);
    }

    public void dibujar(Graphics g){
        g.drawImage(this.image.getImage(), this.positionX, this.positionY, this.width, this.height, null);
    }
    
    public JPanel getItemPanel() {
        return this.itemPanel;
    }

    public void setImage(String ruta) {
        this.image = new ImageIcon(getClass().getResource(ruta));
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
