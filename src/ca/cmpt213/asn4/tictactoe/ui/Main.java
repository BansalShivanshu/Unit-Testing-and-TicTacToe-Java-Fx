package ca.cmpt213.asn4.tictactoe.ui;

import ca.cmpt213.asn4.tictactoe.model.Grid;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    Label turnLabel, winLabel;
    Button newGameButton;
    GridPane gridPane;
    ImageView xView, oView;

    Grid grid;

    boolean turnX;
    int gameStatus; // -1 == in game, 0 == draw, 1 == x won, 2 == o won


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // instantiate the grid object
        grid = new Grid();

        // set turn x variable
        turnX = true;

        // set end game to in-game
        gameStatus = -1;

        // setup the ui
        setupLayout(primaryStage);
    }

    private void setupLayout(Stage primaryStage) {
        // Label to show whose turn it is
        turnLabel = new Label("");
        setTurnLabel();

        // Grid Pane for the game
        gridPane = new GridPane();
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCol(); j++) {
                ImageView imageView = new ImageView(new Image("file:res/img/blank.jpg"));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                gridPane.add(imageView, i, j);

                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        imageClicked(mouseEvent);
                    }
                });
            }
        }
        gridPane.setVgap(3);
        gridPane.setHgap(3);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);

        // Label to show who won
        winLabel = new Label("");
        winLabel.setFont((new Font("Ariel", 25)));

        // Button to create a new game
        newGameButton = new Button("New Game");
        newGameButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        newGameButton.setStyle("-fx-font-size:20; -fx-font-weight:bold; -fx-text-fill:seagreen");

        VBox vBox = new VBox(10, turnLabel, gridPane, winLabel, newGameButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(vBox, 500, 750, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setTurnLabel() {
        if (turnX) {
            turnLabel.setText("Turn of: X");
        } else {
            turnLabel.setText("Turn of: O");
        }
        turnLabel.setTextFill(Color.SEAGREEN);
        turnLabel.setFont(new Font("Ariel", 20));
        turnLabel.setStyle("-fx-font-weight: bold");
    }

    private void setWinLabel(String string) {
        winLabel.setText(string);
        winLabel.setTextFill(Color.ORANGE);
        winLabel.setFont(new Font("Ariel", 20));
        winLabel.setStyle("-fx-font-weight: bold");
    }

    private void imageClicked(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int row = GridPane.getRowIndex(imageView);
        int col = GridPane.getColumnIndex(imageView);

        if (turnX) {
            if (grid.getTimesClicked(row, col) == 0) {
                imageView.setImage(new Image("file:res/img/xLogo.png"));
                imageView.setDisable(true);
                turnX = false;
                setTurnLabel();
                grid.setXClicked(row, col);
            }
        } else {
            if (grid.getTimesClicked(row, col) == 0) {
                imageView.setImage(new Image("file:res/img/oLogo.jpg"));
                imageView.setDisable(true);
                turnX = true;
                setTurnLabel();
                grid.setOClicked(row, col);
            }
        }


    }

    private void displayWon() {
        if (gameStatus == 1) winLabel.setText("*** Game Draw ***");
        else if (gameStatus == 2) winLabel.setText("*** X WINS ***");
        else if (gameStatus == 3) winLabel.setText("*** O WINS ***");

        winLabel.setTextFill(Color.GOLD);
        winLabel.setFont((new Font("Ariel", 25)));
        winLabel.setStyle("-fx-font-weight: bold");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.35), evt -> winLabel.setVisible(false)),
                new KeyFrame(Duration.seconds(0.15), evt -> winLabel.setVisible(true)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
