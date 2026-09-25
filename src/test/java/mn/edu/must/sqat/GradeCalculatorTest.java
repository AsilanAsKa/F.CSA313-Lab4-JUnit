package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCalculatorTest {

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        // Arrange
        GradeCalculator calculator = new GradeCalculator();

        // Act
        String result = calculator.letterGrade(95);

        // Assert
        assertEquals("A", result);
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void eightyFiveIsB() {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(85);

        assertEquals("B", result);
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void seventyFiveIsC() {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(75);

        assertEquals("C", result);
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void sixtyFiveIsD() {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(65);

        assertEquals("D", result);
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void thirtyIsF() {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(30);

        assertEquals("F", result);
    }

    @ParameterizedTest
    @DisplayName("Дүнгийн хязгаарын утгууд зөв ангилагдах ёстой")
    @CsvSource({
            "100, A",
            "90, A",
            "89.99, B",
            "80, B",
            "70, C",
            "60, D",
            "59.99, F",
            "0, F"
    })
    void letterGradeBoundaries(double score, String expected) {
        GradeCalculator calculator = new GradeCalculator();

        String result = calculator.letterGrade(score);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Сөрөг оноо оруулахад алдаа гарах ёстой")
    void negativeScoreThrowsException() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.letterGrade(-1)
        );
    }

    @Test
    @DisplayName("100-аас их оноо оруулахад алдаа гарах ёстой")
    void scoreAbove100ThrowsException() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.letterGrade(101)
        );
    }

    @Test
    @DisplayName("Бүх бүрэлдэхүүн хэсгийн нийлбэр 100 байх ёстой")
    void totalScoreCanBe100() {
        GradeCalculator calculator = new GradeCalculator();

        double result = calculator.totalScore(10, 40, 10, 10, 30);

        assertEquals(100, result);
    }

    @Test
    @DisplayName("Ирц сөрөг байвал алдаа гарах ёстой")
    void negativeAttendanceThrowsException() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Лабораторийн оноо 40-өөс их байвал алдаа гарах ёстой")
    void labAboveMaximumThrowsException() {
        GradeCalculator calculator = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.totalScore(10, 41, 10, 10, 30)
        );
    }

    @ParameterizedTest
    @DisplayName("Нийт оноо зөв тооцогдох ёстой")
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "5, 30, 8, 7, 25, 75",
            "0, 0, 0, 0, 0, 0"
    })
    void totalScoreCalculations(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        GradeCalculator calculator = new GradeCalculator();

        double result = calculator.totalScore(att, lab, quiz1, quiz2, exam);

        assertEquals(expected, result);
    }
}
