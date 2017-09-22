package cell;

import java.util.ArrayList;

/**
 * This is the cell superclass that has abstract cell method shared by all cell subclass
 * 
 * @author estellehe
 *
 */
public abstract class Cell {
	protected int myrow;
	protected int mycol;
	protected int mystate;
	protected int[] mygrid;
	protected int mynextRow;
	protected int mynextCol;
	protected int mynextState;
	
	/**
	 * constructor for cell superclass
	 * @param row
	 * @param column
	 * @param state
	 * @param gridSize is the {row, col} of current grid, used to determine whether on the edge
	 */
	public Cell(int row, int column, int state, int[] gridSize) {
		myrow = row;
		mycol = column;
		mystate = state;
		mygrid = gridSize;
	}
	
	/**
	 * access column
	 * @return column
	 */
	public int column() {
		return mycol;
	}
	
	/**
	 * access row
	 * @return row
	 */
	public int row() {
		return myrow;
	}
	
	/**
	 * access state
	 * @return state
	 */
	public int state() {
		return mystate;
	}
	
	/**
	 * check if the cell is at edge
	 * @return
	 */
	public boolean isEdge() {
		return (myrow == 1 || mycol == 1 || myrow == mygrid[0] || mycol == mygrid[1]);
	}
	
	/**
	 * update cell position and state depending on the neighborlist input
	 * 
	 * store update info
	 * @param neighborlist
	 */
	public abstract void updateInfo(ArrayList<Cell> neighborlist);
	
	/**
	 * execute the update information on the cell
	 */
	public void update() {
		myrow = mynextRow;
		mycol = mynextCol;
		mystate = mynextState;
	}

}
