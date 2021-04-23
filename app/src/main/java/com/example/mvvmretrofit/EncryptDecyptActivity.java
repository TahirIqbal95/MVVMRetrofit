package com.example.mvvmretrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptDecyptActivity extends AppCompatActivity {

    EditText editText;
    TextView textViewEncrypt,textViewDecrypt;
    Button button,btnpublic,btnprivate;

    String encryptedMessage = null;
     String decryptedMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_decypt);

        editText = findViewById(R.id.edttxt);
        textViewEncrypt = findViewById(R.id.txt_encrypt);
        textViewDecrypt = findViewById(R.id.txt_decrypt);
        button = findViewById(R.id.button);
        btnpublic = findViewById(R.id.buttonpublic);
        btnprivate = findViewById(R.id.buttonprivate);


        Aes256Class aes256 = new Aes256Class();


        String targetString = "Hello";

        byte[] encryptedString = aes256.makeAes(targetString.getBytes(), Cipher.ENCRYPT_MODE);

        byte[] decodedString = aes256.makeAes(encryptedString, Cipher.DECRYPT_MODE);


       /* Map keyPair = null;
        try {
            keyPair = CryptoUtil.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        String publicKey = (String) keyPair.get("publicKey");
        String privateKey = (String) keyPair.get("privateKey");


        try {
            encryptedMessage = CryptoUtil.encrypt("abc", publicKey);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


        try {
            decryptedMessage = CryptoUtil.decrypt(encryptedMessage, privateKey);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(EncryptDecyptActivity.this, decryptedMessage, Toast.LENGTH_SHORT).show();


                textViewEncrypt.setText(new String(encryptedString));
                textViewDecrypt.setText(new String(decodedString));


            }
        });

        btnpublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(EncryptDecyptActivity.this,  "my public key"+ "\n" + publicKey.length() , Toast.LENGTH_SHORT).show();

            }
        });

        btnprivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(EncryptDecyptActivity.this,  "my private key"+ "\n" + privateKey.length() , Toast.LENGTH_SHORT).show();

            }
        });


    }
}