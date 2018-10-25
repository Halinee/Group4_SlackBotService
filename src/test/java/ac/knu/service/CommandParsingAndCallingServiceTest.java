package ac.knu.service;

import ac.knu.service.Exception.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CommandParsingAndCallingServiceTest {

    private CommandParsingAndCallingService commandParsingAndCallingService;
    private HashMap<String, Friend> testDatabase;

    @Before
    public void setUp() {
        commandParsingAndCallingService = new CommandParsingAndCallingService();
        testDatabase = new HashMap<>();
    }
    @Test
    public void bot_should_understand_Add_command() throws FriendAddAgeParameterError, WrongNameException, FriendAlreayNameExist, FriendDataBaseEmptyError, EmptyListException, FriendAddNameParameterError, FriendAddGenderParameterError, NotFoundException, FriendDataBaseSizeOver, UnprocessableCommandException {
        String result = commandParsingAndCallingService.parseAndCallCommand(testDatabase, "@4jo Add 조성준 24 M");
        assertTrue(result.contains("추가되었습니다."));
    }

    @Test
    public void bot_should_understand_Remove_command() throws UnprocessableCommandException, FriendDataBaseSizeOver, FriendAddAgeParameterError, WrongNameException, FriendAlreayNameExist, FriendDataBaseEmptyError, EmptyListException, FriendAddNameParameterError, FriendAddGenderParameterError, NotFoundException {
        testDatabase.put("조성준", new Friend("조성준", 24, Gender.M));
        String result = commandParsingAndCallingService.parseAndCallCommand(testDatabase, "@4jo Remove 조성준");
        assertTrue(result.contains("삭제되었습니다."));
    }

    @Test
    public void bot_should_understand_List_command() throws UnprocessableCommandException, FriendDataBaseSizeOver, FriendAddAgeParameterError, WrongNameException, FriendAlreayNameExist, FriendDataBaseEmptyError, EmptyListException, FriendAddNameParameterError, FriendAddGenderParameterError, NotFoundException {
        testDatabase.put("조성준", new Friend("조성준", 24, Gender.M));
        String result = commandParsingAndCallingService.parseAndCallCommand(testDatabase, "@4jo List");
        assertTrue(result.contains("친구 목록"));
    }

    @Test
    public void bot_should_understand_Find_command() throws UnprocessableCommandException, FriendDataBaseSizeOver, FriendAddAgeParameterError, WrongNameException, FriendAlreayNameExist, FriendDataBaseEmptyError, EmptyListException, FriendAddNameParameterError, FriendAddGenderParameterError, NotFoundException {
        testDatabase.put("조성준", new Friend("조성준", 24, Gender.M));
        String result = commandParsingAndCallingService.parseAndCallCommand(testDatabase, "@4jo Find 조성준");
        assertTrue(result.contains("찾았습니다."));
    }

    @Test
    public void bot_should_understand_Time_command() throws UnprocessableCommandException, FriendDataBaseSizeOver, FriendAddAgeParameterError, WrongNameException, FriendAlreayNameExist, FriendDataBaseEmptyError, EmptyListException, FriendAddNameParameterError, FriendAddGenderParameterError, NotFoundException {
        String result = commandParsingAndCallingService.parseAndCallCommand(testDatabase, "@4jo Time");
        assertTrue(result.contains("현재 시간 : "));
    }
}