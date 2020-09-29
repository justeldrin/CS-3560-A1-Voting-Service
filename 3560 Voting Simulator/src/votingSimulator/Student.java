package votingSimulator;
import java.util.*;

public class Student {

	private String studentName;
	private String studentID;
	private String[] studentAnswer;
	private Boolean answerSubmitted;
	private final int ANSWER_SIZE = 6;

	//Constructors

	public Student()
	{
		studentName = "Average Joe";
		studentID = generateStudentID();
		studentAnswer = new String[ANSWER_SIZE];
		answerSubmitted = false;
	}
	
	public Student(String studentName)
	{
		this();
		this.studentName = studentName;
	}
	
	
	public Student(String studentName, String studentID, String[] studentAnswer, Boolean answerSubmitted)
	{
		this.studentName = studentName;
		this.studentID = studentID;
		this.studentAnswer = studentAnswer;
		this.answerSubmitted = answerSubmitted;

	}
	
	public Student(Student student)
	{
		this(student.studentName, student.studentID, student.studentAnswer, student.answerSubmitted);
	}
	
	
/*	//Constructor to Test Duplicate student ID's
	public Student(String studentID)
	{
		super();
		this.studentID = studentID;
	}
*/
	
	//Method to display effects of a student changing their answer before submission
	public void changeAnswer(String[] answerChoices, int numOfAnswers)
	{
		Random ran = new Random();
		int numOfChanges = ran.nextInt(5) + 1;
		for (int i = 0; i < numOfChanges; i++)
		{
			int amountChange = ran.nextInt(numOfAnswers) + 1;
			setStudentAnswer(answerChoices, amountChange);
			System.out.println(studentName + " has changed their answer to: " + Arrays.deepToString(studentAnswer));

		}	
		System.out.println("");
	}
	
	//Generates a random answer given the question type as well as the answerBank
	public String[] generateRandomAnswers(String[] answerChoices, int numOfAnswers)
	{
		Random ran = new Random();
		String[] tempCorrect = new String[numOfAnswers];
		for (int i = 0; i < numOfAnswers; i++)
		{
			String tempChosen = answerChoices[ran.nextInt(answerChoices.length)];
			for (int j = 0; j < tempCorrect.length; j++)
			{
				if(tempChosen == tempCorrect[j])
				{
					tempChosen = answerChoices[ran.nextInt(answerChoices.length)];
					j = -1;
				}
			}
			tempCorrect[i] = tempChosen;
		}
		return tempCorrect;
	}
	

	private String generateStudentID() {
		
		String tempID = "";
		Random ran = new Random();
		
		for (int i = 0; i < 7; i++)
		{
			int tempNum = ran.nextInt(9);
			tempID = tempID + tempNum;
		}
		return tempID;
	}
	
	//HashSet Data Structure Methods
	
	public boolean equals(Student student)
	{
		if(student instanceof Student)
			return this.studentID == student.studentID;
		else
			return false;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentID == null) {
			if (other.studentID != null)
				return false;
		} else if (!studentID.equals(other.studentID))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentID == null) ? 0 : studentID.hashCode());
		return result;
	}

	
	//Set and Get Methods
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	public void setStudentAnswer(String[] answerChoices, int numOfAnswers)
	{
		this.studentAnswer =  generateRandomAnswers(answerChoices, numOfAnswers);
	}
	
	public String[] getStudentAnswer() {
		return studentAnswer;
	}

	public Boolean getAnswerSubmitted() {
		return answerSubmitted;
	}

	public void setAnswerSubmitted(Boolean answerSubmitted) {
		this.answerSubmitted = answerSubmitted;
	}	
	
}
