var Button = javafx.scene.control.Button;
var StackPane = javafx.scene.layout.StackPane;
var Scene = javafx.scene.Scene;


$STAGE.title = "Hello World!";
var button = new Button();
button.text = "Say 'Hello World'";
button.onAction = function() print("Hello World!");
var root = new StackPane();
root.children.add(button);
$STAGE.scene = new Scene(root, 300, 250);
$STAGE.show();