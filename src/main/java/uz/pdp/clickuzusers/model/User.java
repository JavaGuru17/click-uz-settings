package uz.pdp.clickuzusers.model;

import jakarta.persistence.*;
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
    private Gender gender;
    private String passport;
    private String JShShIR;
    private LocalDate dateOfIssue;
    private LocalDate expiryDate;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Region region;
    @uz.pdp.clickuzusers.util.annotations.PIN
    private String PIN;
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Role> roles;
    @OneToMany
    @ToString.Exclude
    private List<CardId> cardIds;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Device> devices;
}

