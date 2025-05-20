package core.interfaces.repositories;

import core.model.Mascota;

import java.util.ArrayList;

public interface IMascotaRepository {
    Mascota getMascotaById(String id);
    ArrayList<Mascota> getMascotasByPropietarioId(String propietarioId);
    public void addMascota(Mascota mascota);
    void updateMascota(String index, Mascota mascota);
    void deleteMascota(String index);
}
