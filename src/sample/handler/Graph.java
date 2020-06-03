package sample.handler;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Graph {

    private XYChart.Series series = new XYChart.Series<>();

    /**
     * Построить график изменения веса
     */
    public void buildWeightGraph(LineChart<?, ?> chart, int numberOfWrite, String login) throws SQLException, ClassNotFoundException {
        series.setName("Изменение веса за последние " + numberOfWrite + " записей");
        DatabaseHandler db = new DatabaseHandler();
        ResultSet resultSet = db.getUserWeight(login, numberOfWrite);

        while (resultSet.next())
        {
            double weight = Double.parseDouble(resultSet.getString(Const.WEIGHT_WEIGHT));
            series.getData().add(new XYChart.Data<>(resultSet.getString(Const.DATE_WEIGHT), weight));
        }

        chart.getData().addAll(series);
    }

}
