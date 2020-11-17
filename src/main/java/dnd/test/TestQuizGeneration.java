package dnd.test;

import dnd.quizzes.QuizGenerator;

public class TestQuizGeneration {
    public void testQuizGeneration() {
        QuizGenerator generator = new QuizGenerator();
        for (int i = 0; i < 100; i++) {
            int gradeLevel = (i%5) + 1;
            int[] quizResults = generator.randomQuiz(gradeLevel);
            System.out.print("Grade level = " + gradeLevel + " | ");
            if (quizResults[4] == 0) {
                System.out.println(quizResults[0] + " + " + quizResults[1] + " = " + quizResults[2]);
            }
            else if (quizResults[4] == 1) {
                System.out.println(quizResults[0] + " - " + quizResults[1] + " = " + quizResults[2]);
            }
            else if (quizResults[4] == 2) {
                System.out.println(quizResults[0] + " * " + quizResults[1] + " = " + quizResults[2]);
            }
            else if (quizResults[4] == 3) {
                System.out.println(quizResults[0] + " / " + quizResults[1] + " = " + quizResults[2]);
            }
            else if (quizResults[4] == 4) {
                System.out.println(quizResults[0] + " / " + quizResults[1] + " = " + quizResults[2] + " R " + quizResults[3]);
            }

        }

    }
}
