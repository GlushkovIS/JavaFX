package looseweight.handler;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Graph {

    private final DatabaseHandler db = new DatabaseHandler();

    /**
     * Построить график изменения веса
     */
    public void buildWeightGraph(AreaChart<?, ?> chart, String period, String login) throws SQLException, ClassNotFoundException {
        chart.getData().clear();
        XYChart.Series series = new XYChart.Series<String, Double>();

        ResultSet resultSet = db.getUserWeightForPeriod(login, period);

        while (resultSet.next()) {
            double weight = Double.parseDouble(resultSet.getString(Const.WEIGHT_WEIGHT));
            series.getData().add(new XYChart.Data<>(resultSet.getString(Const.DATE_WEIGHT), weight));
        }
        styleOfChart(chart);

        chart.getData().add(series);
    }

    private void styleOfChart(AreaChart<?, ?> chart){
        chart.setLegendVisible(false);
        chart.setAnimated(false);
    }

}
