package mihai.alexandru;

import mihai.alexandru.entities.Cursor;
import mihai.alexandru.entities.Maze;
import mihai.alexandru.entities.Orientation;
import mihai.alexandru.entities.Position;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x, y;

        System.out.print("Enter maze dimensions (should be an odd number):\n> ");
        int in = scanner.nextInt();

        x = y = in % 2 == 0 ? in + 1 : in;

        scanner.close();

        try {
            printGeneratedMaze(generatePath(generateOperationalGrid(x, y)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
    public static void printGeneratedMaze(Maze maze) {
        for (int i = 0; i < maze.getPosition().getX(); i++) {
            System.out.print("\n");
            for (int j = 0; j < maze.getPosition().getY(); j++) {
                if ("1".equals(maze.getMaze()[i][j]) || i == 0 && j == maze.getSize() / 2) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            }
        }
    }
    public static Maze generateOperationalGrid(int x, int y) {
        String[][] grid = new String[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i % 2 != 0) {
                    grid[i][j] = "0";
                } else {
                    grid[i][j] = j % 2 == 0 ? "V" : "0";
                }
            }
        }

        return new Maze(new Position(x, y), grid);
    }
    public static Maze generatePath(Maze maze) throws Exception {
        int halfPoint = maze.getPosition().getX() / 2;
        Cursor cursor = new Cursor(new Position(1, halfPoint), Orientation.UP);

        Random random = new Random();

        String[][] grid = maze.getMaze();
        Orientation lastOrientation;

        while (cursor.getPosition().getX() < maze.getPosition().getX()
                && cursor.getPosition().getY() < maze.getPosition().getY()
                && cursor.getPosition().getX() >= 0
                && cursor.getPosition().getY() >= 0) {

            lastOrientation = cursor.getOrientation();

            grid[cursor.getPosition().getX()][cursor.getPosition().getY()] = "1";

            do {
                cursor.setOrientation(Orientation.getOrientationByNumber(random.nextInt(4 - 1 + 1) + 1));
            } while (cursor.getOrientation().equals(lastOrientation)); // TODO fix this (infinite loop)

            System.out.println(cursor.getPosition());

            switch (cursor.getOrientation().getNumValue()) {
                case 1 -> {
                    cursor.getPosition().setX(cursor.getPosition().getX() - 1); // UP
                    cursor.setOrientation(Orientation.DOWN);
                }
                case 2 -> {
                    cursor.getPosition().setY(cursor.getPosition().getY() + 1); // RIGHT
                    cursor.setOrientation(Orientation.LEFT);
                }
                case 3 -> {
                    cursor.getPosition().setY(cursor.getPosition().getY() - 1); // LEFT
                    cursor.setOrientation(Orientation.RIGHT);
                }
                case 4 -> {
                    cursor.getPosition().setX(cursor.getPosition().getX() + 1); // DOWN
                    cursor.setOrientation(Orientation.UP);
                }
            }

            cursor.setJoinedAt(lastOrientation); // TODO make "lastOrientation" not be null, since it doesn't update.

            grid[cursor.getPosition().getX()][cursor.getPosition().getY()] = "1"; // TODO fix X position going below 0. 

            maze.updatePath(cursor.getPosition());
        }

        return maze;
    }
    public static Maze generateSecondStage(Maze maze) throws Exception { // TODO implement second stage methods (generateSecondStage(), generateSecondaryPath())
        Random random = new Random();
        int currentPositionInList = 0;

        while (currentPositionInList < maze.getPath().size()) {
            currentPositionInList += random.nextInt(4 - 1 + 1) + 1;

            maze = generateSecondaryPath(maze, maze.getPath().get(currentPositionInList));
        }

        return maze;
    }
    public static Maze generateSecondaryPath(Maze maze, Position position) throws Exception {
        Cursor cursor = new Cursor(position);
        Random random = new Random();

        String[][] grid = maze.getMaze();

        int advanceCount = 0;
        int pathLength = maze.getSize() / 3;

        while (advanceCount < pathLength) {

            grid[cursor.getPosition().getX()][cursor.getPosition().getY()] = "1";

            do {
                cursor.setOrientation(Orientation.getOrientationByNumber(random.nextInt(4 - 1 + 1) + 1));
            } while (cursor.getOrientation().equals(cursor.getJoinedAt()));

            System.out.println(cursor.getPosition());

            switch (cursor.getOrientation().getNumValue()) {
                case 1 -> {
                    cursor.getPosition().setX(cursor.getPosition().getX() - 1); // UP
                    cursor.setOrientation(Orientation.DOWN);
                }
                case 2 -> {
                    cursor.getPosition().setY(cursor.getPosition().getY() + 1); // RIGHT
                    cursor.setOrientation(Orientation.LEFT);
                }
                case 3 -> {
                    cursor.getPosition().setY(cursor.getPosition().getY() - 1); // LEFT
                    cursor.setOrientation(Orientation.RIGHT);
                }
                case 4 -> {
                    cursor.getPosition().setX(cursor.getPosition().getX() + 1); // DOWN
                    cursor.setOrientation(Orientation.UP);
                }
            }

            grid[cursor.getPosition().getX()][cursor.getPosition().getY()] = "1";

            advanceCount++;
        }

        return new Maze(maze.getPosition(), grid);
    }
}