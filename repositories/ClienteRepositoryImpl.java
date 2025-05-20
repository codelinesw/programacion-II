package repositories;

import core.interfaces.repositories.IClienteRepository;
import core.model.Cliente;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;

public class ClienteRepositoryImpl implements IClienteRepository {

    private static final String ARCHIVO_CLIENTES = "clientes.txt";
    private static final String TEMP_ARCHIVO_CLIENTES = "temp_clientes.txt";
    private final ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    @Override
    public ArrayList<Cliente> getAllClientes() {
        File archivo = new File(ARCHIVO_CLIENTES);
        if (!archivo.exists()) {  return new ArrayList<Cliente>(); }
        clientes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                clientes.add(Cliente.formattValue(linea));
            }
        } catch (IOException e) {
           System.out.println("Error al leer el archivo. -> " + e);
        }
        return clientes;
    }

    @Override
    public Cliente getClienteById(String id) {
        File archivo = new File(ARCHIVO_CLIENTES);
        Cliente cliente = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(id+"|")) { // Eliminamos la línea 2
                    cliente = Cliente.formattValue(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void addCliente(Cliente cliente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES , true))) {
            bw.write(cliente._toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCliente(String index, Cliente cliente) {
        File archivo = new File(ARCHIVO_CLIENTES);
        ArrayList<Cliente> nuevasLineas = new ArrayList<Cliente>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                BufferedWriter writter = new BufferedWriter (new FileWriter(archivo));
        ) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.startsWith(index+"|")) { // Eliminamos la línea 2
                   String nuevaLinea = linea.replace(linea,cliente._toString());
                   writter.write(nuevaLinea);
                } else {
                    writter.write(linea);
                }
                writter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(Paths.get(ARCHIVO_CLIENTES));
            Files.move(Paths.get(TEMP_ARCHIVO_CLIENTES), Paths.get(ARCHIVO_CLIENTES));
            System.out.println("Línea reemplazada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteCliente(String index) {
        File archivo = new File(ARCHIVO_CLIENTES);
        ArrayList<Cliente> nuevasLineas = new ArrayList<Cliente>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.startsWith(index+"|")) { // Eliminamos la línea 2
                    nuevasLineas.add(Cliente.formattValue(linea));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
            for (Cliente l : nuevasLineas) {
                writer.write(l._toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
