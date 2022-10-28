package com.example.fishing;

import com.example.fishing.models.FZ44;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;

public class FZ44Controller extends ListCell<FZ44> {


    @FXML
    private CheckBox check;

    @FXML
    private Button doc1;

    @FXML
    private Button doc2;

    @FXML
    private Button doc3;

    @FXML
    private Button doc4;

    @FXML
    private Button doc5;

    @FXML
    private Button doc6;

    @FXML
    private Button doc7;

    @FXML
    private GridPane grid;

    @FXML
    private Label info;

    @FXML
    private ComboBox<?> maker;

    @FXML
    private Label num;

    @FXML
    private Label orgName;

    @FXML
    private Pane pane441;

    @FXML
    private Pane pane442;

    @FXML
    private Pane pane443;

    @FXML
    private Label price;

    @FXML
    private Label type;

    @FXML
    private Label type1;

    @FXML
    private ListCell<FZ44> fz44ListCell;

    private FXMLLoader mloader;
    Image untip = new Image(Objects.requireNonNull(getClass().getResourceAsStream("!tip.png")));
    Image tip = new Image(Objects.requireNonNull(getClass().getResourceAsStream("tip.png")));

    @Override
    protected void updateItem(FZ44 fz, boolean empty) {
        super.updateItem(fz, empty);

        if (empty || fz == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mloader == null) {
                mloader = new FXMLLoader(getClass().getResource("item44.fxml"));
                mloader.setController(this);

                try {
                    mloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            check.setOnAction(ac -> {
                if (check.isSelected()) {
                    pane441.setStyle("-fx-background-color:  #f8d8b0");
                    pane442.setStyle("-fx-background-color:  #f8e8d8");
                    pane443.setStyle("-fx-background-color:  #f8d8b0");
                    doc1.setStyle("-fx-background-color: #f8d8b0");
                    doc2.setStyle("-fx-background-color: #f8d8b0");
                    doc3.setStyle("-fx-background-color: #f8d8b0");
                    doc4.setStyle("-fx-background-color: #f8d8b0");
                    doc5.setStyle("-fx-background-color: #f8d8b0");
                    doc6.setStyle("-fx-background-color: #f8d8b0");
                    doc7.setStyle("-fx-background-color: #f8d8b0");
                    return;
                } else if (!check.isSelected()) {
                    pane441.setStyle("-fx-background-color: #cdcdcd");
                    pane442.setStyle("-fx-background-color: #ebebeb");
                    pane443.setStyle("-fx-background-color: #cdcdcd");
                    doc1.setStyle("-fx-background-color: #cdcdcd");
                    doc2.setStyle("-fx-background-color: #cdcdcd");
                    doc3.setStyle("-fx-background-color: #cdcdcd");
                    doc4.setStyle("-fx-background-color: #cdcdcd");
                    doc5.setStyle("-fx-background-color: #cdcdcd");
                    doc6.setStyle("-fx-background-color: #cdcdcd");
                    doc7.setStyle("-fx-background-color: #cdcdcd");
                    return;
                }
            });
//            num.setText(fz.getTenderid());
//            price.setText(fz.getMaxPrice());
//            type.setText(fz.getTendertype());
//            info.setText(fz.getArticle());
//            orgName.setText(fz.getClientname());
//            //check.setSelected(fz.getActive());
//            //check.setSelected(fz.setActive(check.get));
//            if (fz.getDocUrl().size() == 1) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//            } else if (fz.getDocUrl().size() == 2) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//                doc2.setText(fz.getDocUrl().get(1).getDocName());
//            } else if (fz.getDocUrl().size() == 3) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//                doc2.setText(fz.getDocUrl().get(1).getDocName());
//                doc3.setText(fz.getDocUrl().get(2).getDocName());
//            } else if (fz.getDocUrl().size() == 4) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//                doc2.setText(fz.getDocUrl().get(1).getDocName());
//                doc3.setText(fz.getDocUrl().get(2).getDocName());
//                doc4.setText(fz.getDocUrl().get(3).getDocName());
//            } else if (fz.getDocUrl().size() == 5) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//                doc2.setText(fz.getDocUrl().get(1).getDocName());
//                doc3.setText(fz.getDocUrl().get(2).getDocName());
//                doc4.setText(fz.getDocUrl().get(3).getDocName());
//                doc5.setText(fz.getDocUrl().get(4).getDocName());
//            } else if (fz.getDocUrl().size() == 6) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//                doc2.setText(fz.getDocUrl().get(1).getDocName());
//                doc3.setText(fz.getDocUrl().get(2).getDocName());
//                doc4.setText(fz.getDocUrl().get(3).getDocName());
//                doc5.setText(fz.getDocUrl().get(4).getDocName());
//                doc6.setText(fz.getDocUrl().get(5).getDocName());
//            } else if (fz.getDocUrl().size() == 7) {
//                //Названия документов
//                doc1.setText(fz.getDocUrl().get(0).getDocName());
//                doc2.setText(fz.getDocUrl().get(1).getDocName());
//                doc3.setText(fz.getDocUrl().get(2).getDocName());
//                doc4.setText(fz.getDocUrl().get(3).getDocName());
//                doc5.setText(fz.getDocUrl().get(4).getDocName());
//                doc6.setText(fz.getDocUrl().get(5).getDocName());
//                doc7.setText(fz.getDocUrl().get(6).getDocName());
//            }
//            doc1.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(0).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            doc2.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(1).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            doc3.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(2).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            doc4.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(3).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            doc5.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(4).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            doc6.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(5).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            doc7.setOnAction(actionEvent -> {
//                try {
//                    Desktop.getDesktop().browse(new URL(fz.getDocUrl().get(6).getUrl()).toURI());
//                } catch (IOException | URISyntaxException e) {
//                    throw new RuntimeException(e);
//                }
//            });
            String db = "jdbc:postgresql://192.168.0.30:5432/TASKS";
            String username = "root";
            String password = "cerf,kzlm765";
            Connection conn;
            Statement stmt = null;

//            try {
//                conn = DriverManager.getConnection(db, username, password);
//                stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery("select uname from public.getmanagers()");
//                //ObservableList<String> langs = FXCollections.observableArrayList(rs.getString(1));
//                //maker = new ComboBox<>(langs);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }


            setText(null);
            setGraphic(grid);
        }
    }
}