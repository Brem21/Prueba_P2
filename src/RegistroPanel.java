import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegistroPanel extends JPanel {
    private ListaHeroe lista;
    private DefaultTableModel tableModel;
    private JTable tabla;
    private JTextField codigoField;
    private JTextField nombreField;
    private JComboBox<String> poderesBox;
    private JComboBox<String> universoBox;
    private JPanel rootPanel;
    private JComboBox<Integer> nivelBox;
    private JButton registrarButton;

    public RegistroPanel(ListaHeroe lista) {
        this.lista = lista;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        codigoField = new JTextField();
        nombreField = new JTextField();

        poderesBox = new JComboBox<>(new String[]{
                "Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"
        });

        universoBox = new JComboBox<>(new String[]{
                "Tierra-616", "Tierra-12041", "Tierra-90214", "Tierra-138"
        });

        nivelBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        formPanel.add(new JLabel("Código:"));
        formPanel.add(codigoField);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Poder Especial:"));
        formPanel.add(poderesBox);
        formPanel.add(new JLabel("Universo:"));
        formPanel.add(universoBox);
        formPanel.add(new JLabel("Nivel de Experiencia:"));
        formPanel.add(nivelBox);

        registrarButton = new JButton("Registrar Héroe");
        formPanel.add(new JLabel(""));
        formPanel.add(registrarButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Código", "Nombre", "Poder", "Universo", "Nivel"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(tableModel);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        registrarButton.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(codigoField.getText().trim());
                String nombre = nombreField.getText().trim();
                String poder = (String) poderesBox.getSelectedItem();
                String universo = (String) universoBox.getSelectedItem();
                int nivel = (int) nivelBox.getSelectedItem();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre.");
                    return;
                }

                SpiderverseHero nuevoHeroe = new SpiderverseHero(codigo, nombre, poder, universo, nivel);
                boolean agregado = lista.agregarHeroe(nuevoHeroe);

                if (agregado) {
                    tableModel.insertRow(0, new Object[]{codigo, nombre, poder, universo, nivel});
                    JOptionPane.showMessageDialog(null, "Héroe registrado con éxito.");
                    codigoField.setText("");
                    nombreField.setText("");
                    poderesBox.setSelectedIndex(0);
                    universoBox.setSelectedIndex(0);
                    nivelBox.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "El código ya existe. No se puede registrar.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un código válido.");
            }
        });
    }

    // Actualiza la tabla con todos los héroes (para recargar desde búsqueda)
    public void actualizarTabla() {
        tableModel.setRowCount(0);
        for (SpiderverseHero h : lista.obtenerTodos()) {
            tableModel.addRow(new Object[]{h.getCodigo(), h.getNombre(), h.getPoderEspecial(), h.getUniverso(), h.getNivelExperiencia()});
        }
    }
}

