package ac.knu.service;

import ac.knu.service.exception.UnprocessableCommandException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommandParsingService {
    private List<String> possibleCommand = new ArrayList<>();

    public CommandParsingService() {
        possibleCommand.add("Time");
        possibleCommand.add("Add");
        possibleCommand.add("Remove");
        possibleCommand.add("List");
        possibleCommand.add("Find");
    }

    public String parseCommand(HashMap<String, Friend> database, String commandLine) throws UnprocessableCommandException {

        CommandExcuteService excuteService = new CommandExcuteService(database);
        String[] commandSplit = commandLine.split(" ");
        String command = commandSplit[1];

        switch(command) {
            case "Time" :
                return excuteService.time();
            case "Add" :
                return excuteService.add(database, commandSplit[2], commandSplit[3], commandSplit[4]);
            case "Remove" :
                return excuteService.remove(database, commandSplit[2]);
            case "List" :
                return excuteService.list(database);
            case "Find" :
                return excuteService.find(database, commandSplit[2]);
            default :
                throw new UnprocessableCommandException();
        }
    }
}