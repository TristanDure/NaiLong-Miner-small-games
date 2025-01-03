import java.awt.*;
public class Line {
    int x1=300;
    int y1=165;
    int x2=300;
    int y2=500;

    double lenth=60;
    double Maxlenth=400;
    double Minlenth=60;
    double n=0.1;
    int dir=1;//方向
    int times=0;
    //状态
    int state=0;
    Image hock= Toolkit.getDefaultToolkit().getImage("image/hock.png");
    Window frame;
    Line(Window frame)
    {
        this.frame=frame;
    }
    void check()//判断是否捕捉到
    {
        for(Object o:this.frame.Objectlist)
        {
            if(x2>o.x&&x2<o.x+o.width&&y2>o.y&&y2<o.y+o.height)
            {
                System.out.println("捕捉到了"+times);//调试检查是否捕捉到
                o.flag=true;//捕捉到了
                state=3;
            }
        }

    }

    void Lines(Graphics g)//绘制线
    {

        g.setColor(Color.pink);
        x2=(int)(x1+lenth*Math.cos(n*Math.PI));
        y2=(int)(y1+lenth*Math.sin(n*Math.PI));
        g.drawLine(x1-1, y1, x2-1, y2);
        g.drawLine(x1+1, y1, x2+1, y2);
        g.drawImage(hock, x2-30, y2-10, null);
    }

    void paintself(Graphics g) {
        if(state==1)check();
        if(frame.bg.date.getTime()-System.currentTimeMillis()>Bg.duration)
        {
            Bg.waterflag=false;
        }
        switch (state)
        {
            case 0://线的摆动
                if(n>=0.9)dir=-1;//改变方向
                if(n<=0.1)dir=1;
                n+=0.005*dir;
                if(Bg.waterflag)//如果喝了神药，加速
                    n+=0.01*dir;
                Lines(g);

                break;
            case 1:
                if(lenth<Maxlenth)//如果长度小于最大长度，增加长度
                {
                    lenth+=5;
                    if(Bg.waterflag)//如果喝了神药，加速
                        lenth+=10;
                    Lines(g);
                }
                else state=2;


                break;
            case 2:
                if(lenth>=Minlenth){//如果长度大于最小长度，减少长度
                    lenth-=5;
                    if(Bg.waterflag)
                        lenth-=10;
                    Lines(g);}
                else
                state=0;//回到摆动状态
                break;
            case 3:

                if(lenth>=Minlenth){//如果长度大于最小长度，减少长度
                    lenth-=5;
                    if(Bg.waterflag)
                        lenth-=10;
                    Lines(g);
                    for(Object o:frame.Objectlist)//捕捉到了就移动
                    {
                        if(o.flag==false)continue;  //如果没有捕捉到就跳过
                        o.x=x2-o.getWidth()/2;
                        o.y=y2;
                        int curm=o.m;//临时质量
                        if(Bg.waterflag)//神药使质量影响微乎其微，加速
                        {
                            curm=1;
                        }
                        else curm=o.m;//正常速度

                        if(lenth<=Minlenth)//拉到最小长度，移出屏幕
                        {
                            o.x-=1200;
                            o.y-=1200;
                            state=0;
                            Bg.all+=o.score;
                            o.flag=false;
                        }
                        try {
                        Thread.sleep(curm);//依据质量延时
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }


                }

                break;
        }


    }
}
