package ra.md05hl.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
@Data
@Builder
public class JwtResponse {
    private final String type = "Bearer Token";
    private String accessToken;
    private String refreshToken;
    private Map<String,Object> user;
}
