package rest.doctor;

import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Doctor")
@Component
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @NotBlank
    @NotEmpty
    @Getter(AccessLevel.NONE)
    private String doctorName="";



    public String getDoctorName() {
        return doctorName.toLowerCase();
    }


}
