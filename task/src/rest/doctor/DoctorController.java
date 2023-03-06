package rest.doctor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import rest.appointment.AppointmentService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping
@Validated
public class DoctorController {

    Logger logger = Logger.getAnonymousLogger();
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationExceptions() {
        // Do nothing; the 400 status code is sufficient
    }

    @PostMapping("/newDoctor")
    public Doctor addNewDoctor(@Valid @RequestBody Doctor doctor) {
        String name = doctor.getDoctorName().toLowerCase();
        doctor.setDoctorName(name.toLowerCase());
        doctorService.saveDoctor(doctor);
        return doctorService.getDoctor(doctor.getId());
    }

    @GetMapping("/allDoctorslist")
    public Iterable<Doctor> getAllDoctors() {
        logger.warning(doctorService.getAllDoctors().toString());
        doctorService.removeDuplicates();
        return doctorService.getAllDoctors();

    }

    @DeleteMapping("/deleteDoctor")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@RequestParam String doc) throws JsonProcessingException {
        doctorService.removeDuplicates();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.warning(doc);
        var docName = doc.toLowerCase();
        logger.warning(docName);
        if (!doctorService.isExistByName(docName))  {
            return new ResponseEntity<>("Doctor not found",HttpStatus.BAD_REQUEST);
        }

        Doctor doctor = doctorService.getDoctorByName(docName);
        if (docName.equals("director")) {
            doctorService.deleteAllByName(docName);
            appointmentService.deleteAppointmentByName(docName);
        } else {
            doctorService.updateDoctor(docName,"director");
            appointmentService.modifyAll(docName,"director");
            logger.warning(appointmentService.getAllAppointments().toString());

        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(new Doctor(doctor.getId(),docName)));

    }


}
