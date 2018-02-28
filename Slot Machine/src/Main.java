import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static javafx.geometry.HPos.CENTER;

public class Main extends Application {

    //declare buttons in the main interface
    private static Button addCoinbtn; //button to add a coin to the credits
    private static Button betOnebtn;  //button to bet a single credit
    private static Button betMaxbtn;  //button to bet a maximum number of credits
    private static Button resetbtn;   //button to return the bet amount back to the credits
    private static Button spinbtn;    //button to spin the three reels
    private static Button statbtn;    //button to display the player's gaming statistics

    //declare labels in the main interface
     static Label wonLostlbl ;   //label to display whether the player won or lost the game
     static Label creditArealbl; //label to display "Credit Area"
     static Label Credit ;       //label to store player's credit
     static Label Bet ;          //label to store the bet
     static Label Result;

    private static Label reelOne ;   //label to display the first image reel
    private static Label reelTwo ;   //label to display the second image reel
    private static Label reelThree;  //label to display the third image reel

     static Label creditlbl ; //label to display the player's current credits
     static Label betlbl ;    //label to display the bet amount player made

    //declare images
    private static Image imageOne;   //stores first reel images
    private static Image imageTwo;   //stores second reel images
    private static Image imageThree; //stores three reel images

    //declare imageviews
     static ImageView imageViewOne;
     static ImageView imageViewTwo;
     static ImageView imageViewThree;

    //initializing variables to calculate the results
    static int credit = 10;     //player's credits
    static int bet = 0;         //bet amount
    static int count = 0;       //no of spinning times
    static int wonCredits = 0;  //if won, amount of credits won
    static int lostCredits = 0; //if lost, amount of credits lost
    static int wonCount = 0;    //no of games won
    static int lostCount = 0;   //no of games lost
    static int newBet;          //new bet amount

    //initializing boolean values
    private static boolean isSpinning = false;//to check whether the reels are spinning or not
    private static boolean first = true;      //to check whether the threads are resumed
    public static boolean isSpin=true;        //
    static boolean reelOneClicked = false;    //to check whether the reel 1 is clicked
    static boolean reelTwoClicked = false;    //to check whether the reel 2 is clicked
    static boolean reelThreeClicked = false;  //to check whether the reel 3 is clicked
    static boolean isBetMax=false;            //to check if the bet max button is clicked more than once

    //declare thread to spin the reels
    static Thread t1 ; //thread to spin the reel1
    static Thread t2 ; //thread to spin the reel2
    static Thread t3 ; //thread to spin the reel3

    //intialize arraylists to store the no of games won and lost
    public static ArrayList<Integer> noOfWins = new ArrayList<>();  //arraylist to store the won no of games
    public static ArrayList<Integer> noOfLoses = new ArrayList<>(); //arraylist to store the won no of games

