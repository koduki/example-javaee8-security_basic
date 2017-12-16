///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cn.orz.pascal.example.javaee8.security_basic;
//
///**
// *
// * @author koduki
// */
//import static java.util.Arrays.asList;
//import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
//
//import java.util.HashSet;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStore;
//
//@ApplicationScoped
//public class TestIdentityStore implements IdentityStore {
//
//    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
//        if (usernamePasswordCredential.compareTo("admin", "password")) {
//            return new CredentialValidationResult("admin", new HashSet<>(asList("admin")));
//        }
//        return INVALID_RESULT;
//    }
//}
