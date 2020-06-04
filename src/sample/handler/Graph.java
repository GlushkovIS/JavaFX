package sample.handler;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Graph {

    private final XYChart.Series series = new XYChart.Series<>();

    /**
     * Построить график изменения веса
     */
    public void buildWeightGraph(LineChart<?, ?> chart, String period, String login) throws SQLException, ClassNotFoundException {
        series.setName("Изменение веса за " + period);
        DatabaseHandler db = new DatabaseHandler();
        ResultSet resultSet = db.getUserWeightForPeriod(login, period);

        while (resultSet.next()) {
            double weight = Double.parseDouble(resultSet.getString(Const.WEIGHT_WEIGHT));
            series.getData().add(new XYChart.Data<>(resultSet.getString(Const.DATE_WEIGHT), weight));
        }

        chart.getData().addAll(series);
    }

}
