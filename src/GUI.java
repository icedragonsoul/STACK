//Ideas:
//Left side of App, Left Arrow that slides out from the left side when hovered and displays Logout
//Animation where entire app slides over to reveal the next one
//Same thing for Right side but with Transactions
//Fourth window will be add contacts



//First string for making new account
//



//package zellememo.GUI;

//import zellememo.Client;
//import zellememo.User;
//import database.requests.TransferMoneyRequest;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.text.DefaultCaret;


/**
 *This is the GUI class that generates a user intractable GUI
 *
 *
 */
public class GUI extends JFrame implements ActionListener
{

    // TODO: set currentUser on successful login
    //private User currentUser;

    /**
     * This is an array of array of Strings containing the data of the players and their statistics
     */
    public ArrayList<ArrayList<String>> allStats;
    private Socket sock;
    private ObjectInputStream sockInput;
    private ObjectOutputStream sockOutput;
    public JLabel label;
    private JLabel label2;
    private JLabel label3;
    // array of player draft choices
    private String[] choice;

    //    private JTextField field;
//    private JTextField field2;
//    private JTextField field3;
//    public JButton btn0;
//    public JButton btn1;
//    public JButton btn2;
//    public JButton btn3;
//    public JButton btn4;
//    public JButton btn5;
//    public JButton btn6;
//    public JButton btn7;
//    public JButton btn8;
//    public JButton btn9;
//    public JButton btnBack;
//    public JButton btnDeci;
    /**
     *   This is an enter button
     */
    public JButton btnEnter;
    /**
     * This button leads to a prior page
     */
    public JButton btnOut;


    public JButton btnOut2;


    /**
     * this button leads to the next page
     */
    public JButton btnIn;


    public JButton btnIn2;
    /**
     * This buttons clear your selection
     */
    public JButton btnClear;

    /**
     * This is the next that prints out for requesting a trade
     */
    String requestText;
    /**
     * String for roster text
     */
    String teamText;

    /**
     * a value that checks the current page number
     */
    public int currentPage = 2;

    /**
     * the background image
     */
    private static Image techimage;


    /**
     * a text area
     */
    private JTextArea textArea;
    /**
     * a scroll panel that's here for reuse-ability
     */
    private JScrollPane scroll;

    /**
     * A text field
     */
    public JTextField textField;
    /**
     * A text field for reusability
     */
    public JTextField textField2;
    /**
     * textfield
     */
    public JTextField textField3;
    /**
     * textfield
     */
    public JTextField textField4;

    /**
     * an error label
     */
    public JLabel invalidLabel;


    /**
     * is this the first run?
     */
    public boolean initial = true;


    /**
     * Money for buying players as a string
     */
    static String moneyString = "0";
    /**
     * Money for buying players
     */
    static Double money = 0.0;
    //static int currentSquare = 0;
    //static int deciCounter = 0;
    /**
     * this is the center north panel
     */
    public static Panel CenterNPanel;
    /**
     * this is the center center panel
     */
    public Panel CenterCPanel;
    /**
     * center south panel
     */
    public Panel CenterSPanel;

    /**
     * north panel
     */
    public JPanel NorthPanel;
    /**
     * center panel
     */
    public JPanel CenterPanel;
    /**
     * south panel
     */
    public JPanel SouthPanel;


    public JPanel panelG;
    /**
     * username
     */
    static String username = "default username";
    /**
     * password
     */
    static String password;
    /**
     * account type
     */
    static String type;
    /**
     * tierlist
     */
    static String tier;

    /**
     * balance
     */
    static Double balance = 0.0;


    /**
     * a meme to send
     */
    static String memo;

    /**
     * current index?
     */
    static int currentIndex;


    /**
     * the first drop down menu
     */
    static JComboBox cb1;
    /**
     * second drop down menu
     */
    static JComboBox cb2;

    static String data;

    /**
     * the displayed string in text box
     */
    String display;

    /**
     * user registration success boolean
     */
    boolean registerSuccess = false;


    /**
     * example messages a
     */
    ArrayList<String> friendChat1 = new ArrayList<String>(Arrays.asList("Joe","Joe: message"));
    /**
     * example messages b
     */
    ArrayList<String> friendChat2 = new ArrayList<String>(Arrays.asList("Bob","Bob: very long message"));
    /**
     * example messages c
     */
    ArrayList<String> friendChat3 = new ArrayList<String>(Arrays.asList("Mary","Mary: brief chat"));
    /**
     * example messages together
     */
    ArrayList<ArrayList<String>> friendChat = new ArrayList<ArrayList<String>>(Arrays.asList(friendChat1,friendChat2,friendChat3));


    ArrayList<String> friends = new ArrayList<>();


    /**
     * All the players of a roster
     */
    ArrayList<String> players;

    /**
     * index value of players
     */
    int player1, player2, player3, player4;

    /**
     * index value of players to be traded
     */
    int tradeCount1, tradeCount2;


    String userChoice1;

    String user2,user3,user4;


















    /**
     * counter
     */
    int i = 0;


    /**
     * Constructor of GUI
     * @param port port nubmer
     * @param page page number
     * @throws IOException exception
     */
    public GUI(int port, int page) throws IOException {
        super("title");

        generatePlayerStats();
        if (page == 1)
        {
            System.out.println("go to page 1 attempted");
            goFirstPage();
        }


        else if (page == 2)
        {
            goSecondPage();
            if (initial)
            {
                System.out.println("Pre initializing current page is " + currentPage);
                currentPage = 1;
                goFirstPage();
                currentPage = 1;
                initial = false;
                System.out.println("Post initializing current page is " + currentPage);
            }
        }

        else if (page == 3)
        {
            goThirdPage();
        }

        else
        {

        }


    }


    /**
     * write to text or server
     * @param title Title of file
     * @param input Text value of file
     */
    public static void write(String title, String input)
    {
        try{
            String path = System.getProperty("user.dir") + "\\src\\"+title+".txt";
            System.out.println("path: " + path);
            FileOutputStream fout=new FileOutputStream(path);
            //System.out.println("Key input:" + input);
            byte byteFormat[]=input.getBytes();
            fout.write(byteFormat);
            fout.close();
        }catch(Exception e){System.out.println(e);}
    }


    /**
     * read from textfile/server
     * @param title title of text/server file to read
     * @return String of read items
     */
    public static String read(String title)
    {
        try{
            String path = System.getProperty("user.dir") + "\\src\\" +title + ".txt";
            FileInputStream fout=new FileInputStream(path);
            int i=0;
            String output = "";
            while((i=fout.read())!=-1){
                output = output + (char)i;
            }
            fout.close();
            //System.out.println("Output: " + output);
            return output;
        }
        catch(Exception e){
            //System.out.println(e);
        }
        return "";
    }





    /**
     *<p>this method resets the board</p>
     */
    public void reset()
    {
        moneyString = "0";
        money = 0.0;
        textField.setText(moneyFormat(money));
        textField2.setText("");
        textField3.setText("");
    }

    /**
     * formatting
     * @param num a value
     * @return reformatted String
     */
    public String moneyFormat(Double num)
    {
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        return defaultFormat.format(num);
    }



//    public void checkDeci()
//    {
//        if (moneyString.contains("."))
//        {
//            deciCounter++;
//        }
//        else
//        {
//            deciCounter = 0;
//        }
//    }


