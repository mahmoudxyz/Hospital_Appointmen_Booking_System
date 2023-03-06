package rest.appointment;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, Long> {


    @Transactional
    @Modifying
    @Query("UPDATE Appointment d SET d.doctor = :newName WHERE LOWER(d.doctor) = LOWER(:oldName)")
    void updateDoctorName(@Param("oldName") String oldName, @Param("newName") String newName);


    void deleteByDoctor(String name);

}
