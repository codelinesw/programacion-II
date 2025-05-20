package presentation.components;


import core.model.Gato;
import core.model.Mascota;
import core.model.Rabbit;
import presentation.viewmodel.MascotaViewModel;
import presentation.viewmodel.VacunaViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;
import java.util.UUID;

public class MascotaForm extends JFrame{

    private JTextField nombreField;
    private JComboBox<String> mascotaTipoComboBox;
    private JTextField edadField;
    private JTextField razaField;


    private final MascotaViewModel mascotaViewModel;
    private final VacunaViewModel vacunaViewModel;
    private boolean edit = false;
    private String propietarioId;
    private JTable tablaMascotas;

    public MascotaForm(MascotaViewModel viewModel, VacunaViewModel _vacunaViewModel, String tempPropietarioId) {
        this.propietarioId = "";
        this.mascotaViewModel = viewModel;
        this.vacunaViewModel = _vacunaViewModel;
        this.propietarioId  = tempPropietarioId;
        setTitle("Formulario de mascota");
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

        panelFormulario.add(new JLabel("Nombre mascota:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(20);
        panelFormulario.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        mascotaTipoComboBox = new JComboBox<>(new String[]{"Perro", "Gato", "Conejo"});
        panelFormulario.add(mascotaTipoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Edad (en meses):"), gbc);
        gbc.gridx = 1;
        edadField = new JTextField();
        panelFormulario.add(edadField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Raza:"), gbc);
        gbc.gridx = 1;
        razaField = new JTextField();
        panelFormulario.add(razaField, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        gbc.gridx = 0;
        gbc.gridy++;
        JButton agregarButton = new JButton(edit  ? "Editar mascota" :"Agregar mascota");
        panelBotones.add(agregarButton);
        gbc.gridx = 1;
        panelFormulario.add(panelBotones, gbc);

        //Configuración de la tabla
        tablaMascotas = new JTable(mascotaViewModel.getAllMascotas(this.propietarioId));
        tablaMascotas.setRowHeight(35);
        tablaMascotas.getColumnModel().getColumn(4).setPreferredWidth(150);

        // Custom cell renderer: solo para mostrar el panel de botones
        tablaMascotas.getColumn("Acciones").setCellRenderer((tbl, value, isSelected, hasFocus, row, col) -> {
            JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 6));
            JButton editBtn = new JButton("Editar");
            JButton delBtn = new JButton("Eliminar");
            JButton vacBtn = new JButton("Vacunas");

            Dimension buttonSize = new Dimension(90, 20);
            editBtn.setPreferredSize(buttonSize);
            delBtn.setPreferredSize(buttonSize);
            vacBtn.setPreferredSize(buttonSize);

            panelAcciones.add(editBtn);
            panelAcciones.add(delBtn);
            panelAcciones.add(vacBtn);
            return panelAcciones;
        });

        // Editor de celda: aquí se maneja la lógica de los botones
        tablaMascotas.getColumn("Acciones").setCellEditor(new DefaultCellEditor(new JTextField()) {
            private JPanel panelAcciones;
            private JButton editBtn;
            private JButton delBtn;
            private JButton vacBtn;
            private int currentRow = -1;

            {
                panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 6));
                editBtn = new JButton("Editar");
                delBtn = new JButton("Eliminar");
                vacBtn = new JButton("Vacunas");

                Dimension buttonSize = new Dimension(90, 20);
                editBtn.setPreferredSize(buttonSize);
                delBtn.setPreferredSize(buttonSize);
                vacBtn.setPreferredSize(buttonSize);

                panelAcciones.add(editBtn);
                panelAcciones.add(delBtn);
                panelAcciones.add(vacBtn);



                editBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < tablaMascotas.getRowCount()) {
                        String mascotaId = (String) tablaMascotas.getValueAt(currentRow, 0);
                        int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas editar la mascota ID: " + mascotaId + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            setInfo(mascotaId);
                            fireEditingStopped();
                        }
                    }
                });

                delBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < tablaMascotas.getRowCount()) {
                        String mascotaId = (String) tablaMascotas.getValueAt(currentRow, 0);
                        System.out.println(tablaMascotas.getValueAt(currentRow, 0));
                        int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar la mascota ID: " + mascotaId + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            fireEditingStopped();
                            mascotaViewModel.deleteMascota(mascotaId);
                            mascotaViewModel.getAllMascotas(propietarioId);
                        } else {
                            fireEditingStopped();
                        }

                    }
                });

                vacBtn.addActionListener(e -> {
                    if (currentRow >= 0 && currentRow < tablaMascotas.getRowCount()) {
                        String mascotaId = (String) tablaMascotas.getValueAt(currentRow, 0);
                        JOptionPane.showMessageDialog(null, "Abrir vacuna de la mascota con ID: " + mascotaId);
                        VacunaForm vacunaForm = new VacunaForm(vacunaViewModel, mascotaId);
                        vacunaForm.setVisible(true);
                        fireEditingStopped();
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
        agregarButton.addActionListener(e -> saveActionButton());
    }

    public void saveActionButton()
    {
        if (this.edit)
            agregarMascota();
        else
            agregarMascota();
    }

    private void agregarMascota() {
        try {
            String nombre = nombreField.getText().trim();
            String mascotaTipo = (String) mascotaTipoComboBox.getSelectedItem();
            String edad = edadField.getText().trim();
            String raza = razaField.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || edad.isEmpty() || raza.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            if (edad.isEmpty()) {
                throw new IllegalArgumentException("La edad no puede estar vacia.");
            }
            if (raza.isEmpty()) {
                throw new IllegalArgumentException("El raza no puede estar vacia.");
            }

            if (mascotaTipo.isEmpty()) {
                throw new IllegalArgumentException("El tipo de mascota no puede estar vacia.");
            }

            this.edit = false;

            if (mascotaTipo.equals("Perro"))
            {
                mascotaViewModel.addMascota(new Mascota(UUID.randomUUID().toString(), nombre, Integer.parseInt(edad), raza, this.propietarioId));
            }

            if (mascotaTipo.equals("Gato"))
            {
                mascotaViewModel.addMascota(new Gato(UUID.randomUUID().toString(), nombre, Integer.parseInt(edad), raza, this.propietarioId));
            }

            if (mascotaTipo.equals("Conejo"))
            {
                mascotaViewModel.addMascota(new Rabbit(UUID.randomUUID().toString(), nombre, Integer.parseInt(edad), raza, this.propietarioId));
            }


            limpiarCampos();
            int option = JOptionPane.showConfirmDialog(null, "Operación exítosa", "La mascota fue registrada con exíto", JOptionPane.OK_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                //setVisible(false);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarMascota() {
        try {
            String nombre = nombreField.getText().trim();
            String mascotaTipo = (String) mascotaTipoComboBox.getSelectedItem();
            String edad = edadField.getText().trim();
            String raza = razaField.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || edad.isEmpty() || raza.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            if (edad.isEmpty()) {
                throw new IllegalArgumentException("La edad no puede estar vacia.");
            }
            if (raza.isEmpty()) {
                throw new IllegalArgumentException("El raza no puede estar vacia.");
            }

            if (mascotaTipo.isEmpty()) {
                throw new IllegalArgumentException("El tipo de mascota no puede estar vacia.");
            }

            this.edit = false;

            if (mascotaTipo.equals("Perro"))
            {
                mascotaViewModel.addMascota(new Mascota(UUID.randomUUID().toString(), nombre, Integer.parseInt(edad), raza, this.propietarioId));
            }

            if (mascotaTipo.equals("Gato"))
            {
                mascotaViewModel.addMascota(new Gato(UUID.randomUUID().toString(), nombre, Integer.parseInt(edad), raza, this.propietarioId));
            }

            if (mascotaTipo.equals("Conejo"))
            {
                mascotaViewModel.addMascota(new Rabbit(UUID.randomUUID().toString(), nombre, Integer.parseInt(edad), raza, this.propietarioId));
            }


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
        edadField.setText("");
        razaField.setText("");
    }

    public void setInfo(String id){
        System.out.println("Estoy tratando de setear");
        Mascota currentMascota = mascotaViewModel.getMascotaById(id);
        if (currentMascota == null){
            JOptionPane.showMessageDialog(this, "No existe una mascota");
        }
        this.edit = true;
        this.nombreField.setText(currentMascota.getNombre());
        this.edadField.setText(Integer.toString(currentMascota.getEdad()));
        this.razaField.setText(currentMascota.getRaza());
    }
}
