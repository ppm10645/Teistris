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
public class LPiece extends Piece{

    public LPiece(Game game) {
        super(game);
        this.squares = new Square[4];
        
        squares[0] = new Square(Game.MAX_X / 2-Game.SQUARE_SIDE , 0, Color.GREEN, game);
        squares[1] = new Square(Game.MAX_X / 2-Game.SQUARE_SIDE, Game.SQUARE_SIDE, Color.GREEN, game);
        squares[2] = new Square(Game.MAX_X / 2-Game.SQUARE_SIDE, 2*Game.SQUARE_SIDE, Color.GREEN, game);
        squares[3] = new Square(Game.MAX_X / 2, 2*Game.SQUARE_SIDE, Color.GREEN, game);
    }
}
