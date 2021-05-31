package com.gzeic.smartcity01.bean;

public class UsersBean {

    /**
     * msg : 操作成功
     * code : 200
     * user : {"userId":1111122,"userName":"test01","nickName":"弄玉","email":"","phonenumber":"18623323323","sex":"1","avatar":"/profile/upload/2021/05/16/5e9134ed-e1cd-4e2e-8dc2-290aada53bc4.png","idCard":"123456789000000001","balance":269,"score":1000}
     */

    private String msg;
    private int code;
    private UserDTO user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public static class UserDTO {
        /**
         * userId : 1111122
         * userName : test01
         * nickName : 弄玉
         * email :
         * phonenumber : 18623323323
         * sex : 1
         * avatar : /profile/upload/2021/05/16/5e9134ed-e1cd-4e2e-8dc2-290aada53bc4.png
         * idCard : 123456789000000001
         * balance : 269
         * score : 1000
         */

        private int userId;
        private String userName;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String idCard;
        private int balance;
        private int score;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
