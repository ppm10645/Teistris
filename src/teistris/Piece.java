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
package teistris;

import java.awt.Color;

/**
 * Clase que implementa a peza cadrada do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class Piece {

    /**
     * Referenza ao obxecto xogo
     */
    private Game game;

    /**
     * Referenzas aos catro cadrados que forman a peza
     */
    private Square[] squares;

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
        this.squares = new Square[4]; //Inicialización del array
        //Creación de los cuadrados y posicion
        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.BLUE, game);
        squares[1] = new Square(Game.MAX_X / 2, 0, Color.BLUE, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE, Color.BLUE, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.BLUE, game);
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
        // A rotación da ficha cadrada non supón ningunha variación na ficha,
        // por iso simplemente devolvemos true
        return true;
    }

}
