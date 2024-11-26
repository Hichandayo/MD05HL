package ra.md05hl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ra.md05hl.model.entity.RoleName;
import ra.md05hl.model.entity.Roles;
import ra.md05hl.repository.IRoleRepository;

@SpringBootApplication
public class Md05HlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Md05HlApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner runner(IRoleRepository roleRepository){
//        return args -> {
//            // tao quyen
//            Roles admin = new Roles(null, RoleName.ROLE_ADMIN);
//            Roles u = new Roles(null, RoleName.ROLE_USER);
//            roleRepository.save(admin);
//            roleRepository.save(u);
//        };
//    }

}
