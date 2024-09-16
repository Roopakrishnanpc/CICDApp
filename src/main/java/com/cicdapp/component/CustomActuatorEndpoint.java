package com.cicdapp.component;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Endpoint(id = "custom")
public class CustomActuatorEndpoint {

    public String message;// = "Default message";

//    @ReadOperation
//    public String getMessage() {
//        return this.message;
//    }

//    @WriteOperation
//    public void setMessage(String message) {
//        this.message = message;
//    }

    @DeleteOperation
    public void clearMessage() {
        this.message = "Default message";
    }
    Map<String,List<String>> releasenotes=new LinkedHashMap<>();
    @PostConstruct
    public void initNotes()
    {
    	releasenotes.put("version-1.0", Arrays.asList("Home page","new custom actutator"));
    	releasenotes.put("version-1.1", Arrays.asList("Home page with views","new custom actutator"));
    }
    @ReadOperation
    public Map<String,List<String>> getReleaseNotesdata()
    {
    	return releasenotes;
    }
    @ReadOperation
    public List<String> getReleaseNotes(@Selector String version)
    {
    	return releasenotes.get(version);
    }
    @WriteOperation
    public List<String> addReleaseNotes(@Selector String version, String rleasenote)
    {
    	return releasenotes.put(version,Arrays.stream(rleasenote.split(",")).collect(Collectors.toList()));
    }
    @DeleteOperation
    public void deleteNotes(@Selector String version)
    {
    	releasenotes.remove(version);
    }
}
