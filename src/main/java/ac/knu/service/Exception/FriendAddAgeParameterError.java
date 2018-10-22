package ac.knu.service.Exception;

public class FriendAddAgeParameterError extends Exception {
    public FriendAddAgeParameterError() {
        System.out.println("나이가 잘못 입력되었습니다.");
    }
}
