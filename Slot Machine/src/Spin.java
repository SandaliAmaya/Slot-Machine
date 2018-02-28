
import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
public class Spin implements Runnable {

    ImageView spin;
    ArrayList<Symbol> reel;

    public Spin(ImageView spin){
        this.spin=spin;
        reel = Reel.spin();

    }

    @Override
    public void run() {

        int count = 0;


        while(true){
            Symbol sy = reel.get(count);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    spin.setImage(sy.getImage());

                }
            });

            if(count<5){
                count++;
            }else{
                count=0;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}