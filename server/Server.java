package twicetry.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import twicetry.IndexForGame;
import twicetry.ShowStringDetail;
import twicetry.clint.Player;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server extends Application implements IndexForGame {
    public static ConcurrentLinkedQueue<Socket> socketList = new ConcurrentLinkedQueue<Socket>();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        Scene scene = new Scene(new Pane(textArea), 650, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("井字棋Server");
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                Player.Serverfail1();
                Player.Serverfail2();
                System.exit(0);
            }
        });

        new Thread(() ->
        {
            ServerSocket serverSocket = null;
            try
            {
                serverSocket = new ServerSocket(9520);
                Platform.runLater(() ->
                {
                    textArea.appendText( "");
                });
                while (true)
                {
                    Platform.runLater(() ->
                    {
                        textArea.appendText("。。。。。正在等待玩家连接。。。。。\n");
                    });
                    Socket user1 = serverSocket.accept();
                    socketList.add(user1);
                    Platform.runLater(() ->
                    {
                        textArea.appendText("               玩家一连接成功！\n");
                    });
                    DataOutputStream toUser1 = new DataOutputStream(user1.getOutputStream());
                    toUser1.writeInt(people1);

                    Socket user2 = serverSocket.accept();
                    socketList.add(user2);
                    Platform.runLater(() ->
                    {
                        textArea.appendText("""
                                               玩家二连接成功！
                                __________________________________________
                                """);
                    });
                    DataOutputStream toUser2 = new DataOutputStream(user2.getOutputStream());
                    toUser2.writeInt(people2);
                    new Thread(new TicTacTeoGame(user1, user2)).start();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    class MClient implements Runnable {
        public void run() {
            try {
                while (true) {

                    Thread.sleep(2000);
                    for (Socket socketTemp : socketList) {
                      }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}