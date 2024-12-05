// row x col 행렬 형태의 보드에 다음과 같이 X,O로 표시가 되어 있다.
// 이때 X로 둘러쌓여있는 O는 X로 변경하고, 상하좌우 방향으로 O에 연결되어 있는 O는 그대로 O로 유지한 후 출력하는 프로그램을 작성하시오.

// 참고) 외곽부에 닿아 있는 O는 X로 둘러쌓여있지 않은 것으로 본다.

// 해결방안 : 외곽에서부터 dfs 실행

// ex) 입출력 예시
// 입력 : {{'X', 'X', 'X', 'X'},{'X', 'O', 'O', 'X'},{'X', 'X', 'O', 'X'},{'X', 'O', 'X', 'X'}}
// 출력 : {{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}}

public class Practice2 {
    public static void solution(char[][] board){
        if (board == null || board.length<3 || board[0].length<3){
            // 3보다 작으면 무조건 외곽에 닿아있기 떄문
            return;
        }

        int row = board.length;
        int col = board[0].length;

        for (int i=0; i<row;i++){
            if(board[i][0] == 'O'){         // 왼쪽 끝 탐색
                // DFS 시작
                dfs(board, i, 0);
            }
            if (board[i][col-1] == 'O'){    // 오른쪽 끝 탐색
                dfs(board, i, col-1);
            }
        }
        for (int i=0; i< col-1; i++){
            if (board[0][i] == 'O'){        // 위쪽 탐색
                dfs(board, 0, i);
            }
            if (board[row -1][i] == 'O') {  // 아래쪽 탐색
                dfs(board, row-1, i);
            }
        }

        for (int i=0; i<row;i++){
            for (int j=0; j<col;j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }

                if(board[i][j] == '*'){
                    board[i][j] = 'O';
                }
            }
        }

    }

    public static void dfs(char[][] board, int x, int y){
        if (x<0 || y<0 || x>board.length-1||y>board[0].length-1){
            return;
        }

        if (board[x][y] != 'O'){
            return;
        }

        board[x][y] = '*';
        dfs(board, x+1, y);
        dfs(board, x-1, y);
        dfs(board, x, y+1);
        dfs(board, x, y-1);

    }

    public static void main(String[] args){
        // Test code
        char[][] board = {{'X', 'X', 'X', 'X'},{'X', 'O', 'O', 'X'},{'X', 'X', 'O', 'X'},{'X', 'O', 'X', 'X'}};

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        solution(board); // X로 둘러쌓여있는 O를 X로 변경

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
