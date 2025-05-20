package presentation.components;

import core.model.Cliente;
import presentation.viewmodel.ClienteViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ClienteForm extends JFrame{

    private JTextField nombreField;
    private JComboBox<String> tipoDocumentoBox;
    private JTextField identificacionField;
    private JTextField correoField;
    private JTextField numeroCelularField;
    private JTextField direccionField;

    private final ClienteViewModel clienteViewModel;
    private boolean edit = false;
    public String clienteId;

    public ClienteForm(ClienteViewModel clienteViewModel, boolean editable, String clienteId) {
        this.clienteViewModel = clienteViewModel;
        this.edit = editable;
        this.clienteId = clienteId;
        setTitle("Formulario de Cliente");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panelFormulario.add(new JLabel("Nombre completo:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(20);
        panelFormulario.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Tipo de documento:"), gbc);
        gbc.gridx = 1;
        tipoDocumentoBox = new JComboBox<>(new String[]{"Cédula de ciudadanía", "Cédula de extranjería"});
        panelFormulario.add(tipoDocumentoBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Identificación (máx. 10):"), gbc);
        gbc.gridx = 1;
        identificacionField = new JTextField();
        panelFormulario.add(identificacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Correo electrónico:"), gbc);
        gbc.gridx = 1;
        correoField = new JTextField();
        panelFormulario.add(correoField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Número celular (mín. 10):"), gbc);
        gbc.gridx = 1;
        numeroCelularField = new JTextField();
        panelFormulario.add(numeroCelularField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        direccionField = new JTextField();
        panelFormulario.add(direccionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton agregarButton = new JButton(edit  ? "Editar cliente" :"Agregar cliente");
        panelBotones.add(agregarButton);
        panelFormulario.add(panelBotones, gbc);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelFormulario, BorderLayout.NORTH);

        this.limpiarCampos();

        // Acción agregar
        agregarButton.addActionListener(e -> saveActionButton());

        // Acción eliminar
        //eliminarButton.addActionListener(e -> eliminarCliente());
    }

    public void saveActionButton()
    {
        if (this.edit)
            agregarCliente();
        else
            agregarCliente();
    }

    private void agregarCliente() {
        try {
            String nombre = nombreField.getText().trim();
            String tipoDocumento = (String) tipoDocumentoBox.getSelectedItem();
            String identificacion = identificacionField.getText().trim();
            String correo = correoField.getText().trim();
            String numeroCelular = numeroCelularField.getText().trim();
            String direccion = direccionField.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || identificacion.isEmpty() || correo.isEmpty() || numeroCelular.isEmpty() || direccion.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            if (identificacion.length() > 10) {
                throw new IllegalArgumentException("La identificación no puede tener más de 10 caracteres.");
            }
            if (numeroCelular.length() < 10) {
                throw new IllegalArgumentException("El número de celular debe tener al menos 10 dígitos.");
            }

            clienteViewModel.addCliente(new Cliente(UUID.randomUUID().toString(), nombre, identificacion, numeroCelular, correo, direccion));

            this.limpiarCampos();
            int option = JOptionPane.showConfirmDialog(null, "El cliente fue registrada con exíto",  "Operación exítosa",JOptionPane.OK_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                setVisible(false);
                clienteViewModel.getAllClientes();
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarCliente() {
        try {
            String nombre = nombreField.getText().trim();
            String tipoDocumento = (String) tipoDocumentoBox.getSelectedItem();
            String identificacion = identificacionField.getText().trim();
            String correo = correoField.getText().trim();
            String numeroCelular = numeroCelularField.getText().trim();
            String direccion = direccionField.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || identificacion.isEmpty() || correo.isEmpty() || numeroCelular.isEmpty() || direccion.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            if (identificacion.length() > 10) {
                throw new IllegalArgumentException("La identificación no puede tener más de 10 caracteres.");
            }
            if (numeroCelular.length() < 10) {
                throw new IllegalArgumentException("El número de celular debe tener al menos 10 dígitos.");
            }

            clienteViewModel.updateCliente(this.clienteId, new Cliente(UUID.randomUUID().toString(), nombre, identificacion, numeroCelular, correo, direccion));

            this.limpiarCampos();
            int option = JOptionPane.showConfirmDialog(null, "El cliente fue actualizado con exíto","Operación exítosa",  JOptionPane.OK_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                setVisible(false);
                clienteViewModel.getAllClientes();
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos() {
        nombreField.setText("");
        identificacionField.setText("");
        correoField.setText("");
        numeroCelularField.setText("");
        direccionField.setText("");
        tipoDocumentoBox.setSelectedIndex(0);
    }

    public void setEditable(boolean editable) {
        this.edit = editable;
    }
    public void setInfo(String index){
        System.out.println("estoy tratando de setear");
        this.edit = true;
        Cliente clienteActual = clienteViewModel.getClienteById(index);
        System.out.println("cliente -> "+ clienteActual.getNombreCompleto());
        nombreField.setText(clienteActual.getNombreCompleto());
        identificacionField.setText(clienteActual.getIdentificacion());
        numeroCelularField.setText(clienteActual.getTelefono());
        correoField.setText(clienteActual.getCorreo());
        direccionField.setText(clienteActual.getDireccion());
    }
}
