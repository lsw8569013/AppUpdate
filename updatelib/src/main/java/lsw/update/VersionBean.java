package lsw.update;

/**
 * author: Created by lsw on 2018/8/1 18:33
 * description:
 */
public class VersionBean {


    /**
     * code : 0
     * message :
     * data : {"buildKey":"60511a9efe9f7ea0222cbc5c69392988","buildType":"2","buildIsFirst":"0","buildIsLastest":"1","buildFileSize":"7781142","buildName":"闲豆回收端","buildVersion":"v2.0.0","buildVersionNo":"1","buildBuildVersion":"61","buildIdentifier":"com.example.brook.myapplicationtest0924","buildIcon":"9acf7893c7a32b08b62aa86c2685696c","buildDescription":"","buildUpdateDescription":"修改登录逻辑","buildScreenshots":"","buildShortcutUrl":"android_xiandou","buildCreated":"2018-07-25 13:43:32","buildUpdated":"2018-07-25 13:43:32","buildQRCodeURL":"https://www.pgyer.com/app/qrcodeHistory/eec32a69b322759d5681942b3afba4155a2ad89dcac91f36a5f27b691c3fe99b","buildFollowed":"0","otherApps":[{"buildKey":"f6b61237dbf2c9a698a8c9f08aaf48b8","buildName":"闲豆回收端","buildVersion":"v2.0.0","buildBuildVersion":"60","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-07-21","buildUpdateDescription":"优化项目架构"},{"buildKey":"66f0b0fe224c7c26b91afe3906460fd6","buildName":"闲豆回收端","buildVersion":"v1.7.8","buildBuildVersion":"59","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-07-16","buildUpdateDescription":""},{"buildKey":"5de90b44a2e5ef4aab2576f1ced3199d","buildName":"闲豆回收端","buildVersion":"v1.7.8","buildBuildVersion":"58","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-04-23","buildUpdateDescription":"1.结单前的上传两张附件改为结单前后各上传一张"},{"buildKey":"11d53dff72b6ed61eac4f07bac7e58b7","buildName":"闲豆回收端","buildVersion":"v1.7.7","buildBuildVersion":"57","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-04-19","buildUpdateDescription":"1.性能优化"}],"otherAppsCount":"60"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * buildKey : 60511a9efe9f7ea0222cbc5c69392988
         * buildType : 2
         * buildIsFirst : 0
         * buildIsLastest : 1
         * buildFileSize : 7781142
         * buildName : 闲豆回收端
         * buildVersion : v2.0.0
         * buildVersionNo : 1
         * buildBuildVersion : 61
         * buildIdentifier : com.example.brook.myapplicationtest0924
         * buildIcon : 9acf7893c7a32b08b62aa86c2685696c
         * buildDescription :
         * buildUpdateDescription : 修改登录逻辑
         * buildScreenshots :
         * buildShortcutUrl : android_xiandou
         * buildCreated : 2018-07-25 13:43:32
         * buildUpdated : 2018-07-25 13:43:32
         * buildQRCodeURL : https://www.pgyer.com/app/qrcodeHistory/eec32a69b322759d5681942b3afba4155a2ad89dcac91f36a5f27b691c3fe99b
         * buildFollowed : 0
         * otherApps : [{"buildKey":"f6b61237dbf2c9a698a8c9f08aaf48b8","buildName":"闲豆回收端","buildVersion":"v2.0.0","buildBuildVersion":"60","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-07-21","buildUpdateDescription":"优化项目架构"},{"buildKey":"66f0b0fe224c7c26b91afe3906460fd6","buildName":"闲豆回收端","buildVersion":"v1.7.8","buildBuildVersion":"59","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-07-16","buildUpdateDescription":""},{"buildKey":"5de90b44a2e5ef4aab2576f1ced3199d","buildName":"闲豆回收端","buildVersion":"v1.7.8","buildBuildVersion":"58","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-04-23","buildUpdateDescription":"1.结单前的上传两张附件改为结单前后各上传一张"},{"buildKey":"11d53dff72b6ed61eac4f07bac7e58b7","buildName":"闲豆回收端","buildVersion":"v1.7.7","buildBuildVersion":"57","buildIdentifier":"com.example.brook.myapplicationtest0924","buildCreated":"2018-04-19","buildUpdateDescription":"1.性能优化"}]
         * otherAppsCount : 60
         */

        private String buildKey;
        private String buildType;
        private String buildIsFirst;
        private String buildIsLastest;
        private String buildFileSize;
        private String buildName;
        private String buildVersion;
        private String buildVersionNo;
        private String buildBuildVersion;
        private String buildIdentifier;
        private String buildIcon;
        private String buildDescription;
        private String buildUpdateDescription;
        private String buildScreenshots;
        private String buildShortcutUrl;
        private String buildCreated;
        private String buildUpdated;
        private String buildQRCodeURL;
        private String buildFollowed;
        private String otherAppsCount;
//        private List<OtherAppsBean> otherApps;

        public String getBuildKey() {
            return buildKey;
        }

        public void setBuildKey(String buildKey) {
            this.buildKey = buildKey;
        }

        public String getBuildType() {
            return buildType;
        }

        public void setBuildType(String buildType) {
            this.buildType = buildType;
        }

