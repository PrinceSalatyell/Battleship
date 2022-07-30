package battleship;

import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[][] field = new char[10][10];
    static char[][] emptyField = new char[10][10];
    static int numberOfCells = 17;

    static void drawGame() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char c = 'A';
        for (char[] x : field) {
            System.out.print(c++);
            for (char y : x) {
                System.out.print(" " + y);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void drawEmptyGame() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char c = 'A';
        for (char[] x : emptyField) {
            System.out.print(c++);
            for (char y : x) {
                System.out.print(" " + y);
            }
            System.out.println();
        }
        System.out.println();
    }

    static String enterShips(int cells, String current, Ship s) {
        String ship;
        while (true) {
            ship = scanner.nextLine();
            if (ship.length() >= 5 && ship.length() <= 7)
                if (ship.charAt(0) >= 'A' && ship.charAt(0) <= 'J' && ship.split(" ")[1].charAt(0) >= 'A' && ship.split(" ")[1].charAt(0) <= 'J')
                    if (ship.charAt(1) >= '1' && ship.charAt(1) <= '9' && ship.split(" ")[1].charAt(1) >= '1' && ship.split(" ")[1].charAt(1) <= '9')
                        if ((ship.split(" ")[0].length() == 3 && ship.split(" ")[0].charAt(2) == '0') || ship.split(" ")[0].length() == 2)
                            if ((ship.split(" ")[1].length() == 3 && ship.split(" ")[1].charAt(2) == '0') || ship.split(" ")[1].length() == 2) {
                                if (ship.split(" ")[0].charAt(0) != ship.split(" ")[1].charAt(0) && !(ship.split(" ")[0].substring(1).equals(ship.split(" ")[1].substring(1)))) {
                                    System.out.println("Error! Wrong ship location! Try again:");
                                    continue;
                                }
                                if (Math.abs(ship.split(" ")[0].charAt(0) - ship.split(" ")[1].charAt(0)) + 1 != cells && Math.abs(Integer.parseInt(ship.split(" ")[0].substring(1)) - Integer.parseInt(ship.split(" ")[1].substring(1))) + 1 != cells) {
                                    System.out.println("Error! Wrong length of the " + current + "! Try again:");
                                    continue;
                                } else {
                                    boolean vertOrHoriz = ship.split(" ")[0].charAt(0) != ship.split(" ")[1].charAt(0);
                                    boolean forwardOrBackwards = ship.split(" ")[0].charAt(0) < ship.split(" ")[1].charAt(0) || Integer.parseInt(ship.split(" ")[0].substring(1)) < Integer.parseInt(ship.split(" ")[1].substring(1));
                                    int a = Character.getNumericValue(ship.split(" ")[0].charAt(0)) - 10;
                                    int b = Integer.parseInt(ship.split(" ")[0].substring(1)) - 1;
                                    boolean isValid = true;
                                    int x = cells;
                                    while (x-- > 0) {
                                        for (int i = a - 1; i < a + 2; i++) {
                                            if (i < 0)
                                                continue;
                                            if (i > 9)
                                                break;
                                            for (int j = b - 1; j < b + 2; j++) {
                                                if (j < 0)
                                                    continue;
                                                if (j > 9)
                                                    break;
                                                if (field[i][j] == 'O') {
                                                    isValid = false;
                                                    break;
                                                }
                                            }
                                            if (!isValid)
                                                break;
                                        }
                                        if (!isValid)
                                            break;
                                        if (vertOrHoriz && forwardOrBackwards)
                                            a++;
                                        else if (vertOrHoriz)
                                            a--;
                                        else if (forwardOrBackwards)
                                            b++;
                                        else
                                            b--;
                                    }
                                    if (!isValid) {
                                        System.out.println("Error! You placed it too close to another one. Try again:");
                                        continue;
                                    } else {
                                        if (vertOrHoriz && forwardOrBackwards) {
                                            for (int i = 0; i < cells; i++) {
                                                String str = ship.split(" ")[0].substring(1);
                                                char c = (char) (ship.charAt(0) + i);
                                                s.setCoordinates(i, String.valueOf(c).concat(str));
                                            }
                                        } else if (vertOrHoriz) {
                                            for (int i = 0; i < cells; i++) {
                                                String str = ship.split(" ")[0].substring(1);
                                                char c = (char) (ship.charAt(0) - i);
                                                s.setCoordinates(i, String.valueOf(c).concat(str));
                                            }
                                        } else if (forwardOrBackwards) {
                                            for (int i = 0; i < cells; i++) {
                                                String str = ship.split(" ")[0].substring(0, 1);
                                                int n = Integer.parseInt(ship.split(" ")[0].substring(1)) + i;
                                                s.setCoordinates(i, str.concat(String.valueOf(n)));
                                            }
                                        } else {
                                            for (int i = 0; i < cells; i++) {
                                                String str = ship.split(" ")[0].substring(0, 1);
                                                int n = Integer.parseInt(ship.split(" ")[0].substring(1)) - i;
                                                s.setCoordinates(i, str.concat(String.valueOf(n)));
                                            }
                                        }
                                        return ship;
                                    }
                                }
                            }
            System.out.println("Error! Invalid input! Try again:");
        }
    }

    static void putShip(String coordinate, int cells) {
        boolean vertOrHoriz = coordinate.split(" ")[0].charAt(0) != coordinate.split(" ")[1].charAt(0);
        boolean forwardOrBackwards = coordinate.split(" ")[0].charAt(0) < coordinate.split(" ")[1].charAt(0) || Integer.parseInt(coordinate.split(" ")[0].substring(1)) < Integer.parseInt(coordinate.split(" ")[1].substring(1));
        int a = Character.getNumericValue(coordinate.split(" ")[0].charAt(0)) - 10;
        int b = Integer.parseInt(coordinate.split(" ")[0].substring(1)) - 1;
        while (cells-- > 0) {
            field[a][b] = 'O';
            if (vertOrHoriz && forwardOrBackwards)
                a++;
            else if (vertOrHoriz)
                a--;
            else if (forwardOrBackwards)
                b++;
            else
                b--;
        }
        System.out.println();
        drawGame();
    }

    static void takeShot(Ship[] ships) {
        String shot;
        while (true) {
            shot = scanner.nextLine();
            if (((shot.length() == 3 && shot.charAt(2) == '0') || shot.length() == 2) && shot.charAt(0) >= 'A' && shot.charAt(0) <= 'J' && shot.charAt(1) >= '1' && shot.charAt(1) <= '9' && Integer.parseInt(shot.substring(1)) >= 1 && Integer.parseInt(shot.substring(1)) <= 10)
                break;
            System.out.println("Error! Wrong input. Try again:");
        }
        int a = Character.getNumericValue(shot.charAt(0)) - 10;
        int b = Integer.parseInt(shot.substring(1)) - 1;
        if (field[a][b] == '~' || field[a][b] == 'M') {
            field[a][b] = 'M';
            emptyField[a][b] = 'M';
            System.out.println();
            drawEmptyGame();
            System.out.println("You missed! Try Again:");
        } else if (field[a][b] == 'O' && numberOfCells == 1) {
            field[a][b] = 'X';
            emptyField[a][b] = 'X';
            numberOfCells--;
        } else if (field[a][b] == 'O') {
            boolean sunk = false;
            field[a][b] = 'X';
            emptyField[a][b] = 'X';
            numberOfCells--;
            for (Ship ship : ships) {
                for (String c : ship.coordinates) {
                    if (c.equals(shot)) {
                        ship.cells--;
                        if (ship.cells == 0)
                            sunk = true;
                    }
                }
            }
            System.out.println();
            drawEmptyGame();
            if (sunk)
                System.out.println("You sank a ship! Specify a new target:");
            else
                System.out.println("You hit a ship! Try Again:");
        } else {
            field[a][b] = 'X';
            emptyField[a][b] = 'X';
            System.out.println();
            drawEmptyGame();
            System.out.println("You hit a ship! Try Again:");
        }
    }

    public static void main(String[] args) {
        for (char[] chars : field) {
            Arrays.fill(chars, '~');
        }
        for (char[] chars : emptyField) {
            Arrays.fill(chars, '~');
        }
        drawGame();

        Ship aircraft = new Ship("Aircraft Carrier", 5);
        Ship battleship = new Ship("Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship destroyer = new Ship("Destroyer", 2);
        Ship[] ships = {aircraft, battleship, submarine, cruiser, destroyer};
        for (Ship x : ships) {
            System.out.println("Enter the coordinates of the " + x.getName() + " (" + x.getCells() + " cells):");
            x.setPosition(enterShips(x.getCells(), x.getName(), x));
            putShip(x.getPosition(), x.getCells());
        }
        System.out.println("The game starts!");
        System.out.println();
        drawEmptyGame();
        System.out.println("Take a shot!");
        while (numberOfCells > 0) {
            takeShot(ships);
        }
        System.out.println();
        drawGame();
        System.out.println("You sank the last ship. You won. Congratulations!");
        scanner.close();
    }
}
