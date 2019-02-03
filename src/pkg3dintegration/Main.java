/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dintegration;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author Crows
 */
public class Main extends Application {
    private Scene scene;
    private Group root;
    private Group cameraBase;
    private Group cameraTilt;
    private Camera camera;
    private Function f;
    private Group axisGroup;
    
    @Override
    public void start(Stage primaryStage) {
        root = new Group();
        createAxis();
        createCamera();
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        //root = new StackPane();
        //root.getChildren().add(btn);
        
        scene = new Scene(root, 300, 250);
        scene.setCamera(camera);
        
        f = new Function(){
            @Override
            public double f(double x){
                return 100 * Math.cos(Math.PI * x / 50);
            }
        };
        
        
        axisGroup.getChildren().add(f.getIntegral(0, 100, 1, Math.PI * 2, 0.01));
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void createCamera(){
        camera = new PerspectiveCamera();
        cameraBase = new Group();
        cameraTilt = new Group();
        
        
        cameraBase.setTranslateZ(-1);
        cameraBase.setRotationAxis(Rotate.Y_AXIS);
        cameraBase.setRotate(45);
        
        cameraBase.getChildren().add(cameraTilt);
        cameraTilt.getChildren().add(camera);
        root.getChildren().add(cameraBase);
    }
    
    private void createAxis(){
        axisGroup = new Group();
        
        Box xAxis = new Box(100, 1, 1);
        Material red = new PhongMaterial(Color.RED);
        xAxis.setTranslateX(50);
        xAxis.setMaterial(red);
        axisGroup.getChildren().add(xAxis);
        
        Box yAxis = new Box(1, 100, 1);
        Material blue = new PhongMaterial(Color.BLUE);
        yAxis.setTranslateY(50);
        yAxis.setMaterial(blue);
        axisGroup.getChildren().add(yAxis);
        
        root.getChildren().add(axisGroup);
    }
    
    private void registerKeys(Scene scene, final Node root){
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
