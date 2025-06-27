package com.web.meuequipo.core.signinPeriod.util;

import com.web.meuequipo.core.shared.exception.BaseException;
import com.web.meuequipo.core.signinPeriod.config.SigninPeriodProperties;
import com.web.meuequipo.core.signinPeriod.exception.SigninFormException;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class SigninFormFileSystemUtil {
    private final SigninPeriodProperties signinPeriodProperties;

    public SigninFormFileSystemUtil(SigninPeriodProperties signinPeriodProperties) {
        this.signinPeriodProperties = signinPeriodProperties;
    }

    @PostConstruct
    public void initPath() {
        Path path = Paths.get(signinPeriodProperties.getPath());
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new BaseException("Erro creando o path para o gardado do formulario de inscripcion", e);
        }

    }


    public String saveNewForm(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String newName = UUID.randomUUID() + "." + originalName;
        String subfolder = getSubfolder();
        String relativePath = String.join("/", subfolder, newName);

        Path targetPath = Paths.get(signinPeriodProperties.getPath(), relativePath);

        try {
            Files.createDirectories(targetPath);
            Path fileInFileSystem = targetPath.resolve(relativePath);
            Files.copy(file.getInputStream(), fileInFileSystem, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BaseException("Error saving new signin form");
        }

        return relativePath;
    }


    public Resource getFormAsResource(String relativePath) {
        try {
            Path path = Paths.get(signinPeriodProperties.getPath(), relativePath);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new SigninFormException("Form not found");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new BaseException("Error getting resource from filesystem", e);
        }
    }

    private String getSubfolder() {
        return LocalDate.now().getYear() + "";
    }


}
