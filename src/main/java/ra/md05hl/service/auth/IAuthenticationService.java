package ra.md05hl.service.auth;


import ra.md05hl.model.dto.request.FormLogin;
import ra.md05hl.model.dto.request.FormRegister;
import ra.md05hl.model.dto.response.JwtResponse;

public interface IAuthenticationService {
    JwtResponse login(FormLogin request);
    JwtResponse register(FormRegister request);
}