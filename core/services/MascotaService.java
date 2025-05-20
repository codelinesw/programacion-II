package core.services;

import core.model.Mascota;
import repositories.MascotaRepositoryImpl;

import java.util.ArrayList;

public class MascotaService {

    private final MascotaRepositoryImpl repository;

    public MascotaService(MascotaRepositoryImpl repository) {
        this.repository = repository;
    }

    public ArrayList<Mascota> getMascotasByPropietarioId(String propietarioId) {
        return repository.getMascotasByPropietarioId(propietarioId);
    }

    public Mascota getMascotaById(String id) {
       return repository.getMascotaById(id);
    }

    public void addMascota(Mascota mascotica) {
        repository.addMascota(mascotica);
    }

    public void updateMascota(String index, Mascota mascotica) {
        repository.updateMascota(index, mascotica);
    }


    public void deleteMascota(String id) {
        repository.deleteMascota(id);
    }

}
