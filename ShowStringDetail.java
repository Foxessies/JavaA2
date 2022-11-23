package twicetry;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class ShowStringDetail {
  private Stage window = new Stage();
  private Scene scene =  new Scene(new Pane(),300,130);
  private Label label = new Label();
  private Button btYes = new Button("确定");
  private Button btNo = new Button("取消");
  private  int flag = 0;
  private DataOutputStream toServer;

  public ShowStringDetail()
  {
      window.setResizable(false);
      window.initStyle(StageStyle.UNDECORATED);
      window.setX(Player.x + 350);
      window.setY(Player.y + 300);
      window.setScene(scene);
      window.initModality(Modality.APPLICATION_MODAL);
  }

  public void display(String s) {
      label.setText(s);
      btYes.setOnAction(e -> window.close());

      scene.setRoot(new StackPane(new VBox(new StackPane(label),new StackPane(btYes))));
      window.showAndWait();
  }
}
