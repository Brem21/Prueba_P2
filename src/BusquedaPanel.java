import javax.swing.*;

public class BusquedaPanel extends JPanel {
    private JPanel rootPanel;
    private JTextField codigoField;
    private JTextField nombreField;
    private JComboBox<String> poderesBox;
    private JComboBox<String> universoBox;
    private JComboBox<Integer> nivelBox;
    private JButton buscarButton;
    private JButton eliminarButton;

    private ListaHeroe lista;
    private RegistroPanel registroPanel;

    public BusquedaPanel(ListaHeroe lista, RegistroPanel registroPanel) {
        $$$setupUI$$$();  // ⚠️ Muy importante para que el .form funcione

        this.lista = lista;
        this.registroPanel = registroPanel;

        // Inicializar los ComboBox con valores
        poderesBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"
        }));
        universoBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Tierra-616", "Tierra-12041", "Tierra-90214", "Tierra-138"
        }));
        nivelBox.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5}));

        nombreField.setEditable(false); // No se edita manualmente

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
