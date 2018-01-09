package Bean;

import java.util.List;

/**
 * Created by ZhangTAO on 2017/12/31.
 */

public class RootDataBean {

    /**
     * msg : 成功
     * code : 200
     */

    private String msg;
    private RetBean ret;
    private String code;

    @Override
    public String toString() {
        return "RootDataBean{" +
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
         * adv : {"imgURL":"","dataId":"","htmlURL":"","shareURL":"","title":""}
         * pnum : 1
         * totalRecords : 30
         * bannerList : []
         * records : 30
         * totalPnum : 5
         */

        private AdvBean adv;
        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        private List<?> bannerList;
        private List<ListBean> list;

        public AdvBean getAdv() {
            return adv;
        }

        public void setAdv(AdvBean adv) {
            this.adv = adv;
        }

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

        public List<?> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<?> bannerList) {
            this.bannerList = bannerList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AdvBean {
            /**
             * imgURL :
             * dataId :
             * htmlURL :
             * shareURL :
             * title :
             */

            private String imgURL;
            private String dataId;
            private String htmlURL;
            private String shareURL;
            private String title;

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getHtmlURL() {
                return htmlURL;
            }

            public void setHtmlURL(String htmlURL) {
                this.htmlURL = htmlURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ListBean {
            /**
             * airTime : 2016
             * duration : 00:13:02
             * loadtype : video
             * score : 0
             * angleIcon : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296586400001011.png
             * dataId : 55d560a957ab41f3a3d61a6cc504d591
             * description : @TV作品库：电影《海沧英雄》通过60后、70后、80后和90后这四代人追逐梦想的故事，展现敢于拼搏、百折不挠的新海沧人的精神。影片以海沧2013厦门国际自行车比赛为故事背景，展开一系列的剧情…
             * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=55d560a957ab41f3a3d61a6cc504d591
             * shareURL : http://m.svipmovie.com/#/moviedetails/55d560a957ab41f3a3d61a6cc504d591
             * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2016/09/21/1474430815901032592.jpg
             * title : 海沧英雄
             * roomId :
             */

            private int airTime;
            private String duration;
            private String loadtype;
            private double score;
            private String angleIcon;
            private String dataId;
            private String description;
            private String loadURL;
            private String shareURL;
            private String pic;
            private String title;
            private String roomId;

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadtype() {
                return loadtype;
            }

            public void setLoadtype(String loadtype) {
                this.loadtype = loadtype;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLoadURL() {
                return loadURL;
            }

            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}
