package ac.knu.service.Exception;

public class UnprocessableCommandException extends Exception {
    public UnprocessableCommandException() {
        System.out.println("처리할 수 없는 명령어입니다.");
    }
}