    //main method
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage)throws Exception{

        //Creating a scene object
        Scene scene = new Scene(addGridPane(), 950, 500);
        scene.getStylesheets().add("CSS/slot.css");
        //Setting title to the Stage
        stage.setTitle("Slot Machine");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

    //method to create the main interface
    public GridPane addGridPane() throws FileNotFoundException {

        //create the buttons
        addCoinbtn=new Button("Add Coin");      //set text to display as " Add Coin" on the button
        creditArealbl=new Label("Credit Area"); //set text to display as " Credit Area" on the button
        betOnebtn=new Button("Bet One");        //set text to display as " Bet One" on the button
        betMaxbtn=new Button("Bet Max");        //set text to display as " Bet Max" on the button
        resetbtn=new Button("Reset");           //set text to display as " Reset" on the button
        spinbtn=new Button("Spin");             //set text to display as " Spin" on the button
        statbtn=new Button("Statistics");       //set text to display as " Statistics" on the button

        //set a minimum width to the buttons
        addCoinbtn.setMinWidth(100);
        creditArealbl.setMinWidth(100);
        betOnebtn.setMinWidth(100);
        betMaxbtn.setMinWidth(100);
        resetbtn.setMinWidth(100);
        spinbtn.setMinWidth(100);
        statbtn.setMinWidth(100);

        //create the labels
        Credit = new Label("Credit Area : "); //set the label text as " Credit Area" to display on the interface
        Bet = new Label("Bet Area : ");       //set the label text as " Bet Area" to display on the interface
        Result=new Label("Result Area : ");   //set the label text as " Result Area" to display on the interface

        wonLostlbl = new Label(); //will display the game result(won/lost)
        reelOne = new Label();    //will hold the first set of images to spin
        reelTwo = new Label();    //will hold the second set of images to spin
        reelThree = new Label();  //will hold the third set of images to spin
        creditlbl = new Label(String.valueOf(credit));  //will display the player's current credit
        betlbl = new Label();     //will display the bet amount

        //set a minimum width to the labels
        Credit.setMinWidth(100);
        Bet.setMinWidth(100);
        wonLostlbl.setMinWidth(100);
        reelOne.setMinWidth(100);
        reelTwo.setMinWidth(100);
        reelThree.setMinWidth(100);
        creditlbl.setMinWidth(100);
        betlbl.setMinWidth(100);
        Result.setMinWidth(100);

        //create the images
        imageOne= new Image("Images\\bell.png");    //initial image to display on the first reel
        imageTwo= new Image("Images\\cherry.png");  //initial image to display on the second reel
        imageThree= new Image("Images\\lemon.png"); //initial image to display on the third reel

        //set each image in an imageview
        imageViewOne=new ImageView();
        imageViewOne.setImage(imageOne);

        imageViewTwo=new ImageView();
        imageViewTwo.setImage(imageTwo);

        imageViewThree=new ImageView();
        imageViewThree.setImage(imageThree);

        //resize the imageview
        imageViewOne.setFitHeight(150);
        imageViewOne.setPreserveRatio(true);

        imageViewTwo.setFitHeight(150);
        imageViewTwo.setPreserveRatio(true);

        imageViewThree.setFitHeight(150);
        imageViewThree.setPreserveRatio(true);

        //set the imageview in a label
        reelOne.setGraphic(imageViewOne);
        reelTwo.setGraphic(imageViewTwo);
        reelThree.setGraphic(imageViewThree);

        reelOne.setId("r1");
        reelTwo.setId("r2");
        reelThree.setId("r3");

        //initialize the thread
        t1= new Thread(new Spin(imageViewOne));
        t2= new Thread(new Spin(imageViewTwo));
        t3= new Thread(new Spin(imageViewThree));


        //create a grid pane to hold all the buttons,labels and images
        GridPane grid = new GridPane();
        grid.setHgap(10); //set the width of the horizontal gaps between columns to 10
        grid.setVgap(10); //set the height of the vertical gaps between rows to 10
        //grid.gridLinesVisibleProperty();

        //http://wallpaper.pickywallpapers.com/samsung-galaxy-tab/cookie-monster.jpg


        //add the buttons to the grid pane
        grid.add(addCoinbtn,1,4);
        grid.add(betOnebtn,1,6);
        grid.add(betMaxbtn,1,12);
        grid.add(resetbtn,8,2);
        grid.add(spinbtn,1,7);
        grid.add(statbtn,1,10);


        //add the labels to the grid pane
        grid.add(reelOne,3,7);
        grid.add(reelTwo,5,7);
        grid.add(reelThree,8,7);
        grid.add(Result,7,16);
        grid.add(wonLostlbl,8,16);
        grid.add(Credit,1,2);
        grid.add(creditlbl,2,2);
        grid.add(Bet,4,2);
        grid.add(betlbl,5,2);


        //handle button events
        //spin button action
        spinbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isSpin=true;
                spin(); //call method named 'spin'
            }
        });

        //add coin button action
        addCoinbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                btnAddCoinClicked(); //call method named 'btnAddCoinClicked'
            }
        });

        //bet one button action
        betOnebtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                btnBetOneClicked(); //call method named 'btnBetOneClicked'
            }
        });

        //bet max button action
        betMaxbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                btnBetMaxClicked(); //call method named 'btnBetMaxClicked'
            }
        });

        //reel one label action
        reelOne.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t1.suspend();
                reelOneClicked =true;
                Symbol.reelClicked(); //call method named 'reelOneClicked)' in the Symbol class
            }
        });

        reelTwo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t2.suspend();
                reelTwoClicked=true;
                Symbol.reelClicked(); //call method named 'reelOneClicked)' in the Symbol class
            }
        });

        reelThree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t3.suspend();
                reelThreeClicked=true;
                Symbol.reelClicked(); //call method named 'reelOneClicked)' in the Symbol class
            }
        });

       statbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                StatisticGuI.statPiechart(); //call method named 'pieChart' in the StatisticGuI Class
            }
        });

        resetbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                btnResetClicked(); //call method named 'btnResetClicked' in the StatisticGuI Class
            }
        });

        return grid;
    }


    //method to handle the reset button action
    public void btnResetClicked(){
        credit =10;    //set credits to 10
        bet = 0;       //set bet to 0
        wonCount = 0;  //set won count to 0
        lostCount = 0; //set lost count to 0
        count = 0;     //set count to 0
        creditlbl.setText(Integer.toString(credit)); //display the credit after resetting
        betlbl.setText(Integer.toString(bet)); //display the bet after reseting
    }

    //method to handle the add coin button action
    public void btnAddCoinClicked(){
        credit+=1; //add one to the current credits/increment credits by one
        creditlbl.setText(Integer.toString(credit)); //display the new credits
    }

    //method to handle the spin button action
    public void spin() {

        /*check if the bet made by the player is greater than zero
        and if the reel is spinning and
        credits are available in the player's account*/
        if (bet > 0 && !isSpinning && first) {
            wonLostlbl.setText("SPINNING"); //display the current status of the game while spinning as "SPINNING"
            t1.start(); //start the thread t1
            t2.start();
            t3.start();
            first = false; //set the boolean value first to false
        }

        //if the credits and bet both are greater than zero
        else if (!first && bet > 0) {
            wonLostlbl.setText("SPINNING"); //display the current status of the game while spinning as "SPINNING"
            t1.resume();
            t2.resume();
            t3.resume();

            }

            //if the player didn't place a bet display an alert
            else {
                Alert credAlert = new Alert(Alert.AlertType.WARNING); //create an Alert to display a warning message to the player
                credAlert.setHeaderText("OOPS!!!"); //message heading
                credAlert.setContentText("Please Place a Bet"); //message to display
                credAlert.show(); //display the warning
            }

            //no of times spun the reel
            count++;
        }

    //method to handle the bet one button action
    public void btnBetOneClicked(){

        //if substract one from credits and still it's greater than or equal to zero
        if (credit - 1 >= 0){
            credit-=1; //then deduct one from credits
            bet+=1;    //add that one to the bet since player is betting 1 credit to play
            creditlbl.setText(Integer.toString(credit)); //display the new credit
            betlbl.setText(Integer.toString(bet)); //display the new bet amount

        }
        //if credits are not in the  player's account
        else{
            Alert credAlert = new Alert(Alert.AlertType.WARNING); //create an Alert to display a warning message to the player
            credAlert.setHeaderText("OOPS!!!"); //message heading
            credAlert.setContentText("No credits available to play. Please add coins first."); //message to display
            credAlert.show(); //display the warning
        }
    }

    //method to handle the reset button action
    public void btnBetMaxClicked(){

        if(!isBetMax){
            if (credit>= 3) {
                credit -= 3;
                bet += 3;
                creditlbl.setText(Integer.toString(credit));
                betlbl.setText(Integer.toString(bet));
                isBetMax=true;

            } else {
                Alert credAlert = new Alert(Alert.AlertType.WARNING); //create an Alert to display a warning message to the player
                credAlert.setHeaderText("OOPS!!!"); //message heading
                credAlert.setContentText("No Credits Available to Play. Please Add Coins First."); //message to display
                credAlert.show(); //display the warning

            }

        } else{
            Alert betMax = new Alert(Alert.AlertType.WARNING);
            betMax.setTitle("Alert");
            betMax.setContentText("Cannot Bet Max Twice for One Game");
            betMax.show();
        }
    }



}


