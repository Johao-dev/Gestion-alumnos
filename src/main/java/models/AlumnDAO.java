package models;

import java.util.List;
import persistence.AlumnJpaController;

public class AlumnDAO {
    
    private final AlumnJpaController ctrl = new AlumnJpaController();
    
    public void createAlumn(Alumn alumn) {
        ctrl.create(alumn);
    }
    
    public void updateAlumn(Alumn alumn) {
        Alumn alu = ctrl.findUser(alumn.getAlumnId());
        
        if(alu != null)
            ctrl.edit(alumn);
    }
    
    public void deleteAlumn(int id) {
        Alumn alumn = ctrl.findUser(id);
        
        if(alumn != null)
            ctrl.destroy(id);
    }
    
    public List<Alumn> getAllAlumns() {
        return ctrl.findUserEntities();
    }
    
    public Alumn getAlumnById(int id) {
        return ctrl.findUser(id);
    }
}
