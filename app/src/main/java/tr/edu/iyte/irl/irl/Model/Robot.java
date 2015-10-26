package tr.edu.iyte.irl.irl.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enes on 10/15/15.
 */
public class Robot {
    @SerializedName("robotName")
    private String name;

    @SerializedName("weight")
    private String weight;

    @SerializedName("robotCategory")
    private String category;

    @SerializedName("ticketNumber")
    private String ticketNo;

    @SerializedName("teamName")
    private String teamName;

    @SerializedName("teamMail")
    private String teamMail;

    @SerializedName("teamTel")
    private String teamNum;

    @SerializedName("isRecorded")
    private String registirationStatus;

    public Robot() {
        super();
    }

    public Robot(String name, String weight, String category,
                 String ticketNo, String teamName, String teamMail,
                 String teamNum, String registirationStatus) {
        this.name = name;
        this.weight = weight;
        this.category = category;
        this.ticketNo = ticketNo;
        this.teamName = teamName;
        this.teamMail = teamMail;
        this.teamNum = teamNum;
        this.registirationStatus = registirationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMail() {
        return teamMail;
    }

    public void setTeamMail(String teamMail) {
        this.teamMail = teamMail;
    }

    public String getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    public String getRegistirationStatus() {
        return registirationStatus;
    }

    public void setRegistirationStatus(String registirationStatus) {
        this.registirationStatus = registirationStatus;
    }
}
