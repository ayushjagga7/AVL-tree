package GradedLab3;


public class AVLTree {
  Value root = null;
    int op;

    void insert(int N, Value R) {
        op=1;
        if (root == null)
            root = new Value(N, 1, null);
        else {
            if (N <= R.N) {
                if (R.left == null) {
                    R.left = new Value(N, 1, R);
                    dobalance(R.left);
                } else
                    insert(N, R.left);
            } else {
                if (R.right == null) {
                    R.right = new Value(N, 1, R);
                    dobalance(R.right);
                } else
                    insert(N, R.right);
            }
        }

    }

    void remove(Value x) {
        Value y, z = null;
        op=0;
        int ch=0;
        if (x.left == null || x.right == null)
            y = x;
        else {
            Value temp = x;
            for (y = temp.parent; y != null && temp == y.right; y = y.parent) {
                temp = y;
            }
        }
        if((x.right==null || x.left==null) && x.parent==null )
        {   root=null;
            x=null;
        }


        else if(y==x)
        {
            if (y.left != null)
                z = y.left;
            else
                z = y.right;
            if (z != null)
                z.parent = y.parent;

            if (y.parent == null) // z was is root
                root = z;
            else { // removing y
                if (y == y.parent.left) // y was in left
                { ch=1;
                    y.parent.left = z;}
                else
                {ch=0;
                    y.parent.right = z; // y was in right}
                }
            }

            if(y.parent.parent==null && y.parent!=null)
            {
                if (ch==1)
                {
                    if(y.parent.right!=null)
                         Lrotation(y.parent.right,y.parent);
                    else if(y.parent.left!=null)
                    { Lrotation(y.parent.left,y.parent);
                        //Rrotation();
                        }
                }
                else if (ch==0 && y.left!=null)
                {

                    if(y.parent.left!=null){

                        Rrotation(y.parent.left,y.parent);
                       /* System.out.println(y.parent.N+" "+y.parent.left.N+" "+y.parent.parent.N);
                        if(root.height>1)
                        {Lrotation(y.parent,y.parent.parent);
                        System.out.println(root.N);}*/
                    }
                    else if(y.parent.right!=null)
                    {
                        Rrotation(y.parent.right,y.parent);

                    }
                }

            }
            x.N = y.N;
            y.height = 0;
        }
        else if(x!=root)
        {
            System.out.println("2");
            y = x;
            z = y.right;
            y.left.parent = z;
            z.left = y.left;
            if (y.parent == null)
                root = z;
            else {
                if (y == y.parent.left)
                    y.parent.left = z;
                else
                    y.parent.right = z;
            }
            z.parent = y.parent;
            x.N = y.N;
            y.height = 0;

        }
        else
        {
            System.out.println("3");
            y = x;
            z = y.right;
            root = z;
            Value temp;


            for(temp=z; temp.left!=null; temp =temp.left)
            {

            }

            y.left.parent = temp;
            temp.left = y.left;
            root.parent = null;

            x.N = y.N;
            y.height = 0;

            temp=root;
            if(temp.right!=null)
            {
            for(temp=root; temp.right.right!=null; temp = temp.right);
            {

            }}
            z = temp;
            System.out.println(z.N);
           // dobalance(z);

        }

        y = z;
        if(y!=null){
            do {
                op=0;
                System.out.println(z.N);
                if(y!=null)
                     y = dobalance(y);

            } while (y != null);
        }


    }

    Value dobalance(Value x) {
        int h1 = 0, h2 = 0;
        Value n1 = null, rent = null;
        Value y = x, z;

        System.out.println(root.height+ " "+op);

         if(x==root && op==0)
        {
            System.out.println(root.height);
            System.out.println(" rotation ");
            if(root.left==null)
            {
                // l rotation
                System.out.println("L rotation ");
                Lrotation(y.right,y);
            }
            else if(root.right == null)
            {
                //r rotation
                System.out.println("R rotation ");
                if(y.right!=null)
                    Rrotation(y.right,y);
            }
            return null;
        }

        while (y.parent != null) {
            if (y.parent.left == y)
                n1 = y.parent.right;
            else if (y.parent.right == y)
                n1 = y.parent.left;
            h1 = y.height;
            if (n1 == null)
                h2 = 0;
            else
                h2 = n1.height;
            if (Math.abs(h2 - h1) > 1)
                break;

            y.parent.height = 1 + Math.max(h1, h2);
            y = y.parent;
        }

        if(y.parent==null)
            return null;


        z = y.parent;
        rent = z;
        h1 = (z.left == null) ? 0 : z.left.height;
        h2 = (z.right == null) ? 0 : z.right.height;
        if (h1 < h2)
            y = z.right;
        else
            y = z.left;

        h1 = (y.left == null) ? 0 : y.left.height;
        h2 = (y.right == null) ? 0 : y.right.height;
        if (h1 < h2)
            x = y.right;
        else
            x = y.left;

        y.parent = z;
        if(x!=null)
            x.parent = y;

        if (z.left == y) {
            if (y.left == x)
                Rrotation(y, z);
            else {
                Lrotation(x, y);
                x.height++;
                Rrotation(x, z);
            }
        } else {
            if (y.right == x) {
                Lrotation(y, z);
            } else {
                Rrotation(x, y);
                x.height++;
                Lrotation(x, z);
            }
        }

        return rent;
    }
    void Rrotation(Value y, Value z) {
        y.parent = z.parent;
        if (y.parent == null)
            root = y;
        else if (y.parent.left == z)
            y.parent.left = y;
        else
            y.parent.right = y;
        z.left = y.right;
        if (z.left != null)
            z.left.parent = z;
        y.right = z;
        z.parent = y;
        z.height--;
    }
    void Lrotation(Value y, Value z) {
        y.parent = z.parent;
        if (z.parent == null)
            root = y;
        else if (z.parent.left == z)
            y.parent.left = y;
        else
            y.parent.right = y;
        z.right = y.left;
        if (z.right != null)
            z.right.parent = z;
        y.left = z;
        z.parent = y;
        z.height--;
    }

    Value search(int N, Value r) {
        if (r == null)
            return null;
        if (N == r.N)
            return r;
        else if (N < r.N)
            return search(N, r.left);
        else
            return search(N, r.right);
    }

    public int maxDepth(Value node)
    {
        if (node == null)
            return 0;
        else
        {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
    public int count = 0;
    int countnodes(Value root)
    {
        if(root != null)
        {
            countnodes(root.left);
            count++;
            countnodes(root.right);
        }
        return count;
    }

}