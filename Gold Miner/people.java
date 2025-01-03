import java.awt.*;
public class people extends Object{
    people()
    {
        this.x=(int)(Math.random()*600);
        this.y=(int)(Math.random()*500+300);
        this.width=68;//宽度
        this.height=128;//高度
        this.m=50;//质量
        this.type=1;
        this.score=100;
        this.flag=false;
        this.img=Toolkit.getDefaultToolkit().getImage("Gold Miner/image/ren.png");

    }
}
class mpeople extends people{
    mpeople()
    {
        this.x=(int)(Math.random()*600);
        this.y=(int)(Math.random()*500+300);
        this.width=80;//宽度
        this.height=126;//高度
        this.m=30;//质量
        this.score=50;
        this.flag=false;
        this.img=Toolkit.getDefaultToolkit().getImage("Gold Miner/image/mren.png");
    }
}