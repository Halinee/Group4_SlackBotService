package ac.knu.service;

import ac.knu.service.Exception.NotFoundException;
import ac.knu.service.Exception.WrongNameException;
import ac.knu.service.Exception.EmptyListException;

import java.util.HashMap;

public class CommandExecuteService extends Exception {

    HashMap<String, Friend> friendDataBase = new HashMap<String, Friend>();

    private String successMessage(String name, String command) {
        if (command.equals("add")) {
            return name + "이 추가되었습니다.";
        } else {
            return name + "이 삭제되었습니다.";
        }
    }

    public String remove(HashMap friendDataBase, String name) throws WrongNameException, NotFoundException, EmptyListException {

        if (friendDataBase.isEmpty())
            throw new EmptyListException();
        else if (!(friendDataBase.containsKey(name)))
            throw new NotFoundException();
        else if (!((name.contains("^[a-zA-Z]*$")) || (name.contains("^[가-힣]*$"))))
            throw new WrongNameException();
        else {
            friendDataBase.remove(name);
            return successMessage(name, "remove");
        }
    }

    public String find(HashMap friendDataBase, String name) {
        if (friendDataBase.containsKey(name))
            return name + "을 찾았습니다.";
        else
            return name + "을 찾지 못했습니다.";
    }

}
