package twicetry;

import java.io.*;
import java.util.ArrayList;

public class PlayerMessage {
    ArrayList<PlayerMessage> playerlist=new ArrayList<>();
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getWontimes() {
        return wontimes;
    }

    public void setWontimes(int wontimes) {
        this.wontimes = wontimes;
    }

    public ArrayList<PlayerMessage> getPlayerlist() {
        return playerlist;
    }

    public void setPlayerlist(ArrayList<PlayerMessage> playerlist) {
        this.playerlist = playerlist;
    }

    private String passport;
    private int times;
    private int wontimes;

    public PlayerMessage(String id, String p, int times, int won){
        this.id=id;
        this.passport=p;
        this.times=times;
        this.wontimes=won;
    }

    public PlayerMessage(String id, String p){
        this.id=id;
        this.passport=p;

    }

    public void  addPlayer(PlayerMessage p){
        playerlist.add(p);
    }

    public String toBeString(PlayerMessage pm){
        return pm.getId()+" "+pm.getPassport()+" "+pm.getTimes()+" "+pm.getWontimes();
    }

    public void savelist(ArrayList<PlayerMessage> pm) throws IOException {
        File file = new File("C:\\Users\\Evan玖\\Desktop\\java\\A2\\Tic-tac-toe-master\\src(second)1119\\src(second)\\src\\twicetry\\list.txt");
        DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
        for(PlayerMessage p:pm){
            os.writeBytes(p.toBeString(p)+"\n");
        }
    }
    public void saveSGlist(ArrayList<PlayerMessage> pm) throws IOException {
        File file = new File("C:\\Users\\Evan玖\\Desktop\\java\\A2\\Tic-tac-toe-master\\src(second)1119\\src(second)\\src\\twicetry\\sameGamePlayer.txt");
        DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
        for(PlayerMessage p:pm){
            os.writeBytes(p.toBeString(p)+"\n");
        }
    }

    public void zeroSGlist(ArrayList<PlayerMessage> pm) throws IOException {
        File file = new File("C:\\Users\\Evan玖\\Desktop\\java\\A2\\Tic-tac-toe-master\\src(second)1119\\src(second)\\src\\twicetry\\sameGamePlayer.txt");
        DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
        os.writeBytes("");

    }


}











