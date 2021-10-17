package GradedLab3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
public class finalmain extends Application {
    static AVLTree AVL = new AVLTree();
    int x = -1;
    int y = -1;
    int z = -1;
    int search=0;
    Value n;
    ArrayList<Integer> inOrder = new ArrayList<>();
    ArrayList<Integer> preOrder = new ArrayList<>();
    ArrayList<Integer> postOrder = new ArrayList<>();
    Label inOrderLabel = new Label();
    Label preOrderLabel = new Label();
    Label postOrderLabel = new Label();
    Pane workingpane = new Pane();
    BorderPane Finalpane=new BorderPane();

    public void start(Stage primaryStage) throws Exception {
        Button top = new Button("AVL Trees");
        top.setPrefSize(400, 100);
        top.setStyle("-fx-background-color: #006400; -fx-font-size: 30px; -fx-text-fill: #FFD700; -fx-alignment: center;");
        top.setMaxWidth(99999D);

        HBox center = new HBox();
        center.setStyle("-fx-background-color: #C0D9AF;");
        center.setMaxWidth(99999D);
        center.setPadding(new Insets(5, 5, 5, 5));
        //center.setSpacing(15);

        HBox hh=new HBox(50);
        Label la3=new Label("Height of AVL search tree is ");
        la3.setText(la3.getText()+Integer.toString(AVL.maxDepth(AVL.root)));
        la3.setStyle(" -fx-text-fill: #FFD700;");
        Label la4=new Label("No of nodes in tree are ");
        la4.setText(la4.getText()+Integer.toString(AVL.countnodes(AVL.root)));
        la4.setStyle(" -fx-text-fill: #FFD700;");
        hh.getChildren().addAll(la3,la4);
        hh.setPrefSize(400, 30);
        hh.setStyle("-fx-background-color: #006400; -fx-font-size: 15px; -fx-alignment: center;");
        hh.setMaxWidth(99999D);
        Finalpane.setBottom(hh);
        AVL.count=0;

        VBox centreleft=new VBox(50);
        Label order=new Label("Order");
        order.setStyle(" -fx-text-fill:#006400 ;-fx-font-size: 30px;");
        inOrderLabel.setText("\n\n\nInorder:"+inOrder.toString()+" \n");
        preOrderLabel.setText("Preorder:"+preOrder.toString()+ "\n");
        postOrderLabel.setText("Postorder:"+postOrder.toString()+ "\n");
        centreleft.getChildren().addAll(order,inOrderLabel,preOrderLabel,postOrderLabel);
        centreleft.setPrefSize(200, 200);
        centreleft.setStyle("-fx-background-color:#FFD700 ; -fx-font-size: 15px; -fx-alignment: center;");
        centreleft.setMaxWidth(99999D);
        centreleft.setMaxHeight(9999D);
        Finalpane.setLeft(centreleft);

        Label list=new Label("Execution List");
        list.setStyle(" -fx-text-fill:#006400 ;-fx-font-size: 30px;");
        VBox centerone=new VBox();
        centerone.setAlignment(Pos.TOP_CENTER);
        centerone.setPrefSize(800,500);
        FlowPane centerbox=new FlowPane();
        centerbox.setHgap(30);
        centerbox.setVgap(30);
        centerbox.setPadding(new Insets(30,30,30,30));
        centerone.getChildren().addAll(list,centerbox);
        workingpane.getChildren().addAll(centerone);





        TextField insert = new TextField();
        Button l1 = new Button("Insert");
        l1.setOnAction(e ->
        {
            x = Integer.parseInt(insert.getText());
            AVL.insert(x, AVL.root);
            insert.clear();
            printer();
            addOrderToPane();

            HBox h=new HBox(50);
            Label la1=new Label("Height of AVL search tree is ");
            la1.setText(la1.getText()+Integer.toString(AVL.maxDepth(AVL.root)));
            la1.setStyle(" -fx-text-fill: #FFD700;");
            Label la2=new Label("No of nodes in tree are ");
            la2.setText(la2.getText()+Integer.toString(AVL.countnodes(AVL.root)));
            la2.setStyle(" -fx-text-fill: #FFD700;");
            h.getChildren().addAll(la1,la2);

            h.setPrefSize(400, 30);
            h.setStyle("-fx-background-color: #006400; -fx-font-size: 15px; -fx-alignment: center;");
            h.setMaxWidth(99999D);
            Finalpane.setBottom(h);
            AVL.count=0;

            Label exe= new Label(Integer.toString(x)+" has been successfully added to the tree->");
            exe.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            centerbox.getChildren().addAll(exe);

        });

        TextField delete = new TextField();
        Button l2 = new Button("Delete");

        l2.setOnAction(e ->
        {
            y = Integer.parseInt(delete.getText());
            Value temp = AVL.search(y, AVL.root);
            if (temp == null)
                JOptionPane.showMessageDialog(null, "Not Found");
            else {
                AVL.remove(temp);
                JOptionPane.showMessageDialog(null, "Removed");
            }
            delete.clear();
            printer();
            addOrderToPane();
            HBox h=new HBox(50);
            Label la1=new Label("Height of AVL search tree is ");
            la1.setText(la1.getText()+Integer.toString(AVL.maxDepth(AVL.root)));
            la1.setStyle(" -fx-text-fill: #FFD700;");
            Label la2=new Label("No of nodes in tree are ");
            la2.setText(la2.getText()+Integer.toString(AVL.countnodes(AVL.root)));
            la2.setStyle(" -fx-text-fill: #FFD700;");
            h.getChildren().addAll(la1,la2);

            h.setPrefSize(400, 30);
            h.setStyle("-fx-background-color: #006400; -fx-font-size: 15px; -fx-alignment: center;");
            h.setMaxWidth(99999D);
            Finalpane.setBottom(h);
            AVL.count=0;


            Label exe= new Label(Integer.toString(y)+" has been successfully dleted from the tree->");
            exe.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            centerbox.getChildren().addAll(exe);

        });


        TextField find = new TextField();
        Button l3 = new Button("Find");
        l3.setOnAction(e ->
        {
            z = Integer.parseInt(find.getText());
            Value temp = AVL.search(z, AVL.root);
            if (temp == null)
                JOptionPane.showMessageDialog(null, "Not Found");
            else {
                JOptionPane.showMessageDialog(null, "Found ");
                search=1;
                n = temp;
            }

            printer();
            find.clear();

            Label exe= new Label(" User searched for the Element "+Integer.toString(z) +" in tree->");
            exe.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            centerbox.getChildren().addAll(exe);


        });

        Button l4 = new Button("Print");
        l4.setOnAction(e ->
        {
            printer();
            Label exe= new Label(" User printed the tree");
            exe.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            centerbox.getChildren().addAll(exe);


        });

        Button l5 = new Button("Clear");
        l5.setOnAction(e ->
        {
            AVL.root=null;
            printer();
            HBox h=new HBox();
            Label la1=new Label("Height of AVL search tree is ");
            la1.setText(la1.getText()+Integer.toString(AVL.maxDepth(AVL.root)));
            Label la2=new Label("No of nodes in tree are ");
            la2.setText(la2.getText()+Integer.toString(AVL.countnodes(AVL.root)));
            h.getChildren().addAll(la1,la2);
            Finalpane.setBottom(h);
            AVL.count=0;

            Label exe= new Label("User has cleared the tree");
            exe.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            centerbox.getChildren().addAll(exe);
            addOrderToPane();

        });

        center.getChildren().addAll(insert, l1, delete, l2, find, l3, l4, l5);
        center.setSpacing(15);
        center.setAlignment(Pos.CENTER_LEFT);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(top, center);


        Finalpane.setTop(vbox);
        Finalpane.setCenter(workingpane);


        Scene sc = new Scene(Finalpane, 1000, 650);
        sc.setOnMouseClicked(e -> {
            System.out.println(e.getX() + "\n" + e.getY());
        });
        primaryStage.setTitle("jAVFX");
        primaryStage.setScene(sc);
        primaryStage.show();

    }





















