package presentation.viewmodel;

import core.model.Cliente;
import core.services.MascotaService;
import core.model.Mascota;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MascotaViewModel {
    //private final MascotaRepositoryImpl repository;
    private final MascotaService mascotaService;
    private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    //Table
    private String [] columns = { "ID", "Nombre", "Edad", "Raza", "Acciones" };
    DefaultTableModel model = new DefaultTableModel(columns, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 4;
        }
    };

    public MascotaViewModel(MascotaService tempMascotaService) {
        this.mascotas.clear();
        this.mascotaService = tempMascotaService;
    }

    public Mascota getMascotaById(String id) {
        return mascotaService.getMascotaById(id);
    }

    public DefaultTableModel getAllMascotas(String propietarioId) {
        model.setRowCount(0);
        for(Mascota m : mascotaService.getMascotasByPropietarioId(propietarioId)) {
            Object[] row = {
                    m.getId(),
                    m.getNombre(),
                    m.getEdad(),
                    m.getRaza()
            };
            model.addRow(row);
        }
        return model;
    }

    public void addMascota(Mascota tempMascota)
    {
        mascotaService.addMascota(tempMascota);
        Object[] row = {
                tempMascota.getId(),
                tempMascota.getNombre(),
                tempMascota.getEdad(),
                tempMascota.getRaza()
        };
        model.addRow(row);
    }

    public void updateMascota(String id, Mascota tempMascota) {
        mascotaService.updateMascota(id, tempMascota);
    }

    public void deleteMascota(String index) {
        mascotaService.deleteMascota(index);
    }
}
