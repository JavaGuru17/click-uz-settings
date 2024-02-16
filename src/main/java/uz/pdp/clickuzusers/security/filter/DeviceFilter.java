package uz.pdp.clickuzusers.security.filter;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.clickuzusers.model.Device;
import uz.pdp.clickuzusers.model.User;
import uz.pdp.clickuzusers.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@NonNullApi
@RequiredArgsConstructor
public class DeviceFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
//    private final Authentication authentication;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
//            String ip = request.getHeader("x-forwarded-from");
            String ip = request.getRemoteAddr();
            String deviceName = request.getHeader("User-Agent");
            Optional<User> optionalUser = userRepository.findByPhoneNumber(authentication.getPrincipal().toString());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                List<Device> devicesToUpdate = new ArrayList<>();
                user.getDevices().forEach(d -> {
                    if (d.getIP().equals(ip)) {
                        d.setLastEnter(LocalDate.now());
                        devicesToUpdate.add(d);
                    }
                });
                if (devicesToUpdate.isEmpty()) {
                    Device newDevice = Device.builder()
                            .name(deviceName)
                            .IP(ip)
                            .registered(LocalDate.now())
                            .lastEnter(LocalDate.now())
                            .build();
                    user.getDevices().add(newDevice);
                }
                userRepository.save(user);
            }
        }
        filterChain.doFilter(request,response);
    }
}
