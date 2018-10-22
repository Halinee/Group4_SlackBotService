package ac.knu.service.Exception;

public class FriendDataBaseEmptyError extends Exception {
    public FriendDataBaseEmptyError(){
        System.out.println("친구 목록이 비어있습니다.");
    }
}
