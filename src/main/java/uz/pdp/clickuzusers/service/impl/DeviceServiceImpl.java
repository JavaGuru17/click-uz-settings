package uz.pdp.clickuzusers.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzusers.exception.NotFoundException;
import uz.pdp.clickuzusers.exception.NullOrEmptyException;
import uz.pdp.clickuzusers.model.Device;
import uz.pdp.clickuzusers.repository.DeviceRepository;
import uz.pdp.clickuzusers.repository.UserRepository;
import uz.pdp.clickuzusers.service.DeviceService;
import uz.pdp.clickuzusers.util.Validator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device getByIp(String IP) {
        if (Validator.isNullOrEmpty(IP))
            throw new NullOrEmptyException("IP");
        return deviceRepository.findAllByIP(IP).orElseThrow(
                ()->new NotFoundException("Device")
        );
    }

    @Override
    public List<Device> getAllByUserId(Long userId) {
        if (userId == null)
            throw new NullOrEmptyException("User Id");
        if (userRepository.findById(userId).isEmpty())
            throw new NotFoundException("User");
        return deviceRepository.findAllByUserId(userId);
    }
}
