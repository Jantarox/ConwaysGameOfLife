import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConwaysGOLWindow extends JFrame implements MouseListener {

    int gridWidth;
    int gridHeight;
    int cellSize;

    GridPanel gridPanel;
    JPanel controlPanel;

    JLabel xyLabel = new JLabel("X: 0, Y: 0");
    JButton startStopButton = new JButton("Start");
    JLabel generationLabel = new JLabel("Genetarion: 0");
    long generationCount;
    JButton clearButton = new JButton("Clear");
    JSlider delaySlider = new JSlider(10,1000,100);

    ConwaysGameOfLife game;

    boolean running;
    Timer timer = new Timer(100, this::gameRun);




    public ConwaysGOLWindow() throws HeadlessException {

        this.gridWidth = 80;
        this.gridHeight = 80;
        this.cellSize = 10;
        this.game = new ConwaysGameOfLife(gridWidth, gridHeight);
        this.running = false;

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gridPanel = new GridPanel(gridWidth, gridHeight, cellSize, this.game.grid);
        this.gridPanel.addMouseListener(this);
        this.add(this.gridPanel, BorderLayout.CENTER);

        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new BoxLayout(this.controlPanel, BoxLayout.PAGE_AXIS));
        this.controlPanel.setPreferredSize(new Dimension(100, 100));

        //        this.xyLabel.setPreferredSize(new Dimension(100,20));
        this.xyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.controlPanel.add(xyLabel);

        this.startStopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.startStopButton.addActionListener(this::startStopGame);
        this.controlPanel.add(startStopButton);

        this.generationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.controlPanel.add(generationLabel);
        this.generationCount = 0;

        this.clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.clearButton.addActionListener(this::clearBoard);
        this.controlPanel.add(clearButton);

        this.delaySlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.delaySlider.setMaximumSize(new Dimension(100, 200));
        this.delaySlider.addChangeListener(this::changeDelay);
        this.delaySlider.setPaintTrack(true);
        this.delaySlider.setMajorTickSpacing(990);
        this.delaySlider.setPaintLabels(true);
        this.delaySlider.setOrientation(SwingConstants.VERTICAL);
        this.controlPanel.add(delaySlider);

        this.add(controlPanel, BorderLayout.EAST);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void clearBoard(ActionEvent actionEvent) {
        if(running){
            running = false;
            timer.stop();
            startStopButton.setText("Start");
        }
        this.gridPanel.setGrid(this.game.clearBoard());
        this.repaint();
    }

    private void changeDelay(ChangeEvent changeEvent) {
        timer.setDelay(delaySlider.getValue());
    }

    private void startStopGame(ActionEvent e) {
        if(running){
            running = false;
            timer.stop();
            startStopButton.setText("Start");
        }else{
            generationCount = 0;
            running = true;
            timer.start();
            startStopButton.setText("Stop");
        }
    }

    public static void main(String[] args) {
        new ConwaysGOLWindow();
    }

    private void gameRun(ActionEvent actionEvent) {
        if(running){
            this.gridPanel.setGrid(this.game.processTurn());
            this.generationLabel.setText("Genetarion: " + Long.toString(++generationCount));
            this.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX()/cellSize;
        int y = e.getY()/cellSize;
        xyLabel.setText("X: " + Integer.toString(x) + ", Y: " + Integer.toString(y));

        this.game.toggleCell(x, y);

        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
