package twicetry.chess;

import java.util.HashMap;

public class SavingChess {
    private static SavingChess savingChess = new SavingChess();
    private static HashMap<String, Chesses> hashMap;
    private SavingChess()
    {
        hashMap = new HashMap<>();
        Chesses circle;
        Chesses xxx;
        circle = new Circle();
        xxx = new XLine();

        hashMap.put("o", circle);
        hashMap.put("x", xxx);
    }
    public static SavingChess forPlace()
    {
        return savingChess;
    } 
    public Chesses getChess(String color)
    {
        return hashMap.get(color);
    }

}

