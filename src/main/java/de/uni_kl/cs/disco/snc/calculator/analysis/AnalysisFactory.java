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

import de.uni_kl.cs.disco.snc.calculator.network.Flow;
import de.uni_kl.cs.disco.snc.calculator.network.Network;
import de.uni_kl.cs.disco.snc.calculator.network.Vertex;

/**
 *
 * @author Sebastian Henningsen
 */
public class AnalysisFactory {
    public static Analyzer getAnalyzer(AnalysisType type, Network nw, Map<Integer,Vertex> vertices, Map<Integer,Flow> flows, int flow_of_interest, int vertex_of_interest, BoundType boundtype) {
        switch(type) {
            case SIMPLE_ANA:
                return new SimpleAnalysis(nw, vertices, flows, flow_of_interest, vertex_of_interest, boundtype);
            case LADDER_ANA:
            	return new LadderAnalysis(nw, vertices, flows, flow_of_interest, vertex_of_interest, boundtype);
            default:
                throw new AnalysisException("Analysis Type: " + type.toString() + " not known.");
        }
    }
}
