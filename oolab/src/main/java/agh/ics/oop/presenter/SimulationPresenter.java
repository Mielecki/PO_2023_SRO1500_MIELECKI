package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    WorldMap map;

    @FXML
    private GridPane mapGrid;
    @FXML
    private TextField moveList;
    @FXML
    private Label moveDescription;

    private static final int CELL_HEIGHT = 40;
    private static final int CELL_WIDTH = 40;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap(){
        clearGrid();
        Vector2d upperRight = map.getCurrentBounds().upperRight();
        Vector2d lowerLeft = map.getCurrentBounds().lowerLeft();

        for (int i = lowerLeft.getY(); i < upperRight.getY()+2; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
        for (int i = lowerLeft.getX(); i < upperRight.getX()+2; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }

        for(int i = lowerLeft.getY(); i < upperRight.getY() + 2;i++){
            Label label;
            if(i == upperRight.getY() + 1){
                label = new Label("y/x");
            } else{
                label = new Label("" + i);
            }
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label, 0, upperRight.getY() - i + 1);
        }


        for (int i = lowerLeft.getX(); i < upperRight.getX() + 1; i++) {
            Label label = new Label("" + i);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label, i - lowerLeft.getX() + 1, 0);

            for (int j = lowerLeft.getY(); j < upperRight.getY() + 1; j++) {
                WorldElement object = map.objectAt(new Vector2d(i, j));
                if(object != null) {
                    label = new Label(object.toString());
                    GridPane.setHalignment(label, HPos.CENTER);
                    mapGrid.add(label, i - lowerLeft.getX() + 1, upperRight.getY() - j + 1);
                }
            }
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });

    }

    public void onSimulationStartClicked() {
        List<MoveDirection> directions = OptionsParser.parse(moveList.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(1,2), new Vector2d(5, 4));
        GrassField map = new GrassField(10);
        map.addObserver(this);
        map.addObserver(new ConsoleMapDisplay());
        setWorldMap(map);
        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsync();
    }
}
