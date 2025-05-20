package presentation.viewmodel;

import core.model.Mascota;
import core.model.Vacuna;
import core.services.VacunaService;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VacunaViewModel {
    private final VacunaService vacunaService;
    private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    //Table
    private String [] columns = { "ID", "Nombre Vacuna", "Fecha aplicación", "Lote", "Veterinario Responsable", "Acciones" };
    DefaultTableModel model = new DefaultTableModel(columns, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 5;
        }
    };

    public VacunaViewModel(VacunaService tempVacunaService) {
        this.mascotas.clear();
        this.vacunaService = tempVacunaService;
    }

    public DefaultTableModel getAllMascotas(String mascotaId) {
        model.setRowCount(0);
        for(Vacuna m : vacunaService.getVacunasByMascotaId(mascotaId)) {
            Object[] row = {
                    m.getId(),
                    m.getNombre(),
                    m.getFechaAplicacion(),
                    m.getLote(),
                    m.getVeterinario()
            };
            model.addRow(row);
        }
        return model;
    }

    public void addVacuna(Vacuna tempMascota)
    {
        vacunaService.addVacuna(tempMascota);
        Object[] row = {
                tempMascota.getId(),
                tempMascota.getNombre(),
                tempMascota.getFechaAplicacion(),
                tempMascota.getLote(),
                tempMascota.getVeterinario()
        };
        model.addRow(row);
    }

    public void deleteVacuna(String id) {
        vacunaService.deleteVacuna(id);
    }
}
