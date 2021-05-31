package com.gzeic.smartcity01.bean;

import java.util.List;

public class TQqitianBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"today":{"hours":[{"hour":"15:00","weather":"晴","temperature":21},{"hour":"16:00","weather":"晴","temperature":21},{"hour":"17:00","weather":"晴","temperature":21},{"hour":"18:00","weather":"晴","temperature":20},{"hour":"19:00","weather":"晴","temperature":21},{"hour":"20:00","weather":"晴","temperature":20}],"airQuantity":{"no2":32,"pm25":22,"o3":26,"so2":6,"pm10":50,"co":0.3},"comfortLevel":{"uv":1,"dressingIndex":"T恤","humidity":28,"coldIndex":"不易","apparentTemperature":18,"uvIndex":"弱","washIndex":"适宜","sportIndex":"较适宜"},"tempInfo":{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"5月26日今天"},"updateTime":"15:00","wind":{"windStrength":"2级","windDirection":"西风"}},"weatherList":[{"maxTemperature":"12","uv":"弱","minTemperature":"23","temperature":"10","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"15","label":"昨天","day":"5月25日昨天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"5月26日今天"},{"maxTemperature":"15","uv":"弱","minTemperature":"25","temperature":"20","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"16","label":"未来第一天","day":"5月27日明天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"20","weather":"多云","humidity":50,"air":"良好","apparentTemperature":"17","label":"未来第二天","day":"5月28日星期五"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"15","label":"未来第三天","day":"5月29日星期六"},{"maxTemperature":"16","uv":"弱","minTemperature":"25","temperature":"21","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"14","label":"未来第四天","day":"5月30日星期日"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"20","label":"未来第五天","day":"5月31日星期一"}]}
     */

    private String msg;
    private int code;
    private DataDTO data;

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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * today : {"hours":[{"hour":"15:00","weather":"晴","temperature":21},{"hour":"16:00","weather":"晴","temperature":21},{"hour":"17:00","weather":"晴","temperature":21},{"hour":"18:00","weather":"晴","temperature":20},{"hour":"19:00","weather":"晴","temperature":21},{"hour":"20:00","weather":"晴","temperature":20}],"airQuantity":{"no2":32,"pm25":22,"o3":26,"so2":6,"pm10":50,"co":0.3},"comfortLevel":{"uv":1,"dressingIndex":"T恤","humidity":28,"coldIndex":"不易","apparentTemperature":18,"uvIndex":"弱","washIndex":"适宜","sportIndex":"较适宜"},"tempInfo":{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"5月26日今天"},"updateTime":"15:00","wind":{"windStrength":"2级","windDirection":"西风"}}
         * weatherList : [{"maxTemperature":"12","uv":"弱","minTemperature":"23","temperature":"10","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"15","label":"昨天","day":"5月25日昨天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"5月26日今天"},{"maxTemperature":"15","uv":"弱","minTemperature":"25","temperature":"20","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"16","label":"未来第一天","day":"5月27日明天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"20","weather":"多云","humidity":50,"air":"良好","apparentTemperature":"17","label":"未来第二天","day":"5月28日星期五"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"15","label":"未来第三天","day":"5月29日星期六"},{"maxTemperature":"16","uv":"弱","minTemperature":"25","temperature":"21","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"14","label":"未来第四天","day":"5月30日星期日"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"20","label":"未来第五天","day":"5月31日星期一"}]
         */

        private TodayDTO today;
        private List<WeatherListDTO> weatherList;

        public TodayDTO getToday() {
            return today;
        }

        public void setToday(TodayDTO today) {
            this.today = today;
        }

        public List<WeatherListDTO> getWeatherList() {
            return weatherList;
        }

        public void setWeatherList(List<WeatherListDTO> weatherList) {
            this.weatherList = weatherList;
        }

        public static class TodayDTO {
            /**
             * hours : [{"hour":"15:00","weather":"晴","temperature":21},{"hour":"16:00","weather":"晴","temperature":21},{"hour":"17:00","weather":"晴","temperature":21},{"hour":"18:00","weather":"晴","temperature":20},{"hour":"19:00","weather":"晴","temperature":21},{"hour":"20:00","weather":"晴","temperature":20}]
             * airQuantity : {"no2":32,"pm25":22,"o3":26,"so2":6,"pm10":50,"co":0.3}
             * comfortLevel : {"uv":1,"dressingIndex":"T恤","humidity":28,"coldIndex":"不易","apparentTemperature":18,"uvIndex":"弱","washIndex":"适宜","sportIndex":"较适宜"}
             * tempInfo : {"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"5月26日今天"}
             * updateTime : 15:00
             * wind : {"windStrength":"2级","windDirection":"西风"}
             */

            private AirQuantityDTO airQuantity;
            private ComfortLevelDTO comfortLevel;
            private TempInfoDTO tempInfo;
            private String updateTime;
            private WindDTO wind;
            private List<HoursDTO> hours;

            public AirQuantityDTO getAirQuantity() {
                return airQuantity;
            }

            public void setAirQuantity(AirQuantityDTO airQuantity) {
                this.airQuantity = airQuantity;
            }

            public ComfortLevelDTO getComfortLevel() {
                return comfortLevel;
            }

            public void setComfortLevel(ComfortLevelDTO comfortLevel) {
                this.comfortLevel = comfortLevel;
            }

            public TempInfoDTO getTempInfo() {
                return tempInfo;
            }

            public void setTempInfo(TempInfoDTO tempInfo) {
                this.tempInfo = tempInfo;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public WindDTO getWind() {
                return wind;
            }

            public void setWind(WindDTO wind) {
                this.wind = wind;
            }

            public List<HoursDTO> getHours() {
                return hours;
            }

            public void setHours(List<HoursDTO> hours) {
                this.hours = hours;
            }

            public static class AirQuantityDTO {
                /**
                 * no2 : 32
                 * pm25 : 22
                 * o3 : 26
                 * so2 : 6
                 * pm10 : 50
                 * co : 0.3
                 */

                private int no2;
                private int pm25;
                private int o3;
                private int so2;
                private int pm10;
                private double co;

                public int getNo2() {
                    return no2;
                }

                public void setNo2(int no2) {
                    this.no2 = no2;
                }

                public int getPm25() {
                    return pm25;
                }

                public void setPm25(int pm25) {
                    this.pm25 = pm25;
                }

                public int getO3() {
                    return o3;
                }

                public void setO3(int o3) {
                    this.o3 = o3;
                }

                public int getSo2() {
                    return so2;
                }

                public void setSo2(int so2) {
                    this.so2 = so2;
                }

                public int getPm10() {
                    return pm10;
                }

                public void setPm10(int pm10) {
                    this.pm10 = pm10;
                }

                public double getCo() {
                    return co;
                }

                public void setCo(double co) {
                    this.co = co;
                }
            }

            public static class ComfortLevelDTO {
                /**
                 * uv : 1
                 * dressingIndex : T恤
                 * humidity : 28
                 * coldIndex : 不易
                 * apparentTemperature : 18
                 * uvIndex : 弱
                 * washIndex : 适宜
                 * sportIndex : 较适宜
                 */

                private int uv;
                private String dressingIndex;
                private int humidity;
                private String coldIndex;
                private int apparentTemperature;
                private String uvIndex;
                private String washIndex;
                private String sportIndex;

                public int getUv() {
                    return uv;
                }

                public void setUv(int uv) {
                    this.uv = uv;
                }

                public String getDressingIndex() {
                    return dressingIndex;
                }

                public void setDressingIndex(String dressingIndex) {
                    this.dressingIndex = dressingIndex;
                }

                public int getHumidity() {
                    return humidity;
                }

                public void setHumidity(int humidity) {
                    this.humidity = humidity;
                }

                public String getColdIndex() {
                    return coldIndex;
                }

                public void setColdIndex(String coldIndex) {
                    this.coldIndex = coldIndex;
                }

                public int getApparentTemperature() {
                    return apparentTemperature;
                }

                public void setApparentTemperature(int apparentTemperature) {
                    this.apparentTemperature = apparentTemperature;
                }

                public String getUvIndex() {
                    return uvIndex;
                }

                public void setUvIndex(String uvIndex) {
                    this.uvIndex = uvIndex;
                }

                public String getWashIndex() {
                    return washIndex;
                }

                public void setWashIndex(String washIndex) {
                    this.washIndex = washIndex;
                }

                public String getSportIndex() {
                    return sportIndex;
                }

                public void setSportIndex(String sportIndex) {
                    this.sportIndex = sportIndex;
                }
            }

            public static class TempInfoDTO {
                /**
                 * maxTemperature : 12
                 * uv : 弱
                 * minTemperature : 24
                 * temperature : 21
                 * weather : 晴
                 * humidity : 60
                 * air : 无污染
                 * apparentTemperature : 15
                 * label : 今天
                 * day : 5月26日今天
                 */

                private String maxTemperature;
                private String uv;
                private String minTemperature;
                private String temperature;
                private String weather;
                private String humidity;
                private String air;
                private String apparentTemperature;
                private String label;
                private String day;

                public String getMaxTemperature() {
                    return maxTemperature;
                }

                public void setMaxTemperature(String maxTemperature) {
                    this.maxTemperature = maxTemperature;
                }

                public String getUv() {
                    return uv;
                }

                public void setUv(String uv) {
                    this.uv = uv;
                }

                public String getMinTemperature() {
                    return minTemperature;
                }

                public void setMinTemperature(String minTemperature) {
                    this.minTemperature = minTemperature;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public String getAir() {
                    return air;
                }

                public void setAir(String air) {
                    this.air = air;
                }

                public String getApparentTemperature() {
                    return apparentTemperature;
                }

                public void setApparentTemperature(String apparentTemperature) {
                    this.apparentTemperature = apparentTemperature;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }
            }

            public static class WindDTO {
                /**
                 * windStrength : 2级
                 * windDirection : 西风
                 */

                private String windStrength;
                private String windDirection;

                public String getWindStrength() {
                    return windStrength;
                }

                public void setWindStrength(String windStrength) {
                    this.windStrength = windStrength;
                }

                public String getWindDirection() {
                    return windDirection;
                }

                public void setWindDirection(String windDirection) {
                    this.windDirection = windDirection;
                }
            }

            public static class HoursDTO {
                /**
                 * hour : 15:00
                 * weather : 晴
                 * temperature : 21
                 */

                private String hour;
                private String weather;
                private int temperature;

                public String getHour() {
                    return hour;
                }

                public void setHour(String hour) {
                    this.hour = hour;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public int getTemperature() {
                    return temperature;
                }

                public void setTemperature(int temperature) {
                    this.temperature = temperature;
                }
            }
        }

        public static class WeatherListDTO {
            /**
             * maxTemperature : 12
             * uv : 弱
             * minTemperature : 23
             * temperature : 10
             * weather : 晴
             * humidity : 50
             * air : 良好
             * apparentTemperature : 15
             * label : 昨天
             * day : 5月25日昨天
             */

            private String maxTemperature;
            private String uv;
            private String minTemperature;
            private String temperature;
            private String weather;
            private int humidity;
            private String air;
            private String apparentTemperature;
            private String label;
            private String day;

            public String getMaxTemperature() {
                return maxTemperature;
            }

            public void setMaxTemperature(String maxTemperature) {
                this.maxTemperature = maxTemperature;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getMinTemperature() {
                return minTemperature;
            }

            public void setMinTemperature(String minTemperature) {
                this.minTemperature = minTemperature;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public String getAir() {
                return air;
            }

            public void setAir(String air) {
                this.air = air;
            }

            public String getApparentTemperature() {
                return apparentTemperature;
            }

            public void setApparentTemperature(String apparentTemperature) {
                this.apparentTemperature = apparentTemperature;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
