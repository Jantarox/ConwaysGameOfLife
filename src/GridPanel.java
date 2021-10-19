import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    int width;

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }

    int height;
    int cellSize;
    boolean[][] grid;


    public GridPanel(int width, int height, int cellSize, boolean[][] grid) {
        this.setPreferredSize(new Dimension(width*cellSize, height*cellSize));
        this.setBackground(Color.BLACK);
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.grid = grid;
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        for(int i=1; i<width; i++){
            g2D.drawLine(i*cellSize, 0, i*cellSize, height*cellSize);
        }
        for(int i=1; i<height; i++){
            g2D.drawLine(0, i*cellSize, width*cellSize, i*cellSize);
        }

        g2D.setStroke(new BasicStroke(5));
        g2D.drawLine(0,0,width*cellSize, 0);
        g2D.drawLine(0,0,0, height*cellSize);
        g2D.drawLine(width*cellSize,0,width*cellSize, height*cellSize);
        g2D.drawLine(0,height*cellSize,width*cellSize, height*cellSize);

        g2D.setPaint(Color.white);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                if(this.grid[i][j])
                    g2D.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
            }
        }
    }
}
