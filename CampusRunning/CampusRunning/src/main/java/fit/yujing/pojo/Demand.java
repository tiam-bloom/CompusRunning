package fit.yujing.pojo;

public class Demand {
    private Integer did;
    private Integer id;
    private String name;
    private String title;
    private String content;
    private Integer reward;
    private Integer state;  //状态码
    private Integer ltd;

    @Override
    public String toString() {
        return "Demand{" +
                "did=" + did +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", reward=" + reward +
                ", state=" + state +
                ", ltd=" + ltd +
                '}';
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLtd() {
        return ltd;
    }

    public void setLtd(Integer ltd) {
        this.ltd = ltd;
    }
}
