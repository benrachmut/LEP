package PoliceTaskAllocation;

import java.util.HashMap;
import java.util.TreeMap;

import Helpers.Metrics;
import Helpers.MetricsSummary;
import Helpers.WriteToFile;
import TaskAllocation.LinearUtility;

public class MainSimulationForThreads {

	/**
	 * @param args
	 * @throws Exception
	 */
	//static HashMap<AgentType, Integer> agents;
	public static int patrols = 4;
	static TreeMap<Double, Double> cumulativeSW;
	static MetricsSummary metrics;
	static long numOfMissionsOnShift;
	static int i;
	static int shift;
	static int agents;

	public static void main(String[] args) throws Exception {

		//int[] missions = {20, 40, 60, 80, 100};
		int[] missions = {4};
		for (i = 2; i <= 2; i++) {

			String algorithm = "SA++";

			agents = (int) Math.pow(i, 2);
			patrols = (int) Math.pow(i, 2);
			algorithm = algorithm + "4";
			metrics = new MetricsSummary(algorithm, 100);
			for (int t = 0; t < 1;t++) {

				
				//numOfMissionsOnShift = Math.round(patrols*missions[t]/9.0);

				numOfMissionsOnShift = missions[t];
				metrics.setup(numOfMissionsOnShift);
				cumulativeSW = new TreeMap<Double, Double>();
				shift = 0;
				int cores = 4;
				ThreadSimulation[] threads = new ThreadSimulation[cores];
				for (int j = 0; j < cores; j++) {
					threads[j] = new ThreadSimulation();
				}
				for (int j = 0; j < cores; j++) {
					threads[j].start();
				}
				for (int j = 0; j < cores; j++) {
					threads[j].join();
				}
				metrics.writeToFile();
				WriteToFile.writeCumultiveUtilitiyToFile(cumulativeSW, 100,
						algorithm, (int) numOfMissionsOnShift);
			}
			System.out.println("finished " + i);
		}

	}

	public static synchronized DynamicPoliceAllocation newSimulation() {
		shift++;
		System.out.println(shift);
		if (shift > 100) {
			return null;
		}
		Initialize init = new Initialize(agents, patrols,shift);
		init.createPatrolAreas("input.xls", "patrol areas_" + patrols);
		String sheet = "" + i + "\\shift_" + shift + "_" + numOfMissionsOnShift
				+ ".csv";
		init.createConstraints("input.xls", "constraints_t");
		init.createMissionEvents(sheet);

		DynamicPoliceAllocation d = new DynamicPoliceAllocation(
				init.getDiary(), init.getActiveEvent(), init.getPoliceUnits(),
				cumulativeSW, shift, metrics);
		return d;
	}

}
