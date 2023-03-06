package rest.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.appointment.Appointment;
import rest.appointment.AppointmentRepo;
import rest.appointment.AppointmentService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class StatisticsController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    AppointmentRepo appointmentRepo;



    @GetMapping("/statisticsDay")
    public ResponseEntity<List<Map<String, Integer>>> getDayStatistics() {
        List<Appointment> appointments = (List<Appointment>) appointmentRepo.findAll();
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Map<String, Integer> statistics = appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDate, Collectors.summingInt(a -> 1)));
        List<Map<String, Integer>> response = statistics.entrySet().stream()
                .map(e -> Collections.singletonMap(e.getKey().toString(), e.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/statisticsDoc")
    public ResponseEntity<List<Map<String, Integer>>> getDoctorStatistics() {
        List<Appointment> appointments = (List<Appointment>) appointmentRepo.findAll();
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Map<String, Integer> statistics = appointments.stream()
                .collect(Collectors.groupingBy(a -> a.getDoctor(), Collectors.summingInt(a -> 1)));
        List<Map<String, Integer>> result = statistics.entrySet().stream()
                .map(e -> Collections.singletonMap(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


}
