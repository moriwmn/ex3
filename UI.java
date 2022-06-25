package ex3;
import javax.swing.*; 
import java.util.HashMap;

public class UI {
    ImageIcon logo = new ImageIcon("logo.png");
	String[] responses_3 = {null,null,null};

    public int panel_welcome(){
    responses_3[0] = "LOG-IN";
    responses_3[1] = "SIGN-IN";
    responses_3[2] = "Exit";
    return JOptionPane.showOptionDialog(null,
    "Welcome to Job Matcher!", 
    "welcome", 
    JOptionPane.YES_NO_CANCEL_OPTION, 
    JOptionPane.INFORMATION_MESSAGE, 
    logo, 
    responses_3, 
    null);
    }

    public HashMap<String, String> login(){
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

    public void error_message(String message){
        JOptionPane.showConfirmDialog(null,
            message,
			"Error", 
			JOptionPane.OK_CANCEL_OPTION, 
			JOptionPane.ERROR_MESSAGE);
    }
    

    
}
    
