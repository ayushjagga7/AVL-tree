package GradedLab3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Drawtree extends JComponent{

    final Color bg = Color.white;
    final Color fg = Color.black;
    final Color red = Color.red;
    final Color white = Color.white;
    final BasicStroke stroke = new BasicStroke(2.0f);
    final BasicStroke wideStroke = new BasicStroke(8.0f);

    Color dGreen = new Color(20, 125, 10);

    Dimension totalSize;
    int height, width;
    Value r = null;
    Value n1 = null;

    public void init(Value N, int x,Color cl) {
        setBackground(bg);
        setForeground(cl);
        r = N;
        width = x;
    }

    Graphics2D g2;

    public void paint(Graphics g) {
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        inorder(r, 0, width, 80);
    }

    public void draw(int x1, int x2, int y, String n, int d) {

        g2.setStroke(stroke);

        g2.setPaint(Color.black);
        int x = (x1 + x2) / 2;
        if (d == 1)
            g2.draw(new Line2D.Double(x2, y - 30, x + 15, y));
        else if (d == 2)
            g2.draw(new Line2D.Double(x + 15, y, x1 + 30, y - 30));
        if(n1!=null)
        {
            if(n1.N == Integer.parseInt(n))
            { g2.setPaint(Color.orange); }

            else
                g2.setPaint(dGreen);
        }
        else
            g2.setPaint(dGreen);


        Shape circle = new Ellipse2D.Double((x1 + x2) / 2, y, 30, 30);
        g2.draw(circle);
        g2.fill(circle);

        g2.setPaint(Color.white);

        g2.drawString(n, x + 10, y + 18);
    }

    int x1 = 500, y1 = 30;

    void inorder(Value r, int x1, int x2, int y) {
        if (r == null)
            return;


        inorder(r.left, x1, (x1 + x2) / 2, y + 40);
        if (r.parent == null)
            draw(x1, x2, y, r.N + "", 0);
        else {
            if (r.parent.N < r.N)
                draw(x1, x2, y, r.N + "", 2);
            else
                draw(x1, x2, y, r.N + "", 1);
        }
        inorder(r.right, (x1 + x2) / 2, x2, y + 40);
    }

    void find(Value r)
    {
        n1 = r;
    }
}