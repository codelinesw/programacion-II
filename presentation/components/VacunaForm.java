package presentation.components;



import core.model.Vacuna;
import presentation.viewmodel.VacunaViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;
import java.util.UUID;

public class VacunaForm extends JFrame{

    private JTextField nombreField;
    private JTextField fechaAplicacionField;
    private JTextField loteField;
    private JTextField veterinarioField;


    private final VacunaViewModel vacunaViewModel;
    private String mascotaId;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;

    public VacunaForm(VacunaViewModel viewModel, String tempMascotaId) {
        this.mascotaId = "";
        this.vacunaViewModel = viewModel;
        this.mascotaId  = tempMascotaId;
        setTitle("Formulario de vacunas");
        setSize(1190, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panelFormulario.add(new JLabel("Nombre de la vacuna:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(20);
        panelFormulario.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Fecha aplicación (mm/dd/yy):"), gbc);
        gbc.gridx = 1;
        fechaAplicacionField = new JTextField();
        panelFormulario.add(fechaAplicacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Lote:"), gbc);
        gbc.gridx = 1;
        loteField = new JTextField();
        panelFormulario.add(loteField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Veterinario:"), gbc);
        gbc.gridx = 1;
        veterinarioField = new JTextField();
        panelFormulario.add(veterinarioField, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        gbc.gridx = 0;
        gbc.gridy++;
        JButton agregarButton = new JButton("Agregar vacuna");
        panelBotones.add(agregarButton);
        gbc.gridx = 1;
        panelFormulario.add(panelBotones, gbc);

        //Configuración de la tabla
        tablaMascotas = new JTable(vacunaViewModel.getAllMascotas(this.mascotaId));
        tablaMascotas.setRowHeight(35);
        tablaMascotas.getColumnModel().getColumn(5).setPreferredWidth(60);

        // Custom cell renderer: solo para mostrar el panel de botones
        tablaMascotas.getColumn("Acciones").setCellRenderer((tbl, value, isSelected, hasFocus, row, col) -> {
            JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 6));
            JButton delBtn = new JButton("Eliminar");

            Dimension buttonSize = new Dimension(100, 20);
            delBtn.setPreferredSize(buttonSize);
            panelAcciones.add(delBtn);
            return panelAcciones;
        });

        // Editor de celda: aquí se maneja la lógica de los botones
        tablaMascotas.getColumn("Acciones").setCellEditor(new DefaultCellEditor(new JTextField()) {
            private JPanel panelAcciones;
            private JButton delBtn;
            private int currentRow = -1;

            {
                panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 6));
                delBtn = new JButton("Eliminar");

                Dimension buttonSize = new Dimension(100, 20);
                delBtn.setPreferredSize(buttonSize);
                panelAcciones.add(delBtn);

                delBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < tablaMascotas.getRowCount()) {
                        String vacunaId = (String) tablaMascotas.getValueAt(currentRow, 0);
                        System.out.println(tablaMascotas.getValueAt(currentRow, 0));
                        int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar vacuna ID: " + vacunaId + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            fireEditingStopped();
                            vacunaViewModel.deleteVacuna(vacunaId);
                            vacunaViewModel.getAllMascotas(mascotaId);
                        } else {
                            fireEditingStopped();
                        }

                    }
                });
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value,
                                                         boolean isSelected, int row, int column) {
                currentRow = row;
                return panelAcciones;
            }

            @Override
            public Object getCellEditorValue() {
                return "";
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tablaMascotas);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        // Acción agregar
        agregarButton.addActionListener(e -> agregarVacuna());
    }

    private void agregarVacuna() {
        try {
            String nombreVacuna = nombreField.getText().trim();
            String fechaAplicacion = fechaAplicacionField.getText().trim();
            String lote = loteField.getText().trim();
            String veterinario = veterinarioField.getText().trim();

            // Validaciones
            if (nombreVacuna.isEmpty() || fechaAplicacion.isEmpty() || lote.isEmpty() || veterinario.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            if (fechaAplicacion.isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacia.");
            }

            if (fechaAplicacion.isEmpty()) {
                throw new IllegalArgumentException("La fecha de aplicación no puede estar vacia.");
            }

            if (lote.isEmpty()) {
                throw new IllegalArgumentException("El lote no puede estar vacia.");
            }

            if (veterinario.isEmpty()) {
                throw new IllegalArgumentException("El veterinario no puede estar vacia.");
            }

            vacunaViewModel.addVacuna(new Vacuna(UUID.randomUUID().toString(),mascotaId, nombreVacuna, fechaAplicacion, lote, veterinario));
            limpiarCampos();
            int option = JOptionPane.showConfirmDialog(null, "Operación exítosa", "La mascota fue registrada con exíto", JOptionPane.OK_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                //setVisible(false);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos()
    {
        // Limpiar campos
        nombreField.setText("");
        fechaAplicacionField.setText("");
        loteField.setText("");
        veterinarioField.setText("");
    }
}
