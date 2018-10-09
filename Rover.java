import java.io.BufferedReader;
import java.io.InputStreamReader;

/*Долго думал над алгоритмом решения задачи. К сожалению достойного выхода не придумал, поэтому
 * прибегнул к перебору. Постарался сделать его максимально быстрым.
 * 
 *  P.S. Если не сложно: уделите 5 минут времени и вышлите письмо на почту sitcomsfan@yandex.by
 *  письмо с минимальным описанием правильного алгоритма для решения данной задачи, очень интересно. 
 *  Заранее спасибо
 * */

public class Rover {
	private int speed = 1;
	private int position = 0;

	public static void main(String[] args) {
		Rover rover = new Rover();
		System.out.println("Введите номер блока, в который должен попасть марсоход.");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
			System.out.println(rover.goToPos(Integer.parseInt(reader.readLine())));
		} catch (Exception e) {
			System.out.println("Неверный ввод.");
		}
	}

	private int goToPos(int pos) {
		int i = 0;
		while (true) {
			if (getPosByCode(i) == pos) {
				return Integer.toBinaryString(i).length();
			}
			i++;
			speed = 1;
			position = 0;
		}
	}

	private void acceleration() {
		position += speed;
		speed *= 2;
	}

	private void reverse() {
		speed = (speed > 0) ? -1 : 1;
	}

	private int getPosByCode(int code) {
		for (char c : Integer.toBinaryString(code).toCharArray()) {
			if (c == '1')
				acceleration();
			else if (c == '0')
				reverse();
		}
		return position;
	}
}
