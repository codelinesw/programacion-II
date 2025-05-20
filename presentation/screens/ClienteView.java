package presentation.screens;

import presentation.components.ClienteForm;
import presentation.components.MascotaForm;
import presentation.viewmodel.ClienteViewModel;
import presentation.viewmodel.MascotaViewModel;
import presentation.viewmodel.VacunaViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

public class ClienteView extends JFrame {
    private final ClienteViewModel viewModel;
    private final MascotaViewModel mascotaViewModel;
    private final VacunaViewModel  vacunaViewModel;
    //Formulario del cliente
    private ClienteForm clienteForm;
    //Formulario mascotas
    private MascotaForm mascotaForm;

    public ClienteView(ClienteViewModel viewModel, MascotaViewModel tempMascotaViewModel, VacunaViewModel tempVacunaViewModel) {
        this.viewModel = viewModel;
        this.mascotaViewModel = tempMascotaViewModel;
        this.vacunaViewModel = tempVacunaViewModel;
        setTitle("Gestión de Dueños");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Administración de Dueños");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        JLabel description = new JLabel("Aquí puedes agregar y ver la lista de dueños registrados.");
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(description);

        JButton addButton = new JButton("Agregar dueño");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(e -> {
            this.clienteForm = new ClienteForm(viewModel, false, null);
            clienteForm.setVisible(true);
            clienteForm.setEditable(false);
            clienteForm.limpiarCampos();
        });
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(addButton);

        // Crea la tabla normalmente
        JTable table = new JTable(viewModel.getAllClientes());
        table.setRowHeight(35);
        table.getColumnModel().getColumn(6).setPreferredWidth(210);

        // Custom cell renderer: solo para mostrar el panel de botones
        table.getColumn("Acciones").setCellRenderer((tbl, value, isSelected, hasFocus, row, col) -> {
            JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 6));
            JButton editBtn = new JButton("Editar");
            JButton delBtn = new JButton("Eliminar");
            JButton petBtn = new JButton("Mascotas");

            Dimension buttonSize = new Dimension(100, 20);
            editBtn.setPreferredSize(buttonSize);
            delBtn.setPreferredSize(buttonSize);
            petBtn.setPreferredSize(buttonSize);

            panelAcciones.add(editBtn);
            panelAcciones.add(delBtn);
            panelAcciones.add(petBtn);
            return panelAcciones;
        });

        // Editor de celda: aquí se maneja la lógica de los botones
        table.getColumn("Acciones").setCellEditor(new DefaultCellEditor(new JTextField()) {
            private JPanel panelAcciones;
            private JButton editBtn;
            private JButton delBtn;
            private JButton petBtn;
            private int currentRow = -1;

            {
                panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 6));
                editBtn = new JButton("Editar");
                delBtn = new JButton("Eliminar");
                petBtn = new JButton("Mascotas");

                Dimension buttonSize = new Dimension(100, 20);
                editBtn.setPreferredSize(buttonSize);
                delBtn.setPreferredSize(buttonSize);
                petBtn.setPreferredSize(buttonSize);

                panelAcciones.add(editBtn);
                panelAcciones.add(delBtn);
                panelAcciones.add(petBtn);



                editBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < table.getRowCount()) {
                        String ownerId = (String) table.getValueAt(currentRow, 0);
                        fireEditingStopped();
                        clienteForm = new ClienteForm(viewModel, true, ownerId);
                        clienteForm.setVisible(true);
                        clienteForm.setInfo(ownerId);
                    }
                });

                delBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < table.getRowCount()) {
                        String ownerId = (String) table.getValueAt(currentRow, 0);
                        int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar dueño ID: " + ownerId + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            fireEditingStopped();
                            viewModel.deleteCliente(ownerId);
                            viewModel.getAllClientes();
                        } else {
                            fireEditingStopped();
                        }
                    }
                });

                petBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < table.getRowCount()) {
                        String ownerId = (String) table.getValueAt(currentRow, 0);
                        fireEditingStopped();
                        JOptionPane.showMessageDialog(null, "Abrir mascotas de dueño ID: " + ownerId);
                        mascotaForm = new MascotaForm(mascotaViewModel, vacunaViewModel, ownerId);
                        mascotaForm.setVisible(true);
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

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(scrollPane);

        add(panel);
        viewModel.getAllClientes();
    }
}
