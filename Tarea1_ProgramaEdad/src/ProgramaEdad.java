//TAREA 1: PROGRAMA EDAD
//ELABORADO POR: RAMÍREZ AREVALO PAMELA

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProgramaEdad extends JFrame implements ActionListener{
    JTextField numUno;
    JButton btnOperar;
    JPanel panel01;
   
    public ProgramaEdad(){
        setTitle("Edad");
        setSize(400,200);
        setDefaultCloseOperation(3);
               
        numUno = new JTextField(5);
        btnOperar = new JButton("Aceptar");
        btnOperar.addActionListener(this);
       
        panel01 = new JPanel();
       
        panel01.add(numUno);
        panel01.add(btnOperar);
       
        add(panel01);
        setVisible(true);
    }
   
    public static void main(String[] args) {
        ProgramaEdad ventana = new ProgramaEdad();        
    }

    //Método para definir si es mayor o menor de edad
    @Override
    public void actionPerformed(ActionEvent e) {
        String edad = numUno.getText();
       
        int años = Integer.parseInt(edad);  
        int mayor = 18;
       
        if(años>=mayor){
            JOptionPane.showMessageDialog(null, "Mayor de edad");
        }else{
            JOptionPane.showMessageDialog(null, "Menor de edad");
        }
     }
}