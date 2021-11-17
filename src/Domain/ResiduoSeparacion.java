/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Util.ItemGame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author congr
 */
public class ResiduoSeparacion extends ItemGame{
    
    private int tipoResiduo;
    private boolean  dragged;
    private Point imageCorner, prevPt;
    
    public ResiduoSeparacion(int x, int y, int w, int h, String rutaImagen){
        super(x, y, w, h);
        super.setImage(rutaImagen);
        super.itemPanel.addMouseListener(new ClickListener());
        super.itemPanel.addMouseMotionListener(new DragListener());
        
        this.imageCorner = new Point(this.positionX, this.positionY);
        this.dragged = false;
    }

    public boolean getDragged(){
        return this.dragged;
    }

    public void setTipoResiduo(int tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public int getTipoResiduo() {
        return this.tipoResiduo;
    }
    
    private class ClickListener extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            dragged = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            itemPanel.setLocation(imageCorner);
            setPositionX(imageCorner.x);
            setPositionY(imageCorner.y);
            dragged = false;
        }
        
    }
    
    private class DragListener extends MouseMotionAdapter{

        @Override
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            imageCorner.translate((int)(currentPt.getX()-prevPt.getX()), (int)(currentPt.getY()-prevPt.getY()));
            positionX = imageCorner.x;
            positionY = imageCorner.y;
            prevPt = currentPt;
        }
        
    }
}
