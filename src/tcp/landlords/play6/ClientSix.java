package tcp.landlords.play6;

import com.alibaba.fastjson.JSONObject;
import tcp.PushObject;
import tcp.ReceiveObject;
import tcp.TcpClient;

import java.util.Scanner;

/**
 * Created by yjh on 16-6-13.
 *
 */
public class ClientSix {

    public static void main(String[] args) {
        TcpClient tcpClient = new TcpClient("127.0.0.1", 9001, "15823634866");
        ReceiveObject obj = new ReceiveObject();
        PushObject data = new PushObject();
        data.setUser(tcpClient.getUser());
        obj.setData(data);
        tcpClient.start();
        String deskNo = "";
        int seatNo = 0;
        while (true) {
            System.out.println("输入对应数字进入对应功能");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            switch (index) {
                case 1:
                    obj.setType(10001);
                    data.setMultiple(10);
                    obj.setData(data);
                    tcpClient.send(JSONObject.toJSONString(obj));
                    break;
                case 2://是否抢庄
                    System.out.println("输入游戏桌号");
                    deskNo = scanner.next();
                    data.setDeskNo(deskNo);
                    System.out.println("输入该玩家的座位号");
                    seatNo = scanner.nextInt();
                    data.setSeatNo(seatNo);
                    obj.setType(10003);
                    System.out.println("输入是否抢庄(0为不抢，1234为倍数抢庄)");
                    int bankerMultiple = scanner.nextInt();
                    data.setMultiple(bankerMultiple);
                    obj.setData(data);
                    tcpClient.send(JSONObject.toJSONString(obj));
                    break;
                case 3://几倍下注
                    System.out.println("输入游戏桌号");
                    deskNo = scanner.next();
                    data.setDeskNo(deskNo);
                    System.out.println("输入该玩家的座位号");
                    seatNo = scanner.nextInt();
                    data.setSeatNo(seatNo);
                    obj.setType(10004);
                    System.out.println("输入几倍下注");
                    int multiple = scanner.nextInt();
                    data.setMultiple(multiple);
                    obj.setData(data);
                    tcpClient.send(JSONObject.toJSONString(obj));
                    break;
                case 4://离开
                    obj.setType(10005);
                    System.out.println("输入该玩家的座位号");
                    seatNo = scanner.nextInt();
                    data.setSeatNo(seatNo);
                    obj.setData(data);
                    tcpClient.send(JSONObject.toJSONString(obj));
                    break;
                case 5://取消配桌
                    obj.setType(10002);
                    data.setUser(tcpClient.getUser());
                    obj.setData(data);
                    tcpClient.send(JSONObject.toJSONString(obj));
                    break;
            }
        }
    }
}
