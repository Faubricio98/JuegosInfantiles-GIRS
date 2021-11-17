/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Util.ItemGame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author congr
 */
public class ResiduoEcoBloque extends ItemGame implements Runnable{
    
    private Thread th;
    private int tipoResiduo;
    private boolean running;
    
    public ResiduoEcoBloque(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.th = new Thread(this);
        this.running = false;
    }

    public void setTipoResiduo(int tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public int getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTh(Thread th) {
        this.th = th;
    }

    public Thread getTh() {
        return th;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
    
    @Override
    public void run() {
        int interval = 50;
        this.setRunning(true);
        while (this.isRunning()) {
            try {
                Thread.sleep(interval);
                this.positionY++;
                if(this.positionY >= 400){
                    this.positionY = (int) (Math.random() * 10 + 1);
                    this.positionX = (int) (Math.random() * (560-10+1)+10);
                }
                if(interval < 10){
                    interval = (int)(Math.random() * 100 + 1);
                }else{
                    interval = interval - (int)(Math.random() * 5 + 1);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ResiduoEcoBloque.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setRunning(false);
    }
    
}
