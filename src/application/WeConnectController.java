package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WeConnectController {

	@FXML
	TextArea chatWindow;

	@FXML
	TextField message;

	@FXML
	Button sendButton;

	private Friend friend = new Friend("localhost", 9090, message -> {

		if (!message.equals("bye")) {
			chatWindow.appendText("Friend: " + message + "\n");
		} else {
			chatWindow.setText("");
		}
	});

	/**
	 * Sends the message
	 * 
	 * @param event The event
	 */
	@FXML
	private void sendMessage(ActionEvent event) {

		String yourMessage = message.getText().trim();

		if (!yourMessage.equals("")) {
			chatWindow.appendText("You: " + yourMessage + "\n");
			message.setText("");

			// Sending the message to friend
			friend.sendMessage(yourMessage);

			if (yourMessage.equals("bye")) { // Ending the connection
				friend.endConnection();
				message.setEditable(false);
			}
		}
	}
}
