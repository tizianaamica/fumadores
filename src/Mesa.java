import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Mesa {

    private int[] ingredientes;
    private Semaphore[] semaforos;
    private Semaphore semaforoMesa;

    private JTextArea areaTexto; // Componente de texto para la interfaz

    public Mesa(JTextArea areaTexto) {
        this.areaTexto = areaTexto;
        ingredientes = new int[] {0, 0, 0}; // Inicializar ingredientes en cero
        semaforos = new Semaphore[] {new Semaphore(1), new Semaphore(1), new Semaphore(1)};
        semaforoMesa = new Semaphore(1);
        mostrarMesa(); // Mostrar la mesa inicial en el componente de texto de la interfaz
    }

    // Métodos para colocar los ingredientes en la mesa
    public void colocarTabaco() {
        try {
            semaforos[0].acquire();
            ingredientes[0]++;
            semaforos[0].release();
            mostrarMesa();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void colocarPapel() {
        try {
            semaforos[1].acquire();
            ingredientes[1]++;
            semaforos[1].release();
            mostrarMesa();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void colocarFosforos() {
        try {
            semaforos[2].acquire();
            ingredientes[2]++;
            semaforos[2].release();
            mostrarMesa();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar la información de la mesa en el componente de texto de la interfaz
    public void mostrarMesa() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tabaco: ").append(ingredientes[0]).append("\n");
        sb.append("Papel: ").append(ingredientes[1]).append("\n");
        sb.append("Fosforos: ").append(ingredientes[2]).append("\n");
        areaTexto.setText(sb.toString());
    }

    // Método para vaciar la mesa
    public void vaciarMesa() {
        try {
            semaforoMesa.acquire();
            for (int i = 0; i < ingredientes.length; i++) {
                semaforos[i].acquire();
                ingredientes[i] = 0;
                semaforos[i].release();
            }
            areaTexto.setText("Mesa vacía\nTabaco: 0\nPapel: 0\nFosforos: 0\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforoMesa.release();
        }
    }

}