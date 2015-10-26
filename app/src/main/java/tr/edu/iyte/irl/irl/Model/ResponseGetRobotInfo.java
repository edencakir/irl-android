package tr.edu.iyte.irl.irl.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enes on 10/15/15.
 */
public class ResponseGetRobotInfo {
    @SerializedName("data")
    private Robot robot = new Robot();

    public ResponseGetRobotInfo() {
        super();
    }

    public Robot getRobot() {
        return this.robot;
    }
}