    /**
     * register checking
     */
    public void register()
    {

        //boolean registerServerResponse = false;

        boolean registerServerResponse = true;



//      Todo add server verification here, also have new Account accommodate balance
        try {
            String[] zipInfo = new String[]{"NEWACC", username, password, type, tier};
            sockOutput.writeObject(zipInfo);
            sockOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

//            try {
//                String[] unzipInfo = (String[]) sockInput.readObject();
//                if (unzipInfo[0].equals("NEWACC FAILURE"))
//                {
//                    registerServerResponse = true;
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }




        if (registerServerResponse == true)
        {

            registerSuccess = true;

            try {
                goFirstPage();
                currentPage++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * checks for the user in the server and verifies information
     */
    public void checkUser()
    {
        System.out.print("This method should send info to the server");
        //String serverResponse = "Fail";
        String serverResponse = "LOGIN SUCCESS";
        // Todo add server verification here
//        try {
//            String[] zipInfo = new String[]{"LOGIN",username,password};
//            sockOutput.writeObject(zipInfo);
//            sockOutput.flush();
//            Object response = sockInput.readObject();
//            if(response instanceof String) {
//                String str = (String) response;
//                System.out.println(str);
//                serverResponse = str;
//            } else if (response instanceof String[]){
//                String[] arr = (String[]) response;
//
//            }
//
//            //String[] unzipInfo = (String[])sockInput.readObject();
//            //serverResponse = unzipInfo[0];
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            serverResponse = (String) sockInput.readObject();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        if (serverResponse.equals("LOGIN SUCCESS"))
        {
            try {
                goSecondPage();
                currentPage++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            invalidLabel.setText("Invalid Login");
            GUI.this.repaint();
        }
    }

    /**
     * Registration page
     * @throws IOException exceptional exception
     */
    public void goZerothPage() throws IOException{

        System.out.println("you're on zeroth");

        new GUI(8080, 0);

        NorthPanel.removeAll();
        CenterPanel.removeAll();
        SouthPanel.removeAll();
        CenterNPanel.removeAll();
        CenterCPanel.removeAll();
        CenterSPanel.removeAll();

        NorthPanel.revalidate();
        CenterPanel.revalidate();
        SouthPanel.revalidate();
        CenterNPanel.revalidate();
        CenterCPanel.revalidate();
        CenterSPanel.revalidate();

        NorthPanel.repaint();
        CenterPanel.repaint();
        SouthPanel.repaint();
        CenterNPanel.repaint();
        CenterCPanel.repaint();
        CenterSPanel.repaint();




        try {
            //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
            techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
            //System.out.println(GUI.class.getResource("techimage.jpg").toString());
            //techimage = ImageIO.read(new File("techimage"));
        }
        catch(Exception e/*IOException e*/) {
            System.out.println("Bad");
        }




        //CenterPanel.setBorder(new EmptyBorder());


        JLabel nameLabel = new JLabel ("Username(Email)", SwingConstants.CENTER);
        JLabel passLabel = new JLabel ("Password", SwingConstants.CENTER);
        JLabel accountType = new JLabel ("Account Type", SwingConstants.CENTER);
        JLabel accountTier = new JLabel ("Account Tier", SwingConstants.CENTER);
        JLabel balanceLabel = new JLabel ("Initial Balance", SwingConstants.CENTER);


        String[] tierStrings = new String[]{"Bronze", "Silver", "Gold"};
        JComboBox<String> tierList = new JComboBox<>(tierStrings);
        tierList.addActionListener(this);
        tierList.setActionCommand("type");

        String[] typeStrings = new String[]{"Private", "Public"};
        JComboBox<String> typeList = new JComboBox<>(typeStrings);
        typeList.addActionListener(this);
        typeList.setActionCommand("tier");


        invalidLabel = new JLabel ("");

        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {}

            /**
             * A method that Overrides the default keyReleased method
             * specific to inputs of textField
             */
            @Override
            public void keyReleased(KeyEvent event) {

                if(event.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    username = textField.getText();
                    password = textField2.getText();
                    type = String.valueOf(typeList.getSelectedItem());
                    tier = String.valueOf(tierList.getSelectedItem());
                    if (!textField3.getText().equals(""))
                    {
                        balance = Double.parseDouble(textField3.getText());
                    }

                    //memo = textField3.getText();
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println("Type: " + type);
                    System.out.println("Tier: " + tier);
                    System.out.println("Balance: " + balance);

                    System.out.println("test");
                    in();

                    //register();
                }
            }


            /**
             * A method of KeyListener that insists on being Overridden
             */

            @Override
            public void keyTyped(KeyEvent event) {
            }

//            private void shortenUpdate(KeyEvent e)
//            {
//
//            }
        };

        CenterPanel.setLayout(new GridLayout(12,1));
        //CenterNPanel.setLayout(new GridLayout(1,1));
        //CenterCPanel.setLayout(new GridLayout(1,1));
        //CenterSPanel.setLayout(new GridLayout(1,1));

        textField = new JTextField(20);
        textField2 = new JTextField(20);




        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
//        textField2.setPreferredSize(new Dimension(2,100));
//        textField2.setSize(new Dimension(2,100));
        //CenterNPanel.setLayout(new BorderLayout());
        //CenterNPanel.add(CenterPanel.add(nameLabel),BorderLayout.SOUTH);


        textField3.addKeyListener(listener);


        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(Color.WHITE);

        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setForeground(Color.WHITE);

        accountType.setFont(new Font("Arial", Font.PLAIN, 18));
        accountType.setForeground(Color.WHITE);

        accountTier.setFont(new Font("Arial", Font.PLAIN, 18));
        accountTier.setForeground(Color.WHITE);

        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceLabel.setForeground(Color.WHITE);


        btnIn = new JButton("Cancel");
        btnIn.addActionListener(this);
        btnIn.setActionCommand("in");


        CenterPanel.add(nameLabel);
        CenterPanel.add(textField);
        CenterPanel.add(passLabel);
        CenterPanel.add(textField2);
        CenterPanel.add(accountType);
        CenterPanel.add(typeList);
        CenterPanel.add(accountTier);
        CenterPanel.add(tierList);
        CenterPanel.add(balanceLabel);
        CenterPanel.add(textField3);
        CenterPanel.add(invalidLabel);
        CenterPanel.add(btnIn);

        //CenterPanel.add(CenterNPanel);
        //CenterPanel.add(CenterSPanel);

        //GUI.this.add(NorthPanel);



        GUI.this.setLayout(null);
        GUI.this.getContentPane().removeAll();

        //GUI.this.refreshBackground();

        GUI.this.add(CenterPanel);

        //GUI.this.add(SouthPanel);

        GUI.this.revalidate();
        GUI.this.repaint();

    }

    public void goSixthPage() throws IOException {

        new GUI(8080, 0);

        textArea.setText("");
        textArea.revalidate();
        textArea.repaint();

        NorthPanel.removeAll();
        CenterPanel.removeAll();
        SouthPanel.removeAll();
        CenterNPanel.removeAll();
        CenterCPanel.removeAll();
        CenterSPanel.removeAll();

        NorthPanel.revalidate();
        CenterPanel.revalidate();
        SouthPanel.revalidate();
        CenterNPanel.revalidate();
        CenterCPanel.revalidate();
        CenterSPanel.revalidate();

        NorthPanel.repaint();
        CenterPanel.repaint();
        SouthPanel.repaint();
        CenterNPanel.repaint();
        CenterCPanel.repaint();
        CenterSPanel.repaint();


        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                try {
                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
                    //techimage = ImageIO.read(new File("techimage"));
                } catch (Exception e/*IOException e*/) {
                    System.out.println("Bad");
                }
                super.paintComponent(g);
                // Draw the background image.
                g.drawImage(techimage, 0, 0, null);
            }
        });

        btnOut = new JButton("<= Chat Room");
        btnOut.addActionListener(this);
        btnOut.setActionCommand("out");


        CenterPanel.add(btnOut);
        GUI.this.add(CenterPanel);


        gifPlay();

//        GUI.this.setLayout(null);
//        GUI.this.getContentPane().removeAll();
//
//        //GUI.this.refreshBackground();
//
//        GUI.this.add(CenterPanel);
//
//        //GUI.this.add(SouthPanel);
//
//        GUI.this.revalidate();
//        GUI.this.repaint();



    }


