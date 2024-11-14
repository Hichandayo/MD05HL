package ra.md05hl.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseError<T> {
    private int code;
    private HttpStatus message;
    private T details;
}
