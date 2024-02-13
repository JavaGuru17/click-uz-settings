package uz.pdp.clickuzusers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.clickuzusers.model.enums.Region;
import uz.pdp.clickuzusers.model.enums.Gender;
import uz.pdp.clickuzusers.util.annotations.Length;
import uz.pdp.clickuzusers.util.annotations.Password;
import uz.pdp.clickuzusers.util.annotations.PhoneNumber;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users")
public class User extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(fieldName = "user name", min = 3, max = 50)
    private String name;
    @Length(fieldName = "user surname", min = 3, max = 50)
    private String surname;
    @Length(fieldName = "user middle name", min = 3, max = 50)
    private String middleName;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;
    private String passport;
    private String JShShIR;
    @NotNull
    private LocalDate dateOfIssue;
    @NotNull
    private LocalDate expiryDate;
    @NotNull
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Region region;
    @uz.pdp.clickuzusers.util.annotations.PIN
    private String PIN;
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
    @ToString.Exclude
    @ManyToMany
    private List<Role> roles;
    @OneToMany
    private List<CardId> cardIds;
    @OneToMany
    private List<Device> devices;
}

