package ex3;
import javax.swing.*; 
import java.util.HashMap;

public class UI {
    final public static ImageIcon LOGO = new ImageIcon("logo.png");
	

    public static int panel_welcome(){
    String[] welcome_buttons = {"LOG-IN", "SIGN-IN",  "Exit"};
    return JOptionPane.showOptionDialog(null,
    "Welcome to Job Matcher!", 
    "welcome", 
    JOptionPane.YES_NO_CANCEL_OPTION, 
    JOptionPane.INFORMATION_MESSAGE, 
    LOGO, 
    welcome_buttons, 
    null);
    }

    public static HashMap<String, String> login(){
        JTextField user_name = new JTextField(5);
      	JTextField password = new JTextField(5);
      	JPanel myPanel = new JPanel();
      	myPanel.add(new JLabel("Please enter your username:"));
      	myPanel.add(user_name);
      	myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      	myPanel.add(new JLabel("Please enter your password:"));
      	myPanel.add(password);
		JOptionPane.showConfirmDialog(null, myPanel,"LOG-IN",JOptionPane.PLAIN_MESSAGE);
        HashMap<String, String> user = new HashMap<String, String>(); 
        user.put("user_name",user_name.getText());
        user.put("password",password.getText());
        return user;
    } 

    public static void reg_message(String title, String message){
        JOptionPane.showMessageDialog(null,
            message,
			title, 
			JOptionPane.OK_CANCEL_OPTION);
    }

    public static void reg_message(String message){
        reg_message(" ", message);
    }

    public static void error_message(String message){
        JOptionPane.showMessageDialog(null, 
        message, 
        "Error", 
        JOptionPane.ERROR_MESSAGE);
    }

    public static String free_input(String title, String message){
        return JOptionPane.showInputDialog(null, 
        message, 
        title, 
        JOptionPane.PLAIN_MESSAGE);
    }

    public static int yes_no(String title, String message){
        return JOptionPane.showConfirmDialog(null, 
        message, 
        title, 
        JOptionPane.YES_NO_OPTION);
    }

    public static int two_options(String title, String message, String option1,  String option2){

        String[] responses = {option1,option2};

        return JOptionPane.showOptionDialog(null,
        message, 
        title, 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.INFORMATION_MESSAGE, 
        null,
        responses,
        responses[0]);
    }

    public static int some_options(String title, String message, String[] options ){
        return JOptionPane.showOptionDialog(null,
        message, 
        title, 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.INFORMATION_MESSAGE, 
        null,
        options,
        options[0]);
    }
    
    public static int list_options(String title, String message, String option1,  String option2){

        String[] responses = {option1,option2};

        return JOptionPane.showOptionDialog(null,
        message, 
        title, 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.INFORMATION_MESSAGE, 
        null,
        responses,
        responses[0]);
    }

    
}
    
