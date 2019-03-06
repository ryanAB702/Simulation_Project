package Controller;

import Model.Cell;

import java.util.ArrayList;
import java.util.List;

public class CornerNeighbors extends Neighbors {
    public CornerNeighbors(int x, int y, Cell[][] grid, CellShape shape, EdgeType edgeType){
        super(x, y, grid, shape, edgeType);
    }

    @Override
    public List<Cell> squareNeighbors(Cell[][] cellGrid, int myX, int myY){
        List<Cell> neighbors = new ArrayList<>();

        for (int i = myX - 1; i < myX + 2; i++) {
            for (int j = myY - 1; j < myY + 2; j++) {
                if (i != myX && j != myY) {
                    Cell temp = edgeCheck(cellGrid, i, j);
                    if (temp != null) {
                        neighbors.add(temp);
                    }
                }
            }
        }
        return neighbors;
    }

    //HEX NEIGHBORS SAME FOR COMPLETE CARDINAL AND CORNER THEREFORE METHOD IN ABSTRACT NEIGHBORS CLASS

    @Override
    public List<Cell> triNeighbors(Cell[][] cellGrid, int x, int y) {
        if(upsideDown()){//upside down triangle - 5,4,3
            return upsideDownNeighbors(cellGrid, x, y);
        }
        else{
            return rightSideUpNeighbors(cellGrid, x, y);
        }
    }

    private List<Cell> upsideDownNeighbors(Cell[][] cellGrid, int myX, int myY){
        List<Cell> neighbors = new ArrayList<>();

        for(int i=myX-1; i<myX+2; i++){
            for(int j=myY-2; j<myY+3; j++){
                if(upsideDownHelper(i, j, myX, myY)){
                    Cell temp = edgeCheck(cellGrid, i, j);
                    if(temp!=null){
                        neighbors.add(temp);
                    }
                }
            }
        }

        return neighbors;
    }

    private List<Cell> rightSideUpNeighbors(Cell[][] cellGrid, int myX, int myY){
        List<Cell> neighbors = new ArrayList<>();

        for(int i=myX-1; i<myX+2; i++){
            for(int j=myY-2; j<myY+3; j++){
                if(righSideUpHelper(i, j, myX, myY)){
                    Cell temp = edgeCheck(cellGrid, i, j);
                    if(temp!=null){
                        neighbors.add(temp);
                    }
                }
            }
        }

        return neighbors;
    }

    private boolean upsideDownHelper(int i, int j, int myX, int myY){
        //Check not this, not cardinal above, not cardinal left/right, not below too left/too right
        return ((i!=myX || j!=myY) && (i!=myX-1 || j!=myY) && (i!=myX || (j!=myY-1 && j!=myY+1)) && (i!=myX+1 || (j!=myY-2 && j!=myY+2)));
    }

    private boolean righSideUpHelper(int i, int j, int myX, int myY){
        return ((i!=myX || j!=myY) && (i!=myX || (j!=myY-1 && j!=myY+1)) && (i!=myX+1 || j!=myY) && (i!=myX-1 || (j!=myY-2 && j!=myY+2)));
    }
}
