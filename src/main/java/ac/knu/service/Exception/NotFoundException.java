package ac.knu.service.Exception;

public class NotFoundException extends Exception {

    public NotFoundException()
    {
        System.out.println("삭제 할 이름이 없습니다.");
    }
}
