package votingSimulator;

import java.util.*;

public class VotingService {
	
	private HashSet<Student> studentList;
	private ArrayList<String> answerCollection;
	private Question currentQuestion;
	private int numCorrect;
	private int numIncorrect;
	
	public VotingService()
	{
		studentList = new HashSet<Student>();
		numCorrect = 0;
		numIncorrect = 0;
		answerCollection = new ArrayList<String>();
	}

	//Starts the service by generatin the answers, collecting answers, and showing the stats after
	public void startService()
	{
		generateAnswers();
		collectAnswers();
		showStats();
	}
	
	//Start method that simulates students changing their answer throughout the submission process
	public void simulateChange()
	{
		generateAnswers();
		changeAnswers();
		collectAnswers();
		showStats();
	}
	
	//Start method that simulates prevention of students from submitting multiple answers
	public void simulatePrevention()
	{
		generateAnswers();
		collectAnswers();
		showStats();
		changeAnswers();
		collectAnswers();
		showStats();
	}
	
	public void generateStudents(String[] nameList)
	{
		studentList.clear();
		Random ran = new Random();
		int numOfStudents = ran.nextInt(30) + 15;
		
		for (int i = 0; i < numOfStudents; i++)
		{
			Student tempStudent = new Student(nameList[ran.nextInt(nameList.length)]);
			this.addStudent(tempStudent);
		}
	}
	
	//Resets the service to be utilized again by clearing answerCollection and setting all students to be ready for the next submission
	public void resetService()
	{
		System.out.println("-------------------------------\nRESETTING SERVICE");
		Iterator<Student> itr = studentList.iterator();
		while(itr.hasNext())
		{
			itr.next().setAnswerSubmitted(false);
		}
		answerCollection.clear();
		numCorrect = 0;
		numIncorrect = 0;
	}
	
	//Displays statistics of the results of the question such as how many students got all answers correct and the cardinality of each answer
	private void showStats()
	{
		System.out.println(currentQuestion + "\n\nSUBMISSION RESULTS:" + "\nCorrect Students: " + numCorrect + "\nIncorrect Students: " + numIncorrect + calculateEachAnswer());
	}
	
	//Creates random answers from all students based on the type of question
	private void generateAnswers()
	{
		Iterator<Student> itr = studentList.iterator();
		Random ran = new Random();
		while(itr.hasNext())
		{
			Student tempStudent = itr.next();	
			if(currentQuestion instanceof multiChoice)
			{
				tempStudent.setStudentAnswer(currentQuestion.getAnswerBank(), ran.nextInt(currentQuestion.getAnswerBank().length) + 1);
			}
			else if (currentQuestion instanceof singleChoice)
				tempStudent.setStudentAnswer(currentQuestion.getAnswerBank(), 1);
		}
	}

	//Simulates students changing their answers before the submission process
	public void changeAnswers()
	{
		Iterator<Student> itr = studentList.iterator();
		Random ran = new Random();
		while(itr.hasNext())
		{
			Student tempStudent = itr.next();	
			if(currentQuestion instanceof multiChoice)
			{
				tempStudent.changeAnswer(currentQuestion.getAnswerBank(), ran.nextInt(currentQuestion.getAnswerBank().length) + 1);
			}
			else if (currentQuestion instanceof singleChoice)
				tempStudent.changeAnswer(currentQuestion.getAnswerBank(), 1);
		}
	}
	
	//Collects all the submitted answers; before collecting the answers checks if the student had previously submitted an answer beforehand
	private void collectAnswers()
	{
		
		Iterator<Student> itr = studentList.iterator();
		while(itr.hasNext())
		{
			Student tempStudent = itr.next();	
			String[] tempAnswer = currentQuestion.submitAnswer(tempStudent);
			if (!tempStudent.getAnswerSubmitted())
			{
				for(int i = 0; i < tempAnswer.length; i++)
				{
			
					answerCollection.add(tempAnswer[i]);		
				}
				
				if(currentQuestion.checkCorrect(tempAnswer))
				{
					System.out.println(tempStudent.getStudentName() + " is correct!\n");
					numCorrect++;
				}
				else
				{
					System.out.println(tempStudent.getStudentName() + " is incorrect!\n");
					numIncorrect++;
				}
				tempStudent.setAnswerSubmitted(true);
			}		
			else
			{
				System.out.println("Answer already submitted by " + tempStudent.getStudentName() + "\n");
			}

		}
	}
	
	//Calculates the cardinality of each answer given by the students
	private String calculateEachAnswer()
	{
		
		String[] tempCollection = new String[answerCollection.size()];
		tempCollection = answerCollection.toArray(tempCollection);
		Map<String, Integer> dataCollection = new HashMap<String, Integer>();
	      for(String answer : tempCollection) {
	            Integer value = dataCollection.get(answer);
	            if(value == null) 
	            {
	                dataCollection.put(answer, 1);
	            }
	            else 
	            {
	                dataCollection.put(answer, value + 1);
	            }
	      	}
	      return "\nAnswer Statistics:" + dataCollection;
	}
	
	//Logistic Methods to manage VotingService

	public void addStudent(Student student)
	{
		studentList.add(student);
	}
	
	public boolean remove(Student student)
	{
		return studentList.remove(student);
	}
	
	public void clearStudents()
	{
		 studentList.clear();
	}
	
	public int getnumOfStudents()
	{
		return studentList.size(); 
	}
	
	//Setters and Getters
	
	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}


	public int getNumCorrect() {
		return numCorrect;
	}


	public int getNumIncorrect() {
		return numIncorrect;
	}
 
}
