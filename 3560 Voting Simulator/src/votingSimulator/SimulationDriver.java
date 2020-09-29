package votingSimulator;

public class SimulationDriver {

	public static void main(String[]args)
	{
		//Generates Students given a random amount of names
		String[] nameList = {"Joe", "Ophelia", "Jasmine", "Alex", "Mark", "Michael", "Marcus", "Opal", "Zuko", "Viktoria", "Victor", "Natasha", "Alpha", "Thor", "Tim", "Tom", "Tam", "Timothy", "Tomothy",
							 "Thomas", "Thomson", "Matthew", "Dianne", "Cecilia", "Roy", "David", "Bean", "Conner", "Ryan", "Byan", "Cyan", "Tyrone", "Dyrone", "Heather", "Zhu Li", "Julie", "Josh",
							 "James", "Jimothy", "Dwight", "Sam", "Samantha", "Cathrine", "Katrine", "Elena", "Homelander", "Daimon", "Rick", "Tyler", "Jason", "Motthew", "Charlie", "Addision",
							 "Toby", "Bella", "Sophie", "Cleo", "Oliver", "Rat Boy", "Batman", "Edward", "Jeremy", "Alphonse"};
		
		System.out.println("Creating Voting Service Object and Adding Random # of Students");
		
		VotingService iVote = new VotingService();
		iVote.generateStudents(nameList);
		
		System.out.println("Current # of students in the Voting Service: " + iVote.getnumOfStudents());
		System.out.println("Creating 2 Questions, one allowing multiple selections, one allowing single selection");
		
		//Both questions use default constructor with 6 options, can also configure the options to be certain number of answer choices
		Question multipleSelect = new multiChoice();
		Question singleSelect = new singleChoice();
		
		System.out.println("\nTesting Single Choice Question\n-------------------------------");
		iVote.setCurrentQuestion(singleSelect);
		iVote.startService();
		iVote.resetService();
		
		System.out.println("\nGenerating Random # of Studnets");
		iVote.clearStudents();
		iVote.generateStudents(nameList);
		System.out.println("Current # of students in the Voting Service: " + iVote.getnumOfStudents());
		System.out.println("Testing Multiple Selection Question\n-------------------------------");
		iVote.setCurrentQuestion(multipleSelect);
		iVote.startService();
		iVote.resetService();
		
		System.out.println("\nGenerating Random # of Studnets");
		System.out.println("Current # of students in the Voting Service: " + iVote.getnumOfStudents());
		System.out.println("Testing Single Selection Question with Two Options\n-------------------------------");
		String[] temp = {"A", "B"};
		Question twoOptions = new singleChoice("A or B?", temp);
		iVote.setCurrentQuestion(twoOptions);
		iVote.startService();
		iVote.resetService();
		
		System.out.println("Testing Single Selection Question while Simulating Students changing Answers\n-------------------------------");
		iVote.simulateChange();
		iVote.resetService();
		
		System.out.println("Testing Multi Selection Question while Simulating Students changing Answers\n-------------------------------");
		iVote.setCurrentQuestion(multipleSelect);
		iVote.simulateChange();
		iVote.resetService();
		
		System.out.println("Testing Single Selection Question while Simulating Students trying to submit answers when answers are already submitted\n-------------------------------");
		iVote.setCurrentQuestion(twoOptions);
		iVote.simulatePrevention();
		iVote.resetService();
		
	}
}
