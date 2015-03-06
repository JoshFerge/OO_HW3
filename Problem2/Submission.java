/**
* Submission.java
*
* A representation of a Submission
*/

import java.util.Random;
import java.util.ArrayList;
public class Submission
{	
    private Random myRandom;
	

    // You may add attributes to this class if necessary
    private boolean lastErrorWasTimeout;
	private boolean lastTestPassed;

	public Submission()
	{
	    myRandom = new Random();
		lastErrorWasTimeout = false;
		
	}
	public Random getRandom(){
		return myRandom;
	}
    public void runTestCase()
	{
	    // For now, randomly pass or fail, possibly due to a timeout
		lastTestPassed = myRandom.nextBoolean();
		if(!lastTestPassed)
		{
		    lastErrorWasTimeout = myRandom.nextBoolean();
		}
		// You can add to the end of this method for reporting purposes
	}
	public boolean getLastTestPassed(){
		return lastTestPassed;
	}
    public boolean getLastErrorWasTimeout()
	{
	    return lastErrorWasTimeout;
	}
}

abstract class Report {
	protected Submission sub;
	public Report(Submission s) {
		sub = s;
	}
	public abstract void printReport();
	
	public abstract void update();
	
}

class PassedReport extends Report {
	private int numPassed;
	public PassedReport(Submission s) {
		super(s);
		numPassed = 0;
	}
	public void printReport() {
		System.out.println("Reports Passed: " + numPassed);
	}
	public void update(){
		if(sub.getLastTestPassed()) {
			numPassed++;
		}
	}
}

class TimeoutReport extends Report {
	private int numTimeout;
	public TimeoutReport(Submission s) {
		super(s);
		numTimeout = 0;
	}
	public void printReport() {
		System.out.println("Reports Timed Out: " + numTimeout);
	}
	public void update(){
		if(!sub.getLastTestPassed())
			if(sub.getLastErrorWasTimeout())
				numTimeout++;
	}
}
class SubmissionObserver {
	private Submission submission;
	private ArrayList<Report> reports;
	public SubmissionObserver(Submission submission){
		this.submission = submission;
		reports = new ArrayList<Report>();
	}
	public void attachReport(Report r){
		reports.add(r);
	}
	public void detachReport(Report r){
		reports.remove(r);
	}
	public void updateReports(){
		for(Report report:reports){
			report.update();
		}
	}
	public void printReports(){
		for(Report report:reports){
			report.printReport();
		}
	}
}
class Driver {
	public static void main(String[] args){
		Submission submission = new Submission();
		Report passedreport = new PassedReport(submission);
		Report timeoutreport = new TimeoutReport(submission);
		SubmissionObserver observer = new SubmissionObserver(submission);
		
		observer.attachReport(passedreport);
		observer.attachReport(timeoutreport);
		
		submission.runTestCase();
		observer.updateReports();
		submission.runTestCase();
		observer.updateReports();
		submission.runTestCase();
		observer.updateReports();
		observer.printReports();
	}
}
