package org.dimigo.gui.helloworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    ListView lst_rank;
    @FXML
    Button btnSearch;
    @FXML
    Button btnMove;
    @FXML
    private WebView web;
    @FXML
    ListView movieSelect;
    @FXML
    Button btn_delete;


    @FXML
    public void showRank(ActionEvent event) {

        try {

            Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn").get();
            System.out.println(doc.html());
            Elements movieresult = doc.select("ul.lst_detail_t1 li dl dt a");
            List<String> movielist = movieresult.eachText();
            System.out.println(movielist);
            ObservableList<String> data = FXCollections.observableArrayList(movielist);
            lst_rank.setItems(data);
            int i = 0;
            StringBuilder top10 = new StringBuilder("");
            for (String movietitle : movielist) {
                top10.append(movietitle + "\n");
                i++;
                if (i == 100) break;
            }
            System.out.println(top10);

            if (lst_rank.getAccessibleText() == null) {
                lst_rank.setAccessibleText(String.valueOf(top10));
            } else {
                lst_rank.setAccessibleText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void moveWeb(ActionEvent event) {
        try {

            web.getEngine().load("https://movie.naver.com/movie/running/current.nhn");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lst_rank.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    Object tmp;
                    if (lst_rank.getSelectionModel().getSelectedItem() != null) {
                        tmp = lst_rank.getSelectionModel().getSelectedItem();
                        listA.add(tmp.toString());

                        ObservableList<String> oListStavaka = FXCollections.observableArrayList(listA);
                        movieSelect.setItems(oListStavaka);

                        System.out.println(listA);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    List listA = new ArrayList();


    public void handleMouseClick2(ActionEvent event) {
        try{
            int tmp_index;
            if(movieSelect.getSelectionModel().getSelectedItem() != null) {
                tmp_index = movieSelect.getSelectionModel().getSelectedIndex();
                listA.remove(tmp_index);

                ObservableList<String> oListStavaka = FXCollections.observableArrayList(listA);
                movieSelect.setItems(oListStavaka);

                System.out.println(listA);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}




