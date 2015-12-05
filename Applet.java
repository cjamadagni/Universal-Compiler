import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

/* <applet code = "EventApplet" width = 300 height = 300> </applet> */
public class EventApplet extends Applet implements ActionListener, Runnable{
    Button b;
    TextField ip,port,name;
    TextArea prog, inp, op;
    Label l1,l2,l3,l4,l5,l6;
    Socket soc;
    String s1,s2,s3,s4,s5,s6;
    int bc;
    DataOutputStream dout;
    DataInputStream din;
    
    Thread th;
    
    public void init()
    {
        /*try
        { soc=new Socket("localhost",2008); }
        catch (Exception m){}*/
        bc=0;
        setLayout(new GridLayout(7,2));
        ip=new TextField(20);
        l1=new Label("IP Address of the Server");
        port=new TextField(8);
        l2=new Label("Port number of the Server");
        name=new TextField(20);
        l3=new Label("Program name (with .c .cpp .py or .java)");
        prog=new TextArea( 1000, 1000);
        l4=new Label("Program");
        inp=new TextArea(100,100);
        l5=new Label("Input (in the sequence your program needs them)");
        op=new TextArea(500,500);
        l6=new Label("Output");
        b=new Button("SUBMIT");
        b.setBackground(Color.green);
        b.addActionListener(this);
        add(l1);
        add(ip);
        add(l2);
        add(port);
        add(l3);
        add(name);
        add(l4);
        add(prog);
        add(l5);
        add(inp);
        add(new Label(" "));
        add(b);
        add(l6);
        add(op);
        th= new Thread(this);
        th.start();
    }
    
    public void run()
    {
        
        while(true)
        {
            /*if(bc==1)
            op.setText("hi");*/
            try
            {th.sleep(10);} catch(Exception j){}
            if(bc==1)
            {
                bc=0;
                try{
                    int p=Integer.parseInt(s2);
                    soc=new Socket(s1,p);
                    dout=new DataOutputStream(soc.getOutputStream());
                    dout.flush();
                    dout.writeUTF(s3);
                    dout.flush();
                    th.sleep(10);
                    dout.writeUTF(s4);
                    dout.flush();
                    th.sleep(10);
                    dout.writeUTF(s5);
                    dout.flush();
                    th.sleep(10);
                    soc.shutdownOutput();
                    din=new DataInputStream(soc.getInputStream());
                    s6=din.readLine();
                    s6=s6.replace('@','\n');
                    op.setText(s6);
                    dout.close();
                    din.close();
                    soc.close();
                    th.sleep(500);
                }
                catch(Exception ex){op.setText("xxx");}
            }
        }
    }
    
    public void actionPerformed(ActionEvent e){
        
        s1=ip.getText();
        s2=port.getText();
        s3=name.getText();
        s4=prog.getText();
        s4='@'+s4;
        s5=inp.getText();
        s5='#'+s5;
        bc=1;
        //b.setEnabled(false);
    }
    
    public static void main( String[] args )
    {
        EventApplet    applet = new EventApplet();
        JFrame  frame = new JFrame();
        frame.setTitle( "My Editor&Compiler" );
        frame.getContentPane().add( applet , BorderLayout.CENTER );
        applet.init();
        applet.start();
        frame.setSize( 460 , 360 );
        Dimension   d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation( ( d.width - frame.getSize().width ) / 2 ,
        ( d.height - frame.getSize().height ) / 2 );
        frame.setVisible( true );
    }
}

