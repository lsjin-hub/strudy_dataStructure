// row x col 행렬 형태의 board와 word 문자열이 주어졌을 때,
// 해당 행렬에서 word 문자열이 인접하게 연결되어 있는지를 확인하는 프로그램을 작성하시오.

// 아래와 같은 행렬에서 word로 "ABCEED" 가 주어진 경우 다음과 같이 인접해 있음을 확인할 수 있다.
// 인접하게 구성이 되면 true를 반환하고 그렇지 않으면 false를 반환하시오.

// ex) board : {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}
//     word  : "ABCCED"
//     결과   : true

// ex) board : {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}
//     word  : "SEE"
//     결과   : true

// ex) board : {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}
//     word  : "ABCB"
//     결과   : false

public class Practice1 {
    final static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // 이동할 수 있는 방향

    public static boolean solution(char[][] board, String word){
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0){
            return false;
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i=0; i< row;i++){
            for (int j=0; j<col;j++){
                if (dfs(board, visited, i, j, 0, word)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, boolean[][] visited, int x, int y, int i, String word){
        int row = board.length;
        int col = board[0].length;

        if (i == word.length()){
            return true;
        }

        if (x < 0 || x>=row || y<0 || y>=col){
            // 범위를 벗어난 경우
            return false;
        }

        if (visited[x][y]){
            // 이미 방문한 곳인 경우
            return false;
        }

        if (board[x][y] != word.charAt(i)){
            // 단어가 다른 경우
            return false;
        }

        visited[x][y] = true;   // 방문 true
        for (int[] dir: dirs) {
            int xNext = x + dir[0];
            int yNext = y + dir[1];

            if (dfs(board, visited, xNext, yNext, i+1, word)){
                return true;
            }
        }
        visited[x][y] = false;  // 방문 false로 수정
        return false;
    }

    public static void main(String[] args){
        // Test code
        char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(solution(board, "ABCCED"));
        System.out.println(solution(board, "SEE"));
        System.out.println(solution(board, "ABCB"));
    }
}
