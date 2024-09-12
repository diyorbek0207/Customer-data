package dasturlash.uz.config;

import dasturlash.uz.enam.CustomerRole;
import dasturlash.uz.enam.GeneralStatus;
import dasturlash.uz.entity.CustomerEntity;
import org.aspectj.weaver.SimpleAnnotationValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private GeneralStatus status;
    private CustomerRole role;

    public CustomerUserDetails(CustomerEntity customer) {
        this.id = customer.getId();
        this.password=customer.getPassword();
        this.username=customer.getName();
        this.status=customer.getStatus();
        this.role=customer.getRole();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(GeneralStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getId(){
        return id;
    }
}
