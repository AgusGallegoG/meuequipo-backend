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

    private final SigninFormFileSystemUtil fileSystemUtil;

    public SigninPeriodServiceImpl(SigninPeriodRepository signinPeriodRepository, SigninFormFileSystemUtil fileSystemUtil) {
        this.signinPeriodRepository = signinPeriodRepository;
        this.fileSystemUtil = fileSystemUtil;
    }


    @Override
    @Transactional
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
    @Transactional
    public SigninPeriodInfoResponse updateForm(MultipartFile file) {
        SigninPeriod period = getActiveSigninPeriod();

        String path = this.fileSystemUtil.saveNewForm(file);

        period.setFormPath(path);

        signinPeriodRepository.save(period);

        return SigninPeriodUtil.mapSigninPeriodToInfoResponse(period);
    }

    @Override
    public SigninPeriodInfoResponse getInfo() {
        return SigninPeriodUtil.mapSigninPeriodToInfoResponse(this.getActiveSigninPeriod());
    }

    @Override
    @Transactional
    public Resource downloadForm() {
        SigninPeriod period = getActiveSigninPeriod();

        period.setDownloads(period.getDownloads() + 1);

        return fileSystemUtil.getFormAsResource(period.getFormPath());
    }

    @Override
    public Boolean checkActive() {
        SigninPeriod period = getActiveSigninPeriod();
        return period.isActive();
    }


    private SigninPeriod getActiveSigninPeriod() {
        return signinPeriodRepository.findPeriodOfActualSeason()
                .orElseThrow(() -> new SigninFormException("Non se atopou o periodo de inscripcións aberto"));
    }
}
