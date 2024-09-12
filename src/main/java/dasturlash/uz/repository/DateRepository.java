package dasturlash.uz.repository;

import dasturlash.uz.entity.DateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DateRepository extends CrudRepository<DateEntity, String>, PagingAndSortingRepository<DateEntity,String> {
    List<DateEntity> findAllByCustomerId(String customerId);
}
