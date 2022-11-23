package twicetry.clint;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import twicetry.*;
import twicetry.chess.GetPlace;
import twicetry.chess.SavingChess;
import java.io.*;
import twicetry.server.Serverlist;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Player extends Application implements IndexForGame {
  private static WholePaneGame linePane = WholePaneGame.decideplace();
  ArrayList<PlayerMessage> playerlist = new ArrayList<>();
  private Button buttonstart = new Button("开始游戏");
  private Button buttontuichu = new Button("退出游戏");
  private Button btfanhui = new Button("退出");
  private ImageView playerq = new ImageView();
  private Label tchess = new Label("");
  private static Circle signForpane = new Circle(3);
  private OtherList mc = new OtherList();
  public static double x;
  public static double y;
  private String str = null;
  private boolean stop = true;
  private int data;
  private static int[][] chess = new int[3][3];
  private ImageView[][] circles = new ImageView[3][3];
  private int player = 0;
  private int myChess = 0;
  private int otherChess = 0;
  private boolean myTurn = false;
  private boolean waiting = true;
  private int rowNow;
  private int colNow;
  private boolean GameContinue = true;
  private DataInputStream InputStream;
  private DataOutputStream OutputStream;
  private Button login = new Button("登录");
  private Button register = new Button("注册");
  Button determine = new Button("确定");
  TextArea textArea = new TextArea();
  TextField tf = new TextField();
  Pane pane = new Pane();
  String name;
  String passp;
  TextField id = new TextField ();
  PasswordField passport = new PasswordField();
  Button b1 = new Button("注册");
  Button b2 = new Button("登录");

  public static void main(String[] args) {
        launch(args);
    }

  public static void Serverfail1() {
        new ShowStringDetail().display("\n服务器中断,对局结束\n");
  }

  public static void Serverfail2() {
        new InputWindow().display("\n服务器中断,对局结束\n");
    }


    @Override
  public void start(Stage primaryStage) {
    tchess.setFont(new Font("黑体", 27));
    tchess.setTextFill(Color.BLACK);
    Label label = new Label("\n 井字棋\n   ");
    label.setFont(Font.font("黑体", 40));
    buttonstart.setFont(new Font("黑体", 40));
    buttonstart.setBackground(Background.EMPTY);
    buttontuichu.setFont(new Font("黑体", 40));
    buttontuichu.setBackground(Background.EMPTY);
    btfanhui.setFont(new Font("黑体", 20));
    btfanhui.setBackground(Background.EMPTY);
    login.setFont(new Font("黑体", 40));
    login.setBackground(Background.EMPTY);
    register.setFont(new Font("黑体", 40));
    register.setBackground(Background.EMPTY);
    determine.setFont(new Font("黑体", 40));
    determine.setBackground(Background.EMPTY);
    signForpane.setFill(Color.WHITE);
    VBox vBox0 = new VBox(15);
    vBox0.getChildren().add(label);
    vBox0.getChildren().add(new HBox(new Label("      "), register));
    vBox0.getChildren().add(new HBox(new Label("      "), login));
    VBox vBox1 = new VBox(15);
    vBox1.getChildren().add(label);
    vBox1.getChildren().add(new HBox(new Label("      "), buttonstart));
    vBox1.getChildren().add(new HBox(new Label("      "), buttontuichu));
    Scene scene = new Scene(new Group(), 300, 120);

    GridPane grid = new GridPane();
    grid.setVgap(4);
    grid.setHgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("ID:"), 0, 0);
    grid.add(id, 1, 0);
    grid.add(new Label("Passport:"), 0, 3);
    grid.add(passport, 1, 3);
    grid.add(b1, 0, 4);
    grid.add(b2, 1, 4);

    Group root = (Group) scene.getRoot();
    root.getChildren().add(grid);
    primaryStage.setScene(scene);
    primaryStage.show();

    textArea.setPrefColumnCount(12);
    textArea.setPrefRowCount(15);
    textArea.setWrapText(true);
    textArea.setEditable(false);

    tf.setOpacity(0.8);

    b1.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            String name0 = id.getText();
            String passp0 = passport.getText();
            PlayerMessage playerMessage = new PlayerMessage(name0, passp0, 0, 0);
            try {
               playerlist = takeoutList(new File(IndexForGame.FILEPATH_LIST));
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
            for (PlayerMessage p : playerlist) {
              if (p.getId().equals(name0)) {
                    new ShowStringDetail().display("用户名已被注册");
                        System.exit(0);
                }
            }
            playerlist.add(playerMessage);
            try {
                    playerMessage.savelist(playerlist);
                    playerMessage.saveSGlist(playerlist);
            } catch (IOException e) {
                    throw new RuntimeException(e);
            }
            new ShowStringDetail().display("注册成功");

            new Thread(() -> {
                try {
                    while (true) {
                      x = primaryStage.getX();
                      y = primaryStage.getY();
                      Thread.sleep(100);
                    }
                    } catch (Exception e) {
                }
            }).start();

            Pane pane1 = new Pane();
            pane1.getChildren().add(new ImageView("Picture/Background2.jpg"));
            pane1.getChildren().add(vBox1);
            Scene scene1 = new Scene(pane1, 700, 650);
            primaryStage.setResizable(false);
            primaryStage.setTitle("井字棋");
            primaryStage.setScene(scene1);
            primaryStage.show();
            HBox hBox = new HBox(10);
            hBox.getChildren().add(tf);

            GridPane gridPane = new GridPane();
            gridPane.add(btfanhui, 0, 94);
            gridPane.setVgap(5);
            gridPane.setHgap(5);
            VBox vBox = new VBox(20);
            vBox.getChildren().addAll(gridPane);
            vBox.setPadding(new Insets(90, 0, 0, 0));

            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(new Label("  "));
            borderPane.setLeft(linePane);
            borderPane.setRight(vBox);

            pane.getChildren().add(new ImageView("Picture/Background1.jpg"));
            pane.getChildren().add(borderPane);

            buttonstart.setOnAction(e -> {
              Serverlist.connectionAgain();
              initialize();
              Connection();
              scene1.setRoot(pane);

            });

            buttontuichu.setOnAction(event -> {
                    System.exit(0);
            });

            btfanhui.setOnAction(e -> {
                try {
                        new ZeroSGlist();
                } catch (IOException ex) {
                        throw new RuntimeException(ex);
                }

              GameContinue = false;
              waiting = false;
              stop = false;
              release();
              scene1.setRoot(pane1);
            });

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        System.exit(0);
                    }
                });
          }
    });

    b2.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                String name0 = id.getText();
                String passp0 = passport.getText();
                try {
                    playerlist = takeoutList(new File(IndexForGame.FILEPATH_LIST));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int j = 0;
                for (PlayerMessage p : playerlist) {
                    if (p.getId().equals(name0) && p.getPassport().equals(passp0)) {
                        new ShowStringDetail().display("登陆成功");
                        j = 1;
                        break;
                    }
                }
                if (j == 0) {
                    new ShowStringDetail().display("用户名或密码错误");
                    System.exit(0);
                }
                new Thread(() -> {
                    try {
                        while (true) {
                          x = primaryStage.getX();
                          y = primaryStage.getY();
                          Thread.sleep(100);
                        }
                    } catch (Exception e) {}
                }).start();

            Pane pane1 = new Pane();
            pane1.getChildren().add(new ImageView("Picture/Background2.jpg"));
            pane1.getChildren().add(vBox1);
            Scene scene1 = new Scene(pane1, 700, 650);
            primaryStage.setResizable(false);
            primaryStage.setTitle("井字棋");
            primaryStage.setScene(scene1);
            primaryStage.show();
            HBox hBox = new HBox(10);
            hBox.getChildren().add(tf);

            GridPane gridPane = new GridPane();
            gridPane.add(btfanhui, 0, 94);
            gridPane.setVgap(5);
            gridPane.setHgap(5);

            VBox vBox = new VBox(20);
            vBox.getChildren().addAll(gridPane);
            vBox.setPadding(new Insets(90, 0, 0, 0));

            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(new Label("  "));
            borderPane.setLeft(linePane);
            borderPane.setRight(vBox);

            pane.getChildren().add(new ImageView("Picture/Background1.jpg"));
            pane.getChildren().add(borderPane);

            buttonstart.setOnAction(e -> {
              Serverlist.connectionAgain();
              initialize();
              Connection();
              scene1.setRoot(pane);
            });

            buttontuichu.setOnAction(event -> {
                    System.exit(0);
            });

            btfanhui.setOnAction(e -> {
                    try {
                        new ZeroSGlist();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

              GameContinue = false;
              waiting = false;
              stop = false;
              release();
              scene1.setRoot(pane1);
            });

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        System.exit(0);
                    }
            });
            }
        });
    }

    private void Connection() {
        name = id.getText();
        passp = passport.getText();
        PlayerMessage playermessage1 = new PlayerMessage(id.getText(), passport.getText());
        playermessage1.addPlayer(playermessage1);

        try {
            InputStream = Serverlist.getPlace().getDataInputStream();
            OutputStream = Serverlist.getPlace().getDataOutputStream();
        } catch (IOException e) {
        }

        new Thread(() -> {
            try {
                player = InputStream.readInt();
                if (player == people1) {
                    myChess = 1;
                    otherChess = 2;

                    Platform.runLater(() -> {
                        ArrayList<PlayerMessage> pl = null;
                        try {
                            pl = takeoutList(new File("C:\\Users\\Evan玖\\Desktop\\java\\A2\\Tic-tac-toe-master\\out\\production\\Tic-tac-toe-master\\twicetry\\sameGamePlayer.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ArrayList<PlayerMessage> finalPl = pl;

                        finalPl.add(playermessage1);
                        try {
                            playermessage1.saveSGlist(finalPl);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        playerq.setImage(new Image("Picture/2.png"));
                        new ShowStringDetail().display("\n您的棋子是O！\n正在等待Player2匹配!\n ");
                    });
                    InputStream.readInt();

                    Platform.runLater(() -> {
                        String n = "";
                        ArrayList<PlayerMessage> fpl = null;
                        try {
                            fpl = takeoutList(new File("C:\\Users\\Evan玖\\Desktop\\java\\A2\\" +
                                    "Tic-tac-toe-master\\out\\production\\Tic-tac-toe-master\\twicetry\\sameGamePlayer.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ArrayList<PlayerMessage> ffinalPl = fpl;
                        for (PlayerMessage p : ffinalPl){
                            if (!Objects.equals(p.getId(), name)) {
                                n = p.getId();
                            }
                        }
                        new ShowStringDetail().display("\nPlayer2进入对局，您先走棋\n ");
                        tchess.setText("轮到你的回合");
                    });
                    passData();
                    myTurn = true;
                } else if (player == people2) {
                    myChess = 2;
                    otherChess = 1;
                    Platform.runLater(() -> {
                      playerq.setImage(new Image("Picture/1.png"));
                      new ShowStringDetail().display("\n您是X,请等待对手的回合！\n ");
                      tchess.setText("轮到对方的回合");
                    });
                    passData();
                }

                while (GameContinue) {
                    if (player == people1) {
                Paneevent(player);
                TimeforP();
                socketevent();
                GetServer();
                    } else if (player == people2) {
                        GetServer();
                        Paneevent(player);
                        TimeforP();
                        socketevent();
                    }
                }
            } catch (Exception e) {
                release();
            }
        }).start();
    }

    private void passData() {

        new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("");
            while (GameContinue) {
                int index;
                try {
                    index = InputStream.readInt();
                    if (index == 1) {
                        data = InputStream.readInt();
                        stop = false;
                        if (data == p1win) {
                            if (myChess == 2) {
                                Getevent();
                            }
                            break;
                        } else if (data == p2win) {
                            if (myChess == 1) {
                                Getevent();
                            }
                            break;
                        } else if (data == equalpp) {
                            if (myChess == 2) {
                                Getevent();
                            }
                            break;
                        } else {
                            Getevent();
                        }
                    }
                    else if (index == 2) {
                        str = InputStream.readUTF();
                        Platform.runLater(() ->
                        {
                            Date date1 = new Date();
                            textArea.appendText(sdf.format(date1) + "\n");
                        });
                    }
                    else if (index == 20) {
                        if (GameContinue) {
                            Platform.runLater(() ->
                            {
                                new ShowStringDetail().display("\n对手已退出游戏\n对局结束");
                            });
                            myTurn = false;
                            GameContinue = false;
                        }
                        if (stop)
                            stop = false;
                        if (waiting)
                            waiting = false;
                        break;
                    }
                } catch (IOException e) {
                    GameContinue = false;
                    release();
                }
            }
        }).start();
    }

    private void TimeforP() throws InterruptedException {
    while (waiting) {
      Thread.sleep(100);
    }
      waiting = true;
    }

  private void TimeForC() throws InterruptedException {
    while (stop) {
      Thread.sleep(100);
        }
        stop = true;
  }

    private void socketevent() throws IOException {
        OutputStream.writeInt(1);
        OutputStream.writeInt(rowNow);
        OutputStream.writeInt(colNow);
    }

  private void GetServer() throws Exception {
        //提示语
    String s = "\nWin！！ ";
    String s1 = "\nLoss ToT";
    String s2 = "\n平局噢\n ";

    TimeForC();
        if (!GameContinue) {
            return;
        }
        if (data == p1win) {
            GameContinue = false;
            waiting = false;
            OutputStream.writeInt(8);
            if (myChess == 1) {
                Platform.runLater(() -> {
                    new ShowStringDetail().display(s);
                });
            } else if (myChess == 2) {
                Platform.runLater(() -> {
                    new ShowStringDetail().display(s1);
                });
            }
        } else if (data == p2win) {
            GameContinue = false;
            waiting = false;
            if (myChess == 2) {
                Platform.runLater(() -> {
                    new ShowStringDetail().display(s);
                });
            } else if (myChess == 1) {
                Platform.runLater(() -> {
                    new ShowStringDetail().display(s1);
                });
            }
        } else if (data == equalpp) {
            GameContinue = false;
            waiting = false;
            Platform.runLater(() -> {
                new ShowStringDetail().display(s2);
            });
        } else {
            myTurn = true;
        }
    }

    private void Getevent() throws IOException {
        int row = InputStream.readInt();
        int col = InputStream.readInt();
        Panevent2(row, col);
    }

  private void Paneevent(int player) {
        linePane.setOnMouseClicked(e1 -> {
            if (myTurn) {
                bk:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        double distance = Math.sqrt(Math.pow((e1.getSceneX() - linePane.zuobiaox(i, j)), 2)
                                + Math.pow((e1.getSceneY() - linePane.zuobiaoy(i, j)), 2));
                        if (distance < 80 && chess[i][j] == 0) {

                            if (player == people1) {
                                circles[i][j] = SavingChess.forPlace().getChess("x")
                                        .SetPlace(new GetPlace(linePane.zuobiaox(i, j), linePane.zuobiaoy(i, j)));
                                chess[i][j] = 1;

                                backchess(circles[i][j], i, j);
                            } else if (player == people2) {
                                circles[i][j] = SavingChess.forPlace().getChess("o")
                                        .SetPlace(new GetPlace(linePane.zuobiaox(i, j), linePane.zuobiaoy(i, j)));
                                chess[i][j] = 2;

                                backchess(circles[i][j], i, j);
                            }
                            linePane.getChildren().add(circles[i][j]);
                            linePane.getChildren().remove(signForpane);
                            rowNow = i;
                            colNow = j;
                            myTurn = false;
                            waiting = false;
                            tchess.setText("等待对方下棋");
                            break bk;
                        }
                    }
                }
            }

        });
    }

    private void Panevent2(int row, int col) {
        Platform.runLater(() -> {
            if (myChess == 1) {
                circles[row][col] = SavingChess.forPlace().getChess("o")
                        .SetPlace(new GetPlace(linePane.zuobiaox(row, col), linePane.zuobiaoy(row, col)));
                chess[row][col] = 2;

                backchess(circles[row][col], row, col);
            } else {
                circles[row][col] = SavingChess.forPlace().getChess("x")
                        .SetPlace(new GetPlace(linePane.zuobiaox(row, col), linePane.zuobiaoy(row, col)));
                chess[row][col] = 1;

                backchess(circles[row][col], row, col);
            }
          linePane.getChildren().add(circles[row][col]);
          signForpane.setCenterX(linePane.zuobiaox(row, col));
          signForpane.setCenterY(linePane.zuobiaoy(row, col));
          linePane.getChildren().add(signForpane);
          tchess.setText("到你了，别摆了");
        });
    }


    private void backchess(ImageView circleChess, int row, int col) {
        ChooseForPeople.getInstance().setOOO(circleChess);
        ChooseForPeople.getInstance().setRow(row);
        ChooseForPeople.getInstance().setCol(col);
        mc.setLfc(ChooseForPeople.getInstance().save());
    }

    private void initialize() {
        linePane.getChildren().clear();
        linePane.display();

        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                if (chess[i][j] != 0) {
                    circles[i][j] = null;
                    chess[i][j] = 0;
                }
            }
        }
        GameContinue = true;
        textArea.clear();
        player = 0;
        myChess = 0;
        stop = true;
        myTurn = false;
        waiting = true;
        tchess.setText("");
        otherChess = 0;
    }


    private void release() {
        GameContinue = false;
        OtherConnectionTo.close(Serverlist.getSocket(), OutputStream, InputStream);
    }

    public ArrayList<PlayerMessage> takeoutList(File f) throws IOException {
        ArrayList<PlayerMessage> playerMessage=new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String lineTxt = null;
        while ((lineTxt = br.readLine()) != null) {
            String[] str = lineTxt.split(" ");
            for (int i=0;i<4;i++) {
                PlayerMessage p=new PlayerMessage(str[0], str[1]
                        , Integer.parseInt(str[2])
                        , Integer.parseInt(str[3]));
                playerMessage.add(p);
                break;
            }
        }
        return playerMessage;
    }
}
