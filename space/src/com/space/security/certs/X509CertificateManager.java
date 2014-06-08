/**
 * 
 */
package com.space.security.certs;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.sql.Date;

import org.omg.CORBA.portable.InputStream;

import com.space.security.helpers.MWASKeyPairGenerator;
import com.space.security.helpers.SecurityConstants;

import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

/**
 * @author Admin
 *
 */
public class X509CertificateManager implements CertificateManager {

	/* (non-Javadoc)
	 * @see com.space.security.helpers.CertificateManager#importCertificate()
	 */
	private final String certType = SecurityConstants.X509_CERT_TYPE;
	
	@Override
	public Certificate importCertificate(InputStream is) {
		Certificate certificate = null;
		try {
			 ///Check InputStream for X509 format else throw RuntimeException
			certificate = CertificateFactory.getInstance(certType).generateCertificate(is);
		
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return certificate;
	}

	/* (non-Javadoc)
	 * @see com.space.security.helpers.CertificateManager#generateCertificate()
	 */
	
	public Certificate generateCertificate(String dn,int days,String certname, String sigalg) {
		// TODO Auto-generated method stub
		X509CertInfo certInfo = new X509CertInfo();
		X509CertImpl certImpl = null;
		
		try {
			MWASKeyPairGenerator.generateKeyPair(SecurityConstants.MD5_RSA);
			
			//certInfo.set(X509CertInfo.NAME, certname);
			X500Name x500name = new X500Name(dn);  //the server host 
			//X500Signer signer = new X500Signer(Signature.getInstance(sigalg),x500name);
	
					
			//certInfo.set(X509CertInfo.ISSUER, new CertificateIssuerName(signer.getSigner()));
			certInfo.set(X509CertInfo.ISSUER, new CertificateIssuerName(x500name));
			//certInfo.set(X509CertInfo.SUBJECT, new CertificateSubjectName(signer.getSigner()));
			certInfo.set(X509CertInfo.SUBJECT, new CertificateSubjectName(x500name));
			
			certInfo.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
			Date from = new Date(System.currentTimeMillis());
			Date to = new Date(from.getTime()+ days*SecurityConstants.DAY_TO_MILLISEC_CONST);
			certInfo.set(X509CertInfo.VALIDITY, new CertificateValidity(from,to));
			
			
			certInfo.set(X509CertInfo.KEY, 
					new CertificateX509Key(MWASKeyPairGenerator.getPublicKey()));
			
			BigInteger sn = new BigInteger(64,new java.security.SecureRandom());
			certInfo.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(sn));
			
			//AlgorithmId algo = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
			AlgorithmId algorithm = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);
		    certInfo.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algorithm));
			
			certImpl = new X509CertImpl(certInfo);
			certImpl.sign(MWASKeyPairGenerator.getPrivateKey(),sigalg);  //Self-sign certificate
			
			return certImpl;
			
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public String getCertificateType() {
		// TODO Auto-generated method stub
		return certType;
	}

	@Override
	public void exportCertificate(Certificate cert) {
		// TODO Auto-generated method stub
		
	}

}
