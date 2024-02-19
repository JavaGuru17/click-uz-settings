package uz.pdp.clickuzusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzusers.dto.CustomResponseEntity;
import uz.pdp.clickuzusers.service.DeviceService;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    @GetMapping("/all")
    public CustomResponseEntity<?> getAll(){
        return CustomResponseEntity.ok(deviceService.getAll());
    }
    @GetMapping("/user/{userId}")
    public CustomResponseEntity<?> getByUserId(@PathVariable Long userId){
        return CustomResponseEntity.ok(deviceService.getAllByUserId(userId));
    }
    @GetMapping("/IP/{IP}")
    public CustomResponseEntity<?> getById(@PathVariable String IP) {
        return CustomResponseEntity.ok(deviceService.getByIp(IP));
    }

}
