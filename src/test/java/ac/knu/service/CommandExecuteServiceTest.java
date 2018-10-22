package ac.knu.service;

import ac.knu.service.Exception.EmptyListException;
import ac.knu.service.Exception.NotFoundException;
import ac.knu.service.Exception.WrongNameException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CommandExecuteServiceTest {

    private CommandExecuteService commandExecuteService;
    private HashMap<String, Friend> testDataBase;

    @Before
    public void setUp() {
        commandExecuteService = new CommandExecuteService();
    }

    @Test
    public void The_list_is_empty_to_execute_the_remove_command() throws EmptyListException, WrongNameException, NotFoundException {
        String result = commandExecuteService.remove(testDataBase, "Jo");
        assertTrue(result.contains("비어있습니다."));
    }

    @Test
    public void The_name_is_not_found_to_execute_the_remove_command() throws NotFoundException, WrongNameException, EmptyListException {
        testDataBase.put("Jo", new Friend("Jo",24,Gender.M));
        String result = commandExecuteService.remove(testDataBase, "Lee");
        assertTrue(result.contains("없습니다"));
    }

    @Test
    public void The_name_is_wrong_to_execute_the_remove_command() throws WrongNameException, NotFoundException, EmptyListException {
        testDataBase.put("Jo", new Friend("Jo",24,Gender.M));
        String result = commandExecuteService.remove(testDataBase, "55");
        assertTrue(result.contains("입력하셨습니다."));
    }

    @Test
    public void Remove_the_name_from_the_list() throws NotFoundException, EmptyListException, WrongNameException {
        testDataBase.put("Jo", new Friend("Jo",24,Gender.M));
        String result = commandExecuteService.remove(testDataBase, "Jo");
        assertTrue(result.contains("삭제되었습니다."));
    }

    @Test
    public void Find_name_on_list() {
        testDataBase.put("Jo", new Friend("Jo",24,Gender.M));
        String result = commandExecuteService.find(testDataBase, "Jo");
        assertTrue(result.contains("찾았습니다."));
    }

    @Test
    public void The_name_not_found_on_the_list() {
        testDataBase.put("Jo", new Friend("Jo",24,Gender.M));
        String result = commandExecuteService.find(testDataBase, "Lee");
        assertTrue(result.contains("못했습니다."));
    }
}