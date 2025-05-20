package presentation.viewmodel;

import core.model.Cliente;
import repositories.ClienteRepositoryImpl;
import core.services.ClienteService;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ClienteViewModel {
    private final ClienteRepositoryImpl repository;
    private final ClienteService clienteService;
    //Data model
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    //Table
    private String [] columns = {"ID", "Nombre", "Identificación", "Correo", "Teléfono", "Dirección", "Acciones"};
    DefaultTableModel model = new DefaultTableModel(columns, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 6;
        }
    };

    public ClienteViewModel(ClienteRepositoryImpl repository) {
        this.repository = repository;
        this.clienteService = new ClienteService(repository);
    }

    public Cliente getClienteById(String id) {
        return clienteService.getClienteById(id);
    }

    public DefaultTableModel getAllClientes(){
        model.setRowCount(0);
        for(Cliente customer : clienteService.getAllClientes()) {
            Object[] row = {
                    customer.getId(),
                    customer.getNombreCompleto(),
                    customer.getIdentificacion(),
                    customer.getCorreo(),
                    customer.getTelefono(),
                    customer.getDireccion(),
            };
            model.addRow(row);
        }
        return model;
    }

    public void addCliente(Cliente tempCliente) {
        clienteService.addCliente(tempCliente);
    }

    public void updateCliente(String id, Cliente tempCliente) {
        clienteService.updateCliente(id, tempCliente);
    }

    public void deleteCliente(String id) {
        clienteService.deleteCliente(id);
    }
}
