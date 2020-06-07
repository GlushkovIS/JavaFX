package looseweight.handler;

import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private SimpleStringProperty date;
    private SimpleStringProperty weight;

    public TableData(String date, String weight){
        this.date = new SimpleStringProperty(date);
        this.weight = new SimpleStringProperty(weight);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getWeight() {
        return weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }
}
