package com.sishuok.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-22
 * <p>Version: 1.0
 */
@RestController
@RequestMapping("/messageGateWay")
public class UserController {

    @RequestMapping(value = "/illegalSenior")
    public String processIllegalSenior() {
    	//TODO Should add process logic after project started
        return "illegalSenior";
    }

    @RequestMapping(value = "/legalSenior", method = RequestMethod.POST)
	public String processLegalSenior(){
    	//TODO Should add process logic after project started
    	return "legalSenior";
    }
}