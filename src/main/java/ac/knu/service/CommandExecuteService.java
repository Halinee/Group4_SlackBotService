package ac.knu.service;

import ac.knu.service.Exception.*;

import java.util.*;
import java.util.regex.Pattern;

public class CommandExecuteService extends Exception {

    HashMap<String, Friend> friendDataBase;
    String errorMessage = "";

    public CommandExecuteService(HashMap<String, Friend> friendDataBase) {
        this.friendDataBase = friendDataBase;
    }

    private String successMessage(String name, String command) {
        if (command.equals("add")) {
            return name + "이(가) 추가되었습니다.";
        } else {
            return name + "이(가) 삭제되었습니다.";
        }
    }

    public String remove(HashMap friendDataBase, String name) throws WrongNameException, NotFoundException, EmptyListException {
        try {
            if (friendDataBase.isEmpty())
                throw new EmptyListException();
            else if (!((Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", name))))
                throw new WrongNameException();
            else if (!(friendDataBase.containsKey(name)))
                throw new NotFoundException();
            else {
                friendDataBase.remove(name);
                return successMessage(name, "remove");
            }
        } catch (EmptyListException e) {
            return errorMessage = "리스트가 비어있습니다.";
        } catch (WrongNameException e) {
            return errorMessage = "잘못된 이름을 입력하셨습니다.";
        } catch (NotFoundException e) {
            return errorMessage = "찾으시는 이름이 없습니다.";
        }
    }

    public String find(HashMap friendDataBase, String name) {
        if (friendDataBase.containsKey(name))
            return name + "을(를) 찾았습니다.";
        else
            return name + "을(를) 찾지 못했습니다.";
    }

    public String add(HashMap friendDataBase, String name, String age, String gender)
            throws FriendDataBaseSizeOver, FriendAlreayNameExist, FriendAddNameParameterError, FriendAddGenderParameterError, FriendAddAgeParameterError {
        try {
            if (friendDataBase.size() >= 10) {
                throw new FriendDataBaseSizeOver();
            } else if (friendDataBase.containsKey(name)) {
                throw new FriendAlreayNameExist();
            } else if (!((Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", name)))) {
                throw new FriendAddNameParameterError();
            } else if (((Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", age)) || (Integer.parseInt((age)) < 0 || Integer.parseInt(age) > 200))) {
                throw new FriendAddAgeParameterError();
            } else if (!(gender.equals(Gender.M + "") || gender.equals(Gender.F + ""))) {
                throw new FriendAddGenderParameterError();
            }

            int newAge = Integer.parseInt(age);
            Gender newGender = Gender.valueOf(gender);
            Friend newFriend = new Friend(name, newAge, newGender);

            friendDataBase.put(newFriend.getName(), newFriend);
            return successMessage(name, "add");
        } catch (FriendDataBaseSizeOver e) {
            return errorMessage = "친구 목록이 꽉 찼습니다.";
        } catch (FriendAlreayNameExist e) {
            return errorMessage = "친구가 이미 있습니다.";
        } catch (FriendAddNameParameterError e) {
            return errorMessage = "이름을 잘못 입력하셨습니다.\n이름 형식 : 한글 or 영어";
        } catch (FriendAddAgeParameterError e) {
            return errorMessage = "나이를 잘못 입력하셨습니다.\n나이 형식 : 0 ~ 200";
        } catch (FriendAddGenderParameterError e) {
            return errorMessage = "성별을 잘못 입력하셨습니다.\n성별 형식 : M(남자) or F(여자)";
        }
    }

    public String list(HashMap friendDataBase) throws FriendDataBaseEmptyError {
        try {
            if (friendDataBase.isEmpty()) {
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
            while (iterator.hasNext()) {
                String key = iterator.next();
                Friend friend = (Friend) friendDataBase.get(key);

                friendList.append("name : " + friend.getName() + " age : " + friend.getAge() +
                        " sex : " + friend.getGender() + "\n");
            }
            return friendList.toString();
        } catch (FriendDataBaseEmptyError e) {
            return errorMessage = "친구리스트가 비어있습니다.";
        }
    }

    public String time() {
        return "현재 시간 : " + new Date();
    }

}
