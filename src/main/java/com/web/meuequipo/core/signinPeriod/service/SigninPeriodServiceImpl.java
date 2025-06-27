package com.web.meuequipo.core.signinPeriod.service;


import com.web.meuequipo.core.signinPeriod.SigninPeriod;
import com.web.meuequipo.core.signinPeriod.data.SigninPeriodRepository;
import com.web.meuequipo.core.signinPeriod.dto.request.SigninPeriodUpdateRequest;
import com.web.meuequipo.core.signinPeriod.dto.response.SigninPeriodInfoResponse;
import com.web.meuequipo.core.signinPeriod.exception.SigninFormException;
import com.web.meuequipo.core.signinPeriod.util.SigninFormFileSystemUtil;
import com.web.meuequipo.core.signinPeriod.util.SigninPeriodUtil;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class SigninPeriodServiceImpl implements SigninPeriodService {

    private final SigninPeriodRepository signinPeriodRepository;

    private final SigninFormFileSystemUtil filseSystemUtil;

    public SigninPeriodServiceImpl(SigninPeriodRepository signinPeriodRepository, SigninFormFileSystemUtil filseSystemUtil) {
        this.signinPeriodRepository = signinPeriodRepository;
        this.filseSystemUtil = filseSystemUtil;
    }


    @Override
    public SigninPeriodInfoResponse updatePeriod(SigninPeriodUpdateRequest request) {
        SigninPeriod period = getActiveSigninPeriod();
        if (period.getId().equals(request.getId())) {
            throw new SigninFormException("Id of active signin period is not match");
        }

        period.setDateInit(request.getDateInit());
        period.setDateEnd(request.getDateEnd());

        signinPeriodRepository.save(period);

        return SigninPeriodUtil.mapSigninPeriodToInfoResponse(period);

    }

    @Override
    public SigninPeriodInfoResponse updateForm(MultipartFile file) {
        SigninPeriod period = getActiveSigninPeriod();

        period.setFormPath(this.filseSystemUtil.saveNewForm(file));

        return SigninPeriodUtil.mapSigninPeriodToInfoResponse(period);
    }

    @Override
    public SigninPeriodInfoResponse getInfo() {
        return SigninPeriodUtil.mapSigninPeriodToInfoResponse(this.getActiveSigninPeriod());
    }

    @Override
    public Resource downloadForm() {
        SigninPeriod period = getActiveSigninPeriod();

        return filseSystemUtil.getFormAsResource(period.getFormPath());
    }


    private SigninPeriod getActiveSigninPeriod() {
        return signinPeriodRepository.findPeriodOfActualSeason()
                .orElseThrow(() -> new SigninFormException("Non se atopou o periodo de inscripcións aberto"));
    }
}
