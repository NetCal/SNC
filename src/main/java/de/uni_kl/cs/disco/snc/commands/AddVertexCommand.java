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

package de.uni_kl.cs.disco.snc.commands;

import de.uni_kl.cs.disco.snc.SNC;
import de.uni_kl.cs.disco.snc.network.Network;
import de.uni_kl.cs.disco.snc.network.NetworkActionException;
import de.uni_kl.cs.disco.snc.symbolic_math.BadInitializationException;
import de.uni_kl.cs.disco.snc.symbolic_math.ServiceFactory;

/**
 * Add a {@link Vertex} with given properties to the target network.
 * 
 * @author Sebastian Henningsen
 */
public class AddVertexCommand implements Command {
	private final String alias;
	private final double rate;
	private final int networkID;
	private final SNC snc;
	
	private int vertexID;
	private boolean success;
    
    /**
     * Creates a new Command to add a vertex
     * 
     * @param alias The name of the vertex
     * @param rate The service rate the vertex offers (Because only constant rate
     * service is possible at the moment
     * @param networkID The network the vertex belongs to
     * @param snc The overall controller
     */
    public AddVertexCommand(String alias, double rate, int networkID, SNC snc) {
        this.alias = alias != null ? alias : "";
        this.rate = rate;
        this.networkID = networkID;
        this.snc = snc;
        
        this.vertexID = -1;
        this.success = false;
    }
    
    @Override
    public void execute() {
    	Network nw = snc.getCurrentNetwork();
    	
        try {
            vertexID = nw.addVertex(ServiceFactory.buildConstantRate(-rate), alias).getID();
        } catch (BadInitializationException ex) {
        	vertexID = -1;
        	success = false;
            throw new NetworkActionException(ex);
        }
		// TODO Why is this? In the try block or after? See AddFlowCommand
		snc.getCurrentNetwork().getVertex(vertexID).getService().getServicedependencies().clear();
	
		success = true;
    }

    @Override
    public void undo() {
        if(success) {
            snc.getCurrentNetwork().removeVertex(vertexID);
        }
    }
}
