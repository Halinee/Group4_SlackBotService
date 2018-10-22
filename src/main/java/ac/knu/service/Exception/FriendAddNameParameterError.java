package ac.knu.service.Exception;

public class FriendAddNameParameterError extends Exception {
    public FriendAddNameParameterError(){
        System.out.println("이름이 잘못 입력되었습니다.");
    }
}
