package myBrain.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import myBrain.exception.SpringBlogException;
@Service
public class JwtProvider {
	private KeyStore keyStore;
	
	@PostConstruct
	public void init(){
		try {
			keyStore=KeyStore.getInstance("JKS");
			InputStream resourceAsStream=getClass().getResourceAsStream("/mybrain.jks");
			keyStore.load(resourceAsStream, "allahone".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException| CertificateException| IOException e) {
			throw new SpringBlogException("Exception occured while loading keystore");
		}
	}

	public String generateToken(Authentication authentication) {
		User principal=(User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.signWith(getPrivateKey())
				.compact();
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("myblog", "allahone".toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			throw new SpringBlogException("Exception occured while retriving private key");
		}
	}

	public boolean validateToken(String jwt) {
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		return true;
	}

	private PublicKey getPublicKey() {
		try {
			return keyStore.getCertificate("myblog").getPublicKey();
		} catch (KeyStoreException e) {
			throw new SpringBlogException("Exception occured while retriving public key");
		}
	}

	public String getUsernameFromJwt(String jwt) {
		Claims claims=Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt).getBody();
		return claims.getSubject();
	}
	
	
}
