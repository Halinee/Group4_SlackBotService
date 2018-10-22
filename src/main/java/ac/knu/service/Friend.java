package ac.knu.service;

enum Gender {F, M};

public class Friend {

    private String name;
    private int age;
    private Gender gender;

    public Friend(String name, int age, Gender gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Gender setGender(Gender gender)
    {
        return gender;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge(int age)
    {
        return age;
    }

    public String getName(String name)
    {
        return name;
    }

    public Gender getGender(Gender gender)
    {
        return gender;
    }

}
