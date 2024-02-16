package uz.pdp.clickuzusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.clickuzusers.model.Device;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findAllByIP(String IP);
    @Query("SELECT d FROM Device d JOIN User u ON u.id=?1 JOIN u.devices ud ON ud.id=d.id")
    List<Device> findAllByUserId(Long userId);
}