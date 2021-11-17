/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Util.PanelGame;
import Domain.BasureroSeparacion;
import Domain.ResiduoSeparacion;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author congr
 */
public class SeparaResiduosWin extends PanelGame{
    
    private ArrayList<BasureroSeparacion> listaBasureros;
    private ArrayList<ResiduoSeparacion> listaResiduos;
    private Timer checkGame;
    private boolean backMenu;
    
    public SeparaResiduosWin(){
        this.setFocusable(true);
	this.requestFocus();
        this.setLayout(null);
        this.init();
    }
    
    @Override
    public void init(){
        this.listaBasureros = new ArrayList<>();
        this.listaResiduos = new ArrayList<>();
        this.backMenu = false;
        
        for(int i=0; i<5; i++){
            BasureroSeparacion bsp = new BasureroSeparacion(158*i+10, 300, 100, 123, "/assets/Basureros/bote-" + i + "-cerrado.png", "/assets/Basureros/bote-" + i + "-abierto.png");
            bsp.getItemPanel().setBounds(bsp.getPositionX(), bsp.getPositionY(), bsp.getWidth(), bsp.getHeight());
            bsp.setTipoBasurero(i);
            this.add(bsp.getItemPanel());
            this.listaBasureros.add(bsp);
        }
        
        for(int i=0; i<20; i++){
            int tipo = (int) (Math.random() * 5); //este random es para escoger diferentes tipos de residuos
            String ruta = "/assets/Residuos/R-" + tipo + "-" + (int) (Math.random() * 9) + ".png";
            ResiduoSeparacion res = new ResiduoSeparacion((int) (Math.random() * (700-10+1)+10), (int) (Math.random() * (250-10+1)+10), 40, 40, ruta);
            res.setTipoResiduo(tipo);
            res.getItemPanel().setBounds(res.getPositionX(), res.getPositionY(), res.getWidth(), res.getHeight());
            this.add(res.getItemPanel());
            this.listaResiduos.add(res);
        }
        
        this.checkGame = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listaResiduos.size(); i++) {
                    if(!listaResiduos.get(i).getDragged()){
                        for(int x = 0; x < listaBasureros.size(); x++){
                            BasureroSeparacion bas = listaBasureros.get(x);
                            if((listaResiduos.get(i).getPositionX() >= bas.getPositionX()&& listaResiduos.get(i).getPositionX() <= bas.getPositionX()+bas.getWidth()) && (listaResiduos.get(i).getPositionY() >= bas.getPositionY() && listaResiduos.get(i).getPositionY() <= bas.getPositionY()+bas.getHeight())){
                                ResiduoSeparacion temp = listaResiduos.get(i);
                                listaBasureros.get(x).getResiduos().add(temp);
                                remove(listaResiduos.get(i).getItemPanel());
                                listaResiduos.remove(i);
                                break;
                            }
                        }
                    }
                }
                
                if(listaResiduos.isEmpty()){
                    checkGame.stop();
                    boolean state = true;
                    for(int i = 0; i < listaBasureros.size(); i++){
                        if(!listaBasureros.get(i).basurerolleno()){
                            state = false;
                            break;
                        }
                    }
                    removeAll();
                    listaBasureros.removeAll(listaBasureros);
                    String msg = "Has fallado ¿Deseas iniciar de nuevo?";
                    if(state){
                        msg = "Muy bien, has ganado ¿Deseas iniciar de nuevo?";
                    }
                    if(JOptionPane.showConfirmDialog(getParent(), msg, "Error", JOptionPane.YES_NO_OPTION, 0) == 0){
                        init();
                    }else{
                        backMenu = true;
                    }
                }
            }
        });
        
        this.checkGame.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < this.listaBasureros.size(); i++) {
            this.listaBasureros.get(i).dibujar(g);
        }
        
        for (int i = 0; i < this.listaResiduos.size(); i++) {
            this.listaResiduos.get(i).dibujar(g);
        }
        this.repaint();
    }
    
    @Override
    public boolean getBackMenu(){
        return this.backMenu;
    }
    
}
