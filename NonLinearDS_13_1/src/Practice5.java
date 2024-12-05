// x축 상에 앞 뒤로만 이동할 수 있는 로봇이 0 위치에 놓여있다.
// 해당 로봇은 다음과 같은 규칙으로만 이동할 수 있다.

// forward 방향으로는 a만큼 움직일 수 있다.
// backward 방향으로는 b만큼 움직일 수 있다.
// backward로 연속해서 2번 움직일 수 없다.
// forbidden 위치로는 갈 수 없다.
// 음수 지역으로 갈 수 없다.

// forbidden 배열과 a,b, 그리고 목적지 x가 주어졌을 때,
// 몇 번의 이동으로 목적지에 도달할 수 있는지 계산하는 프로그램을 구현하라.
// 이동이 가능하면 이동횟수를, 이동이 불가능하면 -1을 반환하시오.

// 입출력 예시
// forbidden        a   b   x   결과
// 14,4,18,1,15     3   15  9   3
// 1,6,2,14,5,17,4  16  9   7   2
// 8,3,16,6,12,20   15  13  11  -1

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// bfs
public class Practice5 {

    public static int solution(int[] forbidden, int a, int b, int x){
        int cnt = 0;
        int limit = x + a + b;  // 무한루프 방지

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});

        HashSet<int[]>visited = new HashSet<>(queue);
        for (int pos:forbidden){
            visited.add(new int[] {0, pos});
            visited.add(new int[] {1, pos});
            limit = Math.max(limit, pos + a + b);
        }

        while(!queue.isEmpty()) {
            for (int i=0; i< queue.size(); i++){
                int[] cur = queue.poll();
                int dir = cur[0];
                int pos = cur[1];

                if(pos == x){
                    return cnt;
                }

                int[] forward = new int[]{0, pos + a};
                if (pos + a <= limit && visited.add(forward)){
                    queue.offer(forward);
                }

                int[] backward = new int[]{1, pos - b};
                if (dir == 0 && pos - b >= 0 && visited.add(backward)){
                    queue.offer(backward);
                }
            }
            cnt++;
        }
        return -1;
    }


    public static void main(String[] agrs){
        // Test code
        int[] forbidden = {14,4,18,1,15};
        System.out.println(solution(forbidden, 3,15,9));
    }
}
