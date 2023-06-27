package com.common.server.utils;

public class WangImgCallback {
    private Integer errno;

    private CallData data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public CallData getData() {
        return data;
    }

    public void setData(CallData data) {
        this.data = data;
    }

    public static class CallData{
        private String url;
        private String alt;
        private String href;

        public CallData() {
        }

        public CallData(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