        public String getBuildIsFirst() {
            return buildIsFirst;
        }

        public void setBuildIsFirst(String buildIsFirst) {
            this.buildIsFirst = buildIsFirst;
        }

        public String getBuildIsLastest() {
            return buildIsLastest;
        }

        public void setBuildIsLastest(String buildIsLastest) {
            this.buildIsLastest = buildIsLastest;
        }

        public String getBuildFileSize() {
            return buildFileSize;
        }

        public void setBuildFileSize(String buildFileSize) {
            this.buildFileSize = buildFileSize;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getBuildVersion() {
            return buildVersion;
        }

        public void setBuildVersion(String buildVersion) {
            this.buildVersion = buildVersion;
        }

        public String getBuildVersionNo() {
            return buildVersionNo;
        }

        public void setBuildVersionNo(String buildVersionNo) {
            this.buildVersionNo = buildVersionNo;
        }

        public String getBuildBuildVersion() {
            return buildBuildVersion;
        }

        public void setBuildBuildVersion(String buildBuildVersion) {
            this.buildBuildVersion = buildBuildVersion;
        }

        public String getBuildIdentifier() {
            return buildIdentifier;
        }

        public void setBuildIdentifier(String buildIdentifier) {
            this.buildIdentifier = buildIdentifier;
        }

        public String getBuildIcon() {
            return buildIcon;
        }

        public void setBuildIcon(String buildIcon) {
            this.buildIcon = buildIcon;
        }

        public String getBuildDescription() {
            return buildDescription;
        }

        public void setBuildDescription(String buildDescription) {
            this.buildDescription = buildDescription;
        }

        public String getBuildUpdateDescription() {
            return buildUpdateDescription;
        }

        public void setBuildUpdateDescription(String buildUpdateDescription) {
            this.buildUpdateDescription = buildUpdateDescription;
        }

        public String getBuildScreenshots() {
            return buildScreenshots;
        }

        public void setBuildScreenshots(String buildScreenshots) {
            this.buildScreenshots = buildScreenshots;
        }

        public String getBuildShortcutUrl() {
            return buildShortcutUrl;
        }

        public void setBuildShortcutUrl(String buildShortcutUrl) {
            this.buildShortcutUrl = buildShortcutUrl;
        }

        public String getBuildCreated() {
            return buildCreated;
        }

        public void setBuildCreated(String buildCreated) {
            this.buildCreated = buildCreated;
        }

        public String getBuildUpdated() {
            return buildUpdated;
        }

        public void setBuildUpdated(String buildUpdated) {
            this.buildUpdated = buildUpdated;
        }

        public String getBuildQRCodeURL() {
            return buildQRCodeURL;
        }

        public void setBuildQRCodeURL(String buildQRCodeURL) {
            this.buildQRCodeURL = buildQRCodeURL;
        }

        public String getBuildFollowed() {
            return buildFollowed;
        }

        public void setBuildFollowed(String buildFollowed) {
            this.buildFollowed = buildFollowed;
        }

        public String getOtherAppsCount() {
            return otherAppsCount;
        }

        public void setOtherAppsCount(String otherAppsCount) {
            this.otherAppsCount = otherAppsCount;
        }

//        public List<OtherAppsBean> getOtherApps() {
//            return otherApps;
//        }
//
//        public void setOtherApps(List<OtherAppsBean> otherApps) {
//            this.otherApps = otherApps;
//        }

        public static class OtherAppsBean {
            /**
             * buildKey : f6b61237dbf2c9a698a8c9f08aaf48b8
             * buildName : 闲豆回收端
             * buildVersion : v2.0.0
             * buildBuildVersion : 60
             * buildIdentifier : com.example.brook.myapplicationtest0924
             * buildCreated : 2018-07-21
             * buildUpdateDescription : 优化项目架构
             */

            private String buildKey;
            private String buildName;
            private String buildVersion;
            private String buildBuildVersion;
            private String buildIdentifier;
            private String buildCreated;
            private String buildUpdateDescription;

            public String getBuildKey() {
                return buildKey;
            }

            public void setBuildKey(String buildKey) {
                this.buildKey = buildKey;
            }

            public String getBuildName() {
                return buildName;
            }

            public void setBuildName(String buildName) {
                this.buildName = buildName;
            }

            public String getBuildVersion() {
                return buildVersion;
            }

            public void setBuildVersion(String buildVersion) {
                this.buildVersion = buildVersion;
            }

            public String getBuildBuildVersion() {
                return buildBuildVersion;
            }

            public void setBuildBuildVersion(String buildBuildVersion) {
                this.buildBuildVersion = buildBuildVersion;
            }

            public String getBuildIdentifier() {
                return buildIdentifier;
            }

            public void setBuildIdentifier(String buildIdentifier) {
                this.buildIdentifier = buildIdentifier;
            }

            public String getBuildCreated() {
                return buildCreated;
            }

            public void setBuildCreated(String buildCreated) {
                this.buildCreated = buildCreated;
            }

            public String getBuildUpdateDescription() {
                return buildUpdateDescription;
            }

            public void setBuildUpdateDescription(String buildUpdateDescription) {
                this.buildUpdateDescription = buildUpdateDescription;
            }
        }
    }
}
