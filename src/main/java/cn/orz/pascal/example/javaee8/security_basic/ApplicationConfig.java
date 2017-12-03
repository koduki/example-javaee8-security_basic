/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.javaee8.security_basic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

/**
 *
 * @author koduki
 */
@BasicAuthenticationMechanismDefinition(
        realmName = "test realm"
)

@ApplicationScoped
@Named
public class ApplicationConfig {

}
