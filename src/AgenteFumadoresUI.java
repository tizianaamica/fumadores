import javax.swing.*;
import java.awt.*;

public class AgenteFumadoresUI extends JFrame {

    private Mesa mesa;
    private AgenteFumadores agente;
    private JTextArea areaTexto, areaEstado; // Componente de texto para mostrar la información del agente

    public AgenteFumadoresUI() {
        super("Agente y fumadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        areaTexto = new JTextArea(); // Crear el componente de texto
        mesa = new Mesa(areaTexto);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        areaEstado = new JTextArea();
        JScrollPane scrollPanes = new JScrollPane(areaEstado);
        scrollPanes.setPreferredSize(new Dimension(300, 100));
        getContentPane().add(scrollPanes, BorderLayout.PAGE_END);

        agente = new AgenteFumadores(mesa, areaEstado); // Pasar el componente de texto al agente
        new Thread(agente).start();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AgenteFumadoresUI();
    }

}