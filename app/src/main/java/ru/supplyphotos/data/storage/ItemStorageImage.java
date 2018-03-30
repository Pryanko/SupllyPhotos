package ru.supplyphotos.data.storage;

/**
 * @author Libgo on 30.03.2018.
 */
public class ItemStorageImage {

    private Integer id_item;

    private String path;

    private Integer countPrint;

    private Boolean isSelected;


    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCountPrint() {
        return countPrint;
    }

    public void setCountPrint(Integer countPrint) {
        this.countPrint = countPrint;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
