package ru.supplyphotos.data.upload;

import java.io.File;

/**
 * @author libgo (19.03.2018)
 */

public class ImageFile {


    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    private File file;

    private String path;

    private String nameImage;

    public Integer getItemOrderId() {
        return itemOrderId;
    }

    public void setItemOrderId(Integer itemOrderId) {
        this.itemOrderId = itemOrderId;
    }

    private Integer itemOrderId;

    public Integer getCountPrint() {
        return countPrint;
    }

    public void setCountPrint(Integer countPrint) {
        this.countPrint = countPrint;
    }

    private Integer countPrint;

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
