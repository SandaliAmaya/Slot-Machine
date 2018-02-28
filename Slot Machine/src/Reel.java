import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

public class Reel {

    //create an array list to add the symbols
    static ArrayList<Symbol> symbolList=new ArrayList<>();


    public static ArrayList<Symbol> spin(){

        //create objects of Symbol
        Symbol s1=new Symbol(6,new Image("Images/bell.png"));      //bell payout credit is 6
        Symbol s2=new Symbol(2,new Image("Images/cherry.png"));    //cherry payout credit is 2
        Symbol s3=new Symbol(3,new Image("Images/lemon.png"));     //lemon payout credit is 3
        Symbol s4=new Symbol(4,new Image("Images/plum.png"));      //plum payout credit is 4
        Symbol s5=new Symbol(7,new Image("Images/redseven.png"));  //seven payout credit is 7
        Symbol s6=new Symbol(5,new Image("Images/watermelon.png"));//watermelon payout credit is 5

        //add symbols to the arraylist by adding the objects
        symbolList.add(s1);
        symbolList.add(s2);
        symbolList.add(s3);
        symbolList.add(s4);
        symbolList.add(s5);
        symbolList.add(s6);

        //shuffle the arraylist to get random symbols/images
        Collections.shuffle(symbolList);
        return symbolList;
    }
}
