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
            if (fz.getDocUrl().size() == 1){
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());

                //Ссылки документов
                href1 = new Hyperlink(fz.getDocUrl().get(0).getUrl());
               // href1.setText(fz.getDocUrl().get(0).getUrl());
            } else if (fz.getDocUrl().size() == 2) {
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());
                docName2.setText(fz.getDocUrl().get(1).getDocName());

                //Ссылки документов
              /*  href1.setText(fz.getDocUrl().get(0).getUrl());
                href2.setText(fz.getDocUrl().get(1).getUrl());*/
            }else if (fz.getDocUrl().size() ==3) {
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());
                docName2.setText(fz.getDocUrl().get(1).getDocName());
                docName3.setText(fz.getDocUrl().get(2).getDocName());

                //Ссылки документов
               /* href1.setText(fz.getDocUrl().get(0).getUrl());
                href2.setText(fz.getDocUrl().get(1).getUrl());
                href3.setText(fz.getDocUrl().get(2).getUrl());*/
            }else if (fz.getDocUrl().size() == 4) {
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());
                docName2.setText(fz.getDocUrl().get(1).getDocName());
                docName3.setText(fz.getDocUrl().get(2).getDocName());
                docName4.setText(fz.getDocUrl().get(3).getDocName());

                //Ссылки документов
               /* href1.setText(fz.getDocUrl().get(0).getUrl());
                href2.setText(fz.getDocUrl().get(1).getUrl());
                href3.setText(fz.getDocUrl().get(2).getUrl());
                href4.setText(fz.getDocUrl().get(3).getUrl());*/
            }else if (fz.getDocUrl().size() == 5) {
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());
                docName2.setText(fz.getDocUrl().get(1).getDocName());
                docName3.setText(fz.getDocUrl().get(2).getDocName());
                docName4.setText(fz.getDocUrl().get(3).getDocName());
                docName5.setText(fz.getDocUrl().get(4).getDocName());

                //Ссылки документов
               /* href1.setText(fz.getDocUrl().get(0).getUrl());
                href2.setText(fz.getDocUrl().get(1).getUrl());
                href3.setText(fz.getDocUrl().get(2).getUrl());
                href4.setText(fz.getDocUrl().get(3).getUrl());
                href5.setText(fz.getDocUrl().get(4).getUrl());*/
            }else if (fz.getDocUrl().size() == 6) {
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());
                docName2.setText(fz.getDocUrl().get(1).getDocName());
                docName3.setText(fz.getDocUrl().get(2).getDocName());
                docName4.setText(fz.getDocUrl().get(3).getDocName());
                docName5.setText(fz.getDocUrl().get(4).getDocName());
                docName6.setText(fz.getDocUrl().get(5).getDocName());

                //Ссылки документов
               /* href1.setText(fz.getDocUrl().get(0).getUrl());
                href2.setText(fz.getDocUrl().get(1).getUrl());
                href3.setText(fz.getDocUrl().get(2).getUrl());
                href4.setText(fz.getDocUrl().get(3).getUrl());
                href5.setText(fz.getDocUrl().get(4).getUrl());
                href6.setText(fz.getDocUrl().get(5).getUrl());*/
            }else if (fz.getDocUrl().size() == 7) {
                //Названия документов
                docName1.setText(fz.getDocUrl().get(0).getDocName());
                docName2.setText(fz.getDocUrl().get(1).getDocName());
                docName3.setText(fz.getDocUrl().get(2).getDocName());
                docName4.setText(fz.getDocUrl().get(3).getDocName());
                docName5.setText(fz.getDocUrl().get(4).getDocName());
                docName6.setText(fz.getDocUrl().get(5).getDocName());
                docName7.setText(fz.getDocUrl().get(6).getDocName());

                //Ссылки документов
               /* href1.setText(fz.getDocUrl().get(0).getUrl());
                href2.setText(fz.getDocUrl().get(1).getUrl());
                href3.setText(fz.getDocUrl().get(2).getUrl());
                href4.setText(fz.getDocUrl().get(3).getUrl());
                href5.setText(fz.getDocUrl().get(4).getUrl());
                href6.setText(fz.getDocUrl().get(5).getUrl());
                href7.setText(fz.getDocUrl().get(6).getUrl());*/
            }



            setText(null);
            setGraphic(grid);
        }
    }
    @FXML
    public void click(){
        href1 = new Hyperlink();
    }
}
