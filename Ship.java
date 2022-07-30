package battleship;

public class Ship {
    final String[] coordinates;
    private String position = null;
    int cells;
    private final String name;

    public Ship(String name, int cells) {
        this.name = name;
        this.cells = cells;
        this.coordinates = new String[cells];
    }

    void setPosition(String coordinates) {
        this.position = coordinates;
    }

    String getPosition() {
        return this.position;
    }

    int getCells() {
        return this.cells;
    }

    String getName() {
        return this.name;
    }

    void setCoordinates(int index, String coordinate) {
        this.coordinates[index] = coordinate;
    }

    String getCoordinates(int index) {
        return this.coordinates[index];
    }
}
