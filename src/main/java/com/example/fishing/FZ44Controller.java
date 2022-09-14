package com.example.fishing;

import com.example.fishing.models.FZ44;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FZ44Controller extends ListCell<FZ44> {

    @FXML
    private CheckBox check;

    @FXML
    private Label docName1;

    @FXML
    private Label docName2;

    @FXML
    private Label docName3;

    @FXML
    private Label docName4;

    @FXML
    private Label docName5;

    @FXML
    private Label docName6;

    @FXML
    private Label docName7;

    @FXML
    private GridPane grid;

    @FXML
    private Hyperlink href1;

    @FXML
    private Hyperlink href2;

    @FXML
    private Hyperlink href3;

    @FXML
    private Hyperlink href4;

    @FXML
    private Hyperlink href5;

    @FXML
    private Hyperlink href6;

    @FXML
    private Hyperlink href7;

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
    private FXMLLoader mloader;

    @Override
    protected void updateItem(FZ44 fz, boolean empty) {
        super.updateItem(fz, empty);

        if (empty || fz == null){

            setText(null);
            setGraphic(null);

        }else {
            if (mloader == null){
                mloader = new FXMLLoader(getClass().getResource("item44.fxml"));
                mloader.setController(this);

                try {
                    mloader.load();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            num.setText(fz.getTenderid());
            price.setText(fz.getMaxPrice());
            type.setText(fz.getTendertype());
            info.setText(fz.getArticle());
            orgName.setText(fz.getClientname());
            info.setWrapText(true);
            orgName.setWrapText(true);

            setText(null);
            setGraphic(grid);
        }
    }
}
