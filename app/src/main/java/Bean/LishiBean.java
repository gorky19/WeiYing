package Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by ZhangTAO on 2018/1/4.
 */
@Entity
public class LishiBean {
    @Id
    private Long lid;
    private String key;
    private String icons;
    private String titles;
    public String getTitles() {
        return this.titles;
    }
    public void setTitles(String titles) {
        this.titles = titles;
    }
    public String getIcons() {
        return this.icons;
    }
    public void setIcons(String icons) {
        this.icons = icons;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Long getLid() {
        return this.lid;
    }
    public void setLid(Long lid) {
        this.lid = lid;
    }
    @Generated(hash = 1900663866)
    public LishiBean(Long lid, String key, String icons, String titles) {
        this.lid = lid;
        this.key = key;
        this.icons = icons;
        this.titles = titles;
    }
    @Generated(hash = 782686426)
    public LishiBean() {
    }

}
