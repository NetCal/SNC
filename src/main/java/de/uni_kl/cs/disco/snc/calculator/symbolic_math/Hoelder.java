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

package de.uni_kl.cs.disco.snc.calculator.symbolic_math;

import java.io.Serializable;

/**
 *
 * @author Sebastian Henningsen
 */
public class Hoelder implements Serializable {

	//Members
	
	private static final long serialVersionUID = 2169450349608663397L;
	private int hoelder_id;
	private double p_value;
	private double q_value;
	
	//Constructors
	
	/**
	 * Creates a dummy Hoelder
	 * 
	 * @param hoelder_id
	 */
	public Hoelder(int hoelder_id){
		this.hoelder_id = hoelder_id;
	}
	
    public Hoelder(int hoelder_id, double p_value){
		this.hoelder_id = hoelder_id;
		this.p_value = p_value;
		this.q_value = 1/(1-1/p_value);
	}
	
    public Hoelder(int hoelder_id, double value, boolean p_value){
		this.hoelder_id = hoelder_id;
		if(p_value){
			this.p_value = value;
			this.q_value = 1/(1-1/value);
		}
		else{
			this.q_value = value;
			this.p_value = 1/(1-1/value);
		}
	}

	//Methods
	
	@Override
	public String toString(){
		return "p-value: "+p_value+"   q-value: "+q_value;
	}
	
	//Getter and Setter
	
	public int getHoelderID() {
		return hoelder_id;
	}

    public double getPValue() {
		return p_value;
	}

    public void setPValue(double p_value) {
		this.p_value = p_value;
		this.q_value = 1/(1-1/p_value);
	}

    public double getQValue() {
		return q_value;
	}

    public void setQValue(double q_value) {
		this.q_value = q_value;
		this.p_value = 1/(1-1/q_value);
	}
	
    public Hoelder copy() {
        return new Hoelder(this.hoelder_id, this.p_value);
    }
}
