package com.example.project;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainScreen {

    public Pane getMainPane() {
        BorderPane main_pane = new BorderPane();
        HBox left_pane = new HBox();
        VBox top_pane = new VBox();
        left_pane.setMinSize(60, 50);
        top_pane.setMinSize(60, 50);

        FlowPane center = new FlowPane();

        left_pane.setBackground(new Background(new BackgroundFill(Color.web("#" + "000000"), CornerRadii.EMPTY, Insets.EMPTY)));
        top_pane.setBackground(new Background(new BackgroundFill(Color.web("#" + "abcdef"), CornerRadii.EMPTY, Insets.EMPTY)));
        center.setBackground(new Background(new BackgroundFill(Color.web("#" + "abcdef"), CornerRadii.EMPTY, Insets.EMPTY)));

        main_pane.setLeft(left_pane);
        main_pane.setTop(top_pane);
        main_pane.setCenter(center);
        return main_pane;
    }

}
