package mihai.alexandru.entities;

public enum Orientation {
    UP(1),
    RIGHT(2),
    LEFT(3),
    DOWN(4);


    Orientation(int numValue) {
        this.numValue = numValue;
    }

    int numValue;

    public int getNumValue() {
        return numValue;
    }

    public static Orientation getOrientationByNumber(int number) throws Exception {
        if (number >= 1 && number <= 4) {
            switch (number) {
                case 1:
                    return Orientation.UP;
                case 2:
                    return Orientation.RIGHT;
                case 3:
                    return Orientation.LEFT;
                case 4:
                    return Orientation.DOWN;
            }
        }

        throw new Exception(String.format("Invalid number \"%d\". Only numbers between 1 and 4 accepted", number));
    }

    public static Position getNewPositionByOrientation(Cursor cursor) {
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

        return cursor.getPosition();
    }
}
