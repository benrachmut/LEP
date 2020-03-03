package Solver;

import java.util.Vector;

import PoliceTaskAllocation.PoliceUnit;
import fisher.FisherDistributed;
import fisher.FisherDistributedCA;
import fisher.FisherPolinom;
import fisher.FisherSemiDistributed;
import TaskAllocation.Assignment;
import TaskAllocation.Task;
import TaskAllocation.Utility;

public class FisherDistributedSolverCA extends FisherSolver {

	private Vector<PoliceUnit>agents;
	private double Tnow;
	
	public FisherDistributedSolverCA(Utility[][] input,
			TaskOrdering taskOrdering, Vector<Task> tasks, Vector<PoliceUnit> agents, double Tnow) {
		super(input, taskOrdering, tasks);
		this.agents = agents;
		this.Tnow = Tnow;

	}

	@Override
	public Vector<Assignment>[] solve() {
		FisherDistributedCA f2 = new FisherDistributedCA(agents, tasks, Tnow);
		creatFisherSolution(f2.algorithm());
		return taskPrioritization(allocation);
	}

}
