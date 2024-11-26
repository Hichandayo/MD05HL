package ra.md05hl.sercurity.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.md05hl.model.entity.Users;
import ra.md05hl.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    // chứa pt loadUserByUsername
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        //bieensn đổi thanh UserDetails
        // biến đổi quyên
        List<? extends GrantedAuthority> authorities = users
                .getRolesSet()
                .stream().map(role-> new SimpleGrantedAuthority(role.getRoleName().name()))
                .toList();
        UserDetails userDetails =  UserDetailsCustom.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .sex(users.getSex())
                .address(users.getAddress())
                .dateOfBirth(users.getDateOfBirth())
                .status(users.getStatus())
                .authorities(authorities)
                .build();
        return userDetails;
    }
}
