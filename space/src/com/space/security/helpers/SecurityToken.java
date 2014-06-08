/**
 * 
 */
package com.space.security.helpers;

import java.util.Random;

/**
 * @author asus
 *
 */
public class SecurityToken {

	public static long createUniqueToken() {
		// TODO Auto-generate method stub
		Random random = new Random(980354);
		return random.nextLong();
	}

	public static int createUserID(){
		
		// TODO Auto-generate method stub
		Random random = new Random(980246);
		return random.nextInt();
	}
}
