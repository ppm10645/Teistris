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

import view.MainWindow;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que implementa o comportamento do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class Game {

    /**
     * Constante que define o tamaño en pixels do lado dun cadrado
     */
    public final static int SQUARE_SIDE = 20;
    /**
     * Constante que define o valor máximo da coordenada x no panel de cadrados
     */
    public final static int MAX_X = 240;

    /**
     * Constante que define o valor máximo da coordenada y no panel de cadrados
     */
    public final static int MAX_Y = 300;

    /**
     * Referenza á peza actual do xogo, que é a única que se pode mover
     */
    private Piece currentPiece;

    /**
     * Referenza á ventá principal do xogo
     */
    private MainWindow mainWindow;

    /**
     * Manexo dos cadrados cando caen no chan
     */
    private Map<String, Square> groundSquares;

    /**
     * Flag que indica se o xogo está en pausa ou non
     */
    private boolean paused = false;

    /**
     * Número de liñas feitas no xogo
     */
    private int numberOfLines = 0;

    /**
     * @return Referenza á ventá principal do xogo
     */
    public MainWindow getMainWindow() {
        return mainWindow;
    }

    /**
     * @param mainWindow Ventá principal do xogo a establecer
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * @return O estado de pausa do xogo
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused O estado de pausa a establecer
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * @return Número de liñas feitas no xogo
     */
    public int getNumberOfLines() {
        return numberOfLines;
    }

    /**
     * @param numberOfLines O número de liñas feitas no xogo
     */
    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    /**
     * Construtor da clase, que crea unha primeira peza
     *
     * @param mainWindow Referenza á ventá principal do xogo
     */
    public Game(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.createNewPiece();
        this.groundSquares = new HashMap<>();
    }

    /**
     * Move a ficha actual á dereita, se o xogo non está pausado
     */
    public void movePieceRight() {
        if (!paused) {
            currentPiece.moveRight();
        }
    }

    /**
     * Move a ficha actual á esquerda, se o xogo non está pausado
     */
    public void movePieceLeft() {
        if (!paused) {
            currentPiece.moveLeft();
        }
    }

    /**
     * Rota a ficha actual, se o xogo non está pausado
     */
    public void rotatePiece() {
        if (!paused) {
            currentPiece.rotate();
        }
    }

    /**
     * Move a peza actual abaixo, se o xogo non está pausado Se a peza choca con
     * algo e xa non pode baixar, pasa a formar parte do chan e créase unha nova
     * peza
     */
    public void movePieceDown() {
        if ((!paused) && (!currentPiece.moveDown())) {
            this.addPieceToGround();
            this.createNewPiece();
            if (this.hitPieceTheGround()) {
                this.mainWindow.showGameOver();
            }
        }
    }

    /**
     * Método que permite saber se unha posición x,y é válida para un cadrado
     *
     * @param x Coordenada x
     * @param y Coordenada y
     * @return true se esa posición é válida, se non false
     */
    public boolean isValidPosition(int x, int y) {
        if ((x == MAX_X) || (x < 0) || (y == MAX_Y)) {
            return false;
        }
        //Crea a clave para verificar a existencia dun cadrado en esa posición
        String key = x + "," + y;
        //Devolve false se xa hai un cadrado en esa posición
        return !groundSquares.containsKey(key);
    }

    /**
     * Crea unha nova peza e a establece como peza actual do xogo
     */
    private void createNewPiece() {
        int pieceType = new java.util.Random().nextInt(4);
        if (pieceType == 0) {
            currentPiece = new SquarePiece(this);
        } else if (pieceType == 1) {
            currentPiece = new LPiece(this);
        } else if (pieceType == 2) {
            currentPiece = new BarPiece(this);
        } else if (pieceType == 3) {
            currentPiece = new TPiece(this);
        }
    }

    /**
     * Engade unha peza ao chan
     */
    private void addPieceToGround() {
        // Engadimos os cadrados da peza ao chan
        Square[] squares = currentPiece.getSquares();

        //Engadimos cada cadrado ao mapa groundSquares
        for (Square square : squares) {
            int x = square.getX();
            int y = square.getY();
            String key = x + "," + y;
            groundSquares.put(key, square);
        }

        // Chamamos ao método que borra as liñas do chan que estean completas
        this.deleteCompletedLines();
    }

    /**
     * Se os cadrados que están forman unha liña completa, bórranse eses
     * cadrados do chan e súmase unha nova liña no número de liñas realizadas
     */
    private void deleteCompletedLines() {
        //Revisamos todas las lineas posibles del tablero en dirección y de un extremo "0" a otro "MAX_Y"
        for (int y = 0; y < MAX_Y; y += SQUARE_SIDE) {
            boolean isComplete = true;

            //Revisamos si la linea está completa 
            for (int x = 0; x < MAX_X; x += SQUARE_SIDE) {
                String key = x + "," + y;
                if (!groundSquares.containsKey(key)) {
                    isComplete = false;
                }
            }

            //Eliminamos la linea que está completa
            if (isComplete) {
                this.deleteLine(y);
                numberOfLines++; //Pasamos a la siguiente linea
                mainWindow.showNumberOfLines(numberOfLines);
            }

        }
    }

    /**
     * Borra todos os cadrados que se atopan na liña indicada pola coordenada y,
     * e baixa todos os cadrados que estean situados por enriba unha posición
     * cara abaixo
     *
     * @param y Coordenada y da liña a borrar
     */
    private void deleteLine(int y) {
        // Eliminamos todos os cadrados da liña especificada
        for (int x = 0; x < MAX_X; x += SQUARE_SIDE) {
            String key = x + "," + y;
            Square sq = groundSquares.get(key);
            this.mainWindow.deleteSquare(sq.getLblSquare());
            groundSquares.remove(key);
        }
        // Baixamos todos os cadrados situados por enriba da liña borrada
        for (int currentY = y - SQUARE_SIDE; currentY >= 0; currentY -= SQUARE_SIDE) {
            for (int x = 0; x < MAX_X; x += SQUARE_SIDE) {
                String key = x + "," + currentY;
                if (groundSquares.containsKey(key)) {
                    Square square = groundSquares.remove(key); // Eliminamos o cadrado da súa posición actual
                    square.setY(square.getY() + SQUARE_SIDE); // Baixamos o cadrado unha posición
                    String newKey = x + "," + (currentY + SQUARE_SIDE);
                    groundSquares.put(newKey, square); // Engadimos o cadrado na nova posición
                }
            }
        }
    }

    /**
     * Comproba se a peza actual choca cos cadrados do chan
     *
     * @return true se a peza actual choca cos cadrados do chan; se non false
     */
    private boolean hitPieceTheGround() {
        // Polo momento, non facemos nada
        return false;
    }
}
