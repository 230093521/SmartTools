package com.gzeic.smartcity01.Tools;

public class ApiUrl {
    public static String SERVER_API_HTTP = "http://192.168.1.24:10001/";
    public static String SERVER_API_TOKEN = "1111111111111111111111111";
    public static String SERVER_RES = "192.168.1.24:10002";

    public static void setServerApiToken(String serverApiToken) {
        SERVER_API_TOKEN = serverApiToken;
    }
    public static void setServerApiHttp(String serverApiHttp) {
        SERVER_API_HTTP = serverApiHttp;
    }
    public static void setServerRes(String serverRes) {
        SERVER_RES = serverRes;
    }

    //通用接口
    public static String TY_login = "prod-api/api/login";
    public static String TY_register = "prod-api/api/register";
    public static String TY_getUserInfo = "prod-api/api/common/user/getInfo";
    public static String TY_upUserInfo = "prod-api/api/common/user";
    public static String TY_upPwd = "prod-api/api/common/user";
    public static String TY_QB_JE_LIST = "prod-api/api/common/balance/list";
    public static String TY_QB_CZ = "prod-api/api/common/balance/recharge";
    public static String TY_FK = "prod-api/api/common/feedback";
    public static String TY_FK_LIST = "prod-api/api/common/feedback/list";
    public static String TY_FK_XQ = "prod-api/api/common/feedback/";
    public static String TY_xw_fl = "prod-api/press/category/list";
    public static String TY_xw_pl = "prod-api/press/comments/list";
    public static String TY_xw_pl_xq = "prod-api/press/comments/";
    public static String TY_xw_dz = "prod-api/press/press/like/";
    public static String TY_xw_list = "prod-api/press/press/list";
    public static String TY_xw_xq = "prod-api/press/press/";
    public static String TY_xw_fb_pl = "prod-api/press/pressComment";
    public static String TY_xw_pl_dz = "prod-api/press/pressComment/like/";
    public static String TY_LBT = "prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2";
    public static String TY_SC_WJ = "common/upload";
    public static String TY_CS_QY = "prod-api/api/common/gps/area";
    public static String TY_SF_CS = "prod-api/api/common/gps/city";
    public static String TY_WD_WZ = "prod-api/api/common/gps/location";
    public static String TY_QG_SF = "prod-api/api/common/gps/province";
    public static String TY_QB_FW = "prod-api/api/service/list";
    public static String TY_QB_DD = "prod-api/api/allorder/list";
    public static String TY_TQ_XX = "prod-api/api/common/weather/today";
    public static String TY_QT_TQ = "prod-api/api/common/weather";
    public static String TY_ZD_LX = "system/dict/data/type/";
    public static String TC_JC = "prod-api/api/park/correct";
    //停哪儿
    public static String TC_List = "prod-api/api/park/lot/list";
    public static String TC_JL_List = "prod-api/api/park/lot/record/list";
    public static String TC_XQ = "prod-api/api/park/lot/";
    public static String TC_CZ_JL = "prod-api/api/park/recharge/list";
    public static String TC_CZ = "prod-api/api/park/recharge/pay";
    public static String TC_JF_HG = "prod-api/api/park/product/list";
    public static String TC_GG_LB = "prod-api/api/park/rotation/list";
    public static String TC_YJ_FK = "prod-api/api/park/feedback";
    public static String TC_FK_List = "prod-api/api/park/feedback/list";
    public static String TC_FK_XQ = "prod-api/api/park/feedback/";
    public static String TC_XW_FL = "prod-api/api/park/press/category/list";
    public static String TC_XW_PL_List = "prod-api/api/park/press/comments/list";
    public static String TC_XW_PL_XQ = "prod-api/api/park/press/comments/";
    public static String TC_XW_DZ = "prod-api/api/park/press/press/like/";
    public static String TC_XW_List = "prod-api/api/park/press/press/list";
    public static String TC_XW_XQ = "prod-api/api/park/press/press/";
    public static String TC_XW_FB_PL = "prod-api/api/park/press/pressComment";
    public static String TC_XW_PL_DZ = "prod-api/api/park/press/pressComment/like/";
    public static String TC_JF_DH = "prod-api/api/park/score/consume/";
    public static String TC_JF_DJ = "prod-api/api/park/score/level/list";
    public static String TC_JF_JL = "prod-api/api/park/score/list";
    public static String TC_JF_XX = "prod-api/api/park/score/notice/list";
    public static String TC_JF_PH = "prod-api/api/park/score/top/list";
    public static String TC_TJ_CL = "prod-api/api/park/car";
    public static String TC_XG_CL = "prod-api/api/park/car";
    public static String TC_CL_LC = "prod-api/api/park/car/consumption";
    public static String TC_TJ_LC = "prod-api/api/park/car/consumption";
    public static String TC_XG_LC = "prod-api/api/park/car/consumption";
    public static String TC_SC_LC = "prod-api/api/park/car/consumption/";
    public static String TC_SQ_NC = "prod-api/api/park/car/move";
    public static String TC_WD_CL = "prod-api/api/park/car/my";
    public static String TC_SC_WD_CL = "prod-api/api/park/car/";
    //城市地铁
    public static String DT_GUG_LB = "prod-api/api/metro/rotation/list";
    public static String DT_YJ_FK = "prod-api/api/metro/feedback";
    public static String DT_YJ_FK_LB = "prod-api/api/metro/feedback/list";
    public static String DT_YJ_FK_XQ = "prod-api/api/metro/feedback/";
    public static String DT_XW_FL = "prod-api/api/metro/press/category/list";
    public static String DT_XW_PL_LB = "prod-api/api/metro/press/comments/list";
    public static String DT_XW_PL_XQ = "prod-api/api/metro/press/comments/";
    public static String DT_XW_DZ = "prod-api/api/metro/press/press/like/";
    public static String DT_XW_LB = "prod-api/api/metro/press/press/list";
    public static String DT_XW_XQ = "prod-api/api/metro/press/press/";
    public static String DT_XW_FB_PL = "prod-api/api/metro/press/pressComment";
    public static String DT_XW_PL_DZ = "prod-api/api/metro/press/pressComment/like/";
    public static String DT_CCM = "prod-api/api/metro/card";
    public static String DT_LQ_CCM = "prod-api/api/metro/card";
    public static String DT_ZX_CCM = "prod-api/api/metro/card/";
    public static String DT_GOG_LB = "prod-api/api/metro/notice/list";
    public static String DT_GOG_XQ = "prod-api/api/metro/notice/";
    public static String DT_DT_SM = "prod-api/api/metro/statement";
    public static String DT_SW_ZL = "prod-api/api/metro/found/list";
    public static String DT_SW_ZL_XQ = "prod-api/api/metro/found/";
    public static String DT_DT_ZLT = "prod-api/api/metro/city";
    public static String DT_ZD_XX = "prod-api/api/metro/line";
    public static String DT_SY_XL = "prod-api/api/metro/line/list";
    public static String DT_SY_ZD = "prod-api/api/metro/list";
    public static String DT_ZD_XX_LB = "prod-api/api/metro/step/list";
    public static String DT_Z_XQ = "prod-api/api/metro/line/";
    public static String DT_CC_JL = "prod-api/api/metro/record/list";
    //找工作
    public static String GZ_GZ_LB = "prod-api/api/job/company/list";
    public static String GZ_GS_XQ = "prod-api/api/job/company/";
    public static String GZ_TD_JL = "prod-api/api/job/deliver";
    public static String GZ_TD_LS = "prod-api/api/job/deliver/list";
    public static String GZ_ZP_LB = "prod-api/api/job/post/list";
    public static String GZ_ZP_XQ = "prod-api/api/job/post/";
    public static String GZ_XZ_YH_JL = "prod-api/api/job/resume";
    public static String GZ_CX_YH_JL = "prod-api/api/job/resume/";
    public static String GZ_CX_ZW_LB = "prod-api/api/job/profession/list";
    //找房子
    public static String FY_LB = "prod-api/api/house/housing/list";
    public static String FY_XQ = "prod-api/api/house/housing/list";
    //智慧交管
    public static String JG_GG = "prod-api/api/traffic/rotation/list";
    public static String JG_XZ_FK = "prod-api/api/traffic/feedback";
    public static String JG_CX_FK = "prod-api/api/traffic/feedback/list";
    public static String JG_HQ_FK_XQ = "prod-api/api/traffic/feedback/";
    public static String JG_BD_CL = "prod-api/api/traffic/car";
    public static String JG_XG_BD_CL = "prod-api/api/traffic/car";
    public static String JG_BD_CL_LB = "prod-api/api/traffic/car/list";
    public static String JG_JC_BD_CL = "prod-api/api/traffic/car/";
    public static String JG_WZ_CF = "prod-api/api/traffic/illegal/accept/";
    public static String JG_WZ_CX_LB = "prod-api/api/traffic/illegal/apply/list";
    public static String JG_WZ_CX = "prod-api/api/traffic/illegal/cancel";
    public static String JG_WZ_XX_LB = "prod-api/api/traffic/illegal/list";
    public static String JG_CFS_LB = "prod-api/api/traffic/illegal/notice/list";
    public static String JG_CFS_XQ = "prod-api/api/traffic/illegal/notice/";
    public static String JG_ZF_FK = "prod-api/api/traffic/illegal/pay/";
    public static String JG_WZ_XQ = "prod-api/api/traffic/illegal/";
    public static String JG_BD_JSZ = "prod-api/api/traffic/license";
    public static String JG_HQ_JSZ = "prod-api/api/traffic/license/user";
    public static String JG_JSZ_XQ = "prod-api/api/traffic/license/";
    public static String JG_JC_JSC = "prod-api/api/traffic/license/";
    //活动
    public static String HD_GG = "prod-api/api/activity/rotation/list";
    public static String HD_FL_LB = "prod-api/api/activity/category/list";
    public static String HD_LB = "prod-api/api/activity/activity/list";
    public static String HD_XQ = "prod-api/api/activity/activity/";
    public static String HD_BM = "prod-api/api/activity/signup";
    public static String HD_BM_ZT = "prod-api/api/activity/signup/check";
    public static String HD_TJ_PL = "prod-api/api/activity/comment";
    public static String HD_PL_LB = "prod-api/api/activity/comment/list";
    public static String HD_HD_PLS = "prod-api/api/activity/comment/number";
    //生活缴费
    public static String JF_GG = "prod-api/api/living/rotation/list";
    public static String JF_XZ_FK = "prod-api/api/living/feedback";
    public static String JF_FK_LB = "prod-api/api/living/feedback/list";
    public static String JF_FK_XQ = "prod-api/api/living/feedback/";
    public static String JF_ZX_FL = "prod-api/api/living/press/category/list";
    public static String JF_ZX_PL_LB = "prod-api/api/living/press/comments/list";
    public static String JF_ZX_PL_XQ = "prod-api/api/living/press/comments/";
    public static String JF_ZX_DZ = "prod-api/api/living/press/press/like/";
    public static String JF_ZX_LB = "prod-api/api/living/press/press/list";
    public static String JF_ZX_XQ = "prod-api/api/living/press/press/";
    public static String JF_ZX_FB_PL = "prod-api/api/living/press/pressComment";
    public static String JF_ZX_PL_DZ = "prod-api/api/living/press/pressComment/like/";
    public static String JF_TQ_YB = "prod-api/api/living/weather";
    public static String JF_FL = "prod-api/api/living/category/list";
    public static String JF_JF = "prod-api/api/living/recharge";
    public static String JF_JL = "prod-api/api/living/recharge/record/list";
    public static String JF_ZD = "prod-api/api/living/bill";
    public static String JF_JF_BH = "prod-api/api/living/account/list";
    public static String JF_HF_YE = "prod-api/api/living/phone";
    public static String JF_HF_CZ = "prod-api/api/living/phone/recharge";
    public static String JF_HF_CZ_JL = "prod-api/api/living/phone/record/list";
    public static String JF_HF_CZ_YH = "prod-api/api/living/phone/rule/list";
    //看电影
    public static String DY_GG = "prod-api/api/movie/rotation/list";
    public static String DY_YP_PL = "prod-api/api/movie/film/comment";
    public static String DY_YP_PL_DZ = "prod-api/api/movie/film/comment/like/";
    public static String DY_YIP_XX_LB = "prod-api/api/movie/film/comment/list";
    public static String DY_KG = "prod-api/api/movie/film/favorite/";
    public static String DY_XK = "prod-api/api/movie/film/like/";
    public static String DY_YPI_XX_LB = "prod-api/api/movie/film/list";
    public static String DY_YG_XX_LB = "prod-api/api/movie/film/preview/list";
    public static String DY_YUG_XX_XQ = "prod-api/api/movie/film/preview/";
    public static String DY_YP_XQ = "prod-api/api/movie/film/";
    public static String DY_YY_LB = "prod-api/api/movie/theatre/list";
    public static String DY_CZ_ZW = "prod-api/api/movie/theatre/list4times";
    public static String DY_YT_XX_LB = "prod-api/api/movie/theatre/room/";
    public static String DY_YT_XQ = "prod-api/api/movie/theatre/room/";
    public static String DY_ZW_LB = "prod-api/api/movie/theatre/seat/list";
    public static String DY_ZW_XQ = "prod-api/api/movie/theatre/seat/";
    public static String DY_YP_CC = "prod-api/api/movie/theatre/times/list";
    public static String DY_YY_XQ = "prod-api/api/movie/theatre/";
    public static String DY_YJ_FK = "prod-api/api/movie/feedback";
    public static String DY_YJ_FK_LB = "prod-api/api/movie/feedback/list";
    public static String DY_YJ_FK_XQ = "prod-api/api/movie/feedback/";
    public static String DY_XW_FL = "prod-api/api/movie/press/category/list";
    public static String DY_XW_PL_LB = "prod-api/api/movie/press/comments/list";
    public static String DY_XW_PL_XQ = "prod-api/api/movie/press/comments/";
    public static String DY_XW_DZ = "prod-api/api/movie/press/press/like/";
    public static String DY_XW_LB = "prod-api/api/movie/press/press/list";
    public static String DY_XW_XQ = "prod-api/api/movie/press/press/list";
    public static String DY_XW_PL = "prod-api/api/movie/press/pressComment";
    public static String DY_XW_PL_DZ = "prod-api/api/movie/press/pressComment/like/";
    public static String DY_GP_TJ_DD = "prod-api/api/movie/ticket";
    public static String DY_YPO_XX_LB = "prod-api/api/movie/ticket/list";
    public static String DY_DD_LB = "prod-api/api/movie/ticket/order/list";
    public static String DY_DD_ZF = "prod-api/api/movie/ticket/order/pay/";
    public static String DY_DD_XQ = "prod-api/api/movie/ticket/order/";
    public static String DY_SC_DD = "prod-api/api/movie/ticket/order/";
    //门诊预约
    public static String MZ_LBT = "prod-api/api/hospital/banner/list";
    public static String MZ_YY_LB = "prod-api/api/hospital/hospital/list";
    public static String MZ_YY_XQ = "prod-api/api/hospital/hospital/";
    public static String MZ_KS_FL = "prod-api/api/hospital/category/list";
    public static String MZ_XZ_JZK = "prod-api/api/hospital/patient";
    public static String MZ_XG_JZK = "prod-api/api/hospital/patient";
    public static String MZ_CX_JZK = "prod-api/api/hospital/patient/list";
    public static String MZ_SC_YYD = "prod-api/api/hospital";
    public static String MZ_YUY_XQ = "prod-api/api/hospital/reservation/";
    public static String MZ_YUY_LB = "prod-api/api/hospital/reservation/list";
    //智慧巴士
    public static String BS_ZD_XX = "prod-api/api/bus/stop/list";
    public static String BS_XL_LB = "prod-api/api/bus/line/list";
    public static String BS_XL_XQ = "prod-api/api/bus/line/";
    public static String BS_XZ_BS_DD = "prod-api/api/bus/order";
    public static String BS_CX_BS_DD = "prod-api/api/bus/order/list";
    public static String BS_ZF_BS_DD = "prod-api/api/bus/pay";



}
