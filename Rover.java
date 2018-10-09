import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rover {
	private int speed;
	private int position;
	private int count;

	public static void main(String[] args) {
		Rover rover = new Rover();
		System.out.println("Введите номер блока, в который должен попасть марсоход.");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
			rover.goToPos(Integer.parseInt(reader.readLine()));
			System.out.println(rover.count);
		} catch (Exception e) {
			System.out.println("Неверный ввод.");
		}
	}

	private Rover() {
		speed = 1;
		position = 0;
		count = 0;
	}

	private void goToPos(int pos) {
		while (position != pos) {
			if ((Integer) Math.abs(pos - position - speed) < (Integer) Math.abs(pos - position)) {
				acceleration();
			} else
				reverse();
		}
	}

	private void acceleration() {
		position += speed;
		speed *= 2;
		count++;
	}

	private void reverse() {
		speed = (speed > 0) ? -1 : 1;
		count++;
	}
}
