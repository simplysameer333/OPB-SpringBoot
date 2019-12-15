package com.banking.opb.domain.custom;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Pin_reset
{
	@JsonProperty("requested_date")
    private String requested_date;
	@JsonProperty("reason_requested")
    private String reason_requested;

   /* public String getRequested_date ()
    {
        return requested_date;
    }

    public void setRequested_date (String requested_date)
    {
        this.requested_date = requested_date;
    }

    public String getReason_requested ()
    {
        return reason_requested;
    }

    public void setReason_requested (String reason_requested)
    {
        this.reason_requested = reason_requested;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [requested_date = "+requested_date+", reason_requested = "+reason_requested+"]";
    }*/
}