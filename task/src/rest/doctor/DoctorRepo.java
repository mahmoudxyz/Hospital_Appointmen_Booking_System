package rest.doctor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor, Long> {

    @Modifying
    @Query("UPDATE Doctor d SET d.doctorName = :newName WHERE d.doctorName = :oldName")
    void updateDoctorName(@Param("oldName") String oldName, @Param("newName") String newName);

    @Modifying
    @Query("DELETE FROM Doctor e WHERE e.doctorName = :name")
    @Transactional
    void deleteByName(@Param("name") String name);



    void deleteAllByDoctorName(String name);

    @Transactional
    boolean existsDoctorByDoctorName(String doctorName);

    @Query("SELECT d FROM Doctor d WHERE d.doctorName = :name")
    Optional<Doctor> findByDoctorName(@Param("name") String name);






}
