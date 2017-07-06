package com.example.hui.dialog.mode;

import java.util.List;

/**
 * Created by 曾辉 on 2016/9/12.
 * Email : 240336124@qq.com
 * Description :
 */
public class CompanyListResult {

    /**
     * code : 000000
     * data : {"domain":null,"pageNo":1,"pageSize":10,"orderBy":null,"order":null,"autoCount":true,"draw":1,"result":[{"id":1,"shortCut":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-10/c7cfdd80-50e7-44ff-a88d-388f067db7a7_200x200.jpg","designerNum":2,"name":"湖南文象有限集团","companyNumber":59,"workNum":1,"banners":[{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908175824473.jpg"},{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908185527601.jpg"},{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-11/20160911183126685.jpg"}]},{"id":2,"shortCut":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-09/096581b0-ccd2-436b-b728-eddeb89953e2_200x200.jpg","designerNum":0,"name":"欧文大帝","companyNumber":7,"workNum":0,"banners":[]},{"id":3,"shortCut":null,"designerNum":0,"name":"水电局","companyNumber":6,"workNum":0,"banners":[]}],"totalCount":3,"orderBySetted":false,"totalPages":1,"hasNext":false,"nextPage":1,"hasPre":false,"prePage":1,"first":1}
     */

    private String code;
    /**
     * domain : null
     * pageNo : 1
     * pageSize : 10
     * orderBy : null
     * order : null
     * autoCount : true
     * draw : 1
     * result : [{"id":1,"shortCut":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-10/c7cfdd80-50e7-44ff-a88d-388f067db7a7_200x200.jpg","designerNum":2,"name":"湖南文象有限集团","companyNumber":59,"workNum":1,"banners":[{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908175824473.jpg"},{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908185527601.jpg"},{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-11/20160911183126685.jpg"}]},{"id":2,"shortCut":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-09/096581b0-ccd2-436b-b728-eddeb89953e2_200x200.jpg","designerNum":0,"name":"欧文大帝","companyNumber":7,"workNum":0,"banners":[]},{"id":3,"shortCut":null,"designerNum":0,"name":"水电局","companyNumber":6,"workNum":0,"banners":[]}]
     * totalCount : 3
     * orderBySetted : false
     * totalPages : 1
     * hasNext : false
     * nextPage : 1
     * hasPre : false
     * prePage : 1
     * first : 1
     */

    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private Object domain;
        private int pageNo;
        private int pageSize;
        private Object orderBy;
        private Object order;
        private boolean autoCount;
        private int draw;
        private int totalCount;
        private boolean orderBySetted;
        private int totalPages;
        private boolean hasNext;
        private int nextPage;
        private boolean hasPre;
        private int prePage;
        private int first;
        /**
         * id : 1
         * shortCut : http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-10/c7cfdd80-50e7-44ff-a88d-388f067db7a7_200x200.jpg
         * designerNum : 2
         * name : 湖南文象有限集团
         * companyNumber : 59
         * workNum : 1
         * banners : [{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908175824473.jpg"},{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908185527601.jpg"},{"banner":"http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-11/20160911183126685.jpg"}]
         */

        private List<CompanyBean> result;

        public Object getDomain() {
            return domain;
        }

        public void setDomain(Object domain) {
            this.domain = domain;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public boolean isAutoCount() {
            return autoCount;
        }

        public void setAutoCount(boolean autoCount) {
            this.autoCount = autoCount;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public boolean isOrderBySetted() {
            return orderBySetted;
        }

        public void setOrderBySetted(boolean orderBySetted) {
            this.orderBySetted = orderBySetted;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isHasPre() {
            return hasPre;
        }

        public void setHasPre(boolean hasPre) {
            this.hasPre = hasPre;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public List<CompanyBean> getResult() {
            return result;
        }

        public void setResult(List<CompanyBean> result) {
            this.result = result;
        }

        public static class CompanyBean {
            private int id;
            private String shortCut;
            private int designerNum;
            private String name;
            private int companyNumber;
            private int workNum;
            /**
             * banner : http://bbjz01.oss-cn-hangzhou.aliyuncs.com/robotImages/2016-09-08/20160908175824473.jpg
             */

            private List<BannersBean> banners;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getShortCut() {
                return shortCut;
            }

            public void setShortCut(String shortCut) {
                this.shortCut = shortCut;
            }

            public int getDesignerNum() {
                return designerNum;
            }

            public void setDesignerNum(int designerNum) {
                this.designerNum = designerNum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCompanyNumber() {
                return companyNumber;
            }

            public void setCompanyNumber(int companyNumber) {
                this.companyNumber = companyNumber;
            }

            public int getWorkNum() {
                return workNum;
            }

            public void setWorkNum(int workNum) {
                this.workNum = workNum;
            }

            public List<BannersBean> getBanners() {
                return banners;
            }

            public void setBanners(List<BannersBean> banners) {
                this.banners = banners;
            }

            public static class BannersBean {
                private String banner;

                public String getBanner() {
                    return banner;
                }

                public void setBanner(String banner) {
                    this.banner = banner;
                }
            }
        }
    }
}
