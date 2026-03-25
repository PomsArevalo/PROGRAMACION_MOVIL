//TAREA 4: FRED20
//ELABORADO POR: RAMÍREZ AREVALO PAMELA
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Fred20 extends JFrame {

    JButton casillas[] = new JButton[4];
    int[] secuencia = new int[6];
    Random r = new Random();

    //Colores para cada botón
    Color[] colores = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public Fred20() {
        setTitle("Fred20");
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(2, 2));

        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = new JButton();
            casillas[i].setBackground(Color.LIGHT_GRAY);

            int index = i;

            casillas[i].addActionListener(e -> {
                new Thread(() -> {
                    try {
                        casillas[index].setBackground(colores[index]); //Prende
                        Thread.sleep(500);
                        casillas[index].setBackground(Color.LIGHT_GRAY); //Apaga
                    } catch (Exception ex) {
                    }
                }).start();
            });

            add(casillas[i]);
        }

        crearSecuencia();
        mostrarSecuencia();
    }

    public void crearSecuencia() {

        for (int i = 0; i < secuencia.length; i++) {
            secuencia[i] = r.nextInt(4);
        }

        for (int x : secuencia) {
            System.out.print(x + " ");
        }
    }

    public void mostrarSecuencia() {

        Thread hilo = new Thread(() -> {

            for (int i = 0; i < secuencia.length; i++) {
                int indice = secuencia[i];
                try {
                    // 🔹 ahora usa el color correcto
                    casillas[indice].setBackground(colores[indice]);
                    Thread.sleep(1000);
                    casillas[indice].setBackground(Color.LIGHT_GRAY);
                    Thread.sleep(300);
                } catch (Exception e) {
                }
            }

        });
        hilo.start();
    }

    public static void main(String[] args) {
        Fred20 f = new Fred20();
        f.setVisible(true);
    }

}