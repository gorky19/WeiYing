package Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ZhangTAO on 2018/1/4.
 */
@Entity
public class ShouBean {
    @Id
    private Long sid;
    private String shou_title;
    private String shou_icon;
    private String shou_dataId;
    public String getShou_dataId() {
        return this.shou_dataId;
    }
    public void setShou_dataId(String shou_dataId) {
        this.shou_dataId = shou_dataId;
    }
    public String getShou_icon() {
        return this.shou_icon;
    }
    public void setShou_icon(String shou_icon) {
        this.shou_icon = shou_icon;
    }
    public String getShou_title() {
        return this.shou_title;
    }
    public void setShou_title(String shou_title) {
        this.shou_title = shou_title;
    }
    public Long getSid() {
        return this.sid;
    }
    public void setSid(Long sid) {
        this.sid = sid;
    }
    @Generated(hash = 1783977333)
    public ShouBean(Long sid, String shou_title, String shou_icon,
            String shou_dataId) {
        this.sid = sid;
        this.shou_title = shou_title;
        this.shou_icon = shou_icon;
        this.shou_dataId = shou_dataId;
    }
    @Generated(hash = 1170396491)
    public ShouBean() {
    }
}
