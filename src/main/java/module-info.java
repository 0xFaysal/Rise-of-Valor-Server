module game.rise_of_valor_server {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens game.rise_of_valor_server.controllers to javafx.fxml;
    exports game.rise_of_valor_server;
}