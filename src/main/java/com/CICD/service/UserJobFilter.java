package com.CICD.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class UserJobFilter implements InfoContributor {
@Autowired
CICDService cICDService;
public long getUserJobFilterDetails(String jobName) {
	return cICDService.getAllUsers().stream().filter(user-> user.getJobName().equals(jobName)).count();
}
@Override
public void contribute(Builder builder) {
	// TODO Auto-generated method stub
	Map<String, Long> userJobMap=new HashMap<>();
	userJobMap.put("enginner", getUserJobFilterDetails("enginner"));
	userJobMap.put("ui/ux", getUserJobFilterDetails("ui/ux"));
	builder.withDetail("userJobFilter", userJobMap);
}
}
