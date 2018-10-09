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

	/*
	 * Подбираю комбинацию действий, результатом которых будет нужная позиция.
	 * Комбинации проверяются от короткой к длинной, а значит - первая найденная -
	 * самая короткая
	 */
	private int goToPos(int pos) {
		if (pos == position) {
			return 0;
		}
		int st1;
		int st0;
		int i = 0;
		// Поиск среди комбинация, начинающихся с единицы (действие "A")
		while (true) {
			if (getPosByCode1(i, pos) == pos) {
				st1 = Integer.toBinaryString(i).length();
				break;
			}
			i++;
			resetFields();
		}

		resetFields();
		i = 0;
		// Поиск среди комбинация, начинающихся с нуля (действие "R")
		while (true) {
			if (getPosByCode0(i, pos) == pos) {
				st0 = Integer.toBinaryString(i).length() + 1;
				break;
			}
			i++;
			resetFields();
		}
		// Сравниваю результаты двух поисков и возвращаю более короткий
		if (st1 <= st0)
			return st1;
		else
			return st0;
	}

	private void acceleration() {
		position += speed;
		speed *= 2;
	}

	private void reverse() {
		speed = (speed > 0) ? -1 : 1;
	}

	/*
	 * Так как число в двоичной системе не может начинаться с нуля, добавил два
	 * метода, вычисляющих позицию по коду: в первом код начинается с единицы(A), во
	 * втором - с нуля(R)
	 */
	private int getPosByCode1(int code, int pos) {
		for (char c : (Integer.toBinaryString(code)).toCharArray()) {
			if (c == '1')
				acceleration();
			else if (c == '0')
				reverse();
		}
		return position;
	}

	private int getPosByCode0(int code, int pos) {
		for (char c : (0 + Integer.toBinaryString(code)).toCharArray()) {
			if (c == '1')
				acceleration();
			else if (c == '0')
				reverse();
		}
		return position;
	}

	private void resetFields() {
		speed = 1;
		position = 0;
	}
}
