package sample.handler;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Graph {

    /**
     * Построить график изменения веса
     */
    public void buildWeightGraph(LineChart<?, ?> chart, int numberOfWrite){
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Изменение веса за последние " + numberOfWrite + " записей");
        series.getData().add(new XYChart.Data<>("0", 77.7f));
        series.getData().add(new XYChart.Data<>("1", 77.7f));
        series.getData().add(new XYChart.Data<>("2", 73.1f));
        series.getData().add(new XYChart.Data<>("3", 77.7f));
        series.getData().add(new XYChart.Data<>("4", 74.0f));
        series.getData().add(new XYChart.Data<>("5", 77.7f));
        series.getData().add(new XYChart.Data<>("6", 78.7f));
        series.getData().add(new XYChart.Data<>("7", 77.7f));
        series.getData().add(new XYChart.Data<>("8", 79.7f));
        chart.getData().addAll(series);

    }
}
