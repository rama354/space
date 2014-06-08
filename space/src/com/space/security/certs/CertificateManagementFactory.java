/**
 * 
 */
package com.space.security.certs;

import com.space.security.helpers.SecurityConstants;

/**
 * @author Admin
 *
 */
public class CertificateManagementFactory {

	private static CertificateManagementFactory instance = new CertificateManagementFactory();
	/**
	 * 
	 */
	CertificateManager certManager = null;
	
	private CertificateManagementFactory() 
	{

	}
	
	public static CertificateManagementFactory getInstance()
	{
		return instance;
	}
	
	public CertificateManager getCertManager() 
	{
		return certManager;
	}

	public void setCertManager(CertificateManager certManager) {
		this.certManager = certManager;
	}

	public CertificateManager createCertManager(String certificateType)
	{
		CertificateManager certManager = null;
		
		if (certificateType.equalsIgnoreCase(SecurityConstants.X509_CERT_TYPE))
		{
			certManager = new X509CertificateManager();	
		}
		
		return certManager;
		
		// Add more managers
	}

}
