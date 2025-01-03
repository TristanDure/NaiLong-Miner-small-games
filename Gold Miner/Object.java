import java.awt.*;

public class Object {
    int x,y;
    int width,height;
    int m;//质量
    int score;//分数
    int type;//类型
    Image img;
    boolean flag;
    double walkrating=5;//移动速度
    int getWidth() {return width;}

    public void walk() {
    double tmpx = Math.random() * 20 - 10; // 随机生成-10到10之间的值
    double tmpy = Math.random() * 20 - 10; // 随机生成-10到10之间的值
        tmpy*=walkrating;
        tmpx*=walkrating;
    // 确保对象不会超出边界
    if (x + tmpx >= 0 && x + tmpx <= 500) {x += tmpx;}
    if (y + tmpy >= 250 && y + tmpy <= 400 - height) {y += tmpy;}
}

    public Rectangle getRect() {return new Rectangle(x, y, width, height);}
    private double t = 0; // 用于控制曲线移动的时间变量
    public void walkInCurve() {
        double rate= Math.random();
        int dir=rate>0.5?1:-1;
        t += 0.1; // 增加时间变量
        double tmpx = Math.cos(t) * 10*dir; // 根据时间变量计算x方向的移动量
        double tmpy = Math.sin(t) * 10*-dir; // 根据时间变量计算y方向的移动量
        tmpy*=walkrating;
        tmpx*=walkrating;
        // 确保对象不会超出边界
        if (x + tmpx >= 0 && x + tmpx <= 500) {x += tmpx;}
        if (y + tmpy >= 250 && y + tmpy <= 400 - height) {y += tmpy;}
}
    void paintself(java.awt.Graphics g)
    {
        g.drawImage(img, x, y, null);
    }
}
