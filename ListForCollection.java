package twicetry;

import javafx.scene.image.ImageView;

/**
 * 备忘录类
 */
public class ListForCollection
{
    private ImageView circleChess;
    private int row, col;

    public ListForCollection(ImageView circleChess, int row, int col)
    {
        this.circleChess = circleChess;
        this.row = row;
        this.col = col;
    }

}
