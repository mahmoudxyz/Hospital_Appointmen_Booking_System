package rest.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;

    @Transactional
    public void saveDoctor(Doctor doctor) {
        doctorRepo.findAll().forEach(doctor1 -> {
            if (doctor1.getDoctorName().equals(doctor.getDoctorName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        });
        doctorRepo.save(doctor);

    }

    public Doctor getDoctor(Long id) {
        return doctorRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public Iterable<Doctor> getAllDoctors() {
        Iterable<Doctor> doctors = doctorRepo.findAll();
        if (!doctors.iterator().hasNext()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return doctors;
    }

    @Transactional
    void updateDoctor(String oldName, String newName) {
         doctorRepo.updateDoctorName(oldName, newName);
    }

    @Transactional
    void deleteAllByName(String name) {
        doctorRepo.deleteAllByDoctorName(name);
    }



    boolean isExistByName(String name) {
        return doctorRepo.existsDoctorByDoctorName(name);
    }

    public Doctor getDoctorByName(String name) {

        return  doctorRepo.findByDoctorName(name).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST
                        ,"Doctor not found")
        );
    }

    @Transactional
    public void removeDuplicates() {
        List<Doctor> entities = (List<Doctor>) doctorRepo.findAll();
        Set<String> seenNames = new HashSet<>();

        for (Doctor entity : entities) {
            String name = entity.getDoctorName();
            if (seenNames.contains(name)) {
                doctorRepo.delete(entity);
            } else {
                seenNames.add(name);
            }
        }
    }
}


