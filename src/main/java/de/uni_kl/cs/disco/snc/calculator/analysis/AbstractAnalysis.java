/*
 *  (c) 2017 Michael A. Beck, Sebastian Henningsen
 *  		disco | Distributed Computer Systems Lab
 *  		University of Kaiserslautern, Germany
 *  All Rights Reserved.
 *
 * This software is work in progress and is released in the hope that it will
 * be useful to the scientific community. It is provided "as is" without
 * express or implied warranty, including but not limited to the correctness
 * of the code or its suitability for any particular purpose.
 *
 * This software is provided under the MIT License, however, we would 
 * appreciate it if you contacted the respective authors prior to commercial use.
 *
 * If you find our software useful, we would appreciate if you mentioned it
 * in any publication arising from the use of this software or acknowledge
 * our work otherwise. We would also like to hear of any fixes or useful
 */

package de.uni_kl.cs.disco.snc.calculator.analysis;

import java.util.Map;

import de.uni_kl.cs.disco.snc.calculator.network.ArrivalNotAvailableException;
import de.uni_kl.cs.disco.snc.calculator.network.Flow;
import de.uni_kl.cs.disco.snc.calculator.network.Network;
import de.uni_kl.cs.disco.snc.calculator.network.Vertex;
import de.uni_kl.cs.disco.snc.calculator.symbolic_math.Arrival;
import de.uni_kl.cs.disco.snc.calculator.symbolic_math.BadInitializationException;

/**
 * This performs the analytical part of determining the sigma- and 
 * rho-parts of the flow of interest (FoI) and the service of 
 * interest (SoI).
 * The analysis is initialized with all information needed to 
 * compute leftover-service- and output-bounds until a
 * (sigma,rho)-representation of the FoI and SoI is known. After 
 * this a description of the performance-bound is calculated and
 * returned in a compact {@link Arrival}-representation. This class
 * does not complete the task of optimizing the parameters, which
 * appear in the performance-bound. 
 * If the network can not be resolved by successive computations of
 * leftover-service- and output-bounds, it is non-feed-forward and 
 * instead of a result a {@link DeadlockException} is thrown.
 * 
 * @author Michael Beck
 * @author Sebastian Henningsen
 */
public abstract class AbstractAnalysis implements Analyzer {
	protected Network nw;
    protected Map<Integer,Vertex> vertices;
    protected Map<Integer,Flow> flows;
    protected int flow_of_interest;
    protected int vertex_of_interest;
	protected BoundType BoundType;
        
	// TODO
 
    public AbstractAnalysis(Network nw, Map<Integer, Vertex> vertices, Map<Integer, Flow> flows, 
							int flow_of_interest, int vertex_of_interest, BoundType BoundType){
    	this.nw = nw;
		this.vertices = vertices;
		this.flows = flows;
		this.flow_of_interest = flow_of_interest; 
		this.vertex_of_interest = vertex_of_interest;
		this.BoundType = BoundType;
	}
	
    @Override
	public abstract Arrival analyze() throws ArrivalNotAvailableException, DeadlockException, BadInitializationException;

    public int getVertexOfInterest() {
		return vertex_of_interest;
	}

    public Map<Integer, Vertex> getVertices() {
		return vertices;
	}

    public Map<Integer, Flow> getFlows() {
		return flows;
	}

    public int getFlowOfInterest() {
		return flow_of_interest;
	}

    public BoundType getBoundType() {
		return BoundType;
	}
}
