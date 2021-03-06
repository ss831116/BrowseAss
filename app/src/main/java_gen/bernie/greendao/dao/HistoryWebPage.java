package bernie.greendao.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "HISTORY_WEB_PAGE".
 */
@Entity
public class HistoryWebPage {

    @Id
    private Long id;
    private String webPageSite;
    private String webTitle;
    private String scanTime;

    @Generated
    public HistoryWebPage() {
    }

    public HistoryWebPage(Long id) {
        this.id = id;
    }

    @Generated
    public HistoryWebPage(Long id, String webPageSite, String webTitle, String scanTime) {
        this.id = id;
        this.webPageSite = webPageSite;
        this.webTitle = webTitle;
        this.scanTime = scanTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebPageSite() {
        return webPageSite;
    }

    public void setWebPageSite(String webPageSite) {
        this.webPageSite = webPageSite;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

}
