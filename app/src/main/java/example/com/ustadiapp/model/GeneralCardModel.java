package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/12/17.
 */

public class GeneralCardModel {

        private String time;
        private String venu;
        private String subject;
        private String day;
        private int slot;
        private String name;
        public GeneralCardModel(){

        }

        public GeneralCardModel(String time, String venu, String subject, String day, int slot,String name) {
            this.time = time;
            this.venu = venu;
            this.subject = subject;
            this.day = day;
            this.slot=slot;
            this.name=name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getVenu() {
            return venu;
        }

        public void setVenu(String venu) {
            this.venu = venu;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }

