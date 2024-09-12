package dasturlash.uz.service;

import dasturlash.uz.dto.CustomerDTO;
import dasturlash.uz.entity.CustomerEntity;
import dasturlash.uz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerDTO create(CustomerDTO customerDTO) {
        Optional<CustomerEntity> optional =customerRepository.findByPhoneAndVisibleTrue(customerDTO.getPhone());
        if (optional.isPresent()) {
            return null;
        }

        CustomerEntity customer=new CustomerEntity();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPhone(customerDTO.getPhone());
        customer.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
        customer.setStatus(customerDTO.getStatus());
        customer.setRole(customerDTO.getRole());

        customerRepository.save(customer);
        customerDTO.setId(customer.getId());

        return customerDTO;
    }

    public List<CustomerDTO> findAll() {
        Iterable<CustomerEntity> iterable = customerRepository.findAll();
        List<CustomerDTO> customerDTOS=new ArrayList<>();

        for (CustomerEntity customerEntity : iterable) {
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setId(customerEntity.getId());
            customerDTO.setName(customerEntity.getName());
            customerDTO.setSurname(customerEntity.getSurname());
            customerDTO.setPhone(customerEntity.getPhone());
            customerDTO.setPassword(customerEntity.getPassword());
            customerDTO.setStatus(customerEntity.getStatus());
            customerDTO.setRole(customerEntity.getRole());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
}
