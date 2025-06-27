package com.web.meuequipo.core.signinPeriod.util;

import com.web.meuequipo.core.signinPeriod.SigninPeriod;
import com.web.meuequipo.core.signinPeriod.dto.response.SigninPeriodInfoResponse;

public class SigninPeriodUtil {

    public static SigninPeriodInfoResponse mapSigninPeriodToInfoResponse(SigninPeriod period) {
        SigninPeriodInfoResponse response = new SigninPeriodInfoResponse();

        response.setId(period.getId());
        response.setDateInit(period.getDateInit());
        response.setDateEnd(period.getDateEnd());
        response.setDownloads(period.getDownloads());
        response.setIsActive(period.isActive());
        response.setHasForm(period.hasForm());

        return response;
    }
}
