package ac.knu.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest {
    private CommandParsingService commandParsingService;

    @Before
    public void setUp(){
        commandParsingService = new CommandParsingService();
    }
    @Test
    public void bot_should_understand_list_command() {
        String command = commandParsingService.parseCommand("list");

    }
    @Test
    public void bot_should_return_current_time_when_time_command_called(){
        String result = commandParsingService.parseCommand("time");

    }
}
