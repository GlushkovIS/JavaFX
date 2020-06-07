package looseweight.handler;

public class User {

    public static String name;
    private static String surname;
    private static String sex;
    private static String login;
    private static String password;

    public User() {
    }

    public User(String name, String surname, String sex, String country, String login, String password) {
        User.name = name;
        User.surname = surname;
        User.sex = sex;
        User.login = login;
        User.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        User.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        User.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        User.sex = sex;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        User.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        User.password = password;
    }
}