    public static void main(String[] args) {
        launch(args);
    }

    public void printer() {

        JFrame f = new JFrame("AVL Tree panel");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });

        JButton button = new JButton("Next!");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(button);
        button.addActionListener(ex -> {
            f.dispose();
        });

        Drawtree applet = new Drawtree();
        f.getContentPane().add("Center", applet);


        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth() / 2);

        if(search==1)
        {
            applet.find(n);

        }

        applet.init(AVL.root,xSize-50,applet.fg);

        f.pack();
        f.setSize(new Dimension(xSize, 500));
        f.setVisible(true);
        n=null;

    }

    void printPostorder(Value node){
        if (node == null)
            return;

        printPostorder(node.left);

        printPostorder(node.right);

        postOrder.add(node.N);
    }

    void printInorder(Value node) {
        if (node == null)
            return;

        printInorder(node.left);
        inOrder.add(node.N);
        printInorder(node.right);
    }

    void printPreorder(Value node) {
        if (node == null)
            return;

        preOrder.add(node.N);

        printPreorder(node.left);

        printPreorder(node.right);
    }

    public void addOrderToPane(){
        VBox orderVBox= new VBox(50);
        printPostorder(AVL.root);
        printInorder(AVL.root);
        printPreorder(AVL.root);

        Label lab=new Label("Order");
        lab.setStyle(" -fx-text-fill:#006400 ;-fx-font-size: 30px;");
        inOrderLabel.setText("\n\n\nInorder:"+inOrder.toString()+" \n");
        preOrderLabel.setText("Preorder:"+preOrder.toString()+ "\n");
        postOrderLabel.setText("Postorder:"+postOrder.toString()+ "\n");

        orderVBox.getChildren().addAll(lab,inOrderLabel, preOrderLabel,postOrderLabel);
        orderVBox.setPrefSize(200, 200);
        orderVBox.setStyle("-fx-background-color:#FFD700 ; -fx-font-size: 15px; -fx-alignment: center;");
        orderVBox.setMaxWidth(99999D);
        orderVBox.setMaxHeight(9999D);
        Finalpane.setLeft(orderVBox);
        preOrder.clear();
        inOrder.clear();
        postOrder.clear();



    }
}