// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package com.amazonaws.kmscsr.examples;

import com.google.gson.annotations.SerializedName;

public class Config {

    @SerializedName("aws_key_spec")
    private String awsKeySpec;

    @SerializedName("aws_key_arn")
    private String awsKeyArn;

    @SerializedName("cert_common_name")
    private String certCommonName;

    @SerializedName("cert_country_code")
    private String certCountryCode;

    @SerializedName("cert_organization_name")
    private String certOrganizationName;

    @SerializedName("cert_locality")
    private String certLocality;

    @SerializedName("cert_state")
    private String certState;

    public String getAwsKeySpec() {
        return awsKeySpec;
    }

    public String getAwsKeyArn() {
        return awsKeyArn;
    }

    public String getCertCommonName() {
        return certCommonName;
    }

    public String getCertCountryCode() {
        return certCountryCode;
    }

    public String getCertOrganizationName() {
        return certOrganizationName;
    }

    public String getCertLocality() {
        return certLocality;
    }

    public String getCertState() {
        return certState;
    }
}