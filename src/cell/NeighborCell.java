package cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * handle all the neighbor cells related issue
 * 
 * @author estellehe
 *
 */
public class NeighborCell {
	private static final int FOURADJACENT = 4;
	private static final int EIGHTADJACENT = 8;
	
	private int myNeighborType;
	private boolean myIsTorus;
	private Cell myCell;
	
	/**
	 * constructor for neighborcell class
	 * 
	 * @param neighborType
	 * @param isTorus
	 * @param current
	 */
	public NeighborCell(int neighborType, boolean isTorus, Cell current) {
		myNeighborType = neighborType;
		myIsTorus = isTorus;
		myCell = current;
	}
	
	/**
	 * check if a cell is a neighbor cell
	 * 
	 * @param other
	 * @return whether a cell is the neighbor cell
	 */
	public boolean isNeighbor(Cell other) {
		int[] otherPos = {other.myrow, other.mycol};
		ArrayList<int[]> adjacentList = myCell.adjacent();
		for (int[] adjacent: adjacentList) {
			if (otherPos[0] == adjacent[0] && otherPos[1] == adjacent[1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * return list of adjacent empty position
	 * 
	 * @param neighborList
	 * @return list of empty position
	 */
	public ArrayList<int[]> emptyNeighbor(ArrayList<Cell> neighborList) {
		ArrayList<int[]> emptyList = adjacentPos();
		System.out.println("current");
		Iterator<Cell> iter = neighborList.iterator();
		if (iter.hasNext()) {
			Cell current = iter.next();
			int[] currentPos = {current.myrow, current.mycol};
			emptyList.remove(currentPos);
		}
		return emptyList;
	}
	
	/**
	 * get list of adjacent position
	 * 
	 * Currently only valid for eight/!torus/!move, four/torus
	 * 
	 * @return a list of adjacent positions in {row, col}
	 */
	public ArrayList<int[]> adjacentPos() {
		ArrayList<int[]> adjacentList;
		if (myNeighborType == FOURADJACENT) {
			adjacentList = fourAdjacent();
		}
		else {		//if (myNeighborType == EIGHTADJACENT)
			adjacentList = eightAdjacent();
		}
		return adjacentList;
	}
	
	/**
	 * implementation of adjacentPos for four adjacent
	 * 
	 * @return list of adjacent position
	 */
	private ArrayList<int[]> fourAdjacent() {
		ArrayList<int[]> adjacentList = new ArrayList<int[]>();
		int[] right = {myCell.myrow, myCell.mycol+1};
		int[] left = {myCell.myrow, myCell.mycol-1};
		int[] up = {myCell.myrow+1, myCell.mycol};
		int[] down = {myCell.myrow-1, myCell.mycol};
		
		if (myIsTorus && myCell.isEdge()) {
			if (myCell.myrow == 0) {
				up[0] = myCell.mygrid[0];
			}
			else if (myCell.myrow == myCell.mygrid[0]-1) {
				down[0] = 1;
			}
			if (myCell.mycol == 0) {
				left[1] = myCell.mygrid[1];
			}
			else if (myCell.mycol == myCell.mygrid[1]-1) {
				right[1] = 1;
			}
		}
		adjacentList.addAll(new ArrayList<int[]>(Arrays.asList(right, left, up, down)));
//		System.out.println(adjacentList.size());
		return adjacentList;
	}
	
	/**
	 * implementation of adjacentPos for eight adjacent 
	 * 
	 * do not include torus option right now
	 * 
	 * @return list of adjacent position
	 */
	private ArrayList<int[]> eightAdjacent() {
		ArrayList<int[]> adjacentList = new ArrayList<int[]>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				else {
					int[] pos = new int[2];
					pos[0] = myCell.myrow+i;
					pos[1] = myCell.mycol+j;
					adjacentList.add(pos);
				}
			}
		}
		return adjacentList;
	}

}
