package uz.pdp.clickuzusers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.clickuzusers.util.annotations.DeviceIP;
import uz.pdp.clickuzusers.util.annotations.Length;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Device{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(fieldName = "device name", min = 2, max = 255)
    private String name;
    @DeviceIP
    private String IP;
    @NotNull
    private LocalDate registered;
    @NotNull
    private LocalDate lastEnter;
}
