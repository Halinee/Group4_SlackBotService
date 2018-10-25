package ac.knu.service;

import ac.knu.service.Exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommandParsingAndCallingService {
    private List<String> possibleCommand = new ArrayList<>();

    public CommandParsingAndCallingService() {
        possibleCommand.add("Time");
        possibleCommand.add("Add");
        possibleCommand.add("Remove");
        possibleCommand.add("List");
        possibleCommand.add("Find");
    }

    public String parseAndCallCommand(HashMap<String, Friend> database, String commandLine) throws UnprocessableCommandException, FriendDataBaseEmptyError, NotFoundException, EmptyListException, WrongNameException, FriendAddGenderParameterError, FriendAddAgeParameterError, FriendDataBaseSizeOver, FriendAlreayNameExist, FriendAddNameParameterError {

        CommandExecuteService executeService = new CommandExecuteService(database);
        String[] commandSplit = commandLine.split(" ");
        String command = commandSplit[1];

        switch(command) {
            case "Time" :
                return executeService.time();
            case "Add" :
                return executeService.add(database, commandSplit[2], commandSplit[3], commandSplit[4]);
            case "Remove" :
                return executeService.remove(database, commandSplit[2]);
            case "List" :
                return executeService.list(database);
            case "Find" :
                return executeService.find(database, commandSplit[2]);
            default :
                throw new UnprocessableCommandException();
        }
    }
}