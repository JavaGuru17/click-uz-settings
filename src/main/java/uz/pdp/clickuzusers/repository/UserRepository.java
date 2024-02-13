package uz.pdp.clickuzusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.clickuzusers.model.User;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.model.enums.Gender;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPassport(String passport);
    Optional<User> findByJShShIR(String JShShIR);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAllByRegion(Region region);
    List<User> findAllByGender(Gender gender);
}