import javax.swing.*;
import java.awt.*;

public class BusquedaPanel extends JPanel {
    private JTextField codigoField;
    private JTextField nombreField;
    private JComboBox<String> poderesBox;
    private JComboBox<String> universoBox;
    private JPanel BusquedaPanel;
    private JComboBox<Integer> nivelBox;
    private JButton buscarButton;
    private JButton eliminarButton;

    private ListaHeroe lista;
    private RegistroPanel registroPanel;

    public BusquedaPanel(ListaHeroe lista, RegistroPanel registroPanel) {
        this.lista = lista;
        this.registroPanel = registroPanel;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo código
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        codigoField = new JTextField(15);
        add(codigoField, gbc);

        // Campo nombre
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(15);
        nombreField.setEditable(false);
        add(nombreField, gbc);

        // Poderes
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Poder especial:"), gbc);
        gbc.gridx = 1;
        poderesBox = new JComboBox<>(new String[]{
                "Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana",
                "Agilidad Mejorada", "Tejido de Telaraña"
        });
        add(poderesBox, gbc);

        // Universo
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Universo:"), gbc);
        gbc.gridx = 1;
        universoBox = new JComboBox<>(new String[]{
                "Tierra-616", "Tierra-12041", "Tierra-90214", "Tierra-138"
        });
        add(universoBox, gbc);

        // Nivel de experiencia
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Nivel de experiencia:"), gbc);
        gbc.gridx = 1;
        nivelBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        add(nivelBox, gbc);

        // Botón Buscar
        gbc.gridx = 0; gbc.gridy = 5;
        buscarButton = new JButton("Buscar");
        add(buscarButton, gbc);

        // Botón Eliminar
        gbc.gridx = 1;
        eliminarButton = new JButton("Eliminar");
        add(eliminarButton, gbc);

        // Acciones
        buscarButton.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(codigoField.getText().trim());
                SpiderverseHero h = lista.buscarPorCodigo(codigo);
                if (h != null) {
                    nombreField.setText(h.getNombre());
                    poderesBox.setSelectedItem(h.getPoderEspecial());
                    universoBox.setSelectedItem(h.getUniverso());
                    nivelBox.setSelectedItem(h.getNivelExperiencia());
                } else {
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "No se encontró héroe con ese código.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un código válido.");
            }
        });

        eliminarButton.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(codigoField.getText().trim());
                boolean eliminado = lista.eliminarPorCodigo(codigo);
                if (eliminado) {
                    limpiarCampos();
                    registroPanel.actualizarTabla();
                    JOptionPane.showMessageDialog(this, "Héroe eliminado.");
                } else {
                    JOptionPane.showMessageDialog(this, "No existe héroe con ese código.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un código válido.");
            }
        });
    }

    private void limpiarCampos() {
        nombreField.setText("");
        poderesBox.setSelectedIndex(0);
        universoBox.setSelectedIndex(0);
        nivelBox.setSelectedIndex(0);
    }
}
