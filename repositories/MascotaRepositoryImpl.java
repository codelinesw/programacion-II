package repositories;

import core.interfaces.repositories.IMascotaRepository;
import core.model.Cliente;
import core.model.Mascota;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MascotaRepositoryImpl implements IMascotaRepository {
    private static final String ARCHIVO_MASCOTAS = "mascotas.txt";
    private static final String TEMP_ARCHIVO_MASCOTAS = "temp_mascotas.txt";
    private final ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

    public Mascota getMascotaById(String id) {
        File archivo = new File(ARCHIVO_MASCOTAS);
        Mascota mascota = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("id="+id+"&")) {
                    mascota = Mascota.formattValue(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mascota;
    }

    @Override
    public ArrayList<Mascota> getMascotasByPropietarioId(String propietarioId) {
        File archivo = new File(ARCHIVO_MASCOTAS);
        if (!archivo.exists()) {  return new ArrayList<Mascota>(); }
        mascotas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(propietarioId)) {
                    mascotas.add(Mascota.formattValue(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo. -> " + e);
        }
        return mascotas;
    }

    @Override
    public void addMascota(Mascota mascota) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_MASCOTAS , true))) {
            bw.write(mascota._toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMascota(String index, Mascota mascota) {
        File archivo = new File(ARCHIVO_MASCOTAS);
        ArrayList<Mascota> nuevasLineas = new ArrayList<Mascota>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                BufferedWriter writter = new BufferedWriter (new FileWriter(archivo));
        ) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.startsWith(index+"|")) { // Eliminamos la línea 2
                    String nuevaLinea = linea.replace(linea,mascota._toString());
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
            Files.delete(Paths.get(ARCHIVO_MASCOTAS));
            Files.move(Paths.get(TEMP_ARCHIVO_MASCOTAS), Paths.get(ARCHIVO_MASCOTAS));
            System.out.println("Línea reemplazada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMascota(String id) {
        File archivo = new File(ARCHIVO_MASCOTAS);
        ArrayList<Mascota> nuevasLineas = new ArrayList<Mascota>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(ARCHIVO_MASCOTAS)))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.startsWith("id="+id+"&")) { // Eliminamos la línea 2
                    nuevasLineas.add(Mascota.formattValue(linea));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
            for (Mascota l : nuevasLineas) {
                writer.write(l._toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
