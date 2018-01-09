package Bean;

import java.util.List;

/**
 * Created by ZhangTAO on 2018/1/2.
 */

public class SelectBean {

    /**
     * msg : 成功
     * ret : {"all":"2","movieNum":"0","pnum":1,"totalRecords":2,"trailerNum":"0","informationNum":"2","records":100,"otherNum":"0","list":[{"airTime":0,"angleIcon":"","director":"","videoType":"","description":"@TV娱乐前线：范冰冰最新杂志封面写真曝光化身性感小黑猫，秀酥胸美背。","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0215/7e11fdba8d544a7fbbe02a7071fd967b_188153_136.jpg","title":"范冰冰变身性感小黑猫","duration":"00:00:49","loadtype":"video","actors":"","score":0,"dataId":"7e11fdba8d544a7fbbe02a7071fd967b","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7e11fdba8d544a7fbbe02a7071fd967b","shareURL":"http://h5.svipmovie.com/zxgl/7e11fdba8d544a7fbbe02a7071fd967b.shtml?fromTo=shoujimovie","region":""},{"airTime":0,"angleIcon":"","director":"","videoType":"","description":"@TV娱乐前线：唐嫣受邀在苏州参加某品牌发布会，当天唐嫣身穿小黑裙，得体优雅甜翻众人。","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0430/7142356454ac4c3397cd0a51f4df9bf9_208769_136.jpg","title":"唐嫣小黑裙亮相超惊艳","duration":"00:01:17","loadtype":"video","actors":"","score":0,"dataId":"7142356454ac4c3397cd0a51f4df9bf9","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7142356454ac4c3397cd0a51f4df9bf9","shareURL":"http://h5.svipmovie.com/zxgl/7142356454ac4c3397cd0a51f4df9bf9.shtml?fromTo=shoujimovie","region":""}],"totalPnum":1}
     * code : 200
     */

    private String msg;
    private RetBean ret;
    private String code;

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
         * all : 2
         * movieNum : 0
         * pnum : 1
         * totalRecords : 2
         * trailerNum : 0
         * informationNum : 2
         * records : 100
         * otherNum : 0
         * list : [{"airTime":0,"angleIcon":"","director":"","videoType":"","description":"@TV娱乐前线：范冰冰最新杂志封面写真曝光化身性感小黑猫，秀酥胸美背。","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0215/7e11fdba8d544a7fbbe02a7071fd967b_188153_136.jpg","title":"范冰冰变身性感小黑猫","duration":"00:00:49","loadtype":"video","actors":"","score":0,"dataId":"7e11fdba8d544a7fbbe02a7071fd967b","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7e11fdba8d544a7fbbe02a7071fd967b","shareURL":"http://h5.svipmovie.com/zxgl/7e11fdba8d544a7fbbe02a7071fd967b.shtml?fromTo=shoujimovie","region":""},{"airTime":0,"angleIcon":"","director":"","videoType":"","description":"@TV娱乐前线：唐嫣受邀在苏州参加某品牌发布会，当天唐嫣身穿小黑裙，得体优雅甜翻众人。","pic":"http://y2.cnliveimg.com:8080/image/itv/2017/0430/7142356454ac4c3397cd0a51f4df9bf9_208769_136.jpg","title":"唐嫣小黑裙亮相超惊艳","duration":"00:01:17","loadtype":"video","actors":"","score":0,"dataId":"7142356454ac4c3397cd0a51f4df9bf9","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7142356454ac4c3397cd0a51f4df9bf9","shareURL":"http://h5.svipmovie.com/zxgl/7142356454ac4c3397cd0a51f4df9bf9.shtml?fromTo=shoujimovie","region":""}]
         * totalPnum : 1
         */

        private String all;
        private String movieNum;
        private int pnum;
        private int totalRecords;
        private String trailerNum;
        private String informationNum;
        private int records;
        private String otherNum;
        private int totalPnum;
        private List<ListBean> list;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getMovieNum() {
            return movieNum;
        }

        public void setMovieNum(String movieNum) {
            this.movieNum = movieNum;
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

        public String getTrailerNum() {
            return trailerNum;
        }

        public void setTrailerNum(String trailerNum) {
            this.trailerNum = trailerNum;
        }

        public String getInformationNum() {
            return informationNum;
        }

        public void setInformationNum(String informationNum) {
            this.informationNum = informationNum;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public String getOtherNum() {
            return otherNum;
        }

        public void setOtherNum(String otherNum) {
            this.otherNum = otherNum;
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
             * airTime : 0
             * angleIcon :
             * director :
             * videoType :
             * description : @TV娱乐前线：范冰冰最新杂志封面写真曝光化身性感小黑猫，秀酥胸美背。
             * pic : http://y2.cnliveimg.com:8080/image/itv/2017/0215/7e11fdba8d544a7fbbe02a7071fd967b_188153_136.jpg
             * title : 范冰冰变身性感小黑猫
             * duration : 00:00:49
             * loadtype : video
             * actors :
             * score : 0
             * dataId : 7e11fdba8d544a7fbbe02a7071fd967b
             * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=7e11fdba8d544a7fbbe02a7071fd967b
             * shareURL : http://h5.svipmovie.com/zxgl/7e11fdba8d544a7fbbe02a7071fd967b.shtml?fromTo=shoujimovie
             * region :
             */

            private int airTime;
            private String angleIcon;
            private String director;
            private String videoType;
            private String description;
            private String pic;
            private String title;
            private String duration;
            private String loadtype;
            private String actors;
            private int score;
            private String dataId;
            private String loadURL;
            private String shareURL;
            private String region;

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getDirector() {
                return director;
            }

            public void setDirector(String director) {
                this.director = director;
            }

            public String getVideoType() {
                return videoType;
            }

            public void setVideoType(String videoType) {
                this.videoType = videoType;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getActors() {
                return actors;
            }

            public void setActors(String actors) {
                this.actors = actors;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
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

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }
    }
}
