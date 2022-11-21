package twicetry.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//bai
public class XLine extends Chesses {
    private ImageView chess;
    private static Image image = new Image("Picture/2.png");
    @Override
    public ImageView SetPlace(GetPlace xy)
    {
        chess = new ImageView(image);
        chess.setX(xy.getX()-28);
        chess.setY(xy.getY()-28);
        return chess;
    }
}
