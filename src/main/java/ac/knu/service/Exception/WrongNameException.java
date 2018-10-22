package ac.knu.service.Exception;

public class WrongNameException extends Exception {

    public WrongNameException()
    {
        System.out.println("잘못된 이름을 입력하셨습니다.");
    }

}
