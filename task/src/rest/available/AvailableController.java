package rest.available;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rest.appointment.Appointment;
import rest.appointment.AppointmentService;
import rest.doctor.DoctorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping
public class AvailableController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    Available available;

    @GetMapping("/availableDatesByDoctor")
    public List<Available> getAvailableTime(@RequestParam String doc) {


        if (!doctorService.getAllDoctors().iterator().hasNext()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        AtomicBoolean flag = new AtomicBoolean(false);
      doctorService.getAllDoctors().forEach(
              doctor2 -> {
                  if (Objects.equals(doctor2.getDoctorName().toLowerCase(), doc.toLowerCase())) {
                      flag.set(true);
                  }
              }
      );

      if (!flag.get()){
          throw new ResponseStatusException(HttpStatus.NO_CONTENT);
      }

        LocalDate lackDate;
        lackDate = LocalDate.parse("2022-11-03");
        List<Available> availableTime = new ArrayList<>();

                for (int i = 0; i < 4; i++) {
                    String str  = lackDate.plusDays(i).toString();
                    available.setAvailabletime(str);
                    available.setBooked(true);
                    availableTime.add(available);
                }



        return availableTime;

    }


}
