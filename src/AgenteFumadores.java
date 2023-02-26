import javax.swing.*;
import java.util.Random;

public class AgenteFumadores implements Runnable {

    private Mesa mesa;
    private Random random;
    private JTextArea areaTexto; // Componente de texto para la interfaz

    public AgenteFumadores(Mesa mesa, JTextArea areaTexto) {
        this.mesa = mesa;
        this.random = new Random();
        this.areaTexto = areaTexto;
    }

    @Override
    public void run() {
        while (true) {
            int ingrediente = random.nextInt(3);
            switch (ingrediente) {
                case 0:
                    mesa.colocarTabaco();
                    areaTexto.append("Agente coloco tabaco en la mesa\n");
                    break;
                case 1:
                    mesa.colocarPapel();
                    areaTexto.append("Agente coloco papel en la mesa\n");
                    break;
                case 2:
                    mesa.colocarFosforos();
                    areaTexto.append("Agente coloco fosforos en la mesa\n");
                    break;
            }
            mesa.mostrarMesa(); // Actualizar el componente de texto con la información de la mesa
            try {
                Thread.sleep(1000); // Esperar un segundo antes de colocar más ingredientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}