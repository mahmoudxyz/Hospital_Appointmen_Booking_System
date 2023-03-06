package rest.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;

    public void saveAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    public void deleteAppointment(Long id) {
            appointmentRepo.deleteById(id);

    }


    public boolean isHere(Long id) {
        return appointmentRepo.existsById(id);
    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Transactional
    public void modifyAll(String oldName, String  newName){
        appointmentRepo.updateDoctorName(oldName,newName);
    }



    @Transactional
    public void deleteAppointmentByName(String name){
        appointmentRepo.deleteByDoctor(name);
    }

    @Transactional
    void deleteAllByName(String name){
        appointmentRepo.deleteByDoctor(name);
    }


}

