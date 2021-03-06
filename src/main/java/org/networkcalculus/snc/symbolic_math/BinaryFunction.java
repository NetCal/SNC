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

package org.networkcalculus.snc.symbolic_math;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.networkcalculus.snc.utils.SetUtils;

import java.util.Set;

/**
 *
 * @author Sebastian Henningsen
 */
public abstract class BinaryFunction implements SymbolicFunction {
	private static final long serialVersionUID = 97304677458400403L;
	
	protected SymbolicFunction first;
    protected SymbolicFunction second;
    
    private Map<Integer, Hoelder> firstParameters;
    private Map<Integer, Hoelder> secondParameters;
    
    protected Hoelder hoelder;

    public BinaryFunction(SymbolicFunction first, SymbolicFunction second) {
		this.first = first;
		this.second = second;
		this.firstParameters = first.getParameters();
		this.secondParameters = second.getParameters();
    }

    public BinaryFunction(SymbolicFunction first, SymbolicFunction second, Hoelder hoelder) {
		this(first, second);
		this.hoelder = hoelder;
    }

    /**
     * The number of parameters, is the sum of the parameters needed for the
     * atom functions (parameters belonging to both functions are counted
     * twice). If the atom-functions are stochastically dependent one further
     * parameter (the Hölder- coefficient) is needed to calculate the values of
     * the resulting function.
     *
     * @return An {@link ArrayList} of the parameter IDs needed to perform
     * {@link getValue}.
     */
    @Override
    public Map<Integer, Hoelder> getParameters() {
		Map<Integer, Hoelder> output = new HashMap<>(0);
		output.putAll(firstParameters);
		output.putAll(secondParameters);
		if (hoelder != null) {
		    output.put(hoelder.getHoelderID(), hoelder);
		}
		return output;
    }

    double getValueOfFunction(double theta, Map<Integer, Hoelder> inputParameters, int functionNo) throws ParameterMismatchException, ThetaOutOfBoundException, ServerOverloadException {
		Map<Integer, Hoelder> parametersForFunction = new HashMap<>();
		Set<Entry<Integer, Hoelder>> entrySet;
		double thetaForFunction = 0;
		if (functionNo == 1) {
		    entrySet = firstParameters.entrySet();
		    thetaForFunction = (hoelder == null) ? theta : theta * hoelder.getPValue();
		} else if (functionNo == 2) {
		    entrySet = secondParameters.entrySet();
		    thetaForFunction = (hoelder == null) ? theta : theta * hoelder.getQValue();
		} else {
		    throw new ParameterMismatchException("No such function with number " + functionNo);
		}
	
		for (Map.Entry<Integer, Hoelder> entry : entrySet) {
		    if (inputParameters.containsKey(entry.getKey())) {
			parametersForFunction.put(entry.getKey(), inputParameters.get(entry.getKey()));
		    } else {
			throw new ParameterMismatchException("Needed hoelder_id is not found in given parameters.");
		    }
		}
	
		return (functionNo == 1 ? first.getValue(thetaForFunction, parametersForFunction)
			: second.getValue(thetaForFunction, parametersForFunction));
	    }
	
	    boolean checkForParameterMismatch(Map<Integer, Hoelder> parameters) {
		return ((hoelder == null && parameters.size() != SetUtils.getUnion(firstParameters.keySet(), secondParameters.keySet()).size())
			|| (hoelder != null && parameters.size() != SetUtils.getUnion(firstParameters.keySet(), secondParameters.keySet()).size() + 1));
    }

    @Override
    public double getmaxTheta() {
		if (hoelder == null) {
		    return Math.min(first.getmaxTheta(), second.getmaxTheta());
		} else {
		    return Math.min(first.getmaxTheta() / hoelder.getPValue(), second.getmaxTheta() / hoelder.getQValue());
		}
    }
}
