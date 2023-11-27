package diary.exception;

public class DiaryLockException extends RuntimeException {
    public DiaryLockException(String message) {
        super(message);
    }
}
