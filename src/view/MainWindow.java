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
package view;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Game;
import javax.swing.Timer;

/**
 * Clase que implementa a ventá principal do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class MainWindow extends javax.swing.JFrame implements ActionListener, KeyEventDispatcher {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
//        KeyboardFocusManager manager
//                = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//        manager.addKeyEventDispatcher(this);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(evt -> {
            if (evt.getID() == KeyEvent.KEY_PRESSED) {

                // indicamos que fai cada tecla
                switch (evt.getKeyCode()) {
                    // Rotar peza con control
                    case KeyEvent.VK_UP:
                        if (game != null) {
                            game.rotatePiece();
                        }
                        break;
                    // Mover para abaixo
                    case KeyEvent.VK_DOWN:
                        if (game != null) {
                            game.movePieceDown();
                        }
                        break;
                    // Mover para a esquerda
                    case KeyEvent.VK_LEFT:
                        if (game != null) {
                            game.movePieceLeft();
                        }
                        break;
                    // Mover para a dereita
                    case KeyEvent.VK_RIGHT:
                        if (game != null) {
                            game.movePieceRight();
                        }
                        break;
                }
            }
            return false; // Para que otros componentes también reciban eventos de teclado
        });

    }

    private Game game = null; // Referenza ao obxecto do xogo actual

    private final int initialDelay = 1000;
    private Timer timer = new Timer(initialDelay, this); //Timer para indicar os ticks do game

    private static final int LINES_THRESHOLD = 10; //Numero de liñas necesarias para aumentar a velocidade de baixada
    

    private boolean gamePaused = false;

    /**
     * Pinta un cadrado no panel de cadrados
     *
     * @param lblSquare Etiqueta co cadrado que se quere pintar no panel
     */
    public void drawSquare(JLabel lblSquare) {
        pnlGame.add(lblSquare);
        pnlGame.repaint();
    }

    /**
     * Borra un cadrado do panel de cadrados
     *
     * @param lblSquare Etiqueta co cadrado que se quere borrar do panel
     */
    public void deleteSquare(JLabel lblSquare) {
        pnlGame.remove(lblSquare);
        pnlGame.repaint();
    }

    /**
     * Actualiza na ventá o número de liñas que van feitas no xogo
     *
     * @param numberOfLines Número de liñas feitas no xogo
     */
    public void showNumberOfLines(int numberOfLines) {
        lblNumberOfLines.setText(String.valueOf(numberOfLines));

        //Verificación para comprobar se alcanzou o limite de liñas para aumentar a dificuldade 
        if (numberOfLines % LINES_THRESHOLD == 0) {
            int currentDelay = timer.getDelay();//Obten o timer actual
            int newDelay = currentDelay / 2;//Reduce a metade o contador
            timer.setDelay(newDelay);//Acutaliza o timer
        }
    }

    /**
     * Mostra unha mensaxe informando ao usuario do final do xogo
     */
    public void showGameOver() {
        game = null;
        timer.stop();
        JOptionPane.showMessageDialog(this, "Fin do xogo");

    }

    /**
     * Inicia un novo xogo
     */
    private void startGame() {
        // Limpamos todo o que puidese haber pintado no panel do xogo
        pnlGame.removeAll();
        // Creamos un novo obxecto xogo
        game = new Game(this);
        // Desactivamos o botón de pausa
        tglbtnPause.setSelected(false);
        // Establecemos o número de liñas que se mostran na ventá a cero
        lblNumberOfLines.setText("0");

        timer.start();
        timer.setDelay(initialDelay);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNewGame = new javax.swing.JButton();
        pnlGame = new javax.swing.JPanel();
        btnRotate = new javax.swing.JButton();
        btnRight = new javax.swing.JButton();
        btnLeft = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        tglbtnPause = new javax.swing.JToggleButton();
        lblLines = new javax.swing.JLabel();
        lblNumberOfLines = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Teistris");
        setLocation(new java.awt.Point(150, 300));
        setResizable(false);

        btnNewGame.setText("Nova partida");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        pnlGame.setBackground(java.awt.Color.white);
        pnlGame.setPreferredSize(new java.awt.Dimension(240, 300));
        pnlGame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pnlGameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        btnRotate.setText("Rotar");
        btnRotate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotateActionPerformed(evt);
            }
        });

        btnRight.setText("Dereita");
        btnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });

        btnLeft.setText("Esquerda");
        btnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });

        btnDown.setText("Abaixo");
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });

        tglbtnPause.setText("Pausa");
        tglbtnPause.setToolTipText("");
        tglbtnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglbtnPauseActionPerformed(evt);
            }
        });

        lblLines.setText("Liñas:");

        lblNumberOfLines.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel1.setText("Joao Pedro Pereira Martins");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLines, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNumberOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnRotate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tglbtnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewGame)
                    .addComponent(tglbtnPause))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumberOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLines)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnLeft))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnDown))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnRight))
                    .addComponent(btnRotate))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        // Ao picar no botón de "Nova partida", invocamos ao método privado 
        // que inicia un novo xogo
        startGame();
    }//GEN-LAST:event_btnNewGameActionPerformed

    private void tglbtnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglbtnPauseActionPerformed
        // Ao picar no botón de "Pausa", chamamos ao obxecto xogo para 
        // establecer o atributo de pausa no estado do botón
        if (game != null) {
            game.setPaused(tglbtnPause.isSelected());
        }

        gamePaused = !gamePaused;
    }//GEN-LAST:event_tglbtnPauseActionPerformed

    private void btnRotateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotateActionPerformed
        // Ao picar no botón de "Rotar", chamamos ao obxecto xogo para que 
        // rote a peza actual
        if (game != null) {
            game.rotatePiece();
        }
    }//GEN-LAST:event_btnRotateActionPerformed

    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        // Ao picar no botón de "Dereita", chamamos ao obxecto xogo para que
        // se mova a peza actual á dereita
        if (game != null) {
            game.movePieceRight();
        }
    }//GEN-LAST:event_btnRightActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        // Ao picar no botón de "Abaixo", chamamos ao obxecto xogo para que
        // se mova a peza actual cara abaixo
        if (game != null) {
            game.movePieceDown();
        }
    }//GEN-LAST:event_btnDownActionPerformed

    private void pnlGameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlGameKeyTyped
        // TODO add your handling code here:
        if (evt.getID() == KeyEvent.KEY_TYPED) {
            System.out.println("typed " + evt.getKeyChar());
        }
    }//GEN-LAST:event_pnlGameKeyTyped

    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        // TODO add your handling code here:
        if (game != null) {
            game.movePieceLeft();
        }
    }//GEN-LAST:event_btnLeftActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnLeft;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnRight;
    private javax.swing.JButton btnRotate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLines;
    private javax.swing.JLabel lblNumberOfLines;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JToggleButton tglbtnPause;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        btnDownActionPerformed(e);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        System.out.println("typed" + e.getKeyCode());
        if (e.getKeyChar() == 'a') {
            game.movePieceLeft();
        }
        return false;
    }
}
