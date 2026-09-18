import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] subjectNames = {"국어", "영어", "수학", "물리", "화학", "사회", "컴퓨터"};
        int[] scores = new int[7]; // A, B, C, D, E, F, G

        // 점수 입력 (0~100 범위를 벗어나면 그 점수만 다시 입력)
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                int v = sc.nextInt();
                if (v >= 0 && v <= 100) {
                    scores[i] = v;
                    break;
                }
                // 범위를 벗어난 경우 다시 입력받는다 (해당 점수만)
            }
        }

        int tot = 0;
        int max = scores[0];
        int min = scores[0];
        for (int v : scores) {
            tot += v;
            if (v > max) max = v;
            if (v < min) min = v;
        }
        int avg = tot / scores.length; // 정수 나눗셈이므로 소수점 이하는 자동으로 버려짐

        System.out.println("TOT : " + tot + " ");
        System.out.println("AVG : " + avg + " ");
        System.out.println("MAX : " + max + " ");
        System.out.println("MIN : " + min + " ");

        // A~G(과목), H(평균), I(최대), J(최소)
        char[] labels = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int[] values = {scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6], avg, max, min};

        // 막대그래프 출력 (100 ~ 10, 10 단위)
        for (int row = 100; row >= 10; row -= 10) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%3d", row));
            for (int v : values) {
                int height = (v / 10) * 10; // 10 단위 이하는 무시
                if (height >= row) {
                    sb.append("   *");
                } else {
                    sb.append("    ");
                }
            }
            System.out.println(sb.toString());
        }

        // 맨 아래 레이블 줄
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (char c : labels) {
            sb.append("   ").append(c);
        }
        System.out.println(sb.toString());

        sc.close();
    }
}