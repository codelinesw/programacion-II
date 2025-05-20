package core.services;

import core.model.Cliente;
import core.model.Vacuna;
import repositories.VacunaRepositoryImpl;

import java.util.ArrayList;

public class VacunaService {

    private final VacunaRepositoryImpl repository;

    public VacunaService(VacunaRepositoryImpl repository) {
        this.repository = repository;
    }

    public ArrayList<Vacuna> getVacunasByMascotaId(String propietarioId) {
        return repository.getVacunasByMascotaId(propietarioId);
    }

    public void addVacuna(Vacuna mascotica) {
        repository.addVacuna(mascotica);
    }

    public void deleteVacuna(String id) {
        repository.deleteVacuna(id);
    }

}
