package dasturlash.uz.repository;

import dasturlash.uz.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity,String>, PagingAndSortingRepository<CustomerEntity,String> {
    Optional<CustomerEntity> findByPhoneAndVisibleTrue(String phone);
}
