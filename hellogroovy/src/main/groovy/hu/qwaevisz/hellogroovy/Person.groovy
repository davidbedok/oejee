package hu.qwaevisz.hellogroovy

enum Gender {
	MAN,
	WOMAN
}

class Person {

	def familyName
	def firstName
	String identifier
	Gender gender

	Person( String familyName, String firstName, String identifier, Gender gender ) {
		this.familyName = familyName
		this.firstName = firstName
		this.identifier = identifier
		this.gender = gender
	}

	String toString() {
		'(' + [
			"familyName" : this.familyName,
			"firstName" : this.firstName,
			"identifier" : this.identifier,
			"gender" : this.gender
		].iterator().join(', ') + ')'
	}
}
