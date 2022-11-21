package twicetry.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//hei
public class Circle extends Chesses {
    private ImageView chess;
    private static Image image = new Image("Picture/1.png");
    @Override
    public ImageView SetPlace(GetPlace xy)
    {
        chess = new ImageView(image);
        chess.setX(xy.getX()-25);
        chess.setY(xy.getY()-25);
        return chess;
    }
}
