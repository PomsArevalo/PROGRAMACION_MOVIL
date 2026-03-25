//TAREA 2: GATO
//ELABORADO POR: RAMÍREZ AREVALO PAMELA

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Gato extends JFrame implements ActionListener {
    JButton botones [] = new JButton[9];
    JButton btnReinciar;
    boolean turnoX=true;
    Font fuente = new Font("Arial",1,60);  
    JPanel pJuego, pOpciones;
    String letra;
   
    public Gato(){
        setTitle("Gato");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        pJuego = new JPanel();
        pJuego.setLayout(new GridLayout(3,3));
       
        for(int i=0;i<botones.length;i++){
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            pJuego.add(botones[i]);            
        }
       
        add(pJuego, BorderLayout.CENTER);
       
        btnReinciar = new JButton("Reiniciar Juego");
        pOpciones = new JPanel();
       
        btnReinciar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ex){
                reiniciarJuego(); 
            }            
        });
       
        pOpciones.add(btnReinciar);
        add(pOpciones, BorderLayout.SOUTH);
    }
   
    public static void main(String a []){
        Gato g01 = new Gato();
        g01.setVisible(true);
    }
   
    public void actionPerformed(ActionEvent btnApretado){
        for(int i=0;i<botones.length;i++){
            if(btnApretado.getSource()==botones[i]){

                if(turnoX){
                    letra="X";
                    turnoX=false;
                }else{
                    letra="O";
                    turnoX=true;
                }
                
                botones[i].setText(letra);  
                botones[i].setEnabled(false);
                
                verificarGanador();
                verificarEmpate();  
                
                break; 
            }    
        }
    }
    
    //Método para verificar ganador
    public void verificarGanador(){
        int[][] combinaciones = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        
        for(int[] c : combinaciones){
            String a = botones[c[0]].getText();
            String b = botones[c[1]].getText();
            String d = botones[c[2]].getText();
            
            if(!a.equals("") && a.equals(b) && a.equals(d)){
                
                JOptionPane.showMessageDialog(this, "Ganó " + a);
                
                reiniciarJuego();
                return;
            }
        }
    }
    
    //Método para verificar si hay empate
    public void verificarEmpate(){
        boolean lleno = true;
        
        for(int i=0;i<botones.length;i++){
            if(botones[i].getText().equals("")){
                lleno = false;
                break;
            }
        }
        
        if(lleno){
            JOptionPane.showMessageDialog(this, "Gato");
            reiniciarJuego(); 
        }
    }
    
    //Método para reiniciar el juego automáticamente
    public void reiniciarJuego(){
        for(int i=0;i<botones.length;i++){
            botones[i].setText("");
            botones[i].setEnabled(true);
        }
        turnoX = true;
    }
}