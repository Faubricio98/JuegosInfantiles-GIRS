/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Util.PanelGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author congr
 */
public class MainWin extends JFrame{
    
    private JButton btnRetornar;
    private JButton btnSeparaResiduos, btnEcoBloque;
    private JPanel panelJuego;
    private Timer closeTimer;
    
    public MainWin(){
        this.setLayout(null);
        this.setSize(810, 600);
        this.setResizable(false);
        this.setTitle("GIRS - Juegos Infantiles");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.init();
    }
    
    public void init(){
        //inicializar botones
        this.btnRetornar = new JButton("<< Volver");
        this.btnRetornar.setBounds(10, 10, 100, 30);
        this.btnRetornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        
        this.btnSeparaResiduos = new JButton("Separación de Residuos");
        this.btnSeparaResiduos.setBounds(10, 10, 200, 50);
        this.btnSeparaResiduos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelGame panel = new SeparaResiduosWin();
                createPanelGame((JPanel) panel);
                closePanelGame(panel);
            }
        });
        
        this.btnEcoBloque = new JButton("Crear Eco Bloque");
        this.btnEcoBloque.setBounds(220, 10, 200, 50);
        this.btnEcoBloque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelGame panel = new EcoBloqueWin();
                createPanelGame((JPanel) panel);
                closePanelGame(panel);
            }
        });
        
        //inicializamos los páneles
        this.panelJuego = new JPanel();
        this.panelJuego.setLayout(null);
        this.panelJuego.setBounds(0, 30, 800, 500);
        
        //agregar botones
        this.add(this.panelJuego);
        this.panelJuego.add(this.btnSeparaResiduos);
        this.panelJuego.add(this.btnEcoBloque);
    }
    
    public void createPanelGame(JPanel panel){
        getContentPane().removeAll();
        getContentPane().add(btnRetornar);
        panel.setBounds(0, 50, 790, 500);
        getContentPane().add(panel);
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    public void closePanelGame(PanelGame panel){
        this.closeTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel.getBackMenu()){
                    showMenu();
                }
            }
        });
        this.closeTimer.start();
    }
    
    public void showMenu(){
        this.closeTimer.stop();
        getContentPane().removeAll();
        getContentPane().add(panelJuego);
        getContentPane().repaint();
        getContentPane().revalidate();
    }
}
