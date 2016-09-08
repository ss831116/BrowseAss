package bernie.greendao.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "BROWSE_ASS_BOOK_MARKS".
 */
@Entity
public class BrowseAssBookMarks {

    @Id
    @Index
    private String id;
    private String webSite;
    private String webSiteIcon;
    private String saveDate;

    @Generated
    public BrowseAssBookMarks() {
    }

    public BrowseAssBookMarks(String id) {
        this.id = id;
    }

    @Generated
    public BrowseAssBookMarks(String id, String webSite, String webSiteIcon, String saveDate) {
        this.id = id;
        this.webSite = webSite;
        this.webSiteIcon = webSiteIcon;
        this.saveDate = saveDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getWebSiteIcon() {
        return webSiteIcon;
    }

    public void setWebSiteIcon(String webSiteIcon) {
        this.webSiteIcon = webSiteIcon;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

}