    /**
     * The chat menu page
     * @throws IOException Except
     */
    public void goFifthPage() throws IOException{

        new GUI(8080, 0);

        textArea.setText("");
        textArea.revalidate();
        textArea.repaint();

        NorthPanel.removeAll();
        CenterPanel.removeAll();
        SouthPanel.removeAll();
        CenterNPanel.removeAll();
        CenterCPanel.removeAll();
        CenterSPanel.removeAll();

        NorthPanel.revalidate();
        CenterPanel.revalidate();
        SouthPanel.revalidate();
        CenterNPanel.revalidate();
        CenterCPanel.revalidate();
        CenterSPanel.revalidate();

        NorthPanel.repaint();
        CenterPanel.repaint();
        SouthPanel.repaint();
        CenterNPanel.repaint();
        CenterCPanel.repaint();
        CenterSPanel.repaint();

        this.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
                    //techimage = ImageIO.read(new File("techimage"));
                }
                catch(Exception e/*IOException e*/) {
                    System.out.println("Bad");
                }
                super.paintComponent(g);
                // Draw the background image.
                g.drawImage(techimage, 0, 0, null);
            }
        });


        //Import this from database normally
        ArrayList<String> petArrayList = new ArrayList<String>();

        for (int i = 0; i < friendChat.size(); i++)
        {
            petArrayList.add(friendChat.get(i).get(0));
        }

        String[] petStrings = new String[friendChat.size()];
        for (int i =0; i < petArrayList.size(); i++)
            petStrings[i] = petArrayList.get(i);

        setLayout(new GridLayout(3,1));


        JComboBox<String> friendList = new JComboBox<>(petStrings);
        friendList.addActionListener(this);
        friendList.setActionCommand("chat");




        String storage = read("fakeDataBase");

//      Todo Read from Database
//        try {
//            String[] zipInfo = new String[]{"HISTRY"};
//            sockOutput.writeObject(zipInfo);
//            sockOutput.flush();
//            String[] unzipInfo = new String[0];
//            try {
//                unzipInfo = (String[]) sockInput.readObject();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            storage = unzipInfo[0];
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        //Comment below out when top works





        String[] lines = storage.split("\r\n|\r|\n");
        int newlineCount = lines.length;

        textArea = new JTextArea(newlineCount,5);

        scroll = new JScrollPane(textArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBackground(new Color(255,255,255,0));

        textField = new JTextField(20);
        textField.addActionListener(this);
        textField.setActionCommand("talk");


        CenterPanel.add(scroll);
        textArea.setText("");
        //textArea.setText(read("fakeDataBase"));

        CenterPanel.add(textField);

        btnIn = new JButton("=> Highlight Reel");
        btnIn.addActionListener(this);
        btnIn.setActionCommand("in");

        btnOut = new JButton("<= Friend Transactions");
        btnOut.addActionListener(this);
        btnOut.setActionCommand("out");

        SouthPanel.add(friendList);
        SouthPanel.add(btnOut);

        SouthPanel.add(btnIn);



        GUI.this.add(CenterPanel);
        GUI.this.add(SouthPanel);

        String display = "";
        if (friendChat.size() > 0)
        {
            for (int i = 1; i < friendChat.get(0).size(); i++)
            {
                display = display + friendChat.get(0).get(i) + "\n";
            }
            textArea.setText(display);
        }

    }

    /**
     * Transaction Listings
     * @throws IOException Except
     */
    public void goFourthPage() throws IOException{

        //System.out.println("This shouldn't happen");


        new GUI(8080, 0);

        textArea.setText("");
        textArea.revalidate();
        textArea.repaint();

        NorthPanel.removeAll();
        CenterPanel.removeAll();
        SouthPanel.removeAll();
        CenterNPanel.removeAll();
        CenterCPanel.removeAll();
        CenterSPanel.removeAll();

        NorthPanel.revalidate();
        CenterPanel.revalidate();
        SouthPanel.revalidate();
        CenterNPanel.revalidate();
        CenterCPanel.revalidate();
        CenterSPanel.revalidate();

        NorthPanel.repaint();
        CenterPanel.repaint();
        SouthPanel.repaint();
        CenterNPanel.repaint();
        CenterCPanel.repaint();
        CenterSPanel.repaint();

        this.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
                    //techimage = ImageIO.read(new File("techimage"));
                }
                catch(Exception e/*IOException e*/) {
                    System.out.println("Bad");
                }
                super.paintComponent(g);
                // Draw the background image.
                g.drawImage(techimage, 0, 0, null);
            }
        });


        //Import this from database normally
//        String[] petStrings = { "Bird", "Cat", "Dog" };




//        ArrayList<String> petArrayList = new ArrayList<String>();
//
//        for (int i = 0; i < friendData.size(); i++)
//        {
//            petArrayList.add(friendData.get(i).get(0));
//        }
//
//        String[] petStrings = new String[friendData.size()];
//        for (int i =0; i < petArrayList.size(); i++)
//            petStrings[i] = petArrayList.get(i);

        //petStrings = (String[])petArrayList.toArray();




        setLayout(new GridLayout(3,1));


//        JComboBox friendList = new JComboBox(petStrings);
//        friendList.addActionListener(this);
//        friendList.setActionCommand("friend");






        String storage = read("fakeDataBase");
        String[] lines = storage.split("\r\n|\r|\n");
        int newlineCount = lines.length;


        textArea = new JTextArea(newlineCount,5);

        scroll = new JScrollPane(textArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBackground(new Color(255,255,255,0));
        CenterPanel.add(scroll);
        textArea.setText("");
        //textArea.setText(read("fakeDataBase"));


        btnOut = new JButton("<= Personal Transactions");
        btnOut.addActionListener(this);
        btnOut.setActionCommand("out");

        btnIn = new JButton("Chat Room =>");
        btnIn.addActionListener(this);
        btnIn.setActionCommand("in");

//        SouthPanel.add(friendList);
        SouthPanel.add(btnOut);
        SouthPanel.add(btnIn);


        GUI.this.add(CenterPanel);
        GUI.this.add(SouthPanel);

        String display = "";
//        if (friendData.size() > 0)
//        {
//            for (int i = 1; i < friendData.get(0).size(); i++)
//            {
//                display = display + friendData.get(0).get(0) + ": " + friendData.get(0).get(i) + "\n";
//            }
//            textArea.setText(display);
//        }


    }

