/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.BasureroSeparacion;
import Util.PanelGame;
import Domain.Botella;
import Domain.ResiduoEcoBloque;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author congr
 */
public class EcoBloqueWin extends PanelGame implements  MouseMotionListener{

    //private Botella botella;
    private ResiduoEcoBloque resB;
    private ArrayList<ResiduoEcoBloque> listaResiduos, listaEcoBloque;
    private boolean backMenu;
    private ImageIcon picoBotella, botella;
    private int pX, pY, width, height;
    private Timer checkGame;
    
    public EcoBloqueWin(){
        this.setFocusable(true);
	this.requestFocus();
        this.setLayout(null);
        this.init();
    }
    
    @Override
    public void init() {
        this.pX = 250;
        this.pY = 300;
        this.width = 125;
        this.height = 100;
        this.listaResiduos = new ArrayList<>();
        this.listaEcoBloque = new ArrayList<>();
        
        for(int i = 0; i < 5; i++){
            //generando otros residuos que no sean del ecobloque
            int tipo = (int) (Math.random() * 4); //este random es para escoger diferentes tipos de residuos
            ResiduoEcoBloque res = new ResiduoEcoBloque((int) (Math.random() * (560-10+1)+10), (int) (Math.random() * 100 + 1), 40, 40);
            res.setTipoResiduo(tipo);
            res.setImage("/assets/Residuos/R-" + tipo + "-" + (int) (Math.random() * 9) + ".png");
            this.add(res.getItemPanel());
            this.listaResiduos.add(res);
            res.getTh().start();
            
            //residuos solo para el ecobloque
            ResiduoEcoBloque resEB = new ResiduoEcoBloque((int) (Math.random() * (560-10+1)+10), (int) (Math.random() * 100 + 1), 40, 40);
            resEB.setTipoResiduo(4);
            resEB.setImage("/assets/Residuos/R-4-" + (int) (Math.random() * 10) + ".png");
            this.add(resEB.getItemPanel());
            this.listaResiduos.add(resEB);
            resEB.getTh().start();
        }

        this.backMenu = false;
        this.picoBotella = new ImageIcon(getClass().getResource("/assets/Botella/pico-botella.png"));
        this.botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella0.png"));
        this.addMouseMotionListener(this);
        
        this.checkGame = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < listaResiduos.size(); i++){
                    //centro de cada residuo está en (x+20, y+20)
                    if(listaResiduos.get(i).getPositionY()+20 >= pY && listaResiduos.get(i).getPositionY()+20 < pY+10){
                        int tempX = listaResiduos.get(i).getPositionX()+20;
                        if(tempX > (pX-20+(int)(width/2)) && tempX < (pX+20+(int)(width/2))){
                            //cambiamos la posición y la imagen al superar la tapa de la botella
                            listaResiduos.get(i).setPositionX((int) (Math.random() * (560-10+1)+10));
                            listaResiduos.get(i).setPositionY((int) (Math.random() * 10 + 1));
                            listaResiduos.get(i).setImage("/assets/Residuos/R-" + listaResiduos.get(i).getTipoResiduo() + "-" + (int) (Math.random() * 9) + ".png");
                            
                            //agregamos un nuevo residuo a la lista
                            if(listaResiduos.get(i).getTipoResiduo() == 4){
                                listaEcoBloque.add(new ResiduoEcoBloque(10, 10, 10, 10));
                            }else{
                                if(listaEcoBloque.size() > 0){
                                    listaEcoBloque.remove(listaEcoBloque.size()-1);
                                }
                            }
                        }
                    }
                }
                if(listaEcoBloque.size() >= 0 && listaEcoBloque.size() < 3){
                    botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella0.png"));
                }else{
                    if(listaEcoBloque.size() >= 3 && listaEcoBloque.size() < 6){
                        botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella1.png"));
                    }else{
                        if(listaEcoBloque.size() >= 6 && listaEcoBloque.size() < 9){
                            botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella2.png"));
                        }else{
                            if(listaEcoBloque.size() >= 9 && listaEcoBloque.size() < 12){
                                botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella3.png"));
                            }else{
                                if(listaEcoBloque.size() >= 12 && listaEcoBloque.size() < 15){
                                    botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella4.png"));
                                }else{
                                    if(listaEcoBloque.size() >= 15){
                                        botella = new ImageIcon(getClass().getResource("/assets/Botella/Botella5.png"));
                                        checkGame.stop();
                                        removeAll();
                                        for(int i = 0; i < listaResiduos.size(); i++){
                                            listaResiduos.get(i).setRunning(false);
                                        }
                                        listaResiduos.removeAll(listaResiduos);
                                        listaEcoBloque.removeAll(listaEcoBloque);
                                        String msg = "Muy bien, has ganado ¿Deseas iniciar de nuevo?";
                                        if(JOptionPane.showConfirmDialog(getParent(), msg, "Error", JOptionPane.YES_NO_OPTION, 0) == 0){
                                            init();
                                        }else{
                                            backMenu = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        this.checkGame.start();
    }

    @Override
    public boolean getBackMenu() {
        return this.backMenu;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(this.picoBotella.getImage(), this.pX, this.pY, this.width, this.height, null);
        g.drawImage(this.botella.getImage(), 600, 100, 200, 200, null);
        for(int i = 0; i < this.listaResiduos.size(); i++){
            this.listaResiduos.get(i).dibujar(g);
        }
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        this.pX = (int)(e.getX() - this.width/2);
        //System.out.println("(" + e.getX() +", "+e.getY()+")");
        //System.out.println("X: " + this.pX);
    }
    
}
