package matchmaking.com.whoplay;

/**
 * Created by robert.arifin on 02/10/2017.
 */

public class CheckBoxModel {

        private String name;
        private boolean selected;

        public CheckBoxModel(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
