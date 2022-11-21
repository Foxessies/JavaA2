package twicetry;

import javafx.scene.image.ImageView;
import twicetry.clint.Player;

/**
 不用交这个
 */
public class ChooseForPeople
{
    private static ChooseForPeople forPeople = new ChooseForPeople();
    //当前棋子
    private ImageView OOO;

    private int row, col;

    public ChooseForPeople(){}

    public static ChooseForPeople getInstance()
    {
        return forPeople;
    }

    public void setOOO(ImageView OOO)
    {
        this.OOO = OOO;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setCol(int col)
    {
        this.col = col;
    }
    //保存状态
    public ListForCollection save()
    {
        return new ListForCollection(OOO, row, col);
    }


}
