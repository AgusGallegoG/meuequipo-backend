package com.web.meuequipo.core.image.service;

import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.dto.ImageViewDTO;
import com.web.meuequipo.core.image.util.ImageFilesystemUtil;
import com.web.meuequipo.core.image.util.ImageUtil;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    private final ImageFilesystemUtil imageFilesystemUtil;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageFilesystemUtil imageFilesystemUtil, ImageRepository imageRepository) {
        this.imageFilesystemUtil = imageFilesystemUtil;
        this.imageRepository = imageRepository;
    }


    @Override
    @Transactional
    public ImageViewDTO saveImage(MultipartFile file) {
        ImageFilesystemUtil.CreationData data = imageFilesystemUtil.saveImageInFileSystem(file);

        Image image = new Image();

        image.setUrl(data.url());
        image.setOriginalName(data.originalName());
        image.setStoredName(data.storedName());
        image.setRelativePath(data.relativePath());

        this.imageRepository.save(image);

        return this.getImageViewDTOById(image.getId());
    }


    @Override
    public ImageViewDTO getImageViewDTOById(Long id) {
        Image image = this.imageRepository.findById(id).orElse(null);
        if (image == null) {
            return null;
        }

        return ImageUtil.getImageViewDTO(image);

    }

    @Override
    public Resource serveImage(String relativePath) {
        return imageFilesystemUtil.getImageResource(relativePath);
    }
}
