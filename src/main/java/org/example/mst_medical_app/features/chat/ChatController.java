package org.example.mst_medical_app.features.chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ChatController {

    // LEFT SIDE
    @FXML private TextField searchField;
    @FXML private VBox conversationList;

    // RIGHT SIDE
    @FXML private VBox chatWindow;
    @FXML private ImageView chatAvatar;
    @FXML private Label chatName;
    @FXML private Label chatStatus;

    @FXML private ScrollPane messageScrollPane;
    @FXML private VBox messageContainer;

    @FXML private HBox typingIndicator;
    @FXML private Label typingDots;

    @FXML private TextField messageField;
    @FXML private Button sendBtn;

    private String currentChatUser = null;

    @FXML
    public void initialize() {
        loadSampleConversations();
        setupSendMessage();
    }

    /** -------------------------
     *  LOAD SAMPLE CONVERSATIONS (UI DEMO ONLY)
     *  ------------------------- */
    private void loadSampleConversations() {
        addConversationItem("Dr. Toan Nghiem","/images/doctor1.png", "See you tomorrow", "14:32");
        addConversationItem("Dr. San Nguyen", "/images/doctor2.png","Send me your results", "09:12");
        addConversationItem("Dr. DT", "/images/doctor 3.png","Okay thank you!", "Yesterday");
    }

    private void addConversationItem(String name, String avatarPath, String lastMsg, String time) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatConversationItem.fxml"));
            HBox item = loader.load();

            ChatConversationItemController controller = loader.getController();
            controller.setData(name, avatarPath, lastMsg, time);

            item.setOnMouseClicked(e -> openChat(name, avatarPath));

            conversationList.getChildren().add(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** -------------------------
     *  OPEN CHAT (UI ONLY)
     *  ------------------------- */
    private void openChat(String user, String avatarPath) {
        currentChatUser = user;

        chatName.setText(user);
        chatStatus.setText("Online");

        try {
            chatAvatar.setImage(new Image(getClass().getResourceAsStream(avatarPath)));
        } catch (Exception e) {
            chatAvatar.setImage(new Image(getClass().getResourceAsStream("/images/default_avatar.png")));
        }

        messageContainer.getChildren().clear();
    }


    /** -------------------------
     *  SEND MESSAGE (UI ONLY)
     *  ------------------------- */
    private void setupSendMessage() {
        sendBtn.setOnAction(e -> sendMessage());
        messageField.setOnAction(e -> sendMessage()); // Enter key
    }

    private void sendMessage() {
        String msg = messageField.getText().trim();
        if (msg.isEmpty() || currentChatUser == null) return;

        addMessageBubble(msg, true);
        messageField.clear();

        scrollToBottom();

        // Phase 2 sẽ thêm: Save DB + refresh receiver
    }

    /** -------------------------
     *  ADD CHAT BUBBLE UI
     *  ------------------------- */
    private void addMessageBubble(String message, boolean isSender) {
        Label bubble = new Label(message);
        bubble.setWrapText(true);
        bubble.setMaxWidth(380);

        if (isSender) {
            bubble.setStyle("-fx-background-color: #0D6EFD; -fx-text-fill: white; -fx-padding: 8 12; -fx-background-radius: 14;");
            HBox box = new HBox(bubble);
            box.setAlignment(Pos.CENTER_RIGHT);
            messageContainer.getChildren().add(box);
        } else {
            bubble.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #0D6EFD; -fx-text-fill: black; -fx-padding: 8 12; -fx-background-radius: 14;");
            HBox box = new HBox(bubble);
            box.setAlignment(Pos.CENTER_LEFT);
            messageContainer.getChildren().add(box);
        }

        scrollToBottom();
    }

    private void scrollToBottom() {
        messageScrollPane.layout();
        messageScrollPane.setVvalue(1.0);
    }
}
