package PoliceTaskAllocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import TaskAllocation.Agent;
import TaskAllocation.ConcaveUtility;
import TaskAllocation.ConcaveUtilityThresholds;
import TaskAllocation.LinearUtility;
import TaskAllocation.Location;
import TaskAllocation.Mailer;
import TaskAllocation.Message;
import TaskAllocation.Messageable;
import TaskAllocation.Task;
import TaskAllocation.Utility;

public class PoliceUnit extends Agent implements Messageable {

	private int decisionCounter;
	private Map<Task, Utility> utilitiesMap;
	private Map<Task, Double> bidsMap;
	private Mailer mailer;
	public PoliceUnit(Location location, int id, HashSet<AgentType> agentType) {
		super(location, id, agentType);
	}

	public PoliceUnit(int i, HashSet<AgentType> agentType) {
		super(i, agentType);
		// TODO Auto-generated constructor stub
	}

	public void createUtiliesBidsAndSendBids(Vector<Task> tasks, Mailer mailer, double Tnow) {
		decisionCounter= 0;
		this.mailer = mailer;

		initUtilitiesMap(tasks, Tnow);
		initBidsMap();
		sendBidsToTasks(tasks);
	}

	private void sendBidsToTasks(Vector<Task> tasks) {
		
		for (Entry<Task, Double> e : bidsMap.entrySet()) {
			Task task = e.getKey();
			Double bid = e.getValue();
			
			this.createMessage(task,bid);
		}
	}

	private void initBidsMap() {
		this.bidsMap = new HashMap<Task, Double>();
		double sumUtilities = calcSumUtils();
		placeBidsInMap(sumUtilities);
		
		
	}

	private void placeBidsInMap(double sumUtilities) {
		for (Entry<Task, Utility> e : utilitiesMap.entrySet()) {
			Task task = e.getKey();
			Utility u  = e.getValue();
			Double bid = u.getUtility(1)/sumUtilities;
			this.bidsMap.put(task,bid);
		}
		
	}

	private double calcSumUtils() {
		double ans = 0.0;
		for (Utility u : utilitiesMap.values()) {
			ans = ans+ u.getUtility(1);
		}
		return ans;
	}

	private void initUtilitiesMap(Vector<Task> tasks, double Tnow) {
		this.utilitiesMap = new HashMap<Task, Utility>();
		for (Task task : tasks) {
			Utility u = new ConcaveUtilityThresholds(this, task, Tnow, 1);
			this.utilitiesMap.put(task, u);
		}
	}

	@Override
	public void getMessage(Message m) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createMessage(Messageable task, double bid) {
		this.mailer.createMessage(this, this.decisionCounter, task, bid);
	}

}



/* unexplained if in utility calculcation
 * if (task.getPriority() <= 2) { u = new ConcaveUtilityThresholds( this, task,
 * Tnow,pow); } else {
 */
