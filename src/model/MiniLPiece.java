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
public class MiniLPiece extends Piece{
    
    public MiniLPiece(Game game) {
        super(game);
        this.squares = new Square[3];
        
        squares[0] = new Square(Game.MAX_X / 2, 0, Color.CYAN, game);
        squares[1] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.CYAN, game);
        squares[2] = new Square(Game.MAX_X / 2+Game.SQUARE_SIDE, Game.SQUARE_SIDE, Color.CYAN, game);
    }
    
}
