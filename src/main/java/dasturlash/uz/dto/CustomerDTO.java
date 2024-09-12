package dasturlash.uz.dto;

import dasturlash.uz.enam.CustomerRole;
import dasturlash.uz.enam.GeneralStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    private String id;
    private String name;
    private String surname;
    private String password;
    private String phone;
    private GeneralStatus status;
    private CustomerRole role;
}
