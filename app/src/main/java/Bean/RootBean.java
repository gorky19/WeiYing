package Bean;

import java.util.List;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class RootBean {

    /**
     * msg : 成功
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
        private List<HotSearchListBean> hotSearchList;
        private List<ListBean> list;

        public List<HotSearchListBean> getHotSearchList() {
            return hotSearchList;
        }

        public void setHotSearchList(List<HotSearchListBean> hotSearchList) {
            this.hotSearchList = hotSearchList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class HotSearchListBean {
            /**
             * refCounter : 1
             * cnname : xingjichuanyue
             * siteId : 1
             * simplename : xjcy
             * id : ff8080815a5f91db015a68a763b750d5
             * tagName : 星际穿越
             * createdtime : 2017-02-23 09:48:04
             * enname :
             */

            private int refCounter;
            private String cnname;
            private String siteId;
            private String simplename;
            private String id;
            private String tagName;
            private String createdtime;
            private String enname;

            public int getRefCounter() {
                return refCounter;
            }

            public void setRefCounter(int refCounter) {
                this.refCounter = refCounter;
            }

            public String getCnname() {
                return cnname;
            }

            public void setCnname(String cnname) {
                this.cnname = cnname;
            }

            public String getSiteId() {
                return siteId;
            }

            public void setSiteId(String siteId) {
                this.siteId = siteId;
            }

            public String getSimplename() {
                return simplename;
            }

            public void setSimplename(String simplename) {
                this.simplename = simplename;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getCreatedtime() {
                return createdtime;
            }

            public void setCreatedtime(String createdtime) {
                this.createdtime = createdtime;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }
        }

        public static class ListBean {
            /**
             * showStyle :
             * loadType : videoList
             * changeOpenFlag : false
             * line : 1
             * showType : banner
             * moreURL :
             * title : Banner
             * bigPicShowFlag :
             */

            private String showStyle;
            private String loadType;
            private String changeOpenFlag;
            private int line;
            private String showType;
            private String moreURL;
            private String title;
            private String bigPicShowFlag;
            private List<ChildListBean> childList;

            public String getShowStyle() {
                return showStyle;
            }

            public void setShowStyle(String showStyle) {
                this.showStyle = showStyle;
            }

            public String getLoadType() {
                return loadType;
            }

            public void setLoadType(String loadType) {
                this.loadType = loadType;
            }

            public String getChangeOpenFlag() {
                return changeOpenFlag;
            }

            public void setChangeOpenFlag(String changeOpenFlag) {
                this.changeOpenFlag = changeOpenFlag;
            }

            public int getLine() {
                return line;
            }

            public void setLine(int line) {
                this.line = line;
            }

            public String getShowType() {
                return showType;
            }

            public void setShowType(String showType) {
                this.showType = showType;
            }

            public String getMoreURL() {
                return moreURL;
            }

            public void setMoreURL(String moreURL) {
                this.moreURL = moreURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBigPicShowFlag() {
                return bigPicShowFlag;
            }

            public void setBigPicShowFlag(String bigPicShowFlag) {
                this.bigPicShowFlag = bigPicShowFlag;
            }

            public List<ChildListBean> getChildList() {
                return childList;
            }

            public void setChildList(List<ChildListBean> childList) {
                this.childList = childList;
            }

            public static class ChildListBean {
                /**
                 * airTime : 2012
                 * duration : 01:46:16
                 * loadType : video
                 * score : 0
                 * angleIcon : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png
                 * dataId : 840ddae38ed346a197a76b46b448067e
                 * description : 影片来自于真实的故事，小镇上的记者亚当（约翰·卡拉辛斯基 John Krasinski 饰）和绿色和平组织的一位志愿者瑞秋（德鲁·巴里摩尔 Drew Barrymore 饰）加入到了拯救北极圈灰鲸的行动中去。 两人必须联合阿拉斯加的因纽特人，对抗石油公司以及苏联和美国的军队的反对——在当时的冷战背景下，他们终于得到了世界的关注，冷战的阴影也因拯救这种濒临灭绝的动物行动而得到缓解。
                 * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=840ddae38ed346a197a76b46b448067e
                 * shareURL : http://m.svipmovie.com/#/moviedetails/840ddae38ed346a197a76b46b448067e
                 * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/11/13/1510554001280066272.jpg
                 * title : 群星拯救临灭绝灰鲸
                 * roomId :
                 */

                private int airTime;
                private String duration;
                private String loadType;
                private int score;
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

                public String getLoadType() {
                    return loadType;
                }

                public void setLoadType(String loadType) {
                    this.loadType = loadType;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
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
}
