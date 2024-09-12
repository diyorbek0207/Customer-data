package dasturlash.uz.config;

import dasturlash.uz.entity.CustomerEntity;
import dasturlash.uz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerEntity> optional=customerRepository.findByPhoneAndVisibleTrue(username);
        if (optional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        CustomerEntity customer=optional.get();

        CustomerUserDetails customerUserDetails=new CustomerUserDetails(customer);
        return customerUserDetails;
    }
}
