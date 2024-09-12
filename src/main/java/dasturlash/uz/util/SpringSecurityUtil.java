package dasturlash.uz.util;

import dasturlash.uz.config.CustomerUserDetails;
import dasturlash.uz.enam.GeneralStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpringSecurityUtil {
    public static String getId(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        CustomerUserDetails customerUserDetails= (CustomerUserDetails) authentication.getPrincipal();
        String id=customerUserDetails.getId();
        return id;
    }
    public static List<String> getProfileRoleList(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        CustomerUserDetails customerUserDetails= (CustomerUserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities= customerUserDetails.getAuthorities();
        List<String> roles=new ArrayList<String>();
        for(GrantedAuthority authority:authorities){
            roles.add(authority.getAuthority());
        }
        return roles;
    }
}

