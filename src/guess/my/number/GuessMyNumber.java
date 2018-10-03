package guess.my.number;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Random;
import javafx.scene.layout.HBox;

public class GuessMyNumber extends Application {

    public static void main(String[] args) {
        launch(GuessMyNumber.class);
    }

    @Override
    public void start(Stage stage) {

        VBox layout = new VBox();
        Label instructions = new Label("Guess the number between 0 and 100.");
        Label state = new Label("");
        TextField input = new TextField();
        Button sendGuess = new Button("Guess");
        
        Label lastGuess = new Label("Last guess: ");

        layout.getChildren().addAll(instructions, input, state, lastGuess, sendGuess);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        
        playerGuesses(input, sendGuess, state, lastGuess);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }

    public int randomNumber() {
        //Generates a random number between 0 and 100.
        Random r = new Random();
        return r.nextInt(100);
    }

    public void playerGuesses(TextField input, Button button, Label state, Label lastGuess) {
        int number = randomNumber();

        button.setOnAction(event -> {
            if (input.getText().isEmpty()) {
                state.setText("Guess something!");
            } else {
                try {
                    int playerGuess = Integer.parseInt(input.getText());

                    if (playerGuess < 0 || playerGuess > 100) {
                        state.setText("Invalid number.");
                    } else {
                        //Checks if the player guessed right. 
                        //If the player guessed wrong, checks if the guess was too high or too low.
                        if (playerGuess == number) {
                            state.setText("You win!");
                            input.setDisable(true);
                        } else if (playerGuess < number) {
                            state.setText("Too low!");
                            input.setText("");
                        } else if (playerGuess > number) {
                            state.setText("Too high!");
                            input.setText("");
                        }
                        
                        lastGuess.setText("Last guess: " + Integer.toString(playerGuess));
                    }
                } catch (NumberFormatException error) {
                    state.setText("Guess a number!");
                }
            }
        });
    }
}
