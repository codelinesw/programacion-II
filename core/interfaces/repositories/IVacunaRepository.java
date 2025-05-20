package core.interfaces.repositories;

import core.model.Vacuna;

import java.util.ArrayList;

public interface IVacunaRepository {
    ArrayList<Vacuna> getVacunasByMascotaId(String propietarioId);
    public void addVacuna(Vacuna vacuna);
    void deleteVacuna(String index);
}