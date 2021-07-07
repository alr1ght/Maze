package mihai.alexandru.entities;

public class Cursor {
    Orientation orientation;
    Orientation joinedAt;

    Position position;

    public Cursor(Position position) {
        this.position = position;
    }

    public Cursor(Position position, Orientation joinedAt) {
        this.position = position;
        this.joinedAt = joinedAt;
    }

    public Cursor(Position position, Orientation orientation, Orientation joinedAt) {
        this.position = position;
        this.orientation = orientation;
        this.joinedAt = joinedAt;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Orientation joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
}
