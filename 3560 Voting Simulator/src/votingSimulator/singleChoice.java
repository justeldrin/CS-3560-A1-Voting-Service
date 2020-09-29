package votingSimulator;

import java.util.Arrays;
import java.util.Random;

public class singleChoice extends Question{

	/* Variables and Fields from Question Class
 	protected String[] correctAnswer;
	protected String displayQuestion;
	protected String[] answerBank = {"A", "B", "C", "D", "E", "F"};
 */
	public singleChoice()
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
	
	public singleChoice(String displayQuestion)
	{
		this();
		this.displayQuestion = displayQuestion;
	}
	
	public singleChoice(String displayQuestion, String[] inputBank)
	{
		this.displayQuestion = ": A, B, C, D, E, or F?";
		answerBank = new String[inputBank.length];
		for(int i = 0; i < inputBank.length; i++)
		{
			answerBank[i] = inputBank[i];
		}
		correctAnswer = generateCorrect();
	}
	
	//Assigns a random value in the answer bank to be the correct answer
	@Override
	String[] generateCorrect() {
		Random ran = new Random();
		String tempChosen[] = new String[1];
		tempChosen[0] = answerBank[ran.nextInt(answerBank.length)];
		return tempChosen;
	}
		
	//Takes Student Object and submits answer to the voting service
	@Override
	String[] submitAnswer(Student student) {
		Student tempStudent = new Student(student);
		String[] submittedAnswer = tempStudent.getStudentAnswer();
		System.out.println(tempStudent.getStudentName() + "'s answer: " + Arrays.deepToString(submittedAnswer));
		return submittedAnswer;
	}
	
	//Checks if given answer is correct
	@Override
	boolean checkCorrect(String[] inputAnswer) {
		return inputAnswer[0] == correctAnswer[0];
	}
}
