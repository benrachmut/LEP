package PoliceTaskAllocation;

import java.util.HashSet;

import TaskAllocation.Agent;
import TaskAllocation.Location;

public class PoliceUnit extends Agent {

	public PoliceUnit(Location location, int id, HashSet<AgentType> agentType) {
		super(location, id, agentType);
	}


	public PoliceUnit(int i, HashSet<AgentType> agentType) {
		super(i, agentType);
		// TODO Auto-generated constructor stub
	}
}
