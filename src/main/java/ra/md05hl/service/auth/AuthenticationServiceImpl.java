package ra.md05hl.service.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.md05hl.model.dto.request.FormLogin;
import ra.md05hl.model.dto.request.FormRegister;
import ra.md05hl.model.dto.response.JwtResponse;
import ra.md05hl.model.entity.RoleName;
import ra.md05hl.model.entity.Roles;
import ra.md05hl.model.entity.Users;
import ra.md05hl.repository.IRoleRepository;
import ra.md05hl.repository.IUserRepository;
import ra.md05hl.sercurity.jwt.JwtProvider;
import ra.md05hl.sercurity.principal.UserDetailsCustom;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final PasswordEncoder encoder;
    private final JwtProvider provider;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtResponse register(FormRegister request) {
        // quyen
        Set<Roles> roles = new HashSet<>();
        List<String> roleList = request.getRoles();
        if (roleList !=null && !roleList.isEmpty()){
            roleList.forEach(s -> {
                switch (s){
                    case "ROLE_ADMIN":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("id not found")));
                        break;
                    case "ROLE_USER":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("id not found")));
                        break;
                    default:
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("id not found")));
                }
            });
        }else {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("id not found")));
        }
        Users users = Users.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encoder.encode(request.getPassword()))
                .address(request.getAddress())
                .sex(request.getSex())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .status(true)
                .rolesSet(roles)
                .build();
        Users userInfo = userRepository.save(users);
        Map<String, Object> map = new HashMap<>();
        map.put("id",userInfo.getId());
        map.put("fullName",userInfo.getFullName());
        map.put("status",userInfo.getStatus());
        map.put("role",userInfo.getRolesSet());
        // tra ve JWTResponse
        JwtResponse response = JwtResponse.builder()
                .accessToken(provider.generateAccessToken(userInfo.getEmail()))
                .refreshToken(provider.generateRefreshToken(userInfo.getEmail()))
                .user(map)
                .build();
        return response;
    }

    @Override
    public JwtResponse login(FormLogin request) {
        // kiểm tra thông tin dang nhap AuthenticationManager
        Authentication auth = null;
        try {
            auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        }catch (Exception e){
            throw new RuntimeException("username or password incorrect");
        }
        UserDetailsCustom detailsCustom = (UserDetailsCustom) auth.getPrincipal();

        Map<String, Object> map = new HashMap<>();
        map.put("id",detailsCustom.getId());
        map.put("fullName",detailsCustom.getFullName());
        map.put("status",detailsCustom.getStatus());
        map.put("role",detailsCustom.getAuthorities());
        // tra ve JWTResponse
        JwtResponse response = JwtResponse.builder()
                .accessToken(provider.generateAccessToken(detailsCustom.getUsername()))
                .refreshToken(provider.generateRefreshToken(detailsCustom.getUsername()))
                .user(map)
                .build();
        return response;
    }

}