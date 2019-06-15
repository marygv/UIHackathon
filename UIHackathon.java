/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uihackathon;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author maryg
 */
public class UIHackathon extends JFrame implements ActionListener, Runnable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /**
     * updates the board with the new generations of creatures
     */
    @Override
    public void run() {
        //only go through the loop while the start/stop/gameOver button is equal to stop so the board doesn't update before or after
        //the program is supposed to be running
        while (startStopButton.getText().equals("stop")) {
            try {
                Thread.sleep(SLEEP_TIME);
                //find which cells are occupied/alive or empty/dead the next generation
                creaturesAliveNextGeneration();
                for (int row = 0; row < boardSize; row++) {
                    for (int col = 0; col < boardSize; col++) {
                        aliveBoard[row][col] = aliveNextGeneration[row][col];
                        if (aliveBoard[row][col] == true) {
                            boardButtons[row][col].setBackground(ALIVE_COLOR);
                        } else {
                            boardButtons[row][col].setBackground(DEAD_COLOR);
                        }
                    }
                }
                generationLabel.setText("Generation #" + generationNumber);

                revalidate();
                repaint();

            } catch (InterruptedException e) {
                System.out.println("Unexpected interrupt");
                System.exit(0);
            }
        }

    }

    /**
     * starts the thread
     */
    public void startThread() {
        Thread theThread = new Thread(this);
        theThread.start();
    }
    
    
}
