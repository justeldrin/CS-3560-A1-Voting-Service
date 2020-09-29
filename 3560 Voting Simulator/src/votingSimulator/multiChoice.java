package votingSimulator;

import java.util.Arrays;
import java.util.Random;

public class multiChoice extends Question {

/* Variables and Fields from Question Class
 	protected String[] correctAnswer;
	protected String displayQuestion;
	protected String[] answerBank = {"A", "B", "C", "D", "E", "F"};
 */
	
	public multiChoice()
	{
		this.displayQuestion = ": A, B, C, D, E, or F?";
		String[] tempBank = {"A", "B", "C", "D", "E", "F"};
		answerBank = new String[tempBank.length];
		for(int i = 0; i < tempBank.length; i++)
		{
			answerBank[i] = tempBank[i];
		}
		correctAnswer = generateCorrect();
	}
	
	public multiChoice(String displayQuestion)
	{
		this();
		this.displayQuestion = displayQuestion;
	}
	
	public multiChoice(String displayQuestion, String[] inputBank)
	{
		this.displayQuestion = ": A, B, C, D, E, or F?";
		answerBank = new String[inputBank.length];
		for(int i = 0; i < inputBank.length; i++)
		{
			answerBank[i] = inputBank[i];
		}
		correctAnswer = generateCorrect();
	}
	
	//Randomly chooses a random number of correct answers as well as which answers are correct
	@Override
	String[] generateCorrect()
	{
		Random ran = new Random();
		int numOfAnswers = ran.nextInt(answerBank.length - 1) + 1;
		String[] tempCorrect = new String[numOfAnswers];
		for (int i = 0; i < numOfAnswers; i++)
		{
			String tempChosen = answerBank[ran.nextInt(answerBank.length)];
			//For Statement below checks if the randomly chosen answer is an answer already included from previous iterations
			for (int j = 0; j < tempCorrect.length; j++)
			{
				if (tempChosen == tempCorrect[j])
				{
					tempChosen = answerBank[ran.nextInt(answerBank.length)];
					j = -1;
				}
				
			}
			tempCorrect[i] = tempChosen;
		}
		Arrays.sort(tempCorrect);
		return tempCorrect;
	}
	
	//Takes Student Object and submits answer to the voting service
	@Override
	String[] submitAnswer(Student student) {
		Student tempStudent = new Student(student);
		String[] submittedAnswer = tempStudent.getStudentAnswer();
		Arrays.sort(submittedAnswer);
		System.out.println(tempStudent.getStudentName() + "'s answer: " + Arrays.deepToString(submittedAnswer));
		return submittedAnswer;
	}
	
	
	//Checks if given answer is correct
	@Override
	boolean checkCorrect(String[] inputAnswer) {
		String compareA = Arrays.deepToString(inputAnswer);
		String compareB = Arrays.deepToString(correctAnswer);
		return compareA.equals(compareB) && compareB.equals(compareA);
	}
}
