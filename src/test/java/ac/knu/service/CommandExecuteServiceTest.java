package ac.knu.service;

import ac.knu.service.Exception.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CommandExecuteServiceTest {

    private CommandExecuteService commandExecuteService;
    private HashMap<String, Friend> testDataBase;

    @Before
    public void setUp() {
        testDataBase = new HashMap<String,Friend>();
        commandExecuteService = new CommandExecuteService(testDataBase);
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

    @Test
    public void The_list_is_full_to_execute_add_command() throws FriendAddGenderParameterError, FriendDataBaseSizeOver, FriendAddAgeParameterError, FriendAlreayNameExist, FriendAddNameParameterError {

        testDataBase.put("김",new Friend("김",25,Gender.M));
        testDataBase.put("이",new Friend("이",25,Gender.M));
        testDataBase.put("박",new Friend("박",25,Gender.M));
        testDataBase.put("손",new Friend("손",25,Gender.M));
        testDataBase.put("갈",new Friend("갈",25,Gender.M));
        testDataBase.put("곽",new Friend("곽",25,Gender.M));
        testDataBase.put("추",new Friend("추",25,Gender.M));
        testDataBase.put("차",new Friend("차",25,Gender.M));
        testDataBase.put("류",new Friend("류",25,Gender.M));
        testDataBase.put("홍",new Friend("홍",25,Gender.M));


        String addTest = commandExecuteService.add(testDataBase,"조","24","M");
        assertTrue(addTest.contains("친구 목록이 꽉 찼습니다."));
    }

    @Test
    public void Name_already_registered_to_execute_add_command() throws FriendAddGenderParameterError, FriendDataBaseSizeOver, FriendAddAgeParameterError, FriendAlreayNameExist, FriendAddNameParameterError {
        testDataBase.put("이승환",new Friend("이승환",25,Gender.M));

        String addTest = commandExecuteService.add(testDataBase,"이승환","24","M");
        assertTrue(addTest.contains("이름이 이미 리스트에 존재합니다."));
    }

    @Test
    public void Enter_the_wrong_name_to_execute_add_command() throws FriendAddGenderParameterError, FriendDataBaseSizeOver, FriendAddAgeParameterError, FriendAlreayNameExist, FriendAddNameParameterError {
        String addTest = commandExecuteService.add(testDataBase,"%%","25","M");
        assertTrue(addTest.contains("이름이 잘못 입력되었습니다."));
    }

    @Test
    public void Enter_the_wrong_age_to_execute_add_command() throws FriendAddGenderParameterError, FriendDataBaseSizeOver, FriendAddAgeParameterError, FriendAlreayNameExist, FriendAddNameParameterError {
        String addTest = commandExecuteService.add(testDataBase,"이승환","이십","M");
        assertTrue(addTest.contains("나이가 잘못 입력되었습니다."));
    }

    @Test
    public void Enter_the_wrong_gender_to_execute_add_command() throws FriendAddGenderParameterError, FriendDataBaseSizeOver, FriendAddAgeParameterError, FriendAlreayNameExist, FriendAddNameParameterError {
        String addTest = commandExecuteService.add(testDataBase,"이승환","25","성별테스트");
        assertTrue(addTest.contains("성별이 잘못 입력되었습니다."));
    }

    @Test
    public void Test_add_command() throws FriendAddGenderParameterError, FriendDataBaseSizeOver, FriendAddAgeParameterError, FriendAlreayNameExist, FriendAddNameParameterError {
        String addTest = commandExecuteService.add(testDataBase,"이승환","24","M");
        assertTrue(addTest.contains("추가되었습니다."));
    }

    @Test
    public void List_is_empty_when_execute_list_command() throws FriendDataBaseEmptyError {
        String addTest = commandExecuteService.list(testDataBase);
        assertTrue(addTest.contains("친구 목록이 비어있습니다."));
    }

    @Test
    public void Test_list_command() throws FriendDataBaseEmptyError {
        testDataBase.put("이승환",new Friend("이승환",24,Gender.M));
        testDataBase.put("조성준",new Friend("조성준",24,Gender.M));
        String addTest = commandExecuteService.list(testDataBase);
        assertTrue(addTest.contains("친구 목록"));
    }


}