package com.example.asus.customer.entity;

import java.util.List;

//
// 2019/6/13.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class ZhangDanJiLuBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"summoney":"59000.00","list":[{"Received":17700,"UpdateTime":"2018-06-06 11:38:55","Period":1},{"Received":20650,"UpdateTime":"2018-06-26 10:44:56","Period":2},{"Received":17700,"UpdateTime":"2018-07-05 10:29:30","Period":3},{"Received":2950,"UpdateTime":"2018-08-01 17:40:11","Period":4}]}
     */

    private String StatusMsg;
    private int StatusCode;
    private BodyBean Body;

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * summoney : 59000.00
         * list : [{"Received":17700,"UpdateTime":"2018-06-06 11:38:55","Period":1},{"Received":20650,"UpdateTime":"2018-06-26 10:44:56","Period":2},{"Received":17700,"UpdateTime":"2018-07-05 10:29:30","Period":3},{"Received":2950,"UpdateTime":"2018-08-01 17:40:11","Period":4}]
         */

        private String summoney;
        private List<ListBean> list;

        public String getSummoney() {
            return summoney;
        }

        public void setSummoney(String summoney) {
            this.summoney = summoney;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * Received : 17700
             * UpdateTime : 2018-06-06 11:38:55
             * Period : 1
             */

            private int Received;
            private String UpdateTime;
            private int Period;

            public int getReceived() {
                return Received;
            }

            public void setReceived(int Received) {
                this.Received = Received;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public int getPeriod() {
                return Period;
            }

            public void setPeriod(int Period) {
                this.Period = Period;
            }
        }
    }
}
