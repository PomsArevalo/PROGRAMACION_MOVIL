//TAREA 3: CALCULADORA LAMDA
//ELABORADO POR: RAMÍREZ AREVALO PAMELA
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraLamda extends JFrame{
    JTextField n1, n2;
    JButton btnSum, btnRes, btnDiv, btnMul;
    JLabel txtRes;
    Font fuente = new Font("Courier",1,60);
   
    public CalculadoraLamda(){
        setTitle("casio");
        setSize(400,400);
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(4,1));
       
        n1 = new JTextField();
        n1.setFont(fuente);
        n2 = new JTextField();
        n2.setFont(fuente);
       
        JPanel pOpciones = new JPanel(new GridLayout(1,4));
        btnSum = new JButton("+");
        btnSum.setFont(fuente);
        btnRes = new JButton("-");
        btnRes.setFont(fuente);
        btnMul = new JButton("*");
        btnMul.setFont(fuente);
        btnDiv = new JButton("/");
        btnDiv.setFont(fuente);
       
        pOpciones.add(btnSum);        
        pOpciones.add(btnRes);
        pOpciones.add(btnMul);
        pOpciones.add(btnDiv);
               
        txtRes = new JLabel("0");
        txtRes.setFont(fuente);
       
        //Cambio a Lamda
        btnSum.addActionListener(e -> {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
           
            Calculadora cc = new Calculadora();
            txtRes.setText(String.valueOf(cc.sumar(num1, num2)));
        });    

        //Cambio a Lamda
        btnMul.addActionListener(e -> {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
            txtRes.setText(String.valueOf(Calculadora.multiplicar(num1, num2)));
        });
   
        btnRes.addActionListener(e -> calcular("-"));
        btnDiv.addActionListener(e -> calcular("/"));
       
        add(n1);
        add(n2);
        add(pOpciones);
        add(txtRes);        
    }
   
    public void calcular(String operador){
         double num1 = Double.parseDouble(n1.getText());
         double num2 = Double.parseDouble(n2.getText());
         double resultado = 0;
         switch(operador){
             case "-": resultado = num1-num2; break;
             case "/": resultado = num1/num2; break;
         }
         txtRes.setText(String.valueOf(resultado));
    }
   
    public static void main(String[] args) {
        CalculadoraLamda cc = new CalculadoraLamda();
        cc.setVisible(true);
    }
}
