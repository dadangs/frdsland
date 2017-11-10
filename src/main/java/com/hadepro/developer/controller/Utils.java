/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.support.XmlWebApplicationContext;
 
public class Utils {
 
    public static void main(String args[]) throws Exception {
        String cryptedPassword = new BCryptPasswordEncoder().encode("111");
        System.out.println(cryptedPassword);
    }
    public static String findSchemaDB(HttpServletRequest request){
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/SpringBeans.xml");
        context.setServletContext(request.getServletContext());
        context.refresh();
        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        String schema = obj.getSchema();
        context.close();
        return schema;
    }
    
    public static int getMaxResults(HttpServletRequest request){
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/SpringBeans.xml");
        context.setServletContext(request.getServletContext());
        context.refresh();
        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        int maxR = Integer.parseInt(obj.getProsen_admin());
        context.close();
        return maxR;
    }
}