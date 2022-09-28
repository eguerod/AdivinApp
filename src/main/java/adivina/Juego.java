package adivina;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Juego extends Application {

	private Label numLabel;
	private TextField nombreText;
	private Button comprobarButton;
	private VBox root;
	private Alert info = new Alert(AlertType.INFORMATION);
	private Alert warning = new Alert(AlertType.WARNING);
	private Alert error = new Alert(AlertType.ERROR);
	private int contador = 0;
	private int random = (int) (Math.random() * 100);

	@Override
	public void start(Stage primaryStage) throws Exception {

		numLabel = new Label();
		numLabel.setText("Introduce un número del 0 al 100");

		nombreText = new TextField();
		nombreText.setPromptText("Introduce un número");

		comprobarButton = new Button("Comprobar");
		comprobarButton.setOnAction(this::onComprobarAction);
		comprobarButton.setDefaultButton(true);
		comprobarButton.setTooltip(new Tooltip("Saludar a la persona indicada en el cuadro de texto"));

		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(numLabel, nombreText, comprobarButton);
		root.setFillWidth(false);
		root.setSpacing(5);

		info.setTitle("AdivinApp");
		info.setHeaderText("¡Has ganado!");

		warning.setTitle("AdivinApp");
		warning.setHeaderText("¡Has fallado!");

		error.setTitle("AdivinApp");
		error.setHeaderText("Error");
		error.setContentText("El número introducido no es válido.");

		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void onComprobarAction(ActionEvent e) {
		try {
			int numero = Integer.parseInt(nombreText.getText());

			if (numero > 100 || numero < 0) {
				error.showAndWait();
			} else {
				if (numero == random) {
					contador++;
					info.setContentText("Sólo has necesitado " + contador
							+ " intentos.\n\nVuelve a jugar e intenta mejorar esa marca.");
					info.showAndWait();
					contador = 0;
					random = (int) (Math.random() * 100);
				}

				else {
					if (numero > random) {
						warning.setContentText(
								"El número a adivinar es menor que " + numero + ".\n\nVuelve a intentarlo.");
						contador = contador + 1;
						warning.showAndWait();
					} else {
						warning.setContentText(
								"El número a adivinar es mayor que " + numero + ".\n\nVuelve a intentarlo.");
						contador = contador + 1;
						warning.showAndWait();
					}
				}
			}

		} catch (NumberFormatException ex) {
			error.showAndWait();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
