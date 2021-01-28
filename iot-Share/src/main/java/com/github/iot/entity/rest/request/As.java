package com.github.iot.entity.rest.request;

public enum As {
    /**
     *
     */
    CLIENTID("clientid"),
    /**
     *
     */
    USERNAME("username"),
    /**
     *
     */
    PEERHOST("peerhost");

    private String as;

    As(String as) {
        as = as;
    }

    public String getRequired() {
        return as;
    }
}
