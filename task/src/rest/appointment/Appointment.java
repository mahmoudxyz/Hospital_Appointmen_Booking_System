package rest.appointment;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Appointment {
    @Id
    @GeneratedValue
    private Long idApp;




    @NotEmpty
    @NotNull
    @NotBlank
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String doctor= "";



    @NotEmpty
    @NotNull
    @NotBlank
    @Getter(AccessLevel.NONE)
    private String patient = "";


    @NotNull
    @NotBlank
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String date;

    public String getDoctor() {
        return doctor.toLowerCase();
    }

    public String getPatient() {
        return patient.toLowerCase();
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor.toLowerCase();
    }

}

