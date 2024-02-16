package uz.pdp.clickuzusers.service;


import org.springframework.stereotype.Service;
import uz.pdp.clickuzusers.model.Device;

import java.util.List;

@Service
public interface DeviceService {
    List<Device> getAll();
    Device getByIp(String IP);
    List<Device> getAllByUserId(Long userId);
}
