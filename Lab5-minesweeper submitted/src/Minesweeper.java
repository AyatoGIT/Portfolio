import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Minesweeper extends Application{
	public static void main(String[] args) {
		launch(args);
		
	}
	public void start(Stage primaryStage) {
		
		/* header part of the game */
		HBox hb1 = new HBox();
		hb1.setSpacing(5);
		hb1.setAlignment(Pos.CENTER);
		
		
		//left box of header
		CustomPane mines = new CustomPane("010");
		mines.setStyle("-fx-background-color: lightblue;");
		
		
		//center box 
		CustomPane faceBox = new CustomPane();
		SmileyFace sf = new SmileyFace(0);
		sf.setGraphic(sf.getFace());
		faceBox.getChildren().addAll(sf);
		faceBox.setStyle("-fx-background-color: lightblue;");
		
		//right one
		CustomPane time = new CustomPane("000");
		time.setStyle("-fx-background-color: lightblue;");
		
		hb1.getChildren().addAll(mines, sf, time);
		
		
		
		/* to determine the size of the game*/
//		int height;
//		int width;
		int[][] mineField = new int[5][5];
		mineField[1][1] = 9;
		mineField[2][3] = 9;
		mineField[3][3] = 9;
		
		int numOfMine = 3;
		/* to randomize the coordinate of mine */
//		for(int a = 0; a < numOfMine; a++) {
//			int n = (int)(Math.random() * height);
//			int m = (int)(Math.random() * width);
//			for(int row = n-1; row <= n+1; row++) {
//				for(int col = m-1; col <= m+1; col++) {
//					if(isInBounds(row,col,height,width) && isNotMine(row,col,n,m)) {
//						mineField[row][col]++;
//					}
//				}
//			}
//		}
		for(int row = 1-1; row <= 1+1; row++) {
			for(int col = 1-1; col <= 1+1; col++) {
				if(isInBounds(row,col,5,5) && isNotMine(mineField,row,col,1,1)) {
					mineField[row][col]++;
				}
			}
		}
		for(int row = 2-1; row <= 2+1; row++) {
			for(int col = 3-1; col <= 3+1; col++) {
				if(isInBounds(row,col,5,5) && isNotMine(mineField,row,col,2,3)) {
					mineField[row][col]++;
				}
			}
		}
		for(int row = 3-1; row <= 3+1; row++) {
			for(int col = 3-1; col <= 3+1; col++) {
				if(isInBounds(row,col,5,5) && isNotMine(mineField,row,col,3,3)) {
					mineField[row][col]++;
				}
			}
		}
		
		printField(mineField);
		
		/* main body of the game*/
		GridPane gp = new GridPane();
		gp.setHgap(2);
		gp.setVgap(2);
		
		GridButton[][] grids = new GridButton[5][5];
		for(int row = 0; row < grids.length;row++) {
			for(int col = 0; col < grids[row].length; col++) {
				
				GridButton gb = new GridButton(mineField[row][col]);
				grids[row][col] = gb;
				gb.setGraphic(gb.getImageCover());
				gb.setOnMouseClicked(e ->{
					gb.setGraphic(gb.getImageOpen());
					
				});
				
				gp.add(gb, col, row);
			}
		}
		
		/*tried to make it look fancy by setAlignment  but leave it for now - March 10th*/
		
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(gp);
		hb2.setAlignment(Pos.CENTER);
		
		VBox screen = new VBox();
		screen.getChildren().addAll(hb1,hb2);
		screen.setSpacing(5);
		
		primaryStage.setScene(new Scene(screen));
		primaryStage.show();
	}
	public boolean isInBounds(int row, int col, int height, int width) {
		return row>=0 && row<height && col>=0 && col<width;
	}
	public boolean isNotMine(int[][] mineField, int row, int col, int n, int m) {
		if(mineField[row][col]==9) {
			return false;
		}else{
			return !(row==n && col==m);
		}
	}
	
	/* just so I could see the value of mineField */
	public static void printField(int[][] field) {
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
class GridButton extends Button{
	private int state; //0 means not opened 1 means opened
	private ImageView imageCover,imageOpen;
	public GridButton(int i) {
		state=0; 
		imageCover = new ImageView(new Image("file:res/cover.png"));
		if(i != 9) {
			imageOpen  = new ImageView(new Image("file:res/" + i + ".png"));
		}else {
			imageOpen = new ImageView(new Image("file:res/mine-grey.png"));
		}
		
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public ImageView getImageCover() {
		return imageCover;
	}
	public void setImageCover(ImageView imageCover) {
		this.imageCover = imageCover;
	}
	public ImageView getImageOpen() {
		return imageOpen;
	}
	public void setImageOpen(ImageView imageOpen) {
		this.imageOpen = imageOpen;
	}
	
}


class CustomPane extends Pane {
	//private Label label;
	private double paneHeight = 60;
	private double paneWidth = 75;
	private double labelHeight = 20;
	private double labelWidth = 30;
	
	public CustomPane() {
		
	}
	public CustomPane(String title) {
		this.setPrefHeight(paneHeight);
		this.setPrefWidth(paneWidth);
		Label label = new Label(title);
		label.setLayoutX((paneWidth - labelWidth)/2);
		label.setLayoutY((paneHeight - labelHeight)/2);
		getChildren().add(label);
	}
	public double getPaneHeight() {
		return paneHeight;
	}
	public void setPaneHeight(double paneHeight) {
		this.paneHeight = paneHeight;
	}
	public double getLabelHeight() {
		return labelHeight;
	}
	public void setLabelHeight(double labelHeight) {
		this.labelHeight = labelHeight;
	}
	
}


class SmileyFace extends Button {
	private int state;//state 0 is default 1 is when you die 2 is when you win
	private ImageView face;
	public SmileyFace(int i) {
		state=i; 
		
		if(i == 0) {
			face = new ImageView(new Image("file:res/face-smile.png"));
		}else if(i == 1) {
			face  = new ImageView(new Image("file:res/face-dead.png"));
		}else {
			face = new ImageView(new Image("file:res/face-win.png"));
		}
		
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public ImageView getFace() {
		return face;
	}
	public void setFace(ImageView face) {
		this.face = face;
	}
	
}


