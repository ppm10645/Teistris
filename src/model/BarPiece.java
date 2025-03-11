/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

/**
 *
 * @author joao.pedro.pereira
 */
public class BarPiece extends Piece {

    public BarPiece(Game game) {
        super(game);
        this.squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2, 0, Color.YELLOW, game);
        squares[2] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.YELLOW, game);
        squares[1] = new Square(Game.MAX_X / 2, 2 * Game.SQUARE_SIDE, Color.YELLOW, game);
        squares[3] = new Square(Game.MAX_X / 2, 3 * Game.SQUARE_SIDE, Color.YELLOW, game);
    }

    @Override
    public boolean rotate() {
        //Seleccionamos o cadrado donde pivotar a peza
        Square pivot = squares[1];
        //Array temporal das novas posicions
        int[][] newPositions = new int[4][2];

        //Verificamos si está en posicion horizontal
        boolean isHorizontal = squares[0].getY() == pivot.getY();

        if (isHorizontal) {
            for (int i = 0; i < 4; i++) {
                newPositions[i][0] = pivot.getX();
                newPositions[i][1] = pivot.getY() - (i - 1) * Game.SQUARE_SIDE;
            }
        } else {
            // Pasamos de vertical a horizontal
            for (int i = 0; i < 4; i++) {
                newPositions[i][0] = pivot.getX() + (i - 1) * Game.SQUARE_SIDE; // Distribución horizontal
                newPositions[i][1] = pivot.getY(); // Misma Y para todos (fila)
            }
        }

        //Verificamos si la rotación es válida
        for (int i = 0; i < 4; i++) {
            if (!game.isValidPosition(newPositions[i][0], newPositions[i][1])) {
                return false;
            }
        }

        for (int i = 0; i < 4; i++) {
            squares[i].setX(newPositions[i][0]);
            squares[i].setY(newPositions[i][1]);
        }

        return true;
    }
}
