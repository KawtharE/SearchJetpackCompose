package com.example.searchmodulejetpackcompose

data class Student(
    val firstName: String,
    val lastName: String,
    val university: String
) {

    fun interpretInputText(input: String): Boolean {
        val firstNameFirstLetter =
            if (firstName.isNotEmpty() && firstName.isNotBlank()) firstName.first() else ""
        val lastNameFirstLetter =
            if (lastName.isNotEmpty() && lastName.isNotBlank()) lastName.first() else ""
        val universityFirstLetter =
            if (university.isNotEmpty() && university.isNotBlank()) university.first() else ""

        val searchCombinationOptions = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "$firstNameFirstLetter$lastNameFirstLetter",
            "$firstNameFirstLetter $lastNameFirstLetter",
            university,
            "$universityFirstLetter",
            "$firstName$lastName$university",
            "$firstName $lastName $university",
            "$firstNameFirstLetter$lastNameFirstLetter$universityFirstLetter",
            "$firstNameFirstLetter $lastNameFirstLetter $universityFirstLetter"
        )

        return searchCombinationOptions.any {
            it.contains(input, ignoreCase = true)
        }
    }

    companion object {
        fun provideStudentList(): List<Student> {
            return listOf(
                Student(
                    firstName = "Ava",
                    lastName = "Jones",
                    university = "Vocational University"
                ),
                Student(
                    firstName = "Wanda",
                    lastName = "Langdon",
                    university = "Institute of Science and Engineering"
                ),
                Student(
                    firstName = "James",
                    lastName = "MacDonald",
                    university = "Institute of Science and Engineering"
                ),
                Student(
                    firstName = "John",
                    lastName = "Glover",
                    university = "Vocational University"
                ),
                Student(
                    firstName = "Phil",
                    lastName = "Scott",
                    university = "Institute of Science and Engineering"
                ),
                Student(
                    firstName = "Adrian",
                    lastName = "Davidson",
                    university = "Vocational University"
                ),
                Student(
                    firstName = "Stewart",
                    lastName = "Mitchell",
                    university = "Commercial University Ltd."
                ),
                Student(
                    firstName = "Alan",
                    lastName = "Miller",
                    university = "Vocational University"
                ),
                Student(
                    firstName = "Adam",
                    lastName = "Black",
                    university = "United Nations University"
                ),
                Student(
                    firstName = "Evan",
                    lastName = "White",
                    university = "Commercial University Ltd."
                )
            )
        }
    }
}