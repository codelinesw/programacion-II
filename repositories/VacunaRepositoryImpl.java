package repositories;

import core.interfaces.repositories.IVacunaRepository;
import core.model.Vacuna;

import java.io.*;
import java.util.ArrayList;

public class VacunaRepositoryImpl implements IVacunaRepository {

    private static final String ARCHIVO_VACUNAS = "vacunas.txt";
    private final ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();

    @Override
    public ArrayList<Vacuna> getVacunasByMascotaId(String mascotaId) {
        File archivo = new File(ARCHIVO_VACUNAS);
        if (!archivo.exists()) {  return new ArrayList<Vacuna>(); }
        vacunas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(mascotaId)) {
                    vacunas.add(Vacuna.formattValue(linea));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo. -> " + e);
        }
        return vacunas;
    }

    @Override
    public void addVacuna(Vacuna mascota) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_VACUNAS , true))) {
            bw.write(mascota._toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVacuna(String id) {
        File archivo = new File(ARCHIVO_VACUNAS);
        ArrayList<Vacuna> nuevasLineas = new ArrayList<Vacuna>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(ARCHIVO_VACUNAS)))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.startsWith("id="+id+"&")) { // Eliminamos la línea 2
                    nuevasLineas.add(Vacuna.formattValue(linea));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
            for (Vacuna vac : nuevasLineas) {
                writer.write(vac._toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
