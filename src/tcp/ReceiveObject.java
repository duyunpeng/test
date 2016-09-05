package tcp;

/**
 * Created by yjh on 16-6-12.
 */
public class ReceiveObject {


    private int type;
    private PushObject data;



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PushObject getData() {
        return data;
    }

    public void setData(PushObject data) {
        this.data = data;
    }
}
