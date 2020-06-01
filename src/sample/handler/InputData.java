package sample.handler;

public class InputData {

    /**
     * Проверяет, что строка содержит только буквы (кириллица + латиница)
     * @return bool
     */
    public boolean isText(String str)
    {
        return str.matches("[a-zA-Zа-яА-я]*");
    }

    /**
     * Проверяет, что строка содержит только числа
     * @return bool
     */
    public boolean isFloat(String str)
    {
        return str.matches("\\d+");
    }
}
