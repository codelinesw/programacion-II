package core.interfaces.repositories;

import core.model.Cliente;
import java.util.ArrayList;

public interface IClienteRepository {
    void addCliente(Cliente owner);
    Cliente getClienteById(String id);
    ArrayList<Cliente> getAllClientes();
    void updateCliente(String index, Cliente cliente);
    void deleteCliente(String index);
}