//
//    public void refreshBackground()
//    {
//        this.setContentPane(new JPanel(){
//            @Override
//            public void paintComponent(Graphics g) {
//                try {
//                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
//                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
//                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
//                    //techimage = ImageIO.read(new File("techimage"));
//                }
//                catch(Exception e/*IOException e*/) {
//                    System.out.println("Bad");
//                }
//                super.paintComponent(g);
//                // Draw the background image.
//                g.drawImage(techimage, 0, 0, null);
//            }
//        });
//    }


    /**
     * Trade initation
     * @throws IOException exception
     */
    public void goThirdPage() throws IOException{

        //System.out.println("This shouldn't happen");


        new GUI(8080, 0);

        NorthPanel.removeAll();
        CenterPanel.removeAll();
        SouthPanel.removeAll();
        CenterNPanel.removeAll();
        CenterCPanel.removeAll();
        CenterSPanel.removeAll();

        NorthPanel.revalidate();
        CenterPanel.revalidate();
        SouthPanel.revalidate();
        CenterNPanel.revalidate();
        CenterCPanel.revalidate();
        CenterSPanel.revalidate();

        NorthPanel.repaint();
        CenterPanel.repaint();
        SouthPanel.repaint();
        CenterNPanel.repaint();
        CenterCPanel.repaint();
        CenterSPanel.repaint();

        this.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
                    //techimage = ImageIO.read(new File("techimage"));
                }
                catch(Exception e/*IOException e*/) {
                    System.out.println("Bad");
                }
                super.paintComponent(g);
                // Draw the background image.
                g.drawImage(techimage, 0, 0, null);
            }
        });




        setLayout(new GridLayout(3,1));
        NorthPanel.setLayout(new GridLayout(3,1));



        //String storage = read("fakeDataBase");
        String stats = "Name: "+ allStats.get(0).get(player1) + ": \n Games Played: " + allStats.get(1)  + "Kills: " + allStats.get(3) + "\n Deaths: "
                + allStats.get(4) + "\n Assists: " + allStats.get(5);

        //owo

        String[] lines = stats.split("\r\n|\r|\n");
        int newlineCount = lines.length;

        //textArea.setText(read("fakeDataBase"));
        textArea.setText(requestText);
        textArea = new JTextArea(newlineCount,5);
        scroll = new JScrollPane(textArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBackground(new Color(255,255,255,0));
        NorthPanel.add(scroll);


        //uwu

        ArrayList<String> playerArrayList = new ArrayList<String>();
        playerArrayList.addAll(players);

        String[] playerStrings = new String[players.size()];
        for (int i =0; i < playerArrayList.size(); i++)
            playerStrings[i] = playerArrayList.get(i);

        JComboBox<String> trade1 = new JComboBox<>(playerStrings);
        trade1.addActionListener(this);
        trade1.setActionCommand("trade1");

        JComboBox<String> trade2 = new JComboBox<>(playerStrings);
        trade2.addActionListener(this);
        trade2.setActionCommand("trade2");


        JButton accept = new JButton("Request/Accept Trade?");
        accept.addActionListener(this);
        accept.setActionCommand("accept");


        JLabel info = new JLabel("Chat Box");

        JTextField enterField = new JTextField();
        enterField.addActionListener(this);
        enterField.setActionCommand("chat2");


        //NorthPanel.add(info);
        NorthPanel.add(accept);
        NorthPanel.add(enterField);


        btnOut = new JButton("<== Transfer");
        btnIn = new JButton("Friend Transactions =>");

        btnOut.addActionListener(this);
        btnOut.setActionCommand("out");

        btnIn.addActionListener(this);
        btnIn.setActionCommand("in");


        SouthPanel.add(btnOut);
        SouthPanel.add(btnIn);

        CenterPanel.setLayout(new GridLayout(1,2));

        CenterPanel.add(trade1);
        CenterPanel.add(trade2);

        GUI.this.add(NorthPanel);
        GUI.this.add(CenterPanel);
        GUI.this.add(SouthPanel);

    }


    /**
     * Roster assembly page
     * @throws IOException  exceptional
     */
    public void goSecondPage() throws IOException {

        currentPage = 2;

        new GUI(8080, 0);

        String storage = read("storage");
        String[] lines = storage.split("\r\n|\r|\n");
        int lineCount = lines.length;

        try
        {
            NorthPanel.removeAll();
            CenterPanel.removeAll();
            SouthPanel.removeAll();
            CenterNPanel.removeAll();
            CenterCPanel.removeAll();
            CenterSPanel.removeAll();

            NorthPanel.revalidate();
            CenterPanel.revalidate();
            SouthPanel.revalidate();
            CenterNPanel.revalidate();
            CenterCPanel.revalidate();
            CenterSPanel.revalidate();

            NorthPanel.repaint();
            CenterPanel.repaint();
            SouthPanel.repaint();
            CenterNPanel.repaint();
            CenterCPanel.repaint();
            CenterSPanel.repaint();
        }
        catch(NullPointerException e)
        {

        }




        this.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
                    //techimage = ImageIO.read(new File("techimage"));
                }
                catch(Exception e/*IOException e*/) {
                    System.out.println("Bad");
                }
                super.paintComponent(g);
                // Draw the background image.
                g.drawImage(techimage, 0, 0, null);
            }
        });

        setSize(500,600);

        //pack();
        setVisible(true);
//todo allow this to work
//        sock = new Socket("localhost", 39520);
//        sockInput = new ObjectInputStream(sock.getInputStream());
//        sockOutput = new ObjectOutputStream(sock.getOutputStream());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));
        //((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        //setLayout(new GridLayout());
        //JButton btn = new JButton("Guess");
        // btn.setActionCommand("myButton");
        //btn.addActionListener(this);


        this.setSize(700,1000);

//            JPanel allPanel = new JPanel();
//            allPanel.setBounds(100,400,400,100);
//            this.setLayout(new BoxLayout(allPanel,BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(3,1));


        SouthPanel = new JPanel();
        NorthPanel = new JPanel();
        CenterPanel = new JPanel();

        CenterNPanel = new Panel();
        CenterCPanel = new Panel();
        CenterSPanel = new Panel();

        label = new JLabel("Player 2");
        label2 = new JLabel("Player 3");
        label3 = new JLabel("Player 4");
//
        textField = new JTextField(20);
        textField.addActionListener(this);
        textField.setActionCommand("setPlayer");
        textField2 = new JTextField(20);
        textField2.addActionListener(this);
        textField2.setActionCommand("setPlayer");
        textField3 = new JTextField(20);
        textField3.addActionListener(this);
        textField3.setActionCommand("setPlayer");





        SouthPanel.setLayout(new GridLayout (4,4));
        NorthPanel.setLayout(new GridLayout (1,1));
        CenterPanel.setLayout(new GridLayout (3,3));

//        btn0 = new JButton("0");
//        btn1 = new JButton("1");
//        btn2 = new JButton("2");
//        btn3 = new JButton("3");
//        btn4 = new JButton("4");
//        btn5 = new JButton("5");
//        btn6 = new JButton("6");
//        btn7 = new JButton("7");
//        btn8 = new JButton("8");
//        btn9 = new JButton("9");
//        btnBack = new JButton("<");
//        btnDeci = new JButton(".");
        btnEnter = new JButton("Send");
        btnOut = new JButton("<= Logout");
        btnIn = new JButton("Transactions =>");
        btnClear = new JButton("Clear");

        btnEnter.setActionCommand("send");
        btnOut.setActionCommand("out");
        btnIn.setActionCommand("in");
        btnClear.setActionCommand("clear");



        JButton startDraft = new JButton("Start Draft");
        startDraft.addActionListener(this);

        ArrayList<String> playerArrayList = new ArrayList<String>();
        players = allStats.get(0);

        playerArrayList.addAll(players);

        String[] playerStrings = new String[players.size()];
        for (int i =0; i < playerArrayList.size(); i++)
            playerStrings[i] = playerArrayList.get(i);



        JComboBox<String> playerList1 = new JComboBox<>(playerStrings);
        playerList1.addActionListener(this);
        playerList1.setActionCommand("player1");

        JComboBox<String> playerList2 = new JComboBox<>(playerStrings);
        playerList2.addActionListener(this);
        playerList2.setActionCommand("player2");

        JComboBox<String> playerList3 = new JComboBox<>(playerStrings);
        playerList3.addActionListener(this);
        playerList3.setActionCommand("player3");

        JComboBox<String> playerList4 = new JComboBox<>(playerStrings);
        playerList4.addActionListener(this);
        playerList4.setActionCommand("player4");




        //playerArrayList.addAll(players);

