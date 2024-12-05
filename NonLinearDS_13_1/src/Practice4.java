// 네 자리 비밀번호를 설정할 수 있는 자물쇠가 주어졌다.
// 원형 바퀴 형태로 숫자를 돌려가며 설정하는 방식이고, 아래와 같은 숫자로 구성된다.

// '0','1','2','3','4','5','6','7','8','9'

// 바퀴의 숫자는 다음과 같이 전후 이동이 가능하다.
// '9'를 '0'으로 바꾸거나 '0'을 '9'로 바꿀 수 있다.
// 각 이동은 숫자 한 칸 이동하는 것을 의미한다.

// 주어진 요구사항은 다음과 같다.
// 자물쇠의 처음 번호 시작은 '0000'에서 시작하고, deadends 목록과 target이 주어진다.
// 자물쇠를 몇 번 이동을 시키면 target에 도달하는지 계산하는 프로그램을 작성하시오.
// 단, deadends 목록에 있는 수로 이동해서는 안 된다.
// 이동이 가능하면 이동 횟수를 반환하고 그렇지 않은 경우 -1을 반환하세요.

// 입출력 예시
// deadends                             target      결과
// "8888"                               "0009"      1
// "0201","0101","0102","1212","2002"   "0202"      6


import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// bfs 이용 => 그래프로 구현하여 search 진행
public class Practice4 {
    public static int solution(String[] deadends, String target) {
        if (target == null || target.length() == 0){
            return -1;
        }

        HashSet<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();

        queue.offer("0000");    // add와 동일하나 큐가 꽉 찬 경우 예외가 아닌 false를 return
        int cnt = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++){
                String curNum = queue.poll();

                if (!visited.add(curNum)){
                    continue;
                }

                if (curNum.equals(target)){
                    return cnt;
                }

                for (String nextNum: getNextNums(curNum.toCharArray())){
                    // 다음 이동가능한 8개에 대한 값
                    if (!visited.contains(nextNum)){
                        queue.offer(nextNum);
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    public static LinkedList<String> getNextNums(char[] cArr){
        LinkedList<String> nums = new LinkedList<>();
        for (int i=0; i<cArr.length; i++){
            char c = cArr[i];
            cArr[i] = c == '9' ? '0' : (char)(c+((char)1));
            nums.add(String.valueOf(cArr));
            cArr[i] = c == '0' ? '9' : (char)(c-((char)1));
            nums.add(String.valueOf(cArr));
            cArr[i] = c;
        }

        return nums;
    }


    public static void main(String[] args){
        // Test code
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        System.out.println(solution(deadends, "0202")); // 6

        deadends = new String[]{"8888"};
        System.out.println(solution(deadends, "0009")); // 1
    }

}
