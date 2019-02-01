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

package org.networkcalculus.snc.utils;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class MapUtils {
	/**
	 * Creates a map containing each element of the <code>Set set</code> as key
	 * and the <coded>Object value</code> as corresponding value.
	 * @param set the set whose elements will be the keys of entries in the map
	 * @param value the value of each of the map's entries
	 * @return a map
	 */
	public static <K,V> Map<K,V> createMap(Set<K> set, V value) {
		Map<K,V> map = new HashMap<K,V>(set.size());
		for (Iterator<K> iter = set.iterator(); iter.hasNext(); ) {
			map.put(iter.next(), value);
		}
		return map;
	}
}
