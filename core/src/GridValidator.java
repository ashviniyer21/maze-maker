import com.github.ashviniyer21.Grid;
import com.github.ashviniyer21.components.*;

public class GridValidator {
    public static boolean isValid(Grid grid){
        boolean[][] prev = new boolean[grid.getHeight()][grid.getWidth()];
        grid.addPlayer();
        int x = grid.getPlayer().getX();
        int y = grid.getPlayer().getY();
        prev[y][x] = true;
        return isValid(grid, x, y, prev);
    }

    public static boolean isValid(Grid grid, int x, int y, boolean[][] prev){
        boolean ans = false;
        if(x > 0){
            if(grid.getComponent(x - 1, y) instanceof EndSpace){
                return true;
            }
            if(isWalkable(grid.getComponent(x - 1, y))){
                prev[x - 1][y] = true;
                ans = isValid(grid, x - 1, y, prev);
                prev[x - 1][y] = false;
            }
        }
        if(x < grid.getWidth() - 1){
            if(grid.getComponent(x + 1, y) instanceof EndSpace){
                return true;
            }
            if(isWalkable(grid.getComponent(x + 1, y))){
                prev[x + 1][y] = true;
                ans = ans || isValid(grid, x + 1, y, prev);
                prev[x + 1][y] = false;
            }
        }
        if(y > 0){
            if(grid.getComponent(x, y - 1) instanceof EndSpace){
                return true;
            }
            if(isWalkable(grid.getComponent(x, y - 1))){
                prev[x][y - 1] = true;
                ans = ans || isValid(grid, x, y - 1, prev);
                prev[x][y - 1] = false;
            }
        }
        if(y < grid.getHeight() - 1){
            if(grid.getComponent(x, y + 1) instanceof EndSpace){
                return true;
            }
            if(isWalkable(grid.getComponent(x, y + 1))){
                prev[x][y + 1] = true;
                ans = ans || isValid(grid, x, y + 1, prev);
                prev[x][y + 1] = false;
            }
        }
        return ans;
    }

    private static boolean isWalkable(GridComponent component){
        return component instanceof Door || component instanceof ElectricFence || component instanceof Floor
                || component instanceof ItemSquare || component instanceof TemporaryFloor;
    }
}
