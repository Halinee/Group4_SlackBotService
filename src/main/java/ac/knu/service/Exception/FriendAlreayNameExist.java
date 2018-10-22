package ac.knu.service.Exception;

public class FriendAlreayNameExist extends Exception {
    public FriendAlreayNameExist(){
        System.out.println("이름이 이미 리스트에 존재합니다.");
    }
}
