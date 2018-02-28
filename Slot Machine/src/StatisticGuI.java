import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatisticGuI {

    //create a button to save the statistics
    static Button statSavebtn;

    //method to display the pie chart
    public static void statPiechart() {

        //create a vbox
        VBox box = new VBox(30);
        box.setAlignment(Pos.CENTER);

        //create the button
        statSavebtn=new Button("Save Satistics");

        //set a minimum width to the button
        statSavebtn.setMinWidth(100);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("WON", Main.noOfWins.size()),
                new PieChart.Data("LOST", Main.noOfLoses.size())
        );

        final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("PieChart");

        //add children (pie chart and the statistic save button) to the vbox
        box.getChildren().addAll(pieChart,statSavebtn);

        //add coin button action
        statSavebtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {
                    statFiles(); //call method named 'statFile'
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Creating a scene object
        Scene scene = new Scene(box, 500, 500);

        //create a new Stage
        Stage stage = new Stage();

        //Setting title to the Stage
        stage.setTitle("Statistics");
        stage.setWidth(500);
        stage.setHeight(500);

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

    //method to handle the save statistics button
    public static void statFiles() throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd,HH_mm_ss");
        Date date = new Date(); //create a new date object
        String file = String.valueOf(dateFormat.format(date));  // The name of the file will be the current date/time.

        try {
            FileWriter fw = new FileWriter(file); //statistics will be saved in a .txt file in the current path.
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Wins   : " + Main.wonCount); //print the total wins
            pw.println("Losses : " + Main.lostCount); //print the total losses
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
