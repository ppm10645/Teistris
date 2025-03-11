/*
 * Copyright (C) 2019 Antonio de Andrés Lema
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import java.awt.Color;

/**
 * Clase que implementa a peza cadrada do xogo do Tetris
 *
 * @author Profe de Programación
 */
public abstract class Piece{

    /**
     * Referenza ao obxecto xogo
     */
    protected Game game;

    /**
     * Referenzas aos catro cadrados que forman a peza
     */
    protected Square[] squares;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    /**
     * Construtor da clase, que crea os catro cadrados que forman a peza
     *
     * @param game
     */
    public Piece(Game game) {  
        this.game = game;
    }
    
    

    /**
     * Metodo para mover la ficha a la derecha
     *
     * Verifica todos los cuadrados de la ficha se pueden mover a la derecha, y si puede mueve todos los cuadrados
     */
    public void moveRight() {
        //Verifico que todos los cuadrados pueden moverse
        for(Square square:squares) {
            if(!game.isValidPosition(square.getX() + Game.SQUARE_SIDE, square.getY())) {
                return; //Si alguna posición no es valida, no se mueve
            }
        }
        //Mueve todos los cuadrados a la derecha
        for(Square square : squares) {
            square.setX(square.getX() + Game.SQUARE_SIDE);
        }
    }

    /**
     * Metodo para mover la ficha a la izquieda
     *
     * Verifica todos los cuadrados de la ficha se pueden mover a la izquierda, y si puede mueve todos los cuadrados
     */
    public void moveLeft() {
        for(Square square:squares) {
            if(!game.isValidPosition(square.getX() - Game.SQUARE_SIDE, square.getY())) {
                return; //Si alguna posición no es valida, no se mueve
        }
    }
        //Mueve todos los cuadrados a la irquierda
        for(Square square:squares) {
            square.setX(square.getX() - Game.SQUARE_SIDE);
        }
    }

    /**
     * Move a ficha a abaixo se é posible
     *
     * Verifica todos los cuadrados de la ficha se pueden mover hacia abajo, y si puede mueve todos los cuadrados
     * @return
     */
    public boolean moveDown() {
        
                //Verifico que todos los cuadrados pueden moverse hacia abajo
        for(Square square:squares) {
            if(!game.isValidPosition(square.getX(), square.getY() + Game.SQUARE_SIDE)) {
                return false; //Si alguna posición no es valida, no se mueve
            }
        }
        //Mueve todos los cuadrados hacia abajo
        for(Square square : squares) {
            square.setY(square.getY() + Game.SQUARE_SIDE);
        }
        return true;
    }
    

    /**
     * Rota a ficha se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean rotate() {
        //Seleccionamos o cadrado donde pivotar a peza
        Square pivot = squares[1];
        //Array temporal das novas posicions
        int[][] newPositions = new int[4][2];

        for (int i = 0; i < squares.length; i++) {
            int relativeX = squares[i].getX() - pivot.getX();
            int relativeY = squares[i].getY() - pivot.getY();

            //Rotación
            int newX = -relativeY + pivot.getX();
            int newY = relativeX + pivot.getY();

            //Gardamos a posición
            newPositions[i][0] = newX;
            newPositions[i][1] = newY;
        }

        //Verificamos si la rotación es válida
        for (int i = 0; i < newPositions.length; i++) {
            if (!game.isValidPosition(newPositions[i][0], newPositions[i][1])) {
                return false;
            }
        }
            for (int i = 0; i < squares.length; i++) {
                squares[i].setX(newPositions[i][0]);
                squares[i].setY(newPositions[i][1]);
            }
        

        return true;
    }

}