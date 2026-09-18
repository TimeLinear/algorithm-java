import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정올 #1697 큐(queue)
 * P0-6 Monaco 추출 실험용 긴 코드 (150줄 이상, 탭 들여쓰기)
 * - 원형 큐를 직접 구현한다
 * - 명령: i a (삽입), o (삭제 후 출력), c (개수 출력)
 */
public class Main {

	// 원형 큐 구현
	static class CircularQueue {
		private final int[] data;
		private int head;
		private int tail;
		private int size;  

		CircularQueue(int capacity) {
			data = new int[capacity];
			head = 0;
			tail = 0;
			size = 0;
		}
		

		boolean isEmpty() {
			return size == 0;
		}

		boolean isFull() {
			return size == data.length;
		}

		int size() {
			return size;
		}

		void offer(int value) {
			if (isFull()) {
				throw new IllegalStateException("queue is full");
			}
			data[tail] = value;
			tail = next(tail);
			size++;
		}

		int poll() {
			if (isEmpty()) {
				throw new IllegalStateException("queue is empty");
			}
			int value = data[head];
			head = next(head);
			size--;
			return value;
		}

		int peek() {
			if (isEmpty()) {
				throw new IllegalStateException("queue is empty");
			}
			return data[head];
		}

		private int next(int index) {
			index++;
			if (index == data.length) {
				index = 0;
			}
			return index;
		}
	}

	// 명령 종류
	static final char CMD_IN = 'i';	// 삽입
	static final char CMD_OUT = 'o';
	static final char CMD_COUNT = 'c';

	// 출력 버퍼
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = readInt(br);

		// 명령 수가 최대 100이므로 넉넉하게 잡는다
		CircularQueue queue = new CircularQueue(n + 1);

		for (int i = 0; i < n; i++) {
			String line = readNonEmptyLine(br);
			if (line == null) {
				break;
			}
			process(queue, line);
		}

		System.out.print(sb);
	}

	// 한 줄 명령 처리
	static void process(CircularQueue queue, String line) {
		StringTokenizer st = new StringTokenizer(line);
		String cmd = st.nextToken();
		char type = cmd.charAt(0);

		switch (type) {
			case CMD_IN:
				handleIn(queue, Integer.parseInt(st.nextToken()));
				break;
			case CMD_OUT:
				handleOut(queue);
				break;
			case CMD_COUNT:
				handleCount(queue);
				break;
			default:
				// 정의되지 않은 명령은 무시한다
				break;
		}
	}

	static void handleIn(CircularQueue queue, int value) {
		queue.offer(value);
	}

	static void handleOut(CircularQueue queue) {
		if (queue.isEmpty()) {
			sb.append("empty").append('\n');
			return;
		}
		sb.append(queue.poll()).append('\n');
	}

	static void handleCount(CircularQueue queue) {
		sb.append(queue.size()).append('\n');
	}

	// 빈 줄은 건너뛴다
	static String readNonEmptyLine(BufferedReader br) throws IOException {
		String line = br.readLine();
		while (line != null && line.trim().isEmpty()) {
			line = br.readLine();
		}
		return line;
	}

	static int readInt(BufferedReader br) throws IOException {
		String line = readNonEmptyLine(br);
		return Integer.parseInt(line.trim());
	}
}
