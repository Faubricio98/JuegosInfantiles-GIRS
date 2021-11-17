/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Util.ItemGame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author congr
 */
public class BasureroSeparacion extends ItemGame{
    
    private ArrayList<ResiduoSeparacion> residuos;
    private String abierto, cerrado;
    private int tipoBasurero;
    
    public BasureroSeparacion(int x, int y, int w, int h, String bCerrado, String bAbierto) {
        super(x, y, w, h);
        this.abierto = bAbierto;
        this.cerrado = bCerrado;
        this.residuos = new ArrayList<>();
        this.image = new ImageIcon(getClass().getResource(this.cerrado));
        this.itemPanel.addMouseListener(new ClickListener());
    }

    public void setTipoBasurero(int tipoBasurero) {
        this.tipoBasurero = tipoBasurero;
    }

    public int getTipoBasurero() {
        return this.tipoBasurero;
    }

    public void setResiduos(ArrayList<ResiduoSeparacion> residuos) {
        this.residuos = residuos;
    }

    public ArrayList<ResiduoSeparacion> getResiduos() {
        return this.residuos;
    }
    
    public boolean basurerolleno(){
        for(int i = 0; i < this.residuos.size(); i++){
            if(this.residuos.get(i).getTipoResiduo() != this.tipoBasurero){
                return false;
            }
        }
        return true;
    }
    
    private class ClickListener extends MouseAdapter{

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
            image = new ImageIcon(getClass().getResource(abierto));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
            image = new ImageIcon(getClass().getResource(cerrado));
        }
        
    }
}
