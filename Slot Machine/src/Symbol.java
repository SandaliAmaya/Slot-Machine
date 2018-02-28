
import javafx.scene.control.Alert;
import javafx.scene.image.Image;


public class Symbol implements ISymbol {

    private int imageValue; //payout value for the each symbol
    private Image img;
    String symbol;

    //constructor
    public Symbol(int imageValue, Image img) {
        this.imageValue = imageValue;
        this.img = img;

    }

    //getter and setter methods
    @Override
    public int getImageValue() {

        return imageValue;
    }

    @Override
    public void setImageValue(int imageValue) {

        this.imageValue = imageValue;
    }

    @Override
    public Image getImage() {

        return img;
    }

    @Override
    public void setImage(Image img) {

        this.img = img;
    }

    public String getSymbol() {

        return symbol;
    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;
    }



    //method to handle the reset button action
    public static void reelClicked() {
        /*check if one of the scenarios below are true after spinning then player will win the game
        01.if the image in reel 1 equals to the image in reel 2
        02.if the image in reel 2 equals to the image in reel 3
        03.if the image in reel 1 equals to the image in reel 3
        */

            //check if any of the reels are clicked
            if (Main.reelOneClicked && Main.reelTwoClicked && Main.reelThreeClicked) {
                if (Main.imageViewOne.getImage() == Main.imageViewTwo.getImage() || Main.imageViewTwo.getImage() == Main.imageViewThree.getImage() || Main.imageViewOne.getImage() == Main.imageViewThree.getImage() || Main.imageViewOne.getImage() == Main.imageViewTwo.getImage() && Main.imageViewTwo.getImage() == Main.imageViewThree.getImage()) {
                    Main.wonLostlbl.setText("You Won"); //display the game result as "Won"
                    Alert credAlert = new Alert(Alert.AlertType.INFORMATION); //create an Alert to display a message to the player
                    credAlert.setHeaderText("Congratulations!!!"); //message heading
                    credAlert.setContentText("You have won" + " " + "$"+ Main.credit); //message to display
                    credAlert.show(); //display the alert

                    //Calculate the amount of money that are won and add to the credit.
                    if (Main.imageViewOne.getImage() == Main.imageViewTwo.getImage() && Main.imageViewTwo.getImage() == Main.imageViewThree.getImage()) {
                        for (Symbol s : Reel.symbolList)
                            if (s.getImage() == Main.imageViewOne.getImage()) {
                                Main.credit += Main.bet * s.getImageValue(); //multiply the amount of bet by image value and add to the credit to get the money amount
                                Main.wonCredits = Main.bet * s.getImageValue(); //amount of money that are won
                                Main.creditlbl.setText(Integer.toString(Main.credit)); //display the new credit
                            }

                    }
                    //compare first image and the second image
                    else if (Main.imageViewOne.getImage() == Main.imageViewTwo.getImage()) {
                        for (Symbol s : Reel.symbolList)
                            if (s.getImage() == Main.imageViewOne.getImage()) {
                                Main.credit += Main.bet * s.getImageValue(); //multiply the amount of bet by image value and add to the credit to get the money amount
                                Main.creditlbl.setText(Integer.toString(Main.credit)); //display the new credit
                            }

                    }
                    //compare second image and the third image
                    else if (Main.imageViewTwo.getImage() == Main.imageViewThree.getImage()) {
                        for (Symbol s : Reel.symbolList)
                            if (s.getImage() == Main.imageViewTwo.getImage()) {
                                Main.credit += Main.bet * s.getImageValue(); //multiply the amount of bet by image value and add to the credit to get the money amount
                                Main.creditlbl.setText(Integer.toString(Main.credit)); //display the new credit
                            }

                    }
                    //compare second image and the third image
                    else if (Main.imageViewOne.getImage() == Main.imageViewThree.getImage()) {
                        for (Symbol s : Reel.symbolList)
                            if (s.getImage() == Main.imageViewOne.getImage()) {
                                Main.credit += Main.bet * s.getImageValue(); //multiply the amount of bet by image value and add to the credit to get the money amount
                                Main.creditlbl.setText(Integer.toString(Main.credit)); //display the new credit
                            }
                    }

                    Main.newBet = 0; //clear the bet after one spin
                    Main.betlbl.setText(Integer.toString(Main.newBet));
                    Main.wonCount++; //increment the won count
                    Main.noOfWins.add(Main.wonCount); //add the won count to the arraylist


                } else {
                    Main.wonLostlbl.setText("You Lost.");
                    Alert credAlert = new Alert(Alert.AlertType.INFORMATION); //create an Alert to display a message to the player
                    credAlert.setHeaderText("Sorry!!!"); //message heading
                    credAlert.setContentText("You have lost" + " " + "$" + Main.bet + " " + "Please Try Again."); //message to display
                    credAlert.show(); //display the alert

                    Main.newBet = 0;
                    Main.betlbl.setText(Integer.toString(Main.newBet));
                    Main.lostCount++; //increment the lost game count
                    Main.noOfLoses.add(Main.lostCount); //add the count to the arraylist
                    Main.lostCredits = Main.bet;

                }
                Main.reelOneClicked = false;
                Main.reelTwoClicked = false;
                Main.reelThreeClicked = false;

            }
        }

    }
