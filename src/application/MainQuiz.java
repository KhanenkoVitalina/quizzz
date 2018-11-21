package application;
	
import java.util.Map;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainQuiz extends Application{
	static Stage primaryStagel;
	Level level = new Level(1);
	User user;
	BackgroundMusic music;
	DataBaseWork work;
	 private static MainQuiz instance;
	    public MainQuiz() {
	        instance = this;
	        final JFXPanel fxPanel = new JFXPanel();	
			 music = new BackgroundMusic();
	    }
	    public static MainQuiz getInstance() {
	        return instance;
	    }
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStagel = primaryStage;
			GridPane root = new GridPane();
			TextField text = new TextField();
			text.setPromptText("login");
			text.setId("text");
			PasswordField text2 = new PasswordField();
			text2.setPromptText("password");
			text2.setId("pas");
			Button b = new Button("OK");
			HBox hb = new HBox();
			root.setAlignment(Pos.CENTER);
			root.setVgap(10);
			root.setHgap(10);
			root.add(text, 1, 0);
			root.add(text2, 1, 1);
			root.add(hb, 1, 2);
			root.setVgap(10);
			root.setHgap(10);
			root.setAlignment(Pos.CENTER);
			hb.getChildren().add(b);
			hb.setAlignment(Pos.CENTER_RIGHT);
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					user = new User(text.getText().toString(), text2.getText().toString(), 1);
					work = new DataBaseWork();
					work.insert(user);
					nextLevel(1);
				}
			});
			Scene scene = new Scene(root,500,500);
			primaryStagel.getIcons().add(new Image("file:quiz.png"));
			scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
			primaryStagel.setScene(scene);
			primaryStagel.show();
			UIQuiz ui = new UIQuiz(level);
			ui.getGate().countDown();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void drawKButtons(Level level, GridPane root, Map<Integer,KButton> buttonList){
		int index = 0;
		int j = 0;
		int i = 0;
		while( i < level.getPlayingElements()) {
			root.add(buttonList.get(index), i, j);
			index++;
			if(i == (level.getPlayingElements() - 1) && index < level.getNumElements()) {
				i = 0;
				j++;
			} else {
				i++;
				}		
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	public   void nextLevel(int lvl) {
		GridPane root = new GridPane();
		level.setLevel(lvl);
		UIQuiz ui = new UIQuiz(level);
		KButton.setIterations(level.getPlayingElements());
		Map<Integer,KButton> buttonsList = ui.getButtonsList();
		drawKButtons(level, root, buttonsList);
		root.setVgap(10);
		root.setHgap(10);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root,500,500);
		primaryStagel.getIcons().add(new Image("file:quiz.png"));
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStagel.setScene(scene);
		primaryStagel.show();
		ui.getGate().countDown();
	}
	public void getResults(Level lvl) {
		BorderPane pane = new BorderPane();
		Label label = getLabel(lvl);
		Label label2 = getLevel(lvl);
		 Button tryAgain = new Button("Try again");
		 tryAgain.setId("try");
		 Button results = new Button();
		 Button list = new Button();
		 list.setId("list");
		 results.setId("results");
		label2.setId("level");
		HBox box = new HBox(results,list);
		VBox vb = new VBox();
		vb.getChildren().add(box);
		pane.setLeft(box);
		vb.setPadding(new Insets(12,12,12,12));
		vb.setAlignment(Pos.TOP_RIGHT);
		BorderPane.setAlignment(tryAgain, Pos.CENTER);
		BorderPane.setAlignment(label2, Pos.CENTER);
		BorderPane.setAlignment(vb, Pos.CENTER);
		BorderPane.setMargin(tryAgain, new Insets(12,12,12,12));
		BorderPane.setMargin(vb, new Insets(12,12,12,12));
		pane.setBottom(tryAgain);
		BorderPane.setMargin(label2, new Insets(12,12,12,12));
		pane.setTop(label2);
		pane.setCenter(label);
		work.update(user);
		Scene scene = new Scene(pane,500,500);
		primaryStagel.getIcons().add(new Image("file:quiz.png"));
		scene.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
		primaryStagel.setScene(scene);
		primaryStagel.show();
		results.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				music.pause();
				
			}
		});
		tryAgain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nextLevel(1);
			}
		});
	}
	public void getLogin() {

	}
	private Label getLabel(Level lvl) {
		Label label;
		if(lvl.getLevel() == lvl.getLastLevel()) {
			label = new Label("Congrats!");
		} else {
			label = new Label("Game over");
		}
		return label;
	}
	private Label getLevel(Level lvl) {
		Label label;
		if (lvl.getLevel() == lvl.getLastLevel()) {
			label = new Label("Final level " + lvl.getLevel());
		} else {
			label = new Label("Level " + lvl.getLevel());
		}
		return label;
	}
}
