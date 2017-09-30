package cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import simulation.SimulationLoop;

public class CellManager {
	
<<<<<<< HEAD
	private ResourceBundle myResources = ResourceBundle.getBundle("resources/Text");
	
	private static final int EMPTY = -1;
	private ArrayList<Cell> myCellList;
=======
	private List<Cell> myCellList;
>>>>>>> Integration
	private String mySimulationType;
	private String myEdgeType;
	private int[] myGridSize = new int[2];
	private Map<String,Double> myParaMap;
<<<<<<< HEAD
	private ArrayList<int[]> myEmptyPos;
	
=======
	private List<int[]> myEmptyPos;
>>>>>>> Integration

	/**
	 * constructor for cell manager, initialize mycelllist
	 */
	public CellManager() {
		myCellList = new ArrayList<Cell>();
		myEmptyPos = new ArrayList<int[]>();
		// TODO change type to enum
	}

	/**
	 * 
	 * @return arraylist of of all current cells
	 */
	public List<Cell> cellList() {
		return myCellList;
	}

	/**
	 * get the neighbor cell list for input cell
	 * 
	 * @param current
	 * @return arraylist of neighbor cell
	 */
	private List<Cell> getNeighborList(Cell current) {
		List<Cell> neighborList = new ArrayList<Cell>();
		for (Cell other: myCellList) {
			if (current.isNeighbor(other)) {
				neighborList.add(other);
			}
		}
		return neighborList;
	}

	/**
	 * update every cell created and stored in myCellList
	 */
	public void update() {
		List<Cell> newCellList = new ArrayList<Cell>();
		List<Cell> removeCellList = new ArrayList<Cell>();
		for (Cell current : myCellList) {
			current.updateInfo(getNeighborList(current), myEmptyPos);
		}
		for (Cell current: myCellList) {
			current.update(removeCellList, newCellList, myEmptyPos);
		}
		myCellList.addAll(newCellList);
		myCellList.removeAll(removeCellList);
	}


	/**
	 * initialize all cells based on the stateArray parsed by XML file and store
	 * them in mycelllist
	 * 
	 * @param stateArray
	 * @param type
	 * @param paraList
	 */
	public void initialize(int[][] stateArray, String edgeType, String simulationType, Map<String,Double> paraMap) {
		int row = stateArray.length;
		int col = stateArray[0].length;
		mySimulationType = simulationType;
		myEdgeType = edgeType;
		myGridSize = new int[2];
		myGridSize[0] = row;
		myGridSize[1] = col;
		myParaMap = paraMap;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (stateArray[i][j] != EMPTY) {
					Cell current = createCell(i, j, stateArray[i][j]);
					myCellList.add(current);
				}
				else {
					int[] empty = {i,j};
					myEmptyPos.add(empty);
					
				}
			}
		}
	}

	/**
	 * create cell based on the designated type
	 * 
	 * @param row
	 * @param col
	 * @param state
	 * @param gridSize
	 * @param type
	 * @return cell
	 */
	private Cell createCell(int row, int col, int state) {
		Cell current;
		switch(mySimulationType) {
			case "GameOfLife":
				current = new GameofLife(row, col, state, myGridSize, myParaMap, myEdgeType);
				break;
			case "PredatorPrey":
				current = new PredatorPrey(row, col, state, myGridSize, myParaMap, myEdgeType);
				break;
			case "Segregation":
				current = new Segregation(row, col, state, myGridSize, myParaMap, myEdgeType);
				break;
			case "Fire":
				current = new Fire(row, col, state, myGridSize, myParaMap, myEdgeType);
				break;
			default:
<<<<<<< HEAD
				current = new Fire(row, col, state, myGridSize, myParaMap);
				// showError();
=======
				current = new GameofLife(row, col, state, myGridSize, myParaMap, myEdgeType);
>>>>>>> Integration
				break;
		}
		return current;
	}
	
	
	/*
	 * Display error message.
	 */
	private void showError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(myResources.getString("ErrorSimulationType"));
		alert.showAndWait();
	}

}
