import core.services.MascotaService;
import core.services.VacunaService;
import presentation.viewmodel.MascotaViewModel;
import presentation.viewmodel.VacunaViewModel;
import repositories.ClienteRepositoryImpl;
import presentation.screens.ClienteView;
import presentation.viewmodel.ClienteViewModel;
import repositories.MascotaRepositoryImpl;
import repositories.VacunaRepositoryImpl;

import javax.swing.*;
public class Launch {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Inyección de dependencias

            var repository = new ClienteRepositoryImpl();
            var clienteViewModel = new ClienteViewModel(repository);

            var mascotaRepository = new MascotaRepositoryImpl();
            var mascotaService = new MascotaService(mascotaRepository);
            var mascotaViewModel = new MascotaViewModel(mascotaService);

            var vacunaRepository = new VacunaRepositoryImpl();
            var vacunaService = new VacunaService(vacunaRepository);
            var vacunaViewModel = new VacunaViewModel(vacunaService);

            var view = new ClienteView(clienteViewModel, mascotaViewModel, vacunaViewModel);
            view.setVisible(true);
        });
    }
}
