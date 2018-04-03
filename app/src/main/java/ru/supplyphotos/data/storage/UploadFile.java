package ru.supplyphotos.data.storage;

import java.io.File;

/**
 * @author Libgo on 03.04.2018.
 */
public class UploadFile {

    private File file;

    public UploadFile(File file, Integer photoId) {
        this.file = file;
        this.photoId = photoId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    private Integer photoId;

}
