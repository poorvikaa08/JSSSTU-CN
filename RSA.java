import java.util.*;
import java.math.*;

class RSA_Algorithm {
	BigInteger publicKey, privateKey, mod;
	
	void getKeys(int bitlen) {
		Random r = new Random();
		BigInteger p = BigInteger.probablePrime(bitlen, r);
		BigInteger q = BigInteger.probablePrime(bitlen, r);
		
		mod = p.multiply(q);
		// n = p * q;
		
		// Compute Euler's toitent
		// phi = (p - 1) * (q - 1);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		//public key exponent --> e
		publicKey = BigInteger.probablePrime(bitlen/2, r);
		
		//validate it
		// gcd(e, phi(n)) = 1 
		// e < phi(n)
		while(!phi.gcd(publicKey).equals(BigInteger.ONE) || publicKey.compareTo(phi) >= 0) {
			publicKey = BigInteger.probablePrime(bitlen/2, r);
		}
		
		// d * e = 1 mod phi(n)
		privateKey = publicKey.modInverse(phi); //generate private key
		
	}

	BigInteger encrypt(BigInteger m) {
		return m.modPow(publicKey, mod);
		// C = M^e mod n

		
	}
	
	BigInteger decrypt(BigInteger c) {
		return c.modPow(privateKey, mod);
		// M = C^d mod n
	}
}

class RSA {
	public static void main(String[] args) {
		RSA_Algorithm rsa = new RSA_Algorithm();
		rsa.getKeys(512);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the message to be encrypted: ");
		String m = sc.next();
		BigInteger message = new BigInteger(m.getBytes());
		
		BigInteger c = rsa.encrypt(message);
		System.out.println("Encrypted message: " + c.longValue());
		
		BigInteger d = rsa.decrypt(c);
		System.out.println("Decrypted message: " + new String(d.toByteArray()));
		
		sc.close();
		
	}
	
}
