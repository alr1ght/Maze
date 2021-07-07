package mihai.alexandru.entities;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    Position position;
    String[][] visualMaze;
    List<Position> path = new ArrayList<>();

    public Maze(Position position, String[][] visualMaze) {
        this.position = position;
        this.visualMaze = visualMaze;

        path.add(new Position(0, this.getSize() / 2));
    }

    public Position getPosition() {
        return position;
    }

    public List<Position> getPath() {
        return path;
    }

    public int getSize() {
        return position.getX();
    }

    public void updatePath(Position position) {
        path.add(position);
    }

    public void clearPath() {
        path.clear();
    }

    public String[][] getMaze() {
        return visualMaze;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