//        String[] friendStrings = new String[friends.size()];
//
//        for (int i =0; i < playerArrayList.size(); i++)
//            friendStrings[i] = playerArrayList.get(i);
//

//
//        JComboBox userList1 = new JComboBox(friendStrings);
//        userList1.addActionListener(this);
//        userList1.setActionCommand("user");







        //bookmark



//        JButton pvp = new JButton("Player verses Player");
//        JButton pve = new JButton("Player verses Computer");
        //reset = new JButton("Reset");

//        InputStream in = getClass().getResourceAsStream("content.txt");
//        try {
//            textArea.read(new InputStreamReader(in), null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //this.setContentPane(new JPanel());



        textArea = new JTextArea("",lineCount,5);
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


        //textArea.setColumns(20);
        textArea.setLineWrap(true);
        //textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        textArea.setPreferredSize(new Dimension(400, 400));
        textArea.setMinimumSize(new Dimension(400, 400));
        textArea.setBounds(0, 0, 400, 400);
        scroll = new JScrollPane(textArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBackground(new Color(255,255,255,0));
        scroll.updateUI();

        //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //scroll.setMaximumSize();




        //this.setLayout(new GridLayout(3,1));



        NorthPanel.add(scroll);

//            Border black = BorderFactory.createLineBorder(Color.green);
//            NorthPanel.setBorder(black);

//        SouthPanel.add(btn0);
//        SouthPanel.add(btn1);
//        SouthPanel.add(btn2);
//        SouthPanel.add(btn3);
//        SouthPanel.add(btn4);
//        SouthPanel.add(btn5);
//        SouthPanel.add(btn6);
//        SouthPanel.add(btn7);
//        SouthPanel.add(btn8);
//        SouthPanel.add(btn9);
//        SouthPanel.add(btnBack);
//        SouthPanel.add(btnDeci);



        SouthPanel.add(btnEnter);
        SouthPanel.add(btnClear);
        SouthPanel.add(playerList1);
        SouthPanel.add(playerList2);
        SouthPanel.add(playerList3);
        SouthPanel.add(playerList4);


        //new





        CenterPanel.setLayout(new GridLayout(3,1));

        CenterNPanel.setLayout(new GridLayout(3,1));
        CenterCPanel.setLayout(new GridLayout(3,1));
        CenterSPanel.setLayout(new GridLayout(1,1));

        CenterNPanel.add(label);
        CenterNPanel.add(textField);
        CenterNPanel.add(label2);
        CenterCPanel.add(textField2);
        CenterCPanel.add(label3);
        CenterCPanel.add(textField3);

        // CenterNPanel.add(userList1);


        CenterSPanel.add(btnOut);
        CenterSPanel.add(btnIn);


        CenterPanel.add(CenterNPanel);
        CenterPanel.add(CenterCPanel);
        CenterPanel.add(CenterSPanel);

//        CenterPanel.add(textField);
//        CenterPanel.add(label);
//        setLayout(new GridLayout(3,3));
//        CenterPanel.add(btnOut);
//        CenterPanel.add(btnIn);


//        NorthPanel.add(pvp);
//        NorthPanel.add(pve);


        //NorthPanel.add(reset);




        //JFrame f = new JFrame();

        //setSize(300,300);

        NorthPanel.setBackground(new Color(255,255,255,0));

        CenterNPanel.setBackground(new Color(255,255,255,0));
        CenterCPanel.setBackground(new Color(255,255,255,0));
        CenterSPanel.setBackground(new Color(255,255,255,0));

        CenterPanel.setBackground(new Color(255,255,255,0));
        SouthPanel.setBackground(new Color(255,255,255,1));
        add(NorthPanel);
        add(CenterPanel);
        add(SouthPanel);


        //        add(new SouthPanel(),BorderLayout.NORTH);
//        add(new MidPanel(),BorderLayout.CENTER);
//        add(new NorthPanel(),BorderLayout.SOUTH);

        //textField.addActionListener(this);

//        btn0.addActionListener(this);
//        btn1.addActionListener(this);
//        btn2.addActionListener(this);
//        btn3.addActionListener(this);
//        btn4.addActionListener(this);
//        btn5.addActionListener(this);
//        btn6.addActionListener(this);
//        btn7.addActionListener(this);
//        btn8.addActionListener(this);
//        btn9.addActionListener(this);
        //btnBack.addActionListener(this);
        //btnDeci.addActionListener(this);
        btnEnter.addActionListener(this);
        btnOut.addActionListener(this);
        btnIn.addActionListener(this);
        btnClear.addActionListener(this);


//        pvp.addActionListener(this);
//        pve.addActionListener(this);
        //reset.addActionListener(this);

//        btn0.setActionCommand("0");
//        btn1.setActionCommand("1");
//        btn2.setActionCommand("2");
//        btn3.setActionCommand("3");
//        btn4.setActionCommand("4");
//        btn5.setActionCommand("5");
//        btn6.setActionCommand("6");
//        btn7.setActionCommand("7");
//        btn8.setActionCommand("8");
//        btn9.setActionCommand("9");
//        btnBack.setActionCommand("<");
//        btnDeci.setActionCommand(".");


//        pvp.setActionCommand("pvp");
//        pve.setActionCommand("pve");
        //reset.setActionCommand("reset");


        setResizable(true);
        //pack();
        //setLocationRelativeTo(null);
        setVisible(true);


        textArea.setText(storage);


    }


    int[] active = {0,1,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,1,0,1,1};
    int length = 25; //basic length in pixels for drawing the lines
    int offset = 50; //so the lines aren't sticked at the border




    /**
     * Login page
     * @throws IOException Exceptional exception
     */
    public void goFirstPage() throws IOException {

        new GUI(8080, 0);

        currentPage = 1;

        try
        {
            NorthPanel.removeAll();
            CenterPanel.removeAll();
            SouthPanel.removeAll();
            CenterNPanel.removeAll();
            CenterCPanel.removeAll();
            CenterSPanel.removeAll();

            NorthPanel.revalidate();
            CenterPanel.revalidate();
            SouthPanel.revalidate();
            CenterNPanel.revalidate();
            CenterCPanel.revalidate();
            CenterSPanel.revalidate();

            NorthPanel.repaint();
            CenterPanel.repaint();
            SouthPanel.repaint();
            CenterNPanel.repaint();
            CenterCPanel.repaint();
            CenterSPanel.repaint();
        }
        catch(NullPointerException e)
        {

        }

        this.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                try {
                    //techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    techimage = ImageIO.read(GUI.class.getResource("techimage.jpg"));
                    //System.out.println(GUI.class.getResource("techimage.jpg").toString());
                    //techimage = ImageIO.read(new File("techimage"));
                }
                catch(Exception e/*IOException e*/) {
                    System.out.println("Bad");
                }
                super.paintComponent(g);
                // Draw the background image.
                g.drawImage(techimage, 0, 0, null);
            }
        });



        //CenterPanel.setBorder(new EmptyBorder());

        int temp = 0;
        int nutrient = 0;
        JLabel nameLabel = new JLabel ("Current Water Temperature:  " + temp + " units", SwingConstants.CENTER);
        JLabel passLabel = new JLabel ("Current Suspended Nutrient Quantity: " + nutrient + " units", SwingConstants.CENTER);


        JLabel invalidLabel = new JLabel ("");

        if(registerSuccess == true)
        {
            invalidLabel.setText("Registration Successful");
            invalidLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            invalidLabel.setForeground(Color.WHITE);

            registerSuccess = false;
        }



        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {}


            /**
             * A method that Overrides the default keyReleased method
             * specific to inputs of textField
             */
            @Override
            public void keyReleased(KeyEvent event) {

                if(event.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    username = textField.getText();
                    password = textField2.getText();
                    //memo = textField3.getText();
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    //System.out.println("Memo: " + memo);
                    checkUser();
                }
            }





            /**
             * A method of KeyListener that insists on being Overridden
             */

            @Override
            public void keyTyped(KeyEvent event) {
            }

