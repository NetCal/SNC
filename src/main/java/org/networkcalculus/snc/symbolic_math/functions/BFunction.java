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

package org.networkcalculus.snc.symbolic_math.functions;

import java.util.Map;

import org.networkcalculus.snc.symbolic_math.Hoelder;
import org.networkcalculus.snc.symbolic_math.ParameterMismatchException;
import org.networkcalculus.snc.symbolic_math.ServerOverloadException;
import org.networkcalculus.snc.symbolic_math.SymbolicFunction;
import org.networkcalculus.snc.symbolic_math.ThetaOutOfBoundException;

/**
 * A class representing functions, which are the result of one
 * theta-dependent function into the "B-function". The 
 * B-function looks like the following: If f is a function depending 
 * on t, the B-function is:<br>
 * B(f,t)= -1/t * log(1-exp(t*f(t)))<br>
 * The B-function is needed for calculating all performance bounds
 * (backlog, delay, output) as well as for concatenate service
 * elements.
 * The original function (called atom-function) is denoted by
 * <code>exponent</code>and must be a {@link SymbolicFunction}. The 
 * maximal theta for which the resulting function is defined, is 
 * given by the maximal theta of the atom-function. 
 * To calculate values of the resulting function knowledge about the 
 * parameter-sets of the atom-function is needed and represented in 
 * <code>parameterIDs</code>.
 * 
 * @author Michael Beck
 * 
 * @see SymbolicFunction
 *
 */
public class BFunction implements SymbolicFunction {
	private static final long serialVersionUID = -393050275685989790L;
	
	private SymbolicFunction exponent;
	private double maxtheta;
	private Map<Integer, Hoelder> parameters;
	
	public BFunction(SymbolicFunction exponent){
		this.exponent = exponent;
		this.parameters = exponent.getParameters();
		maxtheta = exponent.getmaxTheta();
	}
	
	/**
	 * Calculates the value of the resulting B-function at theta 
	 * (first entry in <code>parameters</code>), with given 
	 * <code>parameters</code>.
	 * 
     * @param theta
	 * @param parameters contains the needed parameters (including
	 * theta, as first entry).
	 * 
	 * @return the value of the B-function
	 * 
     * @throws org.networkcalculus.snc.symbolic_math.ThetaOutOfBoundException
	 * @throws ParameterMismatchException 
	 */
	@Override
	public double getValue(double theta, Map<Integer, Hoelder> parameters)
			throws ThetaOutOfBoundException, ServerOverloadException, ParameterMismatchException {
		//Checks for a mismatch in number of given and needed parameters
		if(parameters.size() != this.parameters.size()){
			throw new ParameterMismatchException("Number of parameters does not match for atom function (B-Function)");
		}
		
		//Checks if argument in the logarithm is non-positive (see definition of the B-function)
		if(exponent.getValue(theta, parameters) >= 0){
			throw new ServerOverloadException("Usage of non-positive argument in log(). Argument:"+Double.toString(1-Math.exp(exponent.getValue(theta, parameters))));
		} else {
			return -1/theta*Math.log(1-Math.exp(theta*exponent.getValue(theta, parameters)));
		}
	}

	/**
	 * Returns a string representation of the B-function. In the 
	 * form <code>B(f)</code>.
	 * 
     * @return 
	 */
	@Override
	public String toString(){
		StringBuffer b_str = new StringBuffer();
		
		b_str.append("B(");
		b_str.append(exponent.toString());
		b_str.append(")");
		
		return b_str.toString();
	}

    public Map<Integer, Hoelder> getParameters() {
		return parameters;
	}
	
    @Override
	public double getmaxTheta(){
		return maxtheta;
	}
}