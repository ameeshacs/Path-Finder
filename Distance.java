//Name: Ameesha Senanayake
//UoW ID: w18101205
//IIT ID:2019771

import java.util.LinkedList;
import java.util.Queue;

public class Distance {
        static class Node implements Comparable<Node> {
            int x; //x coordinate
            int y; //y coordinate
            int dist;  	//distance
            String path;  //path of the parent cell
            Queue<int[]> queue2 = new LinkedList<>(); //the previous cell

            //constructor for the class Node
            Node(int x, int y, int dist, String path, int[] last) {
                this.x = x;
                this.y = y;
                this.dist = dist;
                this.path = path +" (" + (x + 1) + ", " + (y + 1) +")\n" ;
                this.queue2.add(last);
            }

            //get method for x coordinate
            public int getX(){
                return x;
            }

            //get method for y coordinate
            public int getY(){
                return y;
            }

            //to string method to display the distance and the staring point
            @Override
            public String toString(){
                return "Distance from the starting point: "+dist+"\nStart at:"+path;
            }

            //compare to function to compare the path
            @Override
            public int compareTo(Node node) {
                return this.dist == node.dist ? this.path.compareTo(node.path) : this.dist - node.dist;
            }
        }

        //BFS, Time O(n^2), Space O(n^2)
        public String shortestPath(int[][] matrix, int[] s, int[] f) {
            //initialize the cells
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[][] pathVisited = new boolean[m][n];

            //queue to store the details of each coordinate
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(s[0],s[1], 0, "", new int[]{}));

            //the directions allowed to move from the point
            String[] directions = {"Up", "Down", "Left", "Right"};
            int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


          //breadth first search
            while (!queue.isEmpty()) {
                Node p= queue.poll();
                //find destination
                if (p.x == f[0] && p.y == f[1]) {
                    return p.toString();
                }

                for (int i = 0; i < move.length; i++) {
                    int row = p.x;
                    int column = p.y;
                    int dist = p.dist;
                    String path = p.path;

                    //move till a wall is met
                    while (row >= 0 && row < m && column >= 0 && column < n && matrix[row][column] == 0 && (row != f[0] || column != f[1])) {
                        row += move[i][0];
                        column += move[i][1];
                        dist += 1;
                    }

                    // if the destination is not found
                    if (row != f[0] || column != f[1]) {
                        row -= move[i][0];
                        column -= move[i][1];
                        dist -= 1;
                    }

                    //if the path is not visited
                    if (!pathVisited[row][column]) {
                        pathVisited[p.x][p.y] = true;
                        queue.offer(new Node(row, column, dist, path + directions[i], new int[]{row, column}));
                    }
                }

            }

            //if no path is available
            return "No path is available";

        }

}