//            private void shortenUpdate(KeyEvent e)
//            {
//
//            }
        };




        CenterPanel.setLayout(new GridLayout(6,1));
        //CenterNPanel.setLayout(new GridLayout(1,1));
        //CenterCPanel.setLayout(new GridLayout(1,1));
        //CenterSPanel.setLayout(new GridLayout(1,1));

        textField = new JTextField(20);
//        textField.setPreferredSize(new Dimension(2,100));
//        textField.setSize(new Dimension(2,100));


        textField2 = new JTextField(20);
//        textField2.setPreferredSize(new Dimension(2,100));
//        textField2.setSize(new Dimension(2,100));
        //CenterNPanel.setLayout(new BorderLayout());
        //CenterNPanel.add(CenterPanel.add(nameLabel),BorderLayout.SOUTH);


        textField2.addKeyListener(listener);


        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(Color.BLACK);

        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setForeground(Color.BLACK);


        btnIn = new JButton("Submit Target Temperature");
        btnIn.addActionListener(this);
        btnIn.setActionCommand("in");
        btnIn2 = new JButton("Submit Target Nutrient value");
        btnIn2.addActionListener(this);
        btnIn2.setActionCommand("in");



        CenterPanel.add(nameLabel);
        CenterPanel.add(textField);
        CenterPanel.add(btnIn);

        CenterPanel.add(passLabel);
        CenterPanel.add(textField2);
        CenterPanel.add(btnIn2);
        //CenterPanel.add(invalidLabel);




        //CenterPanel.add(CenterNPanel);
        //CenterPanel.add(CenterSPanel);



        //GUI.this.add(NorthPanel);
        GUI.this.setLayout(null);
        GUI.this.getContentPane().removeAll();

        GUI.this.add(CenterPanel);


        //GUI.this.add(SouthPanel);

        GUI.this.revalidate();

        GUI.this.repaint();
    }

    public void out()
    {
        System.out.println("Out command currentPage was changed to: " + currentPage);

        if (currentPage == 1) {
            try {
                goZerothPage();
                currentPage--;
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
        else if (currentPage == 2)
        {
            System.out.println("action out: page2");
            try {
                goFirstPage();
                currentPage--;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if (currentPage == 3)
        {
            try {
                goSecondPage();
                currentPage--;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        else if (currentPage == 4)
        {
            try {
                goThirdPage();
                currentPage--;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        else if (currentPage == 5)
        {
            try {
                goFourthPage();
                currentPage--;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(currentPage == 6)
        {
            try {
                goFifthPage();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            currentPage--;

        }
    }

    public void in()
    {
        System.out.println("currentPage was changed to: " + currentPage);

        if (currentPage == 0) {

            try {
                goFirstPage();
                currentPage++;
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        else if (currentPage == 1) {

            try {
                goSecondPage();
                currentPage++;
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        else if (currentPage == 2)
        {
            try {
                goThirdPage();
                currentPage++;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if (currentPage == 3) {
            try {
                goFourthPage();
                currentPage++;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        else if (currentPage == 4) {
            try {
                goFifthPage();
                currentPage++;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(currentPage == 5)
        {
            try {
                goSixthPage();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            currentPage++;
        }
    }

    /**
     * listens to actions
     * @param e responds to stuff
     */
    public void actionPerformed(ActionEvent e)
    {

        if (e.getActionCommand().equals("player"))
        {

            String friend1 = textField.getText();
            String friend2 = textField2.getText();
            String friend3 = textField3.getText();
            friends.add(friend1);
            friends.add(friend2);
            friends.add(friend3);

            textArea.setText("Friends Set: (" + friend1 + " " + friend2 + " " + friend3 + ")");
            GUI.this.revalidate();
            GUI.this.repaint();
        }

        if (e.getActionCommand().equals("user"))
        {
            JComboBox userBox = (JComboBox)e.getSource();
            userChoice1 = friends.get(userBox.getSelectedIndex());
        }

        if(e.getActionCommand().equals("send")) {
            teamText = "Current Team: " + allStats.get(0).get(player1) + " " + allStats.get(0).get(player2) +
                    " " + allStats.get(0).get(player3) + ", " + allStats.get(0).get(player4);
            textArea.setText(teamText);
            //System.out.println("");

            user2 = textField.getText();
            user3 = textField2.getText();
            user4 = textField3.getText();


            GUI.this.revalidate();
            GUI.this.repaint();
        }

//        if(e.getActionCommand().equals("startDraft")){
//            try {
//                startDraft(new Client(username), new Client(user2), new Client(user3), new Client(user4));
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }

        if (e.getActionCommand().equals("accept"))
        {
            //Client.getStreams();
            requestText = "Request trade " +  allStats.get(0).get(tradeCount1) + " for " + allStats.get(0).get(tradeCount2) + "?";
            textArea.setText(requestText);
            //System.out.println("This should create a request");
            GUI.this.revalidate();
            GUI.this.repaint();
        }

        if (e.getActionCommand().equals("trade1"))
        {
            cb1 = (JComboBox)e.getSource();
            tradeCount1 = cb1.getSelectedIndex();
        }
        if (e.getActionCommand().equals("trade2"))
        {
            cb2 = (JComboBox)e.getSource();

            tradeCount2 = cb2.getSelectedIndex();
        }


        if (e.getActionCommand().equals("player1"))
        {
            JComboBox cb = (JComboBox)e.getSource();
            player1 = cb.getSelectedIndex();
        }
        if (e.getActionCommand().equals("player2"))
        {
            JComboBox cb = (JComboBox)e.getSource();
            player2 = cb.getSelectedIndex();
        }
        if (e.getActionCommand().equals("player3"))
        {
            JComboBox cb = (JComboBox)e.getSource();
            player3 = cb.getSelectedIndex();
        }
        if (e.getActionCommand().equals("player4"))
        {
            JComboBox cb = (JComboBox)e.getSource();
            player4 = cb.getSelectedIndex();
        }

        if (e.getActionCommand().equals("type"))
        {
            JComboBox cb = (JComboBox)e.getSource();

            type = String.valueOf(cb.getSelectedItem());

            System.out.println ("type is now: " + type);

            GUI.this.repaint();
        }

        if (e.getActionCommand().equals("tier"))
        {
            JComboBox cb = (JComboBox)e.getSource();

            tier = String.valueOf(cb.getSelectedItem());

            System.out.println ("tier is now: " + tier);

            GUI.this.repaint();

        }


        if (e.getActionCommand().equals("talk"))
        {


            //ArrayList<String> message = new ArrayList<String>(Arrays.asList(username, ));

            friendChat.get(currentIndex).add(username + ": " + textField.getText());



            //String petName = (String)cb.getSelectedItem();
            //Fetch user Transaction data from database
            String display = "";
            //System.out.println("friendsize" +  friendData.get(cb.getSelectedIndex()).size());
            for (int i = 1; i < friendChat.get(currentIndex).size(); i++)
            {
                display = display + friendChat.get(currentIndex).get(i) + "\n";
            }
            //System.out.println("1display:" + display);
            textArea.setText(display);


        }


        if (e.getActionCommand().equals("chat2"))
        {
            JComboBox cb = (JComboBox)e.getSource();
//            currentIndex = cb.getSelectedIndex();
            //String petName = (String)cb.getSelectedItem();
            //Fetch user Transaction data from database
            //display = "";
            //System.out.println("friendsize" +  friendData.get(cb.getSelectedIndex()).size());

            display = display + friendChat.get(cb.getSelectedIndex()).get(i) + "\n";
            //System.out.println("2display:" + display);
            textArea.setText(display);
        }



        if (e.getActionCommand().equals("chat"))
        {
            JComboBox cb = (JComboBox)e.getSource();
            currentIndex = cb.getSelectedIndex();
            //String petName = (String)cb.getSelectedItem();
            //Fetch user Transaction data from database
            String display = "";
            //System.out.println("friendsize" +  friendData.get(cb.getSelectedIndex()).size());
            for (int i = 1; i < friendChat.get(cb.getSelectedIndex()).size(); i++)
            {
                display = display + friendChat.get(cb.getSelectedIndex()).get(i) + "\n";
            }
            //System.out.println("2display:" + display);
            textArea.setText(display);
        }


        //TODO friend Data stuff
        if (e.getActionCommand().equals("friend"))
        {
            JComboBox cb = (JComboBox)e.getSource();
            //String petName = (String)cb.getSelectedItem();
            //Fetch user Transaction data from database
            String display = "";
            //System.out.println("friendsize" +  friendData.get(cb.getSelectedIndex()).size());
//            for (int i = 1; i < friendData.get(cb.getSelectedIndex()).size(); i++)
//            {
//                display = display + friendData.get(cb.getSelectedIndex()).get(i) + "\n";
//            }

            //System.out.println("3display:" + display);
            textArea.setText(display);
        }


        //repaint();

        //System.out.println("test");

        if(e.getActionCommand().equals("clear"))
        {
            System.out.println("clear button flag");
            textArea.setText("");
            write("storage",textArea.getText());
        }


        if(e.getActionCommand().equals("in"))
        {
            System.out.println("currentPage was changed to: " + currentPage);

            if (currentPage == 0) {

                try {
                    goFirstPage();
                    currentPage = 1;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            else if (currentPage == 1) {

                try {
                    goSecondPage();
                    currentPage = 2;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            else if (currentPage == 2)
            {
                try {
                    goThirdPage();
                    currentPage = 3;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if (currentPage == 3) {
                try {
                    goFourthPage();
                    currentPage++;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            else if (currentPage == 4) {
                try {
                    goFifthPage();
                    currentPage++;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(currentPage == 5)
            {
                try {
                    goSixthPage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                currentPage++;
            }

        }
        if(e.getActionCommand().equals("out"))
        {
            System.out.println("Out command currentPage was changed to: " + currentPage);

            if (currentPage == 1) {
                try {
                    goZerothPage();
                    currentPage = 0;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
            else if (currentPage == 2)
            {
                System.out.println("action out: page2");
                try {
                    goFirstPage();
                    currentPage = 1;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if (currentPage == 3)
            {
                try {
                    goSecondPage();
                    currentPage = 2;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            else if (currentPage == 4)
            {
                try {
                    goThirdPage();
                    currentPage--;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            else if (currentPage == 5)
            {
                try {
                    goFourthPage();
                    currentPage--;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(currentPage == 6)
            {
                try {
                    goFifthPage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                currentPage--;

            }


            if (btnOut.getText().equals("<= Logout"))
            {
                System.out.println("Logout current page = " + currentPage);
                //currentPage = 1;
//                try {
//                    goFirstPage();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//      todo all server communication
//                try {
//                    String[] zipInfo = new String[]{"LOGOUT"};
//                    sockOutput.writeObject(zipInfo);
//                    sockOutput.flush();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
            }

        }

    }

    /**
     * Generation of player stats
     */
    private void generatePlayerStats()
    {
        allStats = new ArrayList<>();
        ArrayList<String> PLAYERS = new ArrayList<>(Arrays.asList("Akaadian", " Altec", " Aphromoo", " Apollo", " Bang", " Biofrost", " Bjergsen", " Blaber", " Broken", " Blade", " Broxah", " Closer", " Cody", " Sun", " CoreJJ", " Crown", " Dardoch", " Doublelift", " Eika", " FBI", " Froggen", " Goldenglue", " Grig", " Hakuho", " Hauntzer", " Huni", " IgNar", " Impact", " Jensen", " Jiizuke", " Johnsun", " KEITH", " Kobbe", " Kumo", " Licorice", " Meteos", " Nisqy", " Pobelter", " PowerOfEvil", " Ruin", " Ryoma", " Santorin", " Shernfire", " Smoothie", " Stixxay", " Stunt", " Svenskeren", " Tactical", " V1PER", " Vulcan", " Wiggily", " WildTurtle", " Wind", " Xmithie", " Zeyzal", " Zven", " sOAZ", " solo", " ssumday"));
        //ArrayList<String> position = new ArrayList<>(Arrays.asList("JUNGLE", " ADC", " SUPPORT", " ADC", " ADC", " SUPPORT", " SUPPORT", " MID", " JUNGLE", " TOP", " JUNGLE", " JUNGLE", " ADC", " SUPPORT", " MID", " JUNGLE", " ADC", " MID", " ADC", " MID", " MID", " JUNGLE", " SUPPORT", " TOP", " SUPPORT", " TOP", " SUPPORT", " ADC", " TOP", " MID", " MID", " ADC", " SUPPORT", " ADC", " TOP", " TOP", " JUNGLE", " MID", " MID", " MID", " TOP", " MID", " JUNGLE", " JUNGLE", " SUPPORT", " ADC", " SUPPORT", " JUNGLE", " ADC", " TOP", " ADC", " SUPPORT", " JUNGLE", " ADC", " SUPPORT", " ADC", " JUNGLE", " ADC", " SUPPORT", " SUPPORT", " ADC", " TOP", " TOP", " TOP"));
        ArrayList<String> games = new ArrayList<>(Arrays.asList("8","10","20","9","19","18","18","18","18","12","19","20","18","8","18","15","19","19","20","19","12","19","19","27","19","18","18","19","20","12","18","19","18","20","18","10","19","18","20","19","6","18","16","20","19","3","15","18","18","19","2","19","2","17","18","19","4","20"));
        ArrayList<String> gamesWon = new ArrayList<>(Arrays.asList("4", "5", "9", "3", "11", "9", "9", "17", "9", "5", "9", "11", "7", "1", "9", "5", "8", "9", "9", "9", "5", "8", "9", "13", "10", "7", "7", "11", "9", "5", "9", "11", "17", "11", "17", "2", "10", "3", "11", "10", "2", "3", "2", "11", "11", "2", "8", "17", "3", "10", "1", "8", "11", "17", "8", "2", "11"));
        //ArrayList<String> winRate = new ArrayList<>(Arrays.asList("50", "50", "45", "33.30", "57.89", "50", "50", "94.40", "50", "41.70", "47.40", "55", "38.90", "12.50", "50", "33.30", "42.10", "47.40", "45", "47.40", "41.70", "42.10", "47.40", "48.15", "52.63", "38.90", "38.90", "57.90", "45", "41.70", "50", "57.90", "94.40", "55", "94.40", "20", "52.60", "16.70", "55", "52.60", "33.30", "16.70", "12.50", "55", "57.90", "66.70", "53.30", "94.44", "16.70", "52.63", "50", "42.10", "57.89", "94.44", "42.10", "50", "55"));
        ArrayList<String> kda = new ArrayList<>(Arrays.asList("3.7","3.9","2.5","5.7","7.3","-","4.7","5.3","6.6","3.8","4","2.5","4.5","4.7","1.7","2.7","3.3","3.1","4.1","4.8","2.4","1.9","2.9","2.1","1.9","2","3.4","10","2.8","4.4","3.6","4.1","1.2","5.2","2.4","4.8","3.1","6.7","2.7","3.9","2.4","3.8","3.2","2.5","2.5","3.3","1.9","2.4","5.5","2.5","-","7.6","2.3","3.4","11","2","2","4.3","5.2","-","11.6","1.9","2.3","3.5"));
        ArrayList<String> AVGKILLS = new ArrayList<>(Arrays.asList("3.6", "3", "0.6", "3.9", "3.5", "0.9", "3.3", "4", "2.8", "1.5", "1.6", "3.2", "0.1", "2.6", "1.8", "3", "2.4", "2.3", "2.7", "2.2", "1.3", "0.5", "2.1", "1.2", "0.53", "1.9", "2.5", "3.5", "2.9", "0.6", "2.8", "2.5", "2.4", "1.9", "3.5", "2.8", "3.7", "2.5", "3", "2.1", "1.5", "0.7", "2.4", "1", "1.5", "4", "1.7", "1.2", "1.7", "2.5", "0.5", "1.3", "0.9", "4.8", "1.1", "2", "2.5"));
        ArrayList<String> AVGDEATHS = new ArrayList<>(Arrays.asList("2.1", "1.5", "2.6", "1.2", "1", "1.7", "1.6", "1.7", "2.1", "1.8", "2.5", "1.8", "1.4", "3.3", "3", "1.9", "1.8", "1.4", "1.5", "2.4", "2.8", "2.1", "2.5", "2.9", "2", "1.9", "1.5", "2.2", "1.6", "3.8", "1.5", "2.6", "1.8", "2.6", "1.7", "2.7", "1.9", "3", "2.1", "2.5", "2.5", "2.8", "2.2", "3.6", "3.2", "1.3", "2.5", "1.3", "3.2", "1.9", "3", "2.9", "1.7", "0.9", "2.5", "3.3", "1.8"));
        ArrayList<String> AVGASSISTS = new ArrayList<>(Arrays.asList("4.3", "2.9", "5.7", "3.1", "5.1", "7.2", "4.9", "7.4", "5", "5.4", "4.6", "4.7", "6.7", "2.9", "6.4", "3.1", "3.3", "3.4", "4.5", "3.6", "4.2", "5.5", "3.3", "4.4", "6.7", "3.4", "4.1", "4.4", "3.7", "4.2", "5.1", "3.8", "6", "6", "7.6", "4.5", "3.7", "4.6", "5", "6", "4.8", "6.2", "4.8", "5.9", "6.3", "3.3", "4.3", "9.3", "5.7", "4.5", "5.5", "4.7", "7.2", "6.0", "3.6", "5.5", "3.9"));

        allStats.add(PLAYERS);
        allStats.add(games);
        allStats.add(gamesWon);
        allStats.add(kda);
        allStats.add(AVGKILLS);
        allStats.add(AVGDEATHS);
        allStats.add(AVGASSISTS);
    }


    /**
     * audio file of high reel
     */
    public static void audio() {
        try {
            File file = new File("./src/audio.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.err.println("Put the music.wav file in the sound folder if you want to play background music, only optional!");
        }
    }

    /**
     * this plays the gif
     */
    public void gifPlay()
    {
        JFrame gifFrame = new JFrame();
        JPanel p = new JPanel();
        JLabel l = new JLabel();
        ImageIcon icon = new ImageIcon("./src/background.gif");
        gifFrame.setSize(480, 360);
        gifFrame.setVisible(true);
        l.setIcon(icon);
        p.add(l);
        gifFrame.getContentPane().add(p);
        gifFrame.setLocationRelativeTo(null);
        gifFrame.setResizable(false);
        gifFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        audio();
    }


    /**
     * Processes player choice with client.
     * @throws IOException  failure to process connection with server
     */
    private void processChoice(int i) throws IOException {

    }






    /**
     * Starts and manages the Draft of four clients and season of all Players.
     * @throws IOException  failure to draft player for client
     */
//    private void startDraft(Client C1, Client C2, Client C3, Client C4) throws IOException {
////        // initializes the draft for the Clients
////        Draft draft = new Draft();
////        Players[] Athletes= new Players[57];
////        Client[] Clients = {C1, C2, C3, C4};
////        int i=0;
////        for (i=0;i<57;i++){
////            Athletes[i]= new Players(allStats.get(0).get(i),Double.parseDouble(allStats.get(1).get(i)),
////                    Double.parseDouble(allStats.get(2).get(i)),Double.parseDouble(allStats.get(4).get(i)),
////                    Double.parseDouble(allStats.get(5).get(i)),Double.parseDouble(allStats.get(6).get(i)));
////        }
////
////        // handles the drafting of four players by using the snake draft
////        // (1 -> 2 -> 3 -> 4 -> 4 -> 3 -> 2 -> 1 -> 1 -> ...)
////        int roundsLeft = 10;
////        while(roundsLeft != 0){
////            if(roundsLeft % 2 == 0){
////                for(int a = 0; a < 4; a++){
////                    while(choice[a] == null){
////                        processChoice(a);
////                    }
////                    Players[] players = draft.getAvailableAthletes();
////                    int count = 0;
////                    for(Players player : players){
////                        if(player.GetName().equals(choice[a])){
////                            draft.DraftPlayer(a, count);
////                        }
////                        count++;
////                    }
////                    choice[a] = null;
////                }
////            }
////            else {
////                for(int b = 3; b > -1; b--){
////                    while(choice[b] == null){
////                        processChoice(b);
////                    }
////                    Players[] players = draft.getAvailableAthletes();
////                    int count = 0;
////                    for(Players player : players){
////                        if(player.GetName().equals(choice[b])){
////                            draft.DraftPlayer(b, count);
////                        }
////                        count++;
////                    }
////                    choice[b] = null;
////                }
////            }
////            roundsLeft--;
////        }
////
////        // Simulates 200 games of league for the season for each Athlete
////        int games = 200;
////        while(games != 0){
////            seasonSimulation(Athletes);
////            try {
////                TimeUnit.SECONDS.sleep(69);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            games--;
////        }
////    }

    /**
     * Simulates a season of League of Legends
     * @param players   pro league of legends players
     */

//    private void seasonSimulation(Players[] players){
//        for(Players p : players){
//            boolean result;
//            if(Math.random()*2 > 1){
//                result = true;
//            }
//            else {
//                result = false;
//            }
//            p.NewGame((int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 5), result);
//        }
//    }


    /**
     * main method to start
     * @param args default
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {

//        Client client = new Client("localhost", 8080);
//        client.runClient();

        //new GUI(8080,1);
        new GUI(8080, 2);

    }
}






