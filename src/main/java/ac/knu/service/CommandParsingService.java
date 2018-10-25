package ac.knu.service;

import ac.knu.service.Exception.*;
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

    public String parseCommand(HashMap<String, Friend> database, String commandLine) throws UnprocessableCommandException, FriendDataBaseEmptyError, NotFoundException, EmptyListException, WrongNameException, FriendAddGenderParameterError, FriendAddAgeParameterError, FriendDataBaseSizeOver, FriendAlreayNameExist, FriendAddNameParameterError {

        CommandExecuteService excuteService = new CommandExecuteService(database);
        String[] commandSplit = commandLine.split(" ");
        String command = commandSplit[0];

        switch(command) {
            case "Time" :
                return excuteService.time();
            case "Add" :
                return excuteService.add(database, commandSplit[1], commandSplit[2], commandSplit[3]);
            case "Remove" :
                return excuteService.remove(database, commandSplit[1]);
            case "List" :
                return excuteService.list(database);
            case "Find" :
                return excuteService.find(database, commandSplit[1]);
            default :
                throw new UnprocessableCommandException();
        }
    }
}