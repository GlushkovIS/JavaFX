package sample.handler;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Graph {

    private final DatabaseHandler db = new DatabaseHandler();

    /**
     * Построить график изменения веса
     */
    public void buildWeightGraph(LineChart<?, ?> chart, String period, String login) throws SQLException, ClassNotFoundException {
        chart.getData().clear();
        XYChart.Series series = new XYChart.Series<String, Double>();

        ResultSet resultSet = db.getUserWeightForPeriod(login, period);

        while (resultSet.next()) {
            double weight = Double.parseDouble(resultSet.getString(Const.WEIGHT_WEIGHT));
            series.getData().add(new XYChart.Data<>(resultSet.getString(Const.DATE_WEIGHT), weight));
        }
        resultSet.close();
        styleOfChart(chart);

        chart.getData().addAll(series);
    }

    private void styleOfChart(LineChart<?, ?> chart){
        chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
    }

}
