package com.web.meuequipo.core.image.util;

import com.web.meuequipo.core.image.config.ImageProperties;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.shared.exception.BaseException;
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
public class ImageFilesystemUtil {

    private final ImageProperties imageProperties;

    public ImageFilesystemUtil(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    @PostConstruct
    public void initPath() {
        Path path = Paths.get(imageProperties.getPath());
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new BaseException("Erro creando o path para a subida de imaxes", e);
        }
    }

    public CreationData saveImageInFileSystem(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String storedName = UUID.randomUUID() + "_" + originalName;
        String subfolder = getSubfolder();
        String relativePath = String.join("/", subfolder, storedName);

        Path targetPath = Paths.get(imageProperties.getPath(), subfolder);

        try {
            Files.createDirectories(targetPath);
            Path fileInFileSys = targetPath.resolve(storedName);
            Files.copy(file.getInputStream(), fileInFileSys, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BaseException("Error saving image in file system", e);
        }
        return new CreationData(originalName, storedName, relativePath);
    }

    public Resource getImageResource(String relativePath) {
        try {
            Path path = Paths.get(imageProperties.getPath(), relativePath);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new ImageException("Image not found");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new BaseException("Error getting image resource from file system", e);
        }
    }


    private String getSubfolder() {
        LocalDate now = LocalDate.now();

        String year = now.getYear() + "";
        String month = now.getMonthValue() + "";

        return String.join("/", year, month);
    }

    public record CreationData(
            String originalName,
            String storedName,
            String relativePath
    ) {
    }


}
