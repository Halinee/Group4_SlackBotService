package ac.knu.service.Exception;

public class FriendAddGenderParameterError extends Exception {
    public FriendAddGenderParameterError(){
        System.out.println("성별이 잘못 입력되었습니다.");
    }
}
