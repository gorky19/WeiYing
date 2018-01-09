package Bean;

import java.util.List;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public class CommentaryBean {

    /**
     * msg : 成功
     * ret : {"pnum":1,"totalRecords":7,"records":20,"list":[{"msg":"追求自己的追求！","phoneNumber":"155****6361","dataId":"ff8080815dcb455e015dd5cab4012bcf","userPic":"","time":"2017-08-12 17:33:37","likeNum":0},{"msg":"爱情与事业不能并存的意思？爱情令人丧失斗志？还是填满这人的所有思绪？","phoneNumber":"155****6361","dataId":"ff8080815dcb4639015dd5c178a22bff","userPic":"","time":"2017-08-12 17:23:32","likeNum":0},{"msg":"这里居然还有这电影，爱奇艺可是要付费的","phoneNumber":"·落·","dataId":"ff8080815c9b961b015c9f074056178e","userPic":"http://q.qlogo.cn/qqapp/1101034181/03AC6CE0AA860B34B6AB76B181435520/40","time":"2017-06-13 09:17:51","likeNum":0},{"msg":"故事情节一般，没啥新意。","phoneNumber":"青春之歌","dataId":"ff8080815c687107015c8aad2ccb3762","userPic":"","time":"2017-05-31 17:27:04","likeNum":2},{"msg":"作为一个电影资深评论家，我也是没啥可说的了\u2026\u2026","phoneNumber":"青春之歌","dataId":"ff8080815c687107015c8aad2cc4375f","userPic":"","time":"2017-05-15 06:41:04","likeNum":2},{"msg":"这也叫电影？简直浪费时间！","phoneNumber":"陌路丢了谁","dataId":"ff8080815c687107015c8aad2cc93761","userPic":"","time":"2017-05-06 16:45:04","likeNum":1},{"msg":"这电影看完之后让我无话可说，不过好的一点是：让我看完了。。。。","phoneNumber":"假如回忆不再","dataId":"ff8080815c687107015c8aad2cc73760","userPic":"","time":"2017-05-01 12:47:04","likeNum":0}],"totalPnum":1}
     * code : 200
     */

    private String msg;
    private RetBean ret;
    private String code;

    @Override
    public String toString() {
        return "CommentaryBean{" +
                "msg='" + msg + '\'' +
                ", ret=" + ret +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class RetBean {
        /**
         * pnum : 1
         * totalRecords : 7
         * records : 20
         * list : [{"msg":"追求自己的追求！","phoneNumber":"155****6361","dataId":"ff8080815dcb455e015dd5cab4012bcf","userPic":"","time":"2017-08-12 17:33:37","likeNum":0},{"msg":"爱情与事业不能并存的意思？爱情令人丧失斗志？还是填满这人的所有思绪？","phoneNumber":"155****6361","dataId":"ff8080815dcb4639015dd5c178a22bff","userPic":"","time":"2017-08-12 17:23:32","likeNum":0},{"msg":"这里居然还有这电影，爱奇艺可是要付费的","phoneNumber":"·落·","dataId":"ff8080815c9b961b015c9f074056178e","userPic":"http://q.qlogo.cn/qqapp/1101034181/03AC6CE0AA860B34B6AB76B181435520/40","time":"2017-06-13 09:17:51","likeNum":0},{"msg":"故事情节一般，没啥新意。","phoneNumber":"青春之歌","dataId":"ff8080815c687107015c8aad2ccb3762","userPic":"","time":"2017-05-31 17:27:04","likeNum":2},{"msg":"作为一个电影资深评论家，我也是没啥可说的了\u2026\u2026","phoneNumber":"青春之歌","dataId":"ff8080815c687107015c8aad2cc4375f","userPic":"","time":"2017-05-15 06:41:04","likeNum":2},{"msg":"这也叫电影？简直浪费时间！","phoneNumber":"陌路丢了谁","dataId":"ff8080815c687107015c8aad2cc93761","userPic":"","time":"2017-05-06 16:45:04","likeNum":1},{"msg":"这电影看完之后让我无话可说，不过好的一点是：让我看完了。。。。","phoneNumber":"假如回忆不再","dataId":"ff8080815c687107015c8aad2cc73760","userPic":"","time":"2017-05-01 12:47:04","likeNum":0}]
         * totalPnum : 1
         */

        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        private List<ListBean> list;

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotalPnum() {
            return totalPnum;
        }

        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * msg : 追求自己的追求！
             * phoneNumber : 155****6361
             * dataId : ff8080815dcb455e015dd5cab4012bcf
             * userPic :
             * time : 2017-08-12 17:33:37
             * likeNum : 0
             */

            private String msg;
            private String phoneNumber;
            private String dataId;
            private String userPic;
            private String time;
            private int likeNum;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getUserPic() {
                return userPic;
            }

            public void setUserPic(String userPic) {
                this.userPic = userPic;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(int likeNum) {
                this.likeNum = likeNum;
            }
        }
    }
}
