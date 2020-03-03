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
import TaskAllocation.Task;
import TaskAllocation.Utility;

public class PoliceUnit extends Agent {

	private Map<Task, Utility> utilitiesMap;
	private Map<Task, Double> bidsMap;

	public PoliceUnit(Location location, int id, HashSet<AgentType> agentType) {
		super(location, id, agentType);
	}

	public PoliceUnit(int i, HashSet<AgentType> agentType) {
		super(i, agentType);
		// TODO Auto-generated constructor stub
	}

	public void createUtiliesAndBids(Vector<Task> tasks, Mailer mailer, double Tnow) {
		initUtilitiesMap(tasks, Tnow);
		initBidsMap();
		sendBidsToTasks(tasks, mailer);
	}

	private void initBidsMap() {
		this.bidsMap = new HashMap<Task, Double>();
		double sumUtilis = calcSumUtils();
		
		
		
		
		for (Entry<Task, Utility> e : utilitiesMap.entrySet()) {
			Task task = e.getValue();
			
			
			this.bidsMap.put(, )
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

}



/* unexplained if in utility calculcation
 * if (task.getPriority() <= 2) { u = new ConcaveUtilityThresholds( this, task,
 * Tnow,pow); } else {
 */
