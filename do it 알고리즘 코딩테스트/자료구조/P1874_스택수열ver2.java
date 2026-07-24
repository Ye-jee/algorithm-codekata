import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1874_스택수열ver2 { // 백준 1874번: 스택 수열

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        // 만들어야 하는 수열의 길이
        int n = Integer.parseInt(br.readLine());

        // 문제에서 주어진 목표 수열을 저장하는 배열
        int[] sequence = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        /*
         * 숫자를 임시로 저장할 스택
         *
         * 숫자는 반드시 1, 2, 3, ... 순서로 push할 수 있고,
         * 스택에 들어간 숫자는 가장 위에 있는 숫자부터 pop할 수 있다.
         */
        Stack<Integer> stack = new Stack<>();

        /*
         * push와 pop 연산 결과를 저장한다.
         *
         * push하면 "+"
         * pop하면 "-"
         *
         * 수열을 만들 수 없는 경우에는 저장한 연산을 출력하지 않고
         * "NO"만 출력해야 하므로 바로 출력하지 않고 StringBuilder에 모아둔다.
         */
        StringBuilder resultBuilder = new StringBuilder();

        /*
         * 다음에 스택에 넣을 숫자
         *
         * 숫자는 1부터 시작해 오름차순으로만 넣을 수 있다.
         *
         * 예:
         * num이 5라면 1, 2, 3, 4는 이미 스택에 넣은 적이 있고,
         * 다음에 push할 수 있는 숫자가 5라는 뜻이다.
         */
        int num = 1;

        // 목표 수열을 만들 수 있는지를 나타낸다.
        boolean possible = true;

        /*
         * 목표 수열의 숫자를 앞에서부터 하나씩 확인한다.
         *
         * 매 반복마다 현재 target을 스택에서 pop해
         * 수열의 다음 숫자로 만들어야 한다.
         */
        for (int i = 0; i < sequence.length; i++) {

            // 현재 만들어야 하는 수열의 숫자
            int target = sequence[i];

            /*
             * target이 num 이상이라면,
             * target은 아직 스택에 들어간 적이 없는 숫자이다.
             *
             * 예:
             * target = 4, num = 1
             *
             * 아직 4를 스택에 넣지 않았으므로
             * 1, 2, 3, 4를 차례대로 push해야 한다.
             */
            if (target >= num) {

                /*
                 * target이 스택에 들어갈 때까지
                 * num부터 target까지 오름차순으로 push한다.
                 *
                 * 예:
                 * target = 4, num = 1
                 *
                 * push 1
                 * push 2
                 * push 3
                 * push 4
                 */
                while (target >= num) {

                    // 현재 num을 스택에 넣는다.
                    stack.push(num);

                    // push 연산을 의미하는 "+"를 저장한다.
                    resultBuilder.append("+\n");

                    /*
                     * 방금 num을 스택에 넣었으므로
                     * 다음에 넣을 숫자로 1 증가시킨다.
                     */
                    num++;
                }

                /*
                 * while문이 끝났다면 스택의 맨 위에는
                 * 현재 필요한 target이 있다.
                 *
                 * 예:
                 * target = 4까지 push했다면
                 * 스택 맨 위 숫자는 4이다.
                 *
                 * 따라서 바로 pop해서 목표 수열의 숫자로 사용한다.
                 */
                stack.pop();

                // pop 연산을 의미하는 "-"를 저장한다.
                resultBuilder.append("-\n");

            } else {

                /*
                 * target이 num보다 작다면,
                 * target은 과거에 이미 스택에 넣었던 숫자이다.
                 *
                 * 예:
                 * num = 5, target = 3
                 *
                 * num이 5라는 것은 1~4를 이미 push했다는 뜻이므로
                 * 3을 새로 push할 수 없다.
                 *
                 * 따라서 현재 스택의 맨 위에서 pop해야 한다.
                 */

                /*
                 * 목표 숫자가 이미 스택에 들어간 적 있으므로
                 * 정상적인 입력 구조에서는 스택이 비어 있지 않다.
                 */
                int poppedNumber = stack.pop();

                /*
                 * pop한 숫자가 현재 만들어야 하는 target과 다르면
                 * 해당 수열은 만들 수 없다.
                 *
                 * 예:
                 *
                 * 스택 맨 위
                 *   5
                 *   4
                 *   3
                 *
                 * 현재 필요한 숫자가 3이어도
                 * 스택은 LIFO 구조이므로 5부터 꺼내야 한다.
                 *
                 * 5와 4를 건너뛰고 3을 먼저 꺼낼 수 없으며,
                 * 꺼낸 숫자를 다시 push할 수도 없다.
                 *
                 * 따라서 poppedNumber != target이면
                 * 목표 수열을 만드는 것이 불가능하다.
                 */
                if (poppedNumber != target) {
                    possible = false;
                    break;
                }

                // 원하는 숫자를 정상적으로 pop했으므로 "-"를 저장한다.
                resultBuilder.append("-\n");
            }
        }

        /*
         * 목표 수열을 끝까지 만들었다면
         * 저장해 둔 push/pop 연산을 출력한다.
         *
         * 중간에 만들 수 없는 상태가 발생했다면
         * 연산 과정 대신 "NO"만 출력한다.
         */
        if (possible) {
            System.out.print(resultBuilder);
        } else {
            System.out.println("NO");
        }
    }
}