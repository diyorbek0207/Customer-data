package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "date_entity")
public class DateEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;
    @Column(name = "total")
    private String total;
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    private LocalDateTime createDate=LocalDateTime.now();
    @Column(name = "customer_id")
    private String customerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",updatable = false,insertable = false)
    private CustomerEntity customer;

}
