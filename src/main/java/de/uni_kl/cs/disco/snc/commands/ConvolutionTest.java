package de.uni_kl.cs.disco.snc.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.uni_kl.cs.disco.snc.SNC;
import de.uni_kl.cs.disco.snc.network.Flow;
import de.uni_kl.cs.disco.snc.network.Network;
import de.uni_kl.cs.disco.snc.network.Vertex;
import de.uni_kl.cs.disco.snc.symbolic_math.Arrival;
import de.uni_kl.cs.disco.snc.symbolic_math.functions.ConstantFunction;

public class ConvolutionTest {
	 public static void main(String[] args) {
		 (new ConvolutionTest()).runtest();
	 }
	
	 private void runtest() {
		System.out.println("Convolution Test:");
		Network nw = SNC.getInstance().getCurrentNetwork();
		Command addV1 = new AddVertexCommand("V1", -2.0, -1, SNC.getInstance());
		Command addV2 = new AddVertexCommand("V2", -1.0, -1, SNC.getInstance());
		List<Integer> f1Route = new ArrayList<>();
		List<Integer> f1Prio = new ArrayList<>();
		f1Route.add(1);
		f1Route.add(2);
		f1Prio.add(1);
		f1Prio.add(1);
		Arrival arrival = new Arrival(new ConstantFunction(0), new ConstantFunction(0.5), nw);
		Command addF1 = new AddFlowCommand("F1", arrival, f1Route, f1Prio, -1, SNC.getInstance());
		//Command convV1V2 = new ConvolveVerticesCommand(1, 2, -1, SNC.getInstance());
		SNC.getInstance().invokeCommand(addV1);
		SNC.getInstance().invokeCommand(addV2);
		SNC.getInstance().invokeCommand(addF1);
		//invokeCommand(convV1V2);
		
		Map<Integer, Vertex> vertices = nw.getVertices();
		Map<Integer, Flow> flows = nw.getFlows();
		System.out.println("Flows");
		for (Entry<Integer, Flow> entry : flows.entrySet()) {
			System.out.print(entry.getValue().getAlias() + ": " + entry.getValue().getVerticeIDs());
		}
		
		System.out.println("\nVertices");
		for (Entry<Integer, Vertex> entry : vertices.entrySet()) {
			System.out.print(entry.getKey() + " " + entry.getValue().getAlias() + " ");
		}
	 }
}
