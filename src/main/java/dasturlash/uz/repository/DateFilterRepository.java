package dasturlash.uz.repository;

import dasturlash.uz.dto.DateDTO;
import dasturlash.uz.dto.DateResultDTO;
import dasturlash.uz.entity.DateEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class DateFilterRepository {
    @Autowired
    private EntityManager em;

    public DateResultDTO<DateEntity> filter(DateDTO dto,int page,int size){
        StringBuilder stringBuilder=new StringBuilder(" where 1=1 ");
        Map<String,Object> dateDTOMap=new HashMap<>();

        if(dto.getId()!=null){
            stringBuilder.append(" and s.id =:id ");
            dateDTOMap.put("id",dto.getId());
        }
        if (dto.getTotal()!=null){
            stringBuilder.append(" and s.total=:total ");
            dateDTOMap.put("total",dto.getTotal());
        }
        if (dto.getContentId()!=null){
            stringBuilder.append(" and s.contentId =:contentId ");
            dateDTOMap.put("contentId",dto.getContentId());
        }
        StringBuilder query= new StringBuilder("select s from DateEntity s ");
        query.append(stringBuilder);

        StringBuilder count= new StringBuilder("select count(s) from DateEntity s ");
        count.append(stringBuilder);

        Query selectQuery = em.createQuery(query.toString());
        dateDTOMap.forEach(selectQuery::setParameter);
        selectQuery.setFirstResult(page*size);
        selectQuery.setMaxResults(size);
        List<DateEntity> content = selectQuery.getResultList();

        Query countQuery = em.createQuery(count.toString());
        dateDTOMap.forEach(countQuery::setParameter);
        Long total = (Long) countQuery.getSingleResult();

        return new  DateResultDTO<DateEntity>(content,total);

    }
}
