package com.ivoronline.springbott_security_password.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  String password = "mypassword";
  String salted   = password + "mysalt";

  @ResponseBody
  @RequestMapping("/MD5")
  public String md5() {

    //ENCODE PASSWORDS
    String encodedPassword1 = DigestUtils.md5DigestAsHex(password.getBytes());
    String encodedPassword2 = DigestUtils.md5DigestAsHex(password.getBytes());
    String encodedPassword3 = DigestUtils.md5DigestAsHex(salted  .getBytes());
    String encodedPassword4 = DigestUtils.md5DigestAsHex(salted  .getBytes());

    //DISPLAY ENCODED PASSWORDS
    System.out.println(encodedPassword1); //34819d7beeabb9260a5c854bc85b3e44
    System.out.println(encodedPassword2); //34819d7beeabb9260a5c854bc85b3e44
    System.out.println(encodedPassword3); //399b1141e55e53a9df428ed4585ca3e9
    System.out.println(encodedPassword3); //399b1141e55e53a9df428ed4585ca3e9

    //RETURN SOMETHING
    return "Hello from Controller";

  }

  @ResponseBody
  @RequestMapping("/NoOp")
  public String noOp() {

    //ENCODE PASSWORDS
    PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
    String encodedPassword = noOp.encode(password);

    //DISPLAY ENCODED PASSWORDS
    System.out.println(encodedPassword); //34819d7beeabb9260a5c854bc85b3e44

    //RETURN SOMETHING
    return "Hello from Controller";

  }

  @ResponseBody
  @RequestMapping("/Ldap")
  public String ldap() {

    //ENCODE PASSWORDS
    PasswordEncoder ldap = new LdapShaPasswordEncoder();
    String encodedPassword1 = ldap.encode(password);
    String encodedPassword2 = ldap.encode(password);

    //DISPLAY ENCODED PASSWORDS
    System.out.println(encodedPassword1); //34819d7beeabb9260a5c854bc85b3e44
    System.out.println(encodedPassword2); //34819d7beeabb9260a5c854bc85b3e44

    if(ldap.matches(password, encodedPassword1)) { System.out.println("Passwords match");       }
    else                                         { System.out.println("Passwords don't match"); }

    //RETURN SOMETHING
    return "Hello from Controller";

  }

  @ResponseBody
  @RequestMapping("/Sha256")
  public String sha256() {

    //GET PASSWORD ENCODER
    PasswordEncoder sha256 = new StandardPasswordEncoder();

    //ENCODE PASSWORDS
    String encodedPassword1 = sha256.encode(password);
    String encodedPassword2 = sha256.encode(password);

    //DISPLAY ENCODED PASSWORDS
    System.out.println(encodedPassword1);
    System.out.println(encodedPassword2);

    if(sha256.matches(password, encodedPassword1)) { System.out.println("Passwords match");       }
    else                                           { System.out.println("Passwords don't match"); }

    //RETURN SOMETHING
    return "Hello from Controller";

  }

  @ResponseBody
  @RequestMapping("/BCrypt")
  public String bcrypt() {

    //GET PASSWORD ENCODER
    PasswordEncoder bcrypt = new BCryptPasswordEncoder();

    //ENCODE PASSWORDS
    String encodedPassword1 = bcrypt.encode(password);
    String encodedPassword2 = bcrypt.encode(password);

    //DISPLAY ENCODED PASSWORDS
    System.out.println(encodedPassword1);
    System.out.println(encodedPassword2);

    if(bcrypt.matches(password, encodedPassword1)) { System.out.println("Passwords match");       }
    else                                           { System.out.println("Passwords don't match"); }

    //RETURN SOMETHING
    return "Hello from Controller";

  }

}
