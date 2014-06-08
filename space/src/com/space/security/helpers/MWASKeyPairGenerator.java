/**
 * 
 */
package com.space.security.helpers;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import sun.security.provider.DSAKeyPairGenerator;
import sun.security.rsa.RSAKeyPairGenerator;

/**
 * @author Admin
 *
 */
public class MWASKeyPairGenerator  {

	public static KeyPair kp = null;
	
	
	public static synchronized void generateKeyPair(String alg)
	{
	   if (kp == null){	
		try {
			//KeyPairGenerator kpg = KeyPairGeneratorSpi.classgetInstance(alg);
			if (alg.equalsIgnoreCase(SecurityConstants.DSA_ENCRYPT))
			{
				DSAKeyPairGenerator dkpg = new DSAKeyPairGenerator();
				dkpg.initialize(1024, true, new SecureRandom());
				kp=dkpg.genKeyPair();
			}
			else if (alg.equalsIgnoreCase(SecurityConstants.MD5_RSA))
			{
				RSAKeyPairGenerator rkpg = new RSAKeyPairGenerator();
				rkpg.initialize(1024, new SecureRandom());	
				kp=rkpg.generateKeyPair();
			}
			else
			{
				KeyPairGenerator kpg = KeyPairGenerator.getInstance(alg);
				kpg.initialize(1024);
				kp=kpg.genKeyPair();
			}
				
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   }	
	}
	
	public static PublicKey getPublicKey()
	{
		//PublicKey pubkey = null;
		//if (encryption.equalsIgnoreCase(SecurityConstants.DSA_ENCRYPTION))
		//{
			return kp.getPublic();
		//}
		
		//return pubkey;
	}
	
	
	public static PrivateKey getPrivateKey()
	{
		//PrivateKey privkey = null;
		//if (encryption.equalsIgnoreCase(SecurityConstants.DSA_ENCRYPTION))
		//{
		     return kp.getPrivate();
		//}
		
		//return privkey;
	}
	
	
}
