package ra.md05hl.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseDto<T> {
    private int code;
    private HttpStatus message;
    private T data;
}