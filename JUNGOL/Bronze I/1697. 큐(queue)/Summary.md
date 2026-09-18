# 🤖 AI 분석

## 💡 접근 방식

원형 큐를 사용자 정의 클래스로 구현해 삽입(i), 삭제(o), 개수(c) 명령을 처리하는 방식.

## ⏱️ 시간 복잡도

O(1) — 각 명령의 시간복잡도가 상수 시간, 명령 수가 n일 때 모두 합쳐도 O(n) 개의 명령 연산.

## 📦 공간 복잡도

O(n) — 큐의 크기를 n+1로 정의하여 n개의 원소를 저장할 수 있으며 추가적으로 상수적인 공간 사용.

## 🔧 개선 사항

1) CircularQueue의 크기를 'n'이 아닌 'n'의 2배로 설정해 항상 공간을 여유 있게 사용.
2) 명령 수행 시 직접적으로 BufferedReader를 사용하는 대신 Scanner 클래스를 사용해 코드 가독성을 높일 수 있다.
3) 에러 처리를 예외로 넘기기보다 명확하게 반환값으로 관리해 안정성을 높임. 

예시: `static void handleIn(CircularQueue queue, int value) {` 대체 `void handleIn(CircularQueue queue, Integer value) { if (queue.isFull()) return; }`

## 🎯 다음 추천 문제

백준 10845번 - 큐 | 큐 데이터 구조의 다양한 연산을 연습하기 위해 더 많은 명령어와 활용법을 배울 수 있음.

## 🏷️ 태그

implementation

## ✨ 모범 답안

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class CircularQueue {
        private final int[] data;
        private int head, tail, size;

        CircularQueue(int capacity) {
            data = new int[capacity];
            head = tail = size = 0;
        }

        boolean isEmpty() { return size == 0; }
        boolean isFull() { return size == data.length; }
        int size() { return size; }

        void offer(int value) {
            if (isFull()) throw new IllegalStateException("Queue is full");
            data[tail] = value;
            tail = (tail + 1) % data.length;
            size++;
        }

        int poll() {
            if (isEmpty()) throw new IllegalStateException("Queue is empty");
            int value = data[head];
            head = (head + 1) % data.length;
            size--;
            return value;
        }
    }

    static final char CMD_IN = 'i', CMD_OUT = 'o', CMD_COUNT = 'c';
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        CircularQueue queue = new CircularQueue(n * 2);
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            process(queue, line);
        }

        System.out.print(sb);
    }

    static void process(CircularQueue queue, String line) {
        StringTokenizer st = new StringTokenizer(line);
        String cmd = st.nextToken();
        char type = cmd.charAt(0);

        switch (type) {
            case CMD_IN -> queue.offer(Integer.parseInt(st.nextToken()));
            case CMD_OUT -> { 
                if (queue.isEmpty()) sb.append("empty\n");
                else sb.append(queue.poll()).append('\n');
            }
            case CMD_COUNT -> sb.append(queue.size()).append('\n');
        }
    }
}
```
