import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class PlotData extends JFrame {
    private Repo repo;
    PlotData(Repo repo) {
        this.repo = repo;
        XYSeriesCollection data = new XYSeriesCollection();
        int[][] percents = makePercentsForDates();
        for(int i = 0; i < percents.length; i++) {
            XYSeries holder = new XYSeries(repo.getDates().get(i));
            for(int j = 0;  j < percents[i].length; j++) {
                holder.add(percents[i][j],countPercents(percents[i],percents[i][j]));
            }
            data.addSeries(holder);
        }
        final JFreeChart chart = ChartFactory.createScatterPlot(
                "",                  // chart title
                "Percent of Class Attended",                      // x axis label
                "Count",                      // y axis label
                data,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
        this.setSize(400,400);
        this.setLocation(400, 200);
        this.setVisible(true);
    }
    public ChartPanel getChartPanel() {
        return getChartPanel();
    }
    int countPercents(int[] percents, int key) {
        int count = 0;
        for(int i = 0; i < percents.length; i++) {
            if(percents[i] == key) {
                count++;
            }
        }
        return count;
    }
    int[][] makePercentsForDates() {
        int[][] percents = new int[repo.getNColumns().size()][repo.getData().length];

        for (int i = 0; i < repo.getNColumns().size(); i++) {
            for (int j = 0; j < repo.getNColumns().get(i).length; j++) {
                if((Double.parseDouble(repo.getNColumns().get(i)[j]) / 75) >= 1) {
                    System.out.println(100);
                    percents[i][j] = 100;
                }
                else if ((Double.parseDouble(repo.getNColumns().get(i)[j]) / 75) <= 0) {
                    System.out.println(0);
                    percents[i][j] = 0;
                }
                else {
                    System.out.println(((int)((Double.parseDouble(repo.getNColumns().get(i)[j]) / 75) * 100)) -
                            (((int) ((Double.parseDouble(repo.getNColumns().get(i)[j]) / 75) * 100)) % 5));
                    percents[i][j] = ((int)((Double.parseDouble(repo.getNColumns().get(i)[j]) / 75) * 100)) -
                            (((int) ((Double.parseDouble(repo.getNColumns().get(i)[j]) / 75) * 100)) % 5);
                }
            }
        }
        return percents;
    }
}
