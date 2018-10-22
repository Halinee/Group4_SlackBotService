package ac.knu.service.Exception;

public class FriendDataBaseSizeOver extends Exception {
    public FriendDataBaseSizeOver(){
        System.out.println("친구 목록이 꽉 찼습니다.");
    }
}
