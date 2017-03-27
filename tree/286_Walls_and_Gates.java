public class Solution {
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> res = new LinkedList<>(); 
        for(int i=0; i<rooms.length; i++){
            for(int j = 0;j<rooms[0].length; j++){
                if(rooms[i][j]==0) res.add(new int[]{i,j});
            }
        }
        //as long as the queue is not empty
        while (!res.isEmpty()) {
                int[] tmp =res.remove();
                int row = tmp[0],col = tmp[1];
                if ( (row>0) && rooms[row-1][col] == Integer.MAX_VALUE) {
                    rooms[row-1][col] = rooms[row][col] + 1 ;
                    res.add(new int[]{row-1, col});
                } 
                if ( (row<rooms.length-1) && rooms[row+1][col] == Integer.MAX_VALUE ) {
                     rooms[row+1][col] = rooms[row][col] + 1 ;
                     res.add(new int[]{row+1, col});
                } 
                if ((col<rooms[0].length-1) && rooms[row][col+1] == Integer.MAX_VALUE ) {
                     rooms[row][col+1] = rooms[row][col] + 1 ;
                     res.add(new int[]{row, col+1});
                } 
                if ((col>0) && rooms[row][col-1] == Integer.MAX_VALUE ) {
                    rooms[row][col-1] = rooms[row][col] + 1 ;
                    res.add(new int[]{row, col-1});
                } 
                
        }
        
    }
}
