package Lesson4.Homework4;

import java.util.Random;
import java.util.Scanner;
// Задани № 1
public class TicTacToe {
    private static final int SIZE = 3;
    private static char DOT_HUMAN = 'X';
    private static char DOT_EMPTY = '•';
    private static char DOT_AI = 'O';

    public static final String HEADER_FIRST_SYMBOL = "♥";
    public static final String SPACE_MAP_SYMBOL = " ";

    private static final char [][] MAP = new char[SIZE][SIZE];

    private static final Scanner in = new Scanner(System.in);
    private static final Random random = new Random();

    private static int turnsCount = 0;


    public static void main(String[] args) {
        turnGame();
    }

    public static void turnGame() {
        initMap();
        printMap();
        playGame();
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap(){
        printHeaderMap();
        printBodyMap();
    }

    private static void printHeaderMap() {
        System.out.print(HEADER_FIRST_SYMBOL + SPACE_MAP_SYMBOL);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i + 1 + SPACE_MAP_SYMBOL);
    }

    private static void printBodyMap() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + SPACE_MAP_SYMBOL);
            }
            System.out.println();

        }

    }

    private static void playGame() {
        while (true) {
            humanTurn();
            printMap();
            if (checkEnd(DOT_HUMAN)) {
                break;
            }
            aiTurn();
            printMap();
            if (checkEnd(DOT_AI)) {
                break;
            }
        }
    }

    private static void humanTurn() {
        System.out.println("\nХОД ЧЕЛОВЕКА!");

        int rowNumber;
        int columnNumber;

        while (true) {
            System.out.print("Введите координату строки: ");
            rowNumber = in.nextInt() - 1;
            System.out.print("Введите координату столбца: ");
            columnNumber = in.nextInt() - 1;
            if (isCellFree(columnNumber, rowNumber)) {
                break;
            }
            System.out.printf("ОШИБКА! ЯЧЕЙКА С КООРДИНАТАМИ %s:%s ЗАНЯТА%n", rowNumber + 1, columnNumber + 1);
        }
        MAP[rowNumber][columnNumber] = DOT_HUMAN;
        turnsCount++;

    }

    private static boolean isCellFree(int columnNumber, int rowNumber) {
        return MAP[rowNumber][columnNumber] == DOT_EMPTY;
    }

    public static boolean checkEnd(char symbol) {
        if(checkWin(symbol)) {
            if(symbol == DOT_HUMAN) {
                System.out.println("Ура! Вы победили!");
            } else {
                System.out.println("Победил компьютер!");
            }
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    // Задание 2
    private static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            int row = 0;
            int col = 0;

            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == symbol) {
                    row++;
                } else {
                    row = 0;
                }
                if (row == SIZE) {
                    return true;

                }
                if (MAP[j][i] == symbol) {
                    col++;
                } else {
                    col = 0;
                }
                if (col == SIZE) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDraw() {
        return turnsCount >= SIZE * SIZE;
    }

    private static void aiTurn() {
        System.out.println("\nХод компьютера!");
        int rowNumber;
        int columnNumber;
        do {
            rowNumber = random.nextInt(SIZE);
            columnNumber = random.nextInt(SIZE);
        } while (!isCellFree(rowNumber, columnNumber));

        MAP[rowNumber][columnNumber] = DOT_AI;
        turnsCount++;
    }
}

