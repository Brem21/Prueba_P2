import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListaHeroe lista = new ListaHeroe();

            JFrame frame = new JFrame("Spiderverse Hero Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 450);

            JTabbedPane tabs = new JTabbedPane();

            RegistroPanel registroPanel = new RegistroPanel(lista);
            BusquedaPanel busquedaPanel = new BusquedaPanel(lista, registroPanel);

            tabs.addTab("Registrar Héroe", registroPanel);
            tabs.addTab("Buscar/Eliminar Héroe", busquedaPanel);

            frame.add(tabs);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
