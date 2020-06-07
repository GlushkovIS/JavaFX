package looseweight.handler;

public class InputData {

    /**
     * Проверяет, что строка содержит только буквы (кириллица + латиница)
     *
     * @return bool
     */
    public boolean isText(String str) {
        return !str.matches("[a-zA-Zа-яА-я]*");
    }

    /**
     * Проверяет, что строка содержит только числа
     *
     * @return bool
     */
    public boolean isNumeric(String str) {
        try {
            String strWithDot = str.replace(",", ".");
            Double.parseDouble(strWithDot);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Обрабатывает вес перед записью в БД
     */
    public String stringWeightProcessing(String str) {
        str = str.replace(",", ".");
        str = str.trim();
        return str;
    }
}