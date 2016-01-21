package hu.qwaevisz.school.ejbservice.domain;

public enum Grade {

	INSUFFICIENT(1),
	WEAK(2),
	MEDIUM(3),
	GOOD(4),
	OUTSTANDING(5);

	private final int value;

	private Grade(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	// public static Grade fromString(String name) {
	// Grade grade = INSUFFICIENT;
	// final Grade[] grades = Grade.values();
	// for (final Grade current : grades) {
	// if (current.name().equals(name)) {
	// grade = current;
	// break;
	// }
	// }
	// return grade;
	// }

}
