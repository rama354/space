/**
 * 
 */
package com.space.security.certs;

import java.security.cert.Certificate;

import org.omg.CORBA.portable.InputStream;

/**
 * @author Admin
 *
 */
public interface CertificateManager{

	/**
	 * 
	 */
		
	public void exportCertificate(Certificate cert);


	public Certificate importCertificate(InputStream is);
	
	
	public String getCertificateType();
	
	
	public Certificate generateCertificate(String host,int days,String certname,String sigalg);
	
}
