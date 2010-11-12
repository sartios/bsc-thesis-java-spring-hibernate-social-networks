

package proof;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

public class startDialog extends javax.swing.JDialog {
        String consumerKey="ZDXsxdofOB6X6OgVv7EA";
        String consumerSecret="xanNs4rdJ77muYiPsq7y254NaJUQjVblJkJfZKVuo";

 
    public startDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modeCheckBox = new javax.swing.JCheckBox();
        startButton = new javax.swing.JButton();
        tokenizerField = new javax.swing.JTextField();
        instructions = new javax.swing.JLabel();
        tokenField = new javax.swing.JTextField();
        saveFileButton = new javax.swing.JButton();
        loadFileButton = new javax.swing.JButton();
        copyClipboard = new javax.swing.JButton();
        facebookLabel = new javax.swing.JLabel();
        twitterLabel = new javax.swing.JLabel();
        twitterUnauthorizedBox = new javax.swing.JCheckBox();
        readClipboard = new javax.swing.JButton();
        twitterTokenField = new javax.swing.JTextField();
        generateTokenButton = new javax.swing.JButton();
        twitterSaveButton = new javax.swing.JButton();
        twitterSecretField = new javax.swing.JTextField();
        twitterTokenLabel = new javax.swing.JLabel();
        twitterSecretLabel = new javax.swing.JLabel();
        twitterLoadButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vdella Authorization Dialog");
        setBackground(new java.awt.Color(204, 255, 153));
        setIconImage(null);
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modeCheckBox.setText("Unauthorized");
        getContentPane().add(modeCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 144, 192, -1));

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 340, 92, -1));

        tokenizerField.setEditable(false);
        tokenizerField.setText("https://graph.facebook.com/oauth/authorize?client_id=106911326013695&redirect_uri=http://www.facebook.com/connect/login_success.html&type=user_agent&scope=offline_access&display=popup");
        tokenizerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tokenizerFieldActionPerformed(evt);
            }
        });
        getContentPane().add(tokenizerField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 482, -1));

        instructions.setText("Copy-Paste the below url into your browser give access rights to the app and then copy your browser url to clipboard");
        getContentPane().add(instructions, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 620, 21));

        tokenField.setText("Copy to clipboard the URL from your browser once you loged in and press the extract button");
        getContentPane().add(tokenField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 118, 482, -1));

        saveFileButton.setText("Save to file");
        saveFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(saveFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 144, -1, -1));

        loadFileButton.setText("Load From File");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loadFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 144, -1, -1));

        copyClipboard.setText("Copy to Clipboard");
        copyClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyClipboardActionPerformed(evt);
            }
        });
        getContentPane().add(copyClipboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 69, 130, -1));

        facebookLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        facebookLabel.setText("FACEBOOK LOGIN");
        getContentPane().add(facebookLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 209, 19));

        twitterLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        twitterLabel.setText("TWITTER LOGIN");
        getContentPane().add(twitterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 185, 146, -1));

        twitterUnauthorizedBox.setText("Unauthorized");
        getContentPane().add(twitterUnauthorizedBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, -1, -1));

        readClipboard.setText("Extract Token");
        readClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readClipboardActionPerformed(evt);
            }
        });
        getContentPane().add(readClipboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 130, -1));

        twitterTokenField.setEditable(false);
        getContentPane().add(twitterTokenField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 270, -1));

        generateTokenButton.setText("Generate New Token");
        generateTokenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateTokenButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateTokenButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 210, 150, -1));

        twitterSaveButton.setText("Save to file");
        twitterSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twitterSaveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(twitterSaveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));

        twitterSecretField.setEditable(false);
        getContentPane().add(twitterSecretField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 270, -1));

        twitterTokenLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        twitterTokenLabel.setText("Token");
        getContentPane().add(twitterTokenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 90, 20));

        twitterSecretLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        twitterSecretLabel.setText("Token Secret");
        getContentPane().add(twitterSecretLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 80, 20));

        twitterLoadButton.setText("Load From File");
        twitterLoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twitterLoadButtonActionPerformed(evt);
            }
        });
        getContentPane().add(twitterLoadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
            configFrameSettings settings = new configFrameSettings();
            boolean validFbAuth=false,validTwAuth=false;

        if(modeCheckBox.isSelected()){
            settings.fbAuth=false;
            validFbAuth=true;
       }
       else{
      String givenToken=tokenField.getText(),testResult;
       
      if(givenToken.startsWith("access_token=")){
            testResult=RestHandler.getWall(givenToken, "me");
            if(testResult.isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "For some reason the access token is not working \n" +
                    "Make sure you loged in properly and gave the proper rights for the app, \n" +
                    " check your internet connection \n" +
                    "Or refresh the access token in case the given one timed out","Access Token is not working!",2);
            }
            else{
            settings.fbToken=givenToken;
            validFbAuth=true;
            settings.fbAuth=true;
            }
      }
      else{
          JOptionPane.showMessageDialog(rootPane, "Access token string has an invalid form!","Invalid Access Token",2);
      }



       }

       if(twitterUnauthorizedBox.isSelected()==true){
            settings.twAuth = false;
            validTwAuth = true;
       }
       else{
                
               
                
                try{
                   AccessToken accessToken= new AccessToken(twitterTokenField.getText(),twitterSecretField.getText());
                   Twitter twitter= new TwitterFactory().getOAuthAuthorizedInstance(consumerKey, consumerSecret,accessToken);

                twitter.getId();
                validTwAuth=true;
                settings.twAuth=true;
                settings.twitter=twitter;
                }
                catch(TwitterException e)
                {
                    JOptionPane.showMessageDialog(rootPane, "Could not connect to Twitter! \n" +
                            "plz check your user name and password or your internet connection","Connection to Twitter failed",2);
                }

              
                
       }

       if((validTwAuth==true)&&(validFbAuth==true)){
        configFrame startFrame = new configFrame(settings);
        this.dispose();
        startFrame.setVisible(true);
        
        
       }

    }//GEN-LAST:event_startButtonActionPerformed

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
      try{

          tokenField.setText( FileHandler.readFile("token.txt"));
          JOptionPane.showMessageDialog(rootPane,"Succesfully Loaded");
      }catch(Exception e){
          JOptionPane.showMessageDialog(rootPane,"Cannot be loaded \n check if file exists!");
          System.out.println(e.getMessage());}
    }//GEN-LAST:event_loadFileButtonActionPerformed

    private void saveFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileButtonActionPerformed
       try{
        FileHandler.writeFile("token.txt",tokenField.getText());
        JOptionPane.showMessageDialog(rootPane,"Succesfully Saved");
       }catch(Exception e){System.out.println(e.getMessage());}
    }//GEN-LAST:event_saveFileButtonActionPerformed

    private void copyClipboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyClipboardActionPerformed
       this.copyTextToClipBoard(tokenizerField.getText());

    }//GEN-LAST:event_copyClipboardActionPerformed

    private void tokenizerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tokenizerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tokenizerFieldActionPerformed

    private void readClipboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readClipboardActionPerformed
       Clipboard clip =this.getToolkit().getSystemClipboard();
       String text="";
       Transferable content = clip.getContents(null);
       boolean canBeTransfered = (content != null)&&content.isDataFlavorSupported(DataFlavor.stringFlavor);
       if(canBeTransfered){
           try{
               text=(String)content.getTransferData(DataFlavor.stringFlavor);
           }
           catch(UnsupportedFlavorException ex){System.out.println(ex.getMessage());}
           catch(IOException ioEx){System.out.println(ioEx.getMessage());}
       }

      try{
      text= text.substring(text.indexOf("access_token="),text.indexOf("&expires_in="));
      }catch(Exception e){
       JOptionPane.showMessageDialog(rootPane, "Cannot extract token from given URL \n " +
               "Make sure you copied to clipboard the whole URL from the browser","Cannot extract token from given URL",2);
      }
  

       tokenField.setText(text);
    }//GEN-LAST:event_readClipboardActionPerformed

    private void generateTokenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateTokenButtonActionPerformed
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        AccessToken accessToken =null;
        try {

            RequestToken requestToken = twitter.getOAuthRequestToken();
            String url=requestToken.getAuthorizationURL();
            String pin="";
            this.copyTextToClipBoard(url);

            while((pin!=null)&&(accessToken==null)){
            pin=JOptionPane.showInputDialog(null,"The authorization URL is copied to your clipboard \n"+
                    "paste it to your browser give access rights to the app \n" +
                    "and then enter the given PIN to create the token","Access Token Creation Process", 1);
            if(pin!=null){
                try{
                if(pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                    accessToken = twitter.getOAuthAccessToken();
                }
                } catch (TwitterException te) {
                    if(401 == te.getStatusCode()){
                        JOptionPane.showMessageDialog(rootPane,"Access token cannot be created \n" +
                        "make sure you entered the correct PIN and given the access rights to the application","Access Token Cannot Be Created",0);
                }else{
                 te.printStackTrace();
                }
                }
            }
            }

            try{
            twitterTokenField.setText(accessToken.getToken());
            twitterSecretField.setText(accessToken.getTokenSecret());
            }catch(Exception e){}



        } catch (TwitterException ex) {
            Logger.getLogger(startDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_generateTokenButtonActionPerformed

    private void twitterSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twitterSaveButtonActionPerformed

        List<String> tokenList= new ArrayList();
        tokenList.add(twitterTokenField.getText());
        tokenList.add(twitterSecretField.getText());
        try {
            FileHandler.writeFile("twitterToken", tokenList);
            JOptionPane.showMessageDialog(rootPane,"Succesfully Saved");
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(rootPane,"Cannot be saved! \n check write protection");
        }

    }//GEN-LAST:event_twitterSaveButtonActionPerformed

    private void twitterLoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twitterLoadButtonActionPerformed
        try {
            List storedToken = FileHandler.getListFromFile("twitterToken");
            twitterTokenField.setText(storedToken.get(0).toString());
            twitterSecretField.setText(storedToken.get(1).toString());
            JOptionPane.showMessageDialog(rootPane,"Succesfully Loaded");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane,"Cannot be loaded \n check if file exists!");
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_twitterLoadButtonActionPerformed

    void copyTextToClipBoard(String clipText){
        try{
        Clipboard clip =this.getToolkit().getSystemClipboard();
        StringSelection data = new StringSelection(clipText);
        Transferable clipData=data;
        clip.setContents(clipData, null);
        }catch(Exception e){System.out.println(e.getMessage());}

    }
    /**
    * @param args the command line arguments
    */
    public static void main() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                startDialog dialog = new startDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyClipboard;
    private javax.swing.JLabel facebookLabel;
    private javax.swing.JButton generateTokenButton;
    private javax.swing.JLabel instructions;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JCheckBox modeCheckBox;
    private javax.swing.JButton readClipboard;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField tokenField;
    private javax.swing.JTextField tokenizerField;
    private javax.swing.JLabel twitterLabel;
    private javax.swing.JButton twitterLoadButton;
    private javax.swing.JButton twitterSaveButton;
    private javax.swing.JTextField twitterSecretField;
    private javax.swing.JLabel twitterSecretLabel;
    private javax.swing.JTextField twitterTokenField;
    private javax.swing.JLabel twitterTokenLabel;
    private javax.swing.JCheckBox twitterUnauthorizedBox;
    // End of variables declaration//GEN-END:variables

}
