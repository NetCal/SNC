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

package org.networkcalculus.snc.optimization;

import org.networkcalculus.snc.analysis.BoundType;
import org.networkcalculus.snc.symbolic_math.Arrival;
import org.networkcalculus.snc.symbolic_math.ParameterMismatchException;
import org.networkcalculus.snc.symbolic_math.ServerOverloadException;
import org.networkcalculus.snc.symbolic_math.ThetaOutOfBoundException;

/**
 * This interface specifies the methods an optimization algorithm must be capable of.
 * 
 * @author Sebastian Henningsen
 */
public interface Optimizer {
    /**
     * Minimizes the @link Optimizable bound which is provided via the constructor of the specific algorithm.
     * 
     * @param thetagranularity
     * @param hoeldergranularity
     * 
     * @return
     * 
     * @throws ThetaOutOfBoundException
     * @throws ParameterMismatchException
     * @throws ServerOverloadException
     */
    public double minimize(double thetagranularity, double hoeldergranularity) 
            throws ThetaOutOfBoundException, ParameterMismatchException, ServerOverloadException;

    @Deprecated
    public double Bound(Arrival input, BoundType boundtype, 
            double bound, double thetagranularity, double hoeldergranularity) 
            throws ThetaOutOfBoundException, ParameterMismatchException, ServerOverloadException;

    @Deprecated
    public abstract double ReverseBound(Arrival input, BoundType boundtype, 
            double violation_probability, double thetagranularity, double hoeldergranularity) 
            throws ThetaOutOfBoundException, ParameterMismatchException, ServerOverloadException;
}
