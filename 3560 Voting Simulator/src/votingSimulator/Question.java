package votingSimulator;

import java.util.*;

abstract class Question {
	
	protected String[] correctAnswer;
	protected String displayQuestion;
	protected String[] answerBank;
	
	abstract String[] submitAnswer(Student student);
	
	abstract String[] generateCorrect();

	abstract boolean checkCorrect(String[] inputAnswer);
		
	private String showQuestion()
	{
		return displayQuestion;
	}
	
	private String[] showAnswers()
	{
		return answerBank;
	}
	
	private String[] showCorrect()
	{
		return correctAnswer;
	}
	
	
	public String[] getAnswerBank() {
		return answerBank;
	}

	public void setAnswerBank(String[] answerBank) {
		this.answerBank = answerBank;
	}

	public String toString()
	{
		return "-------------------------------" + "\nQuestion: " + showQuestion() + "\nAnswer Choices: " + Arrays.deepToString(showAnswers()) + "\nCorrect Answer: " + Arrays.deepToString(showCorrect());	
	}
	
	 
	
}
