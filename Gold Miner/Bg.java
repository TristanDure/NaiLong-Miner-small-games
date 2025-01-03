import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Date;

public class Bg {
    Image bg= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/bg.png");
    Image bg1= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/sky.png");
    Image bg2= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/nailong.png");
    Image bg3= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/water.png");
    Image bg4= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/hongwen.png");
    Image bg5= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/defeat.jpg");
    Image bg6= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/defeat.jpg");

    long begin=0;
    long end=1000000;
    long Gameduration=20000;
    static int all=0;//总分
    //定时器
    Date date = new Date();//获取当前时间
    //关卡
    static int level=1;
    static int target=5*level;
    static int water=5;//神药
    static int duration=10000;//神药持续时间
    static boolean waterflag=false;//神药药效
    public void paintself(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.drawImage(bg1, 0, 0, null);
        switch (Window.state)
        {
            case 0:
                DrawWord(g,"按右键开始游戏",200,200,Color.black,20);
                //游戏规则
                DrawWord(g,"游戏规则：",200,250,Color.black,20);
                DrawWord(g,"1.鼠标左键抓取",200,300,Color.black,20);
                DrawWord(g,"2.鼠标右键喝神药,神药可以加速抓取",200,350,Color.black,20);
                DrawWord(g,"3.抓取到目标分数即可过关",200,400,Color.black,20);
                DrawWord(g,"4.每过一关,有概率获得一瓶神药",200,450,Color.black,20);
                begin=System.currentTimeMillis();
                break;
            case 1:if(!waterflag)g.drawImage(bg2, 215, 80, null);
            else  g.drawImage(bg4, 215, 80, null);
                g.drawImage(bg3,320 , 80, null);
                DrawWord(g,"得分："+all,10,50,Color.red,20);
                DrawWord(g,"神药："+water,330,80,Color.red,20);
                //绘制关卡
                DrawWord(g, "关卡："+Bg.level, 270, 50, Color.red, 20);
                //绘制目标
                DrawWord(g, "目标："+Bg.target, 10, 70, Color.red, 20);
                //绘制神药效果
                if(Bg.waterflag)
                {
                    g.setColor(Color.red);
                    g.setFont(new Font("微软雅黑", Font.BOLD, 20));
                    if(Bg.duration-(System.currentTimeMillis()-date.getTime())>=0)
                    {g.drawString("神药效果中", 200, 200);
                        g.drawString("剩余时间："+(Bg.duration-(System.currentTimeMillis()-date.getTime()))/1000+"秒", 200, 250);}
                    else
                    {
                        Bg.waterflag=false;
                    }
                }
                end=System.currentTimeMillis();
                long time=(Gameduration-(end-begin))/1000;
                if(time>0)DrawWord(g,"剩余时间："+time+"秒",10,20,Color.red,20);
                g.drawString("现在时间还剩"+time+"s", 10, 100);
                break;
            case 2:
                g.drawImage(bg6, 200, 200, null);
                DrawWord(g,"恭喜过关",270,200,Color.red,20);
                break;
            case 3:
                g.drawImage(bg5, 200, 200, null);
                DrawWord(g,"游戏结束",270,200,Color.red,20);
                Bg.level=1;
                Bg.all=0;
                Bg.target=50*level;
                Bg.water=5;//神药数量
                break;
        }

    }
    public boolean gametime()//判断是否时间到了
    {
        if(end-begin<Gameduration)
        {
            return true;
        }
        return false;
    }
    public void DrawWord(Graphics g,String str,int x,int y,Color color,int size )
    {
        g.setColor(color);//设置颜色
        g.setFont(new Font("微软雅黑", Font.BOLD, size));//设置字体
        g.drawString(str, x, y);//绘制字符串
    }
}
