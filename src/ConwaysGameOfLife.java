public class ConwaysGameOfLife {
    boolean[][] grid;
    int width;
    int height;

    public ConwaysGameOfLife(int width, int height) {
        this.grid = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    public boolean[][] clearBoard(){
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                this.grid[i][j] = false;
            }
        }
        return this.grid;
    }

    public void toggleCell(int x, int y){
        if(x<0 || x>width || y<0 || y>height)
            return;
        this.grid[x][y] = !this.grid[x][y];
    }

    public boolean[][] processTurn(){
        boolean[][] newGrid = new boolean[width][height];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                newGrid[i][j] = cellNextGen(i,j);
            }
        }
        this.grid = newGrid;
        return newGrid;
    }

    private boolean cellNextGen(int x, int y) {
        int count = 0;
        for(int i=x-1; i<=x+1; i++){
            for(int j=y-1; j<=y+1; j++){
                if(x == i && y == j)
                    continue;
                if(cellIsAlive(i, j))
                    count++;
            }
        }
        if(cellIsAlive(x,y)){
            return count == 2 || count == 3;
        }
        else
            return count == 3;
    }

    private boolean cellIsAlive(int x, int y) {
        if(x<-1 || x>width || y<-1 || y>height)
            return false;

        return this.grid[(x+width)%width][(y+height)%height];
    }
}
