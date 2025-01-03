import java.awt.*;

public class Stone extends Object{
    Stone()
    {
        this.x=(int)(Math.random()*600);
        this.y=(int)(Math.random()*500+300);
        this.width=22;//宽度
        this.height=18;//高度
        this.m=70;//质量
        this.score=20;//分数
        this.type=2;//类型
        this.flag=false;
        this.img= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/stone.png");
    }
}
class mStone extends Stone{
    mStone()
    {
        this.x=(int)(Math.random()*600);
        this.y=(int)(Math.random()*500+300);
        this.width=52;//宽度
        this.height=44;//高度
        this.score=10;//分数
        this.m=40;//质量
        this.flag=false;
        this.img= Toolkit.getDefaultToolkit().getImage("Gold Miner/image/mrr.png");
    }
}
