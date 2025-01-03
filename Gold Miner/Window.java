import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Window extends JFrame {
    people p = new people();//临时对象
    Image all;//双缓冲
    int num=10;//固定数量
    static int state=0;//游戏状态 0:未开始 1:进行中 2:胜利 3:失败
    List<Object> Objectlist = new ArrayList<>();
    {
        people p;//临时对象
        for(int i=0;i<num+Bg.level*0.5;i++)
        {
            boolean flag=true;//判断是否重叠
            double rate=Math.random();//随机生成的概率
            if(rate>0.6)p=new people();
            else p=new mpeople();
            for(Object o:Objectlist)//遍历所有对象
            {
                if(o.getRect().intersects(p.getRect()))//判断是否重叠
                {
                    flag=false;//重叠
                    break;
                }
            }
            if(flag)Objectlist.add(p);//不重叠就添加
            else i--;//重叠就重来
        }
        Stone s;//临时对象
        for(int i=0;i<num+Bg.level*0.8;i++)
        {
            boolean flag=true;//判断是否重叠
            double rate=Math.random();//随机生成的概率
            if(rate>0.6)s=new Stone();
            else s=new mStone();
            for(Object o:Objectlist)//遍历所有对象
            {
                if(o.getRect().intersects(s.getRect()))//判断是否重叠
                {
                    flag=false;
                    break;
                }
            }
            if(flag)Objectlist.add(s);
            else i--;
        }
    }

    public void lauchFrame() {
        this.setTitle("Gold Miner");//标题
        this.setSize(600, 500);//大小
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //鼠标操作
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);
                switch (state)
                {
                    case 0:
                        if(e.getButton()==3){
                            Bg.waterflag=false;//重置神药效果
                            bg.date=new Date();//重置开始时间
                            state=1;//开始游戏
                        }
                        break;
                    case 1:
                        if(e.getButton()==1&&line.state==0)//抓取
                        {
                            line.state=1;
                        }
                        if(e.getButton()==3)//右键喝神药
                        {
                            if(Bg.water>0&&Bg.waterflag==false)//神药效果
                            {
                                Bg.water--;//神药数量减少
                                Bg.waterflag=true;//神药效果
                            }
                        }
                        break;
                    case 2:
                        double rate=Math.random();//随机生成的概率
                        if(rate>0.5)//获得神药的概率
                        {
                            Bg.water++;
                        }
                        state=0;//下一关
                        break;
                    case 3:
                        state=0;//失败
                        break;
                }
            }
        });


        while(true)
        {
            nextlevel();//检查是否过关
            repaint();//重绘
            try {
                Thread.sleep(10);//延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    Line line = new Line(this);
    Bg bg = new Bg();
    @Override
    public void paint(Graphics g) {
        all=this.createImage(600, 500);
        Graphics g2=all.getGraphics();
        //绘制背景
        bg.paintself(g2);
        if(state==1) {
            //绘制人物
            for (Object o : Objectlist) {
                o.paintself(g2);//绘制对象
                if(o instanceof people||o instanceof mStone)//判断是否是人物，是就移动
                {
                    double rate=Math.random();//随机两种移动方式
                    if(rate>0.5)
                    o.walk();
                    else o.walkInCurve();
                }
            }
            //绘制钩子
            line.paintself(g2);
        }
        g.drawImage(all,0,0,null);//绘制双缓冲
    }

    public void nextlevel()//检查是否过关
    {
        if(state!=1&& !bg.gametime())return;//游戏未开始或者时间到了就返回
        if(Bg.all>=Bg.target)//过关
        {
        Bg.target=Bg.level*50;//目标分数
        Bg.all=0;//总分
        Bg.waterflag=false;//神药效果
        Bg.level++;//关卡加一
        state=2;//胜利
        dispose();//销毁当前窗口
        Window frame = new Window();//下一关
        frame.lauchFrame();//重新启动
        }
        else if(!bg.gametime())//时间到了
        {
            state=3;//失败
        }
    }


    public static void main(String[] args) {
        new Window().lauchFrame();//启动
    }
}
