package twicetry;

import javafx.application.Application;

import java.io.*;

public class ZeroSGlist {
    public ZeroSGlist() throws IOException {
        File file = new File("C:\\Users\\Evan玖\\Desktop\\java\\A2\\Tic-tac-toe-master\\src(second)1119\\src(second)\\src\\twicetry\\sameGamePlayer.txt");
        DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
        FileWriter fw=new FileWriter(file);
     //   fw.write("oooo");
        fw.write("");
        fw.flush();
        fw.close();
//        os.write("".getBytes());
    }
}
