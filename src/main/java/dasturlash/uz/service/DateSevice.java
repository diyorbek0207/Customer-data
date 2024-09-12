package dasturlash.uz.service;

import dasturlash.uz.dto.DateDTO;
import dasturlash.uz.dto.DateResultDTO;
import dasturlash.uz.enam.CustomerRole;
import dasturlash.uz.entity.DateEntity;
import dasturlash.uz.exception.AppBadRequestException;
import dasturlash.uz.exception.ItemNotFoundException;
import dasturlash.uz.repository.DateFilterRepository;
import dasturlash.uz.repository.DateRepository;
import dasturlash.uz.util.SpringSecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DateSevice {
    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private DateFilterRepository dateFilterRepository;

    public DateDTO create(DateDTO dto) {
        String customerId=SpringSecurityUtil.getId();
        DateEntity dateEntity =new DateEntity();
        dateEntity.setContent(dto.getContent());
        dateEntity.setTotal(dto.getTotal());
        dateEntity.setCustomerId(customerId);
        dateRepository.save(dateEntity);
        dto.setId(dateEntity.getId());
        dto.setContentId(customerId);

        return dto;
    }
    public List<DateDTO> findAll() {
        Iterable<DateEntity> iterable=dateRepository.findAll();
        List<DateDTO> dtos=new ArrayList<>();
        for(DateEntity dateEntity:iterable) {
            dtos.add(toDTO(dateEntity));
        }
        return dtos;
    }
    public List<DateDTO> findAllByCustomerID() {
        String customerId= SpringSecurityUtil.getId();
        List<DateEntity> dateEntities=dateRepository.findAllByCustomerId(customerId);
        List<DateDTO> dtos=new ArrayList<>();
        for(DateEntity dateEntity:dateEntities) {
            dtos.add(toDTO(dateEntity));
        }
        return  dtos;
    }
    public Boolean update(DateDTO dto,String id) {
        DateEntity dateEntity=get(id);
        dateEntity.setContent(dto.getContent());
        dateEntity.setTotal(dto.getTotal());
        List<String> rolList=SpringSecurityUtil.getProfileRoleList();
        if (!dateEntity.getCustomerId().equals(SpringSecurityUtil.getId()) && !rolList.contains(CustomerRole.ROLE_ADMIN.name())) {
            throw new AppBadRequestException("It does not belong to the customer ID");
        }
        dateRepository.save(dateEntity);
        return true;
    }
    public DateEntity get(String id) {
        return dateRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("Not Found");
        });
    }
    public Boolean delete(String id) {
        DateEntity dateEntity=get(id);
        List<String> rolList=SpringSecurityUtil.getProfileRoleList();
        if (!dateEntity.getCustomerId().equals(SpringSecurityUtil.getId()) && !rolList.contains(CustomerRole.ROLE_ADMIN.name())) {
            throw new AppBadRequestException("It does not belong to the customer ID");
        }
        dateRepository.delete(dateEntity);
        return true;
    }

    public Boolean deleteAdmin(String id) {
        DateEntity dateEntity=get(id);

        dateRepository.delete(dateEntity);
        return true;
    }
    public PageImpl pagination(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page, size);
        Page<DateEntity> pageEntity=dateRepository.findAll(pageRequest);

        Long total=pageEntity.getTotalElements();
        List<DateDTO> content=new ArrayList<>();

        for (DateEntity dateEntity : pageEntity.getContent()) {
            DateDTO dto=new DateDTO();
            dto.setTotal(dateEntity.getTotal());
            dto.setContent(dateEntity.getContent());
            dto.setId(dateEntity.getId());
            dto.setContentId(dateEntity.getId());
            content.add(dto);
        }
        return new PageImpl(content, pageRequest, total);
    }

    public PageImpl<DateDTO> filter(DateDTO dto, int page, int size) {
        PageRequest pageRequest=PageRequest.of(page, size);
        DateResultDTO<DateEntity> dateResultDTO=dateFilterRepository.filter(dto,page,size);

        List<DateEntity> list=dateResultDTO.getList();
        Long total=dateResultDTO.getTotal();
        List<DateDTO> content=new ArrayList<>();
        for (DateEntity dateEntity: list){
            DateDTO dateDTO=new DateDTO();
            dateDTO.setTotal(dateEntity.getTotal());
            dateDTO.setContent(dateEntity.getContent());
            dateDTO.setId(dateEntity.getId());
            dateDTO.setContentId(dateEntity.getId());
            content.add(dateDTO);
        }
        return new PageImpl<>(content, pageRequest, total);
    }

    public DateDTO toDTO(DateEntity dateEntity) {
        DateDTO dto=new DateDTO();
        dto.setContent(dateEntity.getContent());
        dto.setTotal(dateEntity.getTotal());
        dto.setId(dateEntity.getId());
        dto.setContentId(dateEntity.getCustomerId());
        return dto;
    }
}
