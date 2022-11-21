package twicetry;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import twicetry.clint.Player;
import twicetry.server.Serverlist;

import java.io.DataOutputStream;
import java.io.IOException;

public class InputWindow
{
    private Stage window = new Stage();
    private Scene scene =  new Scene(new Pane(),300,130);
    private Label label = new Label();
    private Button btYes = new Button("确定");

    //输入对话框
    TextField tf = new TextField();
    TextField tf1 = new TextField();

    public String getStr() {
        return str;
    }

    public String getStr1() {
        return str1;
    }

    String str,str1;
    private  int flag = 0;

    private DataOutputStream toServer;

    public InputWindow()
    {
        window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        //居中
        window.setX(Player.x + 900);
        window.setY(Player.y + 300);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }

    public void display(String s)
    {
        label.setText(s);
        btYes.setOnAction(e -> window.close());

        scene.setRoot(new StackPane(new VBox(new StackPane(label),new StackPane(btYes))));
        window.showAndWait();
    }


}
//    HBox hbox0 = new HBox(10); // 创建一个水平箱子
//    Label label00 = new Label("ID："); // 创建一个标签
//    TextField field0 = new TextField(); // 创建一个单行输入框
//        field0.setPrefSize(200, 50); // 设置单行输入框的推荐宽高
//                field0.setEditable(true); // 设置单行输入框能否编辑
//                field0.setAlignment(Pos.CENTER_LEFT); // 设置单行输入框的对齐方式
//                field0.setPrefColumnCount(11); // 设置单行输入框的推荐列数
//                hbox0.getChildren().addAll(label, field0); // 给水平箱子添加一个密码输入框
//
//                HBox hbox00 = new HBox(10); // 创建一个水平箱子
//                Label label0 = new Label("密码："); // 创建一个标签
//                PasswordField field = new PasswordField(); // 创建一个密码输入框
//                field.setPrefSize(200, 30); // 设置密码输入框的推荐宽高
//                field.setEditable(true); // 设置密码输入框能否编辑
//                field.setAlignment(Pos.CENTER_LEFT); // 设置密码输入框的对齐方式
//                field.setPrefColumnCount(11); // 设置密码输入框的推荐列数
//                hbox00.getChildren().addAll(label, field); // 给水平箱子添加一个密码输入框
//
//                VBox vboxx=new VBox(15);
//                vboxx.getChildren().addAll(hbox0,hbox00,btn1);
//
//                BorderPane borderPane0 = new BorderPane();
//                borderPane0.setCenter(vboxx); // 把水平箱子放到边界窗格的中央
//
//                Scene scene0 = new Scene(borderPane0);
//                primaryStage.setScene(scene0);
//                primaryStage.show();