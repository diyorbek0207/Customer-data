package dasturlash.uz.entity;

import dasturlash.uz.enam.CustomerRole;
import dasturlash.uz.enam.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "cutomer_entity")
public class CustomerEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "passvord")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private CustomerRole role;
    @Column(name = "visible")
    private Boolean visible=Boolean.TRUE;
    @Column(name = "createDate")
    private LocalDateTime createDate=LocalDateTime.now();
}
