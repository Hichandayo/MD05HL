package ra.md05hl.model.dto.response;

import lombok.Data;
@Data
public class UserInfo {
    private Long id;
    private String username;
    private Boolean status;
    private String fullName;
    private String role;
}
