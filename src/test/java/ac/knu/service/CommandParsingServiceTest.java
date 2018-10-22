package ac.knu.service;

import ac.knu.service.exception.UnprocessableCommandException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CommandParsingServiceTest {

    private CommandParsingService commandParsingService;
    private HashMap<String, Friend> testDatabase;

    @Before
    public void setUp() {
        commandParsingService = new CommandParsingService();
        testDatabase = new HashMap<String, Friend>();
    }
    @Test
    public void bot_should_understand_Add_command() throws UnprocessableCommandException {
        String result = commandParsingService.parseCommand(testDatabase, "Add");
        assertTrue(result.contains("추가되었습니다."));
    }

    @Test
    public void bot_should_understand_Remove_command() throws UnprocessableCommandException {
        String result = commandParsingService.parseCommand(testDatabase, "Remove");
        assertTrue(result.contains("삭제되었습니다."));
    }

    @Test
    public void bot_should_understand_List_command() throws UnprocessableCommandException {
        String result = commandParsingService.parseCommand(testDatabase, "List");
        assertTrue(result.contains("친구 목록"));
    }

    @Test
    public void bot_should_understand_Find_command() throws UnprocessableCommandException {
        String result = commandParsingService.parseCommand(testDatabase, "Find");
        assertTrue(result.contains("찾았습니다."));
    }

    @Test
    public void bot_should_understand_Time_command() throws UnprocessableCommandException {
        String result = commandParsingService.parseCommand(testDatabase, "Time");
        assertTrue(result.contains("현재 시간 : "));
    }
}