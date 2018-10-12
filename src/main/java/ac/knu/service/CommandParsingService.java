package ac.knu.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandParsingService {
    private List<String> commandList = new ArrayList<>();
    public void CommandParsingService(){
        commandList.add("add");
        commandList.add("time");
    }
    public String parseCommand(String command) {
        String result = "";
        int commandIndex = searchCommand(command);
        if(commandIndex < 0 ){
            result += "Sorry i understand only ";
            for(int i =0; i< commandList.size(); i++){
                result += commandList.get(i) + ",";
            }
            result = result.substring(0,result.lastIndexOf(","));
        }
        else{
            switch (command){
                case "time": result = new java.util.Date().toString();
            }
        }
        return result;
    }

    private int searchCommand(String command) {
        for(int i = 0; i< commandList.size(); i++){
            if(commandList.get(i).compareTo(command) == 0){
                return i;
            }
        }
        return -1;
    }
}