package ra.md05hl.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(securedEnabled = true)// cau hinh phan quyen tren phuong thuc
public class SecurityConfig  {// cấu hình bảo mật
}
