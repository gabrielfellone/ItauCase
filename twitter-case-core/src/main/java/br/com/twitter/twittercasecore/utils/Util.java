package br.com.twitter.twittercasecore.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Util {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String IV = "";
	private String chaveencriptacao = "";

	public String encrypt(String originalPassword) {

		byte[] password = null;
		try {

			password = encrypt(originalPassword, chaveencriptacao);

		} catch (Exception e) {

			logger.info(e.toString());
		}

		return new String(Base64.getEncoder().encode(password));
	}

	private byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return encripta.doFinal(textopuro.getBytes("UTF-8"));
	}

	public Boolean mapHashtag(String hashtag) {
		if (hashtag.equalsIgnoreCase("cloud") || hashtag.equalsIgnoreCase("container")
				|| hashtag.equalsIgnoreCase("devops") || hashtag.equalsIgnoreCase("aws")
				|| hashtag.equalsIgnoreCase("microservices") || hashtag.equalsIgnoreCase("docker")
				|| hashtag.equalsIgnoreCase("openstack") || hashtag.equalsIgnoreCase("automation")
				|| hashtag.equalsIgnoreCase("gcp") || hashtag.equalsIgnoreCase("azure")
				|| hashtag.equalsIgnoreCase("istio") || hashtag.equalsIgnoreCase("sre")) {
			
			return true;

		}
		return false;

	}

}
