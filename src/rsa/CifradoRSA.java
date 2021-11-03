/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author User
 */
public class CifradoRSA {
    
    int tam_Primo;
    BigInteger n, p, q; //numero que ocupa el rsa
    BigInteger fi;
    BigInteger e, d;
    
    //constructor de la clase
    public CifradoRSA(int tam_Primo){
        this.tam_Primo = tam_Primo;
    }
    
    //metodo para generar los numeros primos
    public void generarPrimos(){
        p = new BigInteger(tam_Primo, 10, new Random());
        do q = new BigInteger(tam_Primo, 10, new Random());
        while(q.compareTo(p) == 0);
    }
    
    //el metodo para generar las claves 
    //n = p * q
    // fi = (p-1) * (q-1)
    //de ahi hay que elegir el numero e y d eligiendo como un coprimo menor que
    
    public void generarClaves(){
        //n = p * q
        n = p.multiply(q);
        
        // fi = (p-1) * (q-1)
        
        fi = p.subtract(BigInteger.valueOf(1));
        fi = fi.multiply(q.subtract(BigInteger.valueOf(1)));
        
        //como elegimos un coprimo de 1 < e < fi
        
        do e = new BigInteger(2*tam_Primo, new Random());
        while((e.compareTo(fi) != -1) || (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0));
        
        //calcular d
        // d = e^1 mod fi
        //el multiplo inversor
        
        d = e.modInverse(fi);
    }
    
    //vamos a cifrar usando la clave publica
    //solo se pueden cifrar numeros 
    
    
    public BigInteger[] cifrar(String mensaje){
        
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        
        BigInteger[] bigdigitos = new BigInteger[digitos.length];
        
        //vamos a iterar esos digitos 
        
        for(i = 0; i < bigdigitos.length; ++i){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }
        
        // C = M^e mod(fi)
        
        BigInteger[] cifrado = new BigInteger[bigdigitos.length];
        
        for(i = 0; i < bigdigitos.length; ++i){
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }
        
        return cifrado;
    }
    
    //descifrar con la clave privada
    
    public String descifrar(BigInteger[] cifrado){
        
        BigInteger[] descifrado = new BigInteger[cifrado.length];
        
        //M = C^d mod n

        for(int i = 0; i < descifrado.length; ++i){
            descifrado[i] = cifrado[i].modPow(d, n);
        }
        
        char[] charArray = new char[descifrado.length];
        
        for(int i = 0; i < descifrado.length; ++i){
            charArray[i] = (char)(descifrado[i].intValue());
        }
        
        return (new String(charArray));
        
    }
    
}
