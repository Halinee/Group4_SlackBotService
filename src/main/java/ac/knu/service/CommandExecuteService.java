package ac.knu.service;

import ac.knu.service.Exception.*;

import java.util.*;

public class CommandExecuteService extends Exception {

    HashMap<String, Friend> friendDataBase;

    public CommandExecuteService(HashMap<String,Friend> friendDataBase){
        this.friendDataBase = friendDataBase;
    }
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
    public String add(HashMap friendDataBase, String name, String age, String gender)
            throws FriendDataBaseSizeOver, FriendAlreayNameExist, FriendAddNameParameterError, FriendAddGenderParameterError, FriendAddAgeParameterError {

        if(friendDataBase.size() > 10) {
            throw new FriendDataBaseSizeOver();
        } else if (friendDataBase.containsKey(name)) {
            throw new FriendAlreayNameExist();
        } else if(!(name.contains("^[a-zA-Z]*$"))
                || name.contains(("^[가-힣]*$"))) {
            throw new FriendAddNameParameterError();
        } else if(!(gender.equals(Gender.M) || gender.equals(Gender.F))){
            throw new FriendAddGenderParameterError();
        } else if((Integer.parseInt(age) < 0 || Integer.parseInt(age) > 200)
                || (age.contains("^[a-zA-Z]*$")) || (age.contains("^[가-힣]*$"))){
            throw new FriendAddAgeParameterError();
        }

        int newAge = Integer.parseInt(age);
        Gender newGender = Gender.valueOf(gender);
        Friend newFriend = new Friend(name,newAge,newGender);

        friendDataBase.put(newFriend.getName(), newFriend);
        return successMessage(name,"add");
    }

    public String list(HashMap friendDataBase) throws FriendDataBaseEmptyError {
        if(friendDataBase.isEmpty()){
            throw new FriendDataBaseEmptyError();
        }

        ArrayList<String> friends = new ArrayList<String>(friendDataBase.keySet());
        Collections.sort(friends, new Comparator<String>() {
            public int compare(String prev, String next) {
                return prev.compareTo(next);
            }
        });
        Iterator<String> iterator = friends.iterator();
        StringBuilder friendList = new StringBuilder();
        friendList.append("친구 목록\n");
        while(iterator.hasNext()){
            String key = iterator.next();
            Friend friend = (Friend) friendDataBase.get(key);

            friendList.append("name : " + friend.getName() + "age : " + friend.getAge() +
                    "sex : " + friend.getGender()+"\n");
        }
        return friendList.toString();
    }
    public String time(){
        return "현재 시간 : " + new Date();
    }

}
