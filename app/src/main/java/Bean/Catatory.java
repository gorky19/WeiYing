package Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ZhangTAO on 2018/1/2.
 */
@Entity
public class Catatory {
    @Id
    private Long cid;
    private String lishi_text;
    public String getLishi_text() {
        return this.lishi_text;
    }
    public void setLishi_text(String lishi_text) {
        this.lishi_text = lishi_text;
    }
    public Long getCid() {
        return this.cid;
    }
    public void setCid(Long cid) {
        this.cid = cid;
    }
    @Generated(hash = 1179402496)
    public Catatory(Long cid, String lishi_text) {
        this.cid = cid;
        this.lishi_text = lishi_text;
    }
    @Generated(hash = 1632142732)
    public Catatory() {
    }
}
