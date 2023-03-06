package rest.appointment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping
@Validated
public class AppointmentController {


    @Autowired
    AppointmentService appointmentService;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationExceptions() {
        // Do nothing; the 400 status code is sufficient
    }

    @PostMapping("/setAppointment")

    public Appointment setAppointment(@RequestBody @Valid Appointment appointment) {
        if (Objects.equals(appointment.getDoctor(), "director")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        appointmentService.saveAppointment(appointment);
        return appointmentService.getAppointment(appointment.getIdApp());
    }


    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<String> deleteAppointment(@RequestParam Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Appointment appointment = new Appointment();
        if (appointmentService.isHere(id)) {
            appointment = appointmentService.getAppointment(id);
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(objectMapper.writeValueAsString(appointment));
        } else return ResponseEntity.badRequest().body("{\n" +
                "   \"error\": \"The appointment does not exist or was already cancelled\"\n" +
                "}");
    }

    @GetMapping("/appointments")
    public Iterable<Appointment> getAllAppointments() {
        if (!appointmentService.getAllAppointments().iterator().hasNext()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return appointmentService.getAllAppointments();
    }

//    @PostMapping("/test")
//    @Transactional
//    public Iterable<Appointment> test(@RequestParam String name ) {
//        appointmentService.modifyAll(name,"Mahmoud");
//        return appointmentService.getAllAppointments();
//    }


}
