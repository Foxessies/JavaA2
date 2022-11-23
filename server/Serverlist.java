package twicetry.server;

import twicetry.OtherConnectionTo;
import twicetry.ShowStringDetail;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Serverlist
{
    private  final static Serverlist connect  = new Serverlist();
    private static Socket socket;
    private Serverlist() {}

    public static void connectionAgain() {
        try {
            //建立连接（本地连接）
            socket = new Socket("127.0.0.1", 9520);
        }
        catch (Exception e){
            new ShowStringDetail().display("连接服务器失败！");
            System.exit(0);
            receive();
        }
    }

    public static Serverlist getPlace()
    {
        return connect;
    }

    public DataInputStream getDataInputStream() throws IOException
    {
        return new DataInputStream(socket.getInputStream());
    }
    public DataOutputStream getDataOutputStream() throws IOException
    {
        return new DataOutputStream(socket.getOutputStream());
    }
    public static Socket getSocket()
    {
        return socket;
    }
    private static void receive()
    {
        OtherConnectionTo.close(socket);
    }
}
