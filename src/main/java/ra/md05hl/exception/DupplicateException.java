package ra.md05hl.exception;

public class DupplicateException  extends RuntimeException{
    public DupplicateException(String message) {
        super(message);
    }
}
