package com.csl456.bikerentalapp;

import com.csl456.bikerentalapp.core.*;
import com.fasterxml.jackson.annotation.*;
import io.dropwizard.Configuration;
import io.dropwizard.db.*;

import javax.validation.*;
import javax.validation.constraints.*;

public class BikeRentalAppConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private final DataSourceFactory database = new DataSourceFactory();

    @NotNull
    @JsonProperty("smtpServerDetails")
    private SMTPServerDetails smtpServerDetails;

    public SMTPServerDetails getSmtpServerDetails() { return smtpServerDetails;}

    public void setSmtpServerDetails(SMTPServerDetails smtpServerDetails) {
        this.smtpServerDetails = smtpServerDetails;
    }

    public DataSourceFactory getDatabase() { return database;}

}
