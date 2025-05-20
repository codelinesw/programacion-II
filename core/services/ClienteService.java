package core.services;

import core.model.Cliente;
import repositories.ClienteRepositoryImpl;

import java.util.ArrayList;

public class ClienteService {

    private final ClienteRepositoryImpl repository;

    public ClienteService(ClienteRepositoryImpl repository) {
        this.repository = repository;
    }

    public ArrayList<Cliente> getAllClientes() {
        return repository.getAllClientes();
    }

    public Cliente getClienteById(String id) {
       return repository.getClienteById(id);
    }

    public void addCliente(Cliente clientecito) {
        repository.addCliente(clientecito);
    }

    public void updateCliente(String id, Cliente clientecito) {
        repository.updateCliente(id, clientecito);
    }

    public void deleteCliente(String id) {
        repository.deleteCliente(id);
    }

}
