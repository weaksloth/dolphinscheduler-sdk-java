package com.github.weaksloth.dolphinscheduler.sdk.core;

public class DolphinClient {

    private String address;

    private String projectCode;

    public DolphinClient(String address,String projectCode) {
        this.address = address;
        this.projectCode = projectCode;
    }


    /**
     * create workflow
     *
     * @return
     */
    public String createProcessDefinition() {

    }

